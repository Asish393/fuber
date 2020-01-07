package com.taxi.fuber.rides

import com.fasterxml.jackson.databind.ObjectMapper
import com.taxi.fuber.mapper.BaseMapper
import com.taxi.fuber.model.dto.BaseDto
import com.taxi.fuber.model.entity.BaseEntity
import com.taxi.fuber.repository.BaseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification


abstract class BaseSpec<E extends BaseEntity, D extends BaseDto, R extends BaseRepository<E>, M extends BaseMapper<E, D>> extends Specification {
	@Autowired
	protected MockMvc mockMvc
	@Autowired
	protected ObjectMapper objectMapper
	@Autowired
	protected R repository
	@Autowired
	protected M mapper
	@Shared
	protected String rootUrl
	abstract E createRandomEntity()
}