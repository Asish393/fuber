package com.taxi.fuber.controller;

import com.taxi.fuber.model.RideRequest;
import com.taxi.fuber.model.dto.impl.RideDto;
import com.taxi.fuber.model.dto.impl.TaxiDto;
import com.taxi.fuber.model.dto.impl.UserDto;
import com.taxi.fuber.service.impl.RideService;
import com.taxi.fuber.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(tags = "Fuber Ride request")
@RestController
@RequestMapping("v1/ride")
public class RideController {

	private final RideService rideService;

	@Autowired
	public RideController(RideService rideService) {
		this.rideService = rideService;
	}

	@ApiOperation(value = "Request for new ride")
	@PostMapping("/request")
	public TaxiDto requestRide(@RequestBody RideRequest rideRequest) {
		return null;
	}

	@ApiOperation(value = "Confirm for new ride")
	@PostMapping("/confirm/{userUuid}/{taxiUuid}")
	public TaxiDto confirmRide(@PathVariable final UUID userUuid, @PathVariable final UUID taxiUuid) {
		return null;
	}

	@ApiOperation(value = "Cancel for the ride")
	@PostMapping("/cancel/{userUuid}/{taxiUuid}")
	public RideDto cancelRide(@PathVariable final UUID userUuid, @PathVariable final UUID taxiUuid) {
		return null;
	}

	@ApiOperation(value = "Complete the ride")
	@PostMapping("/complete/{userUuid}/{taxiUuid}")
	public RideDto completeRide(@PathVariable final UUID userUuid, @PathVariable final UUID taxiUuid) {
		return null;
	}
}
