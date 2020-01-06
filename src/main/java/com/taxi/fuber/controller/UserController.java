package com.taxi.fuber.controller;

import com.taxi.fuber.model.dto.impl.UserDto;
import com.taxi.fuber.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(tags = "Fuber User Registration")
@RestController
@RequestMapping("v1/user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "Register new user")
	@PostMapping("/register")
	public UserDto register(@RequestBody UserDto userDto) {
		return userService.create(userDto);
	}

	@ApiOperation(value = "Unregister user")
	@DeleteMapping("/{uuid}")
	public void deRegister(@PathVariable final UUID uuid) {
		userService.deleteByUuid(uuid);
	}
}
