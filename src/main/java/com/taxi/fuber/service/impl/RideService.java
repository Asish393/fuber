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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.Duration;
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

	private BigDecimal calculateAmount(LocalDateTime start, LocalDateTime end, RideRequest rideRequest) {
		BigDecimal totalDistance = distance(rideRequest.getOriginLat(), rideRequest.getOrginLong(), rideRequest.getDestLat(), rideRequest.getDestLong());
		Long totalTime = Math.abs(Duration.between(end, start).toMinutes());
		BigDecimal amount = totalDistance.multiply(BigDecimal.valueOf(2)).add(BigDecimal.valueOf(totalTime).multiply(BigDecimal.ONE));
		if (rideRequest.getTaxiType().equals(TaxiType.PINK)) {
			amount = amount.add(new BigDecimal(5));
		}
		return amount;
	}

	public RideDto completeRide(UUID taxiUuid, UUID rideUuid, RideRequest rideRequest) {
		TaxiDto taxiDto = taxiService.releaseTaxi(taxiUuid, rideRequest);
		Ride ride = repository.findOneByUuid(rideUuid);
		LocalDateTime end = LocalDateTime.now();
		ride.setRideEnd(end);
		ride.setAmount(calculateAmount(ride.getRideStart(), end, rideRequest));
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

	private BigDecimal distance(BigDecimal origLat, BigDecimal origLang, BigDecimal destLat, BigDecimal destLong) {
		if ((origLat.equals(destLat)) && (origLang.equals(destLong))) {
			return BigDecimal.ZERO;
		} else {
			double theta = origLang.doubleValue() - destLong.doubleValue();
			double dist = Math.sin(Math.toRadians(origLat.doubleValue())) * Math.sin(Math.toRadians(destLat.doubleValue())) + Math.cos(Math.toRadians(origLat.doubleValue())) * Math
					.cos(Math.toRadians(destLat.doubleValue())) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			dist = dist * 1.609344;
			return new BigDecimal(dist);
		}
	}

	public TaxiDto getNearestTaxi(@Valid RideRequest rideRequest) {
		Boolean isPink = !Objects.isNull(rideRequest.getTaxiType()) && rideRequest.getTaxiType() == TaxiType.PINK;
		return taxiService.findNearestTaxi(rideRequest.getOriginLat(), rideRequest.getOrginLong(), isPink);
	}
}
