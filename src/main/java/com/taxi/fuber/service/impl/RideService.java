package com.taxi.fuber.service.impl;

import com.taxi.fuber.mapper.impl.RideMapper;
import com.taxi.fuber.mapper.impl.UserMapper;
import com.taxi.fuber.model.dto.impl.RideDto;
import com.taxi.fuber.model.dto.impl.UserDto;
import com.taxi.fuber.model.entity.impl.Ride;
import com.taxi.fuber.model.entity.impl.User;
import com.taxi.fuber.repository.impl.RideRepository;
import com.taxi.fuber.repository.impl.UserRepository;
import com.taxi.fuber.service.BaseService;

import org.springframework.stereotype.Service;

@Service
public class RideService extends BaseService<Ride, RideDto, RideRepository, RideMapper> {
	public RideService() {
		super(Ride.class);
	}
}
