package com.taxi.fuber.mapper;

import com.taxi.fuber.model.dto.BaseDto;
import com.taxi.fuber.model.entity.BaseEntity;

import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

public interface BaseMapper<E extends BaseEntity, D extends BaseDto> {

	void dtoToEntity(D source, @MappingTarget E target);

	default Page<D> entitiesToDtos(final Page<E> source) {
		return source.map(this::entityToDto);
	}

	D entityToDto(E source);
}