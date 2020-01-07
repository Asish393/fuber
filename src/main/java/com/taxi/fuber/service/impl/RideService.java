package com.taxi.fuber.service.impl;

import com.taxi.fuber.mapper.impl.RideMapper;
import com.taxi.fuber.model.RideRequest;
import com.taxi.fuber.model.dto.impl.RideDto;
import com.taxi.fuber.model.dto.impl.TaxiDto;
import com.taxi.fuber.model.entity.impl.Ride;
import com.taxi.fuber.model.enums.Status;
import com.taxi.fuber.model.enums.TaxiType;
import com.taxi.fuber.repository.impl.RideRepository;
import com.taxi.fuber.service.BaseService;
import com.taxi.fuber.service.UtilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.validation.Valid;

@Service
@Validated
public class RideService extends BaseService<Ride, RideDto, RideRepository, RideMapper> {
	private final TaxiService taxiService;

	@Autowired
	public RideService(TaxiService taxiService) {
		super(Ride.class);
		this.taxiService = taxiService;

	}

	public RideDto completeRide(UUID taxiUuid, UUID rideUuid, RideRequest rideRequest) {
		TaxiDto taxiDto = taxiService.releaseTaxi(taxiUuid, rideRequest);
		Ride ride = repository.findOneByUuid(rideUuid);
		LocalDateTime end = LocalDateTime.now();
		ride.setRideEnd(end);
		ride.setAmount(UtilService.calculateAmount(ride.getRideStart(), end, rideRequest));
		return this.mapper.entityToDto(ride);
	}

	public RideDto confirmRide(UUID taxiUuid, RideRequest rideRequest) {
		taxiService.updateTaxiLocationAndStatus(taxiUuid, rideRequest);
		RideDto rideDto = createRide(taxiUuid, rideRequest);
		rideDto.setRideStart(LocalDateTime.now());
		return create(rideDto);
	}

	private RideDto createRide(UUID taxiUuid, RideRequest rideRequest) {
		RideDto rideDto = new RideDto();
		rideDto.setTaxiId(taxiUuid);
		rideDto.setOriginLat(rideRequest.getOriginLat());
		rideDto.setOrginLong(rideRequest.getDestLong());
		rideDto.setDestLat(rideRequest.getDestLat());
		rideDto.setDestLong(rideRequest.getDestLong());
		rideDto.setUserId(rideRequest.getUserId());
		rideDto.setStatus(Status.ACTIVE);
		return rideDto;
	}

	public TaxiDto getNearestTaxi(@Valid RideRequest rideRequest) {
		Boolean isPink = !Objects.isNull(rideRequest.getTaxiType()) && rideRequest.getTaxiType() == TaxiType.PINK;
		return taxiService.findNearestTaxi(rideRequest.getOriginLat(), rideRequest.getOrginLong(), isPink);
	}
}
