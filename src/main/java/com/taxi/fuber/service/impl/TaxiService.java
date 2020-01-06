package com.taxi.fuber.service.impl;

import com.taxi.fuber.mapper.impl.TaxiMapper;
import com.taxi.fuber.model.dto.impl.TaxiDto;
import com.taxi.fuber.model.entity.impl.Taxi;
import com.taxi.fuber.repository.impl.TaxiRepository;
import com.taxi.fuber.service.BaseService;

import org.springframework.stereotype.Service;

@Service
public class TaxiService extends BaseService<Taxi, TaxiDto, TaxiRepository, TaxiMapper> {

	public TaxiService() {
		super(Taxi.class);
	}
}
