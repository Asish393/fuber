package com.taxi.fuber.rides.impl

import com.taxi.fuber.annotation.FuberIntegrationTest
import com.taxi.fuber.mapper.impl.TaxiMapper
import com.taxi.fuber.mapper.impl.UserMapper
import com.taxi.fuber.model.dto.impl.TaxiDto
import com.taxi.fuber.model.dto.impl.UserDto
import com.taxi.fuber.model.entity.impl.Taxi
import com.taxi.fuber.model.entity.impl.User
import com.taxi.fuber.model.enums.Status
import com.taxi.fuber.model.enums.TaxiType
import com.taxi.fuber.repository.impl.TaxiRepository
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
class TaxiControllerSpec extends BaseSpec<Taxi, TaxiDto, TaxiRepository, TaxiMapper> {


	def setupSpec() {
		rootUrl = "/v1/taxi/"
	}

	def cleanup() {
		repository.deleteAll()
	}

	private int i = 0

	@Override
	Taxi createRandomEntity() {
		i++
		return Taxi.builder()
				.latitude(BigDecimal.valueOf(RandomUtils.nextLong()))
				.taxiType(TaxiType.PINK)
				.isEngaged(false)
				.averageSpeed(BigDecimal.ONE)
				.longitude(BigDecimal.valueOf(RandomUtils.nextLong()))
				.build()
	}

	def 'should register taxi and return'() {
		TaxiDto taxiDto = mapper.entityToDto(createRandomEntity())
		given:
		expect: "Status is 200, and user is returned "
		this.mockMvc.perform(post(rootUrl + "register").contentType(APPLICATION_JSON)
				                     .content(objectMapper.writeValueAsString(taxiDto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
	}
	def 'should unregister taxi'() {
		Taxi taxi = createRandomEntity()
		repository.save(taxi)
		given:
		expect: "Status is 200, and taxi is returned with status in active"
		this.mockMvc.perform(delete(rootUrl + taxi.uuid.toString()))
		    .andExpect(MockMvcResultMatchers.status().isOk())
		Objects.nonNull(repository.findOneByUuidAndStatus(taxi.uuid, Status.INACTIVE))
	}

}
