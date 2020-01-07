package com.taxi.fuber.rides.impl

import com.taxi.fuber.annotation.FuberIntegrationTest
import com.taxi.fuber.mapper.impl.RideMapper
import com.taxi.fuber.model.RideRequest
import com.taxi.fuber.model.dto.impl.RideDto
import com.taxi.fuber.model.entity.impl.Ride
import com.taxi.fuber.model.entity.impl.Taxi
import com.taxi.fuber.model.entity.impl.User
import com.taxi.fuber.model.enums.TaxiType
import com.taxi.fuber.repository.impl.RideRepository
import com.taxi.fuber.repository.impl.TaxiRepository
import com.taxi.fuber.repository.impl.UserRepository
import com.taxi.fuber.rides.BaseSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.shaded.org.apache.commons.lang.math.RandomUtils

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@FuberIntegrationTest
@ActiveProfiles("TEST")
class RideControllerSpec extends BaseSpec<Ride, RideDto, RideRepository, RideMapper> {

	@Autowired
	private TaxiRepository taxiRepository
	@Autowired
	private UserRepository userRepository

	def setupSpec() {
		rootUrl = "/v1/ride/"
	}

	def cleanup() {
		taxiRepository.deleteAll()
		userRepository.deleteAll()
		repository.deleteAll()
	}

	@Override
	Ride createRandomEntity() {
		return Ride.builder()
		           .build()
	}

	Taxi createRandomTaxi(TaxiType taxiType) {
		return Taxi.builder()
		           .latitude(BigDecimal.valueOf(RandomUtils.nextLong()))
		           .taxiType(taxiType)
		           .isEngaged(false)
		           .averageSpeed(BigDecimal.ONE)
		           .longitude(BigDecimal.valueOf(RandomUtils.nextLong()))
		           .build()
	}

	User createRandomUser() {
		return User.builder()
		           .name("ABC" + RandomUtils.nextInt(12))
		           .phoneNumber(RandomUtils.nextInt(10).toString())
		           .build()
	}

	RideRequest createRandomRideRequest(TaxiType taxiType) {
		return RideRequest.builder()
		                  .taxiType(taxiType)
		                  .originLat(BigDecimal.valueOf(RandomUtils.nextLong()))
		                  .orginLong(BigDecimal.valueOf(RandomUtils.nextLong()))
		                  .destLat(BigDecimal.valueOf(RandomUtils.nextLong()))
		                  .destLong(BigDecimal.valueOf(RandomUtils.nextLong()))
		                  .build()
	}

	def 'should send error for bad rideRequest'() {
		Taxi taxi1 = createRandomTaxi(TaxiType.PINK)
		taxiRepository.save(taxi1)
		String request = objectMapper.writeValueAsString(createRandomRideRequest(TaxiType.PINK))
		given:
		expect: "Status is 400, and error message is returned "
		this.mockMvc.perform(post(rootUrl + "request").contentType(APPLICATION_JSON).content(request))
		    .andDo(print())
		    .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn()
	}


	def 'should send 404 for no taxi found for pink'() {
		Taxi taxi1 = createRandomTaxi(TaxiType.ALL)
		taxiRepository.save(taxi1)
		User user = createRandomUser()
		userRepository.save(user)
		RideRequest request = createRandomRideRequest(TaxiType.PINK)
		request.setUserId(user.uuid)
		given:
		expect: "Status is 404, and error message is returned "
		this.mockMvc.perform(post(rootUrl + "request").contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
		    .andDo(print())
		    .andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn()
	}

	def 'should send 404 for no taxi found if engaged'() {
		Taxi taxi1 = createRandomTaxi(TaxiType.ALL)
		taxi1.setIsEngaged(true)
		taxiRepository.save(taxi1)
		User user = createRandomUser()
		userRepository.save(user)
		RideRequest request = createRandomRideRequest(TaxiType.ALL)
		request.setUserId(user.uuid)
		given:
		expect: "Status is 404, and error message is returned "
		this.mockMvc.perform(post(rootUrl + "request").contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
		    .andDo(print())
		    .andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn()
	}

	def 'should send 200 for pink taxi found'() {
		Taxi taxi1 = createRandomTaxi(TaxiType.PINK)
		taxiRepository.save(taxi1)
		Taxi taxi2 = createRandomTaxi(TaxiType.ALL)
		taxiRepository.save(taxi2)
		Taxi taxi3 = createRandomTaxi(TaxiType.ALL)
		taxiRepository.save(taxi3)
		User user = createRandomUser()
		userRepository.save(user)
		RideRequest request = createRandomRideRequest(TaxiType.PINK)
		request.setUserId(user.uuid)
		given:
		expect: "Status is 200, and error message is returned "
		this.mockMvc.perform(post(rootUrl + "request").contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
		    .andDo(print())
		    .andExpect(MockMvcResultMatchers.status().isOk())
		    .andExpect(jsonPath("\$.uuid").value(taxi1.uuid.toString()))
	}

	def 'should send 200 for nearest taxi found, confirm and reserve'() {
		Taxi taxi1 = createRandomTaxi(TaxiType.PINK)
		taxi1.setLatitude(new BigDecimal(10))
		taxi1.setLongitude(new BigDecimal(10))
		taxiRepository.save(taxi1)
		Taxi taxi2 = createRandomTaxi(TaxiType.ALL)
		taxi2.setLatitude(new BigDecimal(12))
		taxi2.setLongitude(new BigDecimal(12))
		taxiRepository.save(taxi2)
		Taxi taxi3 = createRandomTaxi(TaxiType.ALL)
		taxi3.setLatitude(new BigDecimal(12))
		taxi3.setLongitude(new BigDecimal(12))
		taxiRepository.save(taxi3)
		User user = createRandomUser()
		userRepository.save(user)
		RideRequest request = createRandomRideRequest(TaxiType.ALL)
		request.setOriginLat(new BigDecimal(1))
		request.setOrginLong(new BigDecimal(1))
		request.setUserId(user.uuid)
		given:
		expect: "Status is 200, and error message is returned "
		this.mockMvc.perform(post(rootUrl + "request").contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
		    .andDo(print())
		    .andExpect(MockMvcResultMatchers.status().isOk())
		    .andExpect(jsonPath("\$.uuid").value(taxi1.uuid.toString()))
		MvcResult rideResult = this.mockMvc.perform(post(rootUrl + "confirm/" + taxi1.uuid).contentType(APPLICATION_JSON).content(objectMapper.
				writeValueAsString(request)))
		                           .andDo(print())
		                           .andExpect(MockMvcResultMatchers.status().isOk())
		                           .andExpect(jsonPath("\$.taxiId").value(taxi1.uuid.toString())).andReturn()
		RideDto rideDto = objectMapper.readValue(rideResult.getResponse().getContentAsString(), RideDto.class)
		this.mockMvc.perform(post(rootUrl + "complete/" + taxi1.uuid.toString() + "/" + rideDto.getUuid().toString())
				                     .contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
		    .andDo(print())
		    .andExpect(MockMvcResultMatchers.status().isOk())
		    .andExpect(jsonPath("\$.taxiId").value(taxi1.uuid.toString())).andReturn()

	}


}
