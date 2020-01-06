package com.taxi.fuber.model.entity.impl;

import com.taxi.fuber.model.entity.BaseEntity;

import java.math.BigDecimal;

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
@Entity(name = "FUB_TAXI")
public class Taxi extends BaseEntity {
	@Column(name = "LAT", nullable = false)
	private BigDecimal latitude;
	@Column(name = "LONG", nullable = false)
	private BigDecimal longitude;
	@Column(name = "COLOR", nullable = false)
	private String color;
	@Column(name = "AVG_SPEED", nullable = false)
	private BigDecimal averageSpeed;
	@Column(name = "IS_ENGAGED", nullable = false)
	private Boolean isEngaged = Boolean.FALSE;
}
