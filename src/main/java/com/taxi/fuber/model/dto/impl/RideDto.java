package com.taxi.fuber.model.dto.impl;

import com.taxi.fuber.model.dto.BaseDto;
import com.taxi.fuber.model.entity.impl.Ride;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RideDto extends BaseDto<Ride> {
	private UUID userId;
	private UUID taxiId;
	private String taxiType;
	private BigDecimal originLat;
	private BigDecimal orginLong;
	private BigDecimal destLat;
	private BigDecimal destLong;
	private BigDecimal amount;
	private LocalDateTime rideStart;
	private LocalDateTime rideEnd;
}
