package com.taxi.fuber.service.impl;

import com.taxi.fuber.mapper.impl.TaxiMapper;
import com.taxi.fuber.model.dto.impl.TaxiDto;
import com.taxi.fuber.model.entity.impl.Taxi;
import com.taxi.fuber.repository.impl.TaxiRepository;
import com.taxi.fuber.service.BaseService;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class TaxiService extends BaseService<Taxi, TaxiDto, TaxiRepository, TaxiMapper> {
	public TaxiService() {
		super(Taxi.class);
	}

	public TaxiDto findNearestTaxi(BigDecimal lat, BigDecimal lng, Boolean isPink) {
		Taxi taxi = isPink ? repository.findNearestPinkTaxi(lat, lng) : repository.findNearestTaxi(lat, lng);
		return mapper.entityToDto(Objects.isNull(taxi) ? new Taxi() : taxi);
	}
}
