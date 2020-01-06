package com.taxi.fuber.model.dto.impl;


import com.taxi.fuber.model.dto.BaseDto;
import com.taxi.fuber.model.entity.impl.Taxi;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TaxiDto extends BaseDto<Taxi> {
	private BigDecimal latitude;
	private BigDecimal longitude;
	private String color;
	private BigDecimal averageSpeed;
	private Boolean isEngaged;
}
