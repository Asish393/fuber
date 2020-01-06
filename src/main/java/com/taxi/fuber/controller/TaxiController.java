package com.taxi.fuber.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Validated
@Api(tags = "Fuber Taxxi")
@RestController
@RequestMapping("v1/taxi")
public class TaxiController {
	@ApiOperation(value = "Retrieves dimensions for the rateType")
	@GetMapping("/test")
	public String test() {
		return "this.rateServiceFactory.getRateService(rateType).findDimensions()";
	}

}
