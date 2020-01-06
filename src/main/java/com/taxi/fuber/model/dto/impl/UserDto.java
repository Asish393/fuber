package com.taxi.fuber.model.dto.impl;

import com.taxi.fuber.model.dto.BaseDto;
import com.taxi.fuber.model.entity.impl.User;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto<User> {
	private String name;
	private String phoneNumber;
}
