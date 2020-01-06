package com.taxi.fuber.controller;

import com.taxi.fuber.model.dto.impl.TaxiDto;
import com.taxi.fuber.service.impl.TaxiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(tags = "Fuber Taxi Registration")
@RestController
@RequestMapping("v1/taxi")
public class TaxiController {

	private final TaxiService taxiService;

	@Autowired
	public TaxiController(TaxiService taxiService) {
		this.taxiService = taxiService;
	}

	@ApiOperation(value = "Register new taxi")
	@PostMapping("/register")
	public TaxiDto register(@RequestBody TaxiDto taxiDto) {
		return taxiService.create(taxiDto);
	}



	@ApiOperation(value = "Unregister taxi")
	@DeleteMapping("/{uuid}")
	public void deRegister(@PathVariable final UUID uuid) {
		taxiService.deleteByUuid(uuid);
	}
}
