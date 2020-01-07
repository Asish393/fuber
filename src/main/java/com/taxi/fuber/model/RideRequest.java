package com.taxi.fuber.model;

import com.taxi.fuber.model.enums.TaxiType;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotNull;

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
	@NotNull(message = "userId is mandatory")
	private UUID userId;
	@NotNull(message = "taxiType is mandatory")
	private TaxiType taxiType;
	@NotNull(message = "originLat is mandatory")
	private BigDecimal originLat;
	@NotNull(message = "orginLong is mandatory")
	private BigDecimal orginLong;
	@NotNull(message = "destLat is mandatory")
	private BigDecimal destLat;
	@NotNull(message = "destLong is mandatory")
	private BigDecimal destLong;
}
