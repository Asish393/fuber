package com.taxi.fuber.model.entity.impl;

import com.taxi.fuber.model.entity.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;

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
@Entity(name = "FUB_RIDE")
public class Ride extends BaseEntity {
	@Column(name = "USER_ID", nullable = false)
	private UUID userId;
	@Column(name = "TAXI_ID", nullable = false)
	private UUID taxiId;
	@Column(name = "ORIG_LAT", nullable = false)
	private BigDecimal originLat;
	@Column(name = "ORIG_LONG", nullable = false)
	private BigDecimal orginLong;
	@Column(name = "DEST_LAT", nullable = false)
	private BigDecimal destLat;
	@Column(name = "DEST_LONG", nullable = false)
	private BigDecimal destLong;
	@Column(name = "AMOUNT", nullable = false)
	private BigDecimal amount;
	@Column(name = "RIDE_START")
	private LocalDateTime rideStart;
	@Column(name = "RIDE_END")
	private LocalDateTime rideEnd;
}
