package com.taxi.fuber.model;

import com.taxi.fuber.model.enums.TaxiType;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RideRequest {
	private UUID userId;
	private TaxiType taxiType;
	private BigDecimal originLat;
	private BigDecimal orginLong;
	private BigDecimal destLat;
	private BigDecimal destLong;
}
