package com.taxi.fuber.model.dto;

import com.taxi.fuber.model.entity.BaseEntity;

import java.util.UUID;

import lombok.Data;

@Data
public abstract class BaseDto<E extends BaseEntity> {
	private UUID uuid;
}