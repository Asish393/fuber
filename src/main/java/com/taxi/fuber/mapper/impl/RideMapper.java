package com.taxi.fuber.mapper.impl;

import com.taxi.fuber.config.BaseMapperConfig;
import com.taxi.fuber.mapper.BaseMapper;
import com.taxi.fuber.model.dto.impl.RideDto;
import com.taxi.fuber.model.entity.impl.Ride;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public interface RideMapper extends BaseMapper<Ride, RideDto> {
}
