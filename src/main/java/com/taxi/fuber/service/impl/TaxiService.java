package com.taxi.fuber.service.impl;

import com.taxi.fuber.exception.TaxiNotFoundException;
import com.taxi.fuber.mapper.impl.TaxiMapper;
import com.taxi.fuber.model.RideRequest;
import com.taxi.fuber.model.dto.impl.TaxiDto;
import com.taxi.fuber.model.entity.impl.Taxi;
import com.taxi.fuber.repository.impl.TaxiRepository;
import com.taxi.fuber.service.BaseService;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Service
public class TaxiService extends BaseService<Taxi, TaxiDto, TaxiRepository, TaxiMapper> {
	public TaxiService() {
		super(Taxi.class);
	}

	public TaxiDto findNearestTaxi(BigDecimal lat, BigDecimal lng, Boolean isPink) {
		Taxi taxi = isPink ? repository.findNearestPinkTaxi(lat, lng) : repository.findNearestTaxi(lat, lng);
		if (Objects.isNull(taxi)) {
			throw new TaxiNotFoundException("No taxi available");
		}
		return mapper.entityToDto(taxi);
	}

	public TaxiDto releaseTaxi(UUID taxiUuid, RideRequest rideRequest) {
		Taxi taxi = repository.findOneByUuidAndIsEngaged(taxiUuid, true);
		taxi.setIsEngaged(false);
		taxi.setLatitude(rideRequest.getDestLat());
		taxi.setLongitude(rideRequest.getDestLong());
		return mapper.entityToDto(taxi);
	}

	public void updateTaxiLocationAndStatus(UUID taxiUuid, RideRequest rideRequest) {
		Taxi taxi = repository.findOneByUuidAndIsEngaged(taxiUuid, false);
		if (Objects.isNull(taxi)) {
			throw new TaxiNotFoundException("Taxi took another ride book another one");
		}
		taxi.setIsEngaged(true);
		taxi.setLatitude(rideRequest.getOriginLat());
		taxi.setLongitude(rideRequest.getOrginLong());
	}
}
