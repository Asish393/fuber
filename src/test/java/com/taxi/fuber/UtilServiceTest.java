package com.taxi.fuber;

import com.taxi.fuber.model.RideRequest;
import com.taxi.fuber.model.enums.TaxiType;
import com.taxi.fuber.service.UtilService;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilServiceTest {

	@Test
	public void testMinimumDistance() {
		assertTrue(UtilService.distance(new BigDecimal(1), new BigDecimal(1), new BigDecimal(5), new BigDecimal(5))
				.doubleValue() > UtilService.distance(new BigDecimal(2), new BigDecimal(2), new BigDecimal(5), new BigDecimal(5)).doubleValue());
		assertFalse(UtilService.distance(new BigDecimal(3), new BigDecimal(3), new BigDecimal(5), new BigDecimal(5))
				.doubleValue() > UtilService.distance(new BigDecimal(2), new BigDecimal(2), new BigDecimal(5), new BigDecimal(5)).doubleValue());
		RideRequest pink = RideRequest.builder()
				.taxiType(TaxiType.PINK)
				.originLat(BigDecimal.valueOf(1))
				.orginLong(BigDecimal.valueOf(1))
				.destLat(BigDecimal.valueOf(1))
				.destLong(BigDecimal.valueOf(1))
				.build();
		RideRequest all = RideRequest.builder()
				.taxiType(TaxiType.ALL)
				.originLat(BigDecimal.valueOf(1))
				.orginLong(BigDecimal.valueOf(1))
				.destLat(BigDecimal.valueOf(1))
				.destLong(BigDecimal.valueOf(1))
				.build();
		LocalDateTime s = LocalDateTime.now();
		LocalDateTime e = s.plusMinutes(10);
		assertEquals(5d, UtilService.calculateAmount(s, e, pink).doubleValue() - UtilService.calculateAmount(s, e, all).doubleValue(), 0.0);
	}
}
