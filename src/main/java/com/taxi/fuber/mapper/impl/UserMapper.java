package com.taxi.fuber.mapper.impl;

import com.taxi.fuber.config.BaseMapperConfig;
import com.taxi.fuber.mapper.BaseMapper;
import com.taxi.fuber.model.dto.impl.UserDto;
import com.taxi.fuber.model.entity.impl.User;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public interface UserMapper extends BaseMapper<User, UserDto> {
}
