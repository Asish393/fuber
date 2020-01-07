package com.taxi.fuber.controller;

import com.taxi.fuber.model.RideRequest;
import com.taxi.fuber.model.dto.impl.RideDto;
import com.taxi.fuber.model.dto.impl.TaxiDto;
import com.taxi.fuber.service.impl.RideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import javax.validation.Valid;

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

	@ApiOperation(value = "Complete the ride")
	@PostMapping("/complete/{taxiUuid}/{rideUuid}")
	public RideDto completeRide(@PathVariable UUID taxiUuid, @PathVariable UUID rideUuid, @RequestBody RideRequest rideRequest) {
		return rideService.completeRide(taxiUuid, rideUuid, rideRequest);
	}

	@ApiOperation(value = "Confirm for new ride")
	@PostMapping("/confirm/{taxiUuid}")
	public RideDto confirmRide(@PathVariable UUID taxiUuid, @RequestBody RideRequest rideRequest) {
		return rideService.confirmRide(taxiUuid, rideRequest);
	}

	@ApiOperation(value = "Request for new ride")
	@PostMapping("/request")
	public TaxiDto requestRide(@RequestBody @Valid RideRequest rideRequest) {
		return rideService.getNearestTaxi(rideRequest);
	}
}
