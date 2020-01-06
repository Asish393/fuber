package com.taxi.fuber.service.impl;

import com.taxi.fuber.mapper.impl.UserMapper;
import com.taxi.fuber.model.dto.impl.UserDto;
import com.taxi.fuber.model.entity.impl.User;
import com.taxi.fuber.repository.impl.UserRepository;
import com.taxi.fuber.service.BaseService;

public class UserService extends BaseService<User, UserDto, UserRepository, UserMapper> {
	public UserService() {
		super(User.class);
	}
}
