package com.taxi.fuber.rides.impl

import com.taxi.fuber.annotation.FuberIntegrationTest
import com.taxi.fuber.mapper.impl.UserMapper
import com.taxi.fuber.model.dto.impl.UserDto
import com.taxi.fuber.model.entity.impl.User
import com.taxi.fuber.model.enums.Status
import com.taxi.fuber.repository.impl.UserRepository
import com.taxi.fuber.rides.BaseSpec
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.shaded.org.apache.commons.lang.math.RandomUtils

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@FuberIntegrationTest
@ActiveProfiles("TEST")
class UserControllerSpec extends BaseSpec<User, UserDto, UserRepository, UserMapper> {


	def setupSpec() {
		rootUrl = "/v1/user/"
	}

	def cleanup() {
		repository.deleteAll()
	}

	@Override
	User createRandomEntity() {
		return User.builder()
		              .name("ABC"+ RandomUtils.nextInt(12))
				      .phoneNumber(RandomUtils.nextInt(10).toString())
		              .build()
	}

	def 'should register user and return'() {
		UserDto userDto = mapper.entityToDto(createRandomEntity())
		given:
		expect: "Status is 200, and user is returned "
		this.mockMvc.perform(post(rootUrl + "register").contentType(APPLICATION_JSON)
				                     .content(objectMapper.writeValueAsString(userDto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
	}
	def 'should unregister user'() {
		User user = createRandomEntity()
		repository.save(user)
		given:
		expect: "Status is 200, and user is returned with status in active"
		this.mockMvc.perform(delete(rootUrl + user.uuid.toString()))
		    .andExpect(MockMvcResultMatchers.status().isOk())
		Objects.nonNull(repository.findOneByUuidAndStatus(user.uuid, Status.INACTIVE))
	}

}
