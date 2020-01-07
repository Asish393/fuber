package com.taxi.fuber.service;

import com.taxi.fuber.model.RideRequest;
import com.taxi.fuber.model.enums.TaxiType;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class UtilService {
	public static BigDecimal distance(BigDecimal origLat, BigDecimal origLang, BigDecimal destLat, BigDecimal destLong) {
		if ((origLat.equals(destLat)) && (origLang.equals(destLong))) {
			return BigDecimal.ZERO;
		} else {
			double theta = origLang.doubleValue() - destLong.doubleValue();
			double dist = Math.sin(Math.toRadians(origLat.doubleValue())) * Math.sin(Math.toRadians(destLat.doubleValue())) + Math.cos(Math.toRadians(origLat.doubleValue())) * Math
					.cos(Math.toRadians(destLat.doubleValue())) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			dist = dist * 1.609344;
			return new BigDecimal(dist);
		}
	}

	public static BigDecimal calculateAmount(LocalDateTime start, LocalDateTime end, RideRequest rideRequest) {
		BigDecimal totalDistance = distance(rideRequest.getOriginLat(), rideRequest.getOrginLong(), rideRequest.getDestLat(), rideRequest.getDestLong());
		Long totalTime = Math.abs(Duration.between(end, start).toMinutes());
		BigDecimal amount = totalDistance.multiply(BigDecimal.valueOf(2)).add(BigDecimal.valueOf(totalTime).multiply(BigDecimal.ONE));
		if (rideRequest.getTaxiType().equals(TaxiType.PINK)) {
			amount = amount.add(new BigDecimal(5));
		}
		return amount;
	}
}
