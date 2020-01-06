package com.taxi.fuber.mapper.impl;

import com.taxi.fuber.config.BaseMapperConfig;
import com.taxi.fuber.mapper.BaseMapper;
import com.taxi.fuber.model.dto.impl.TaxiDto;
import com.taxi.fuber.model.entity.impl.Taxi;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public interface TaxiMapper extends BaseMapper<Taxi, TaxiDto> {
}
