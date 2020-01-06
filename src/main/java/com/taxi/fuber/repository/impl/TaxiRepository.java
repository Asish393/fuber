package com.taxi.fuber.repository.impl;

import com.taxi.fuber.model.entity.impl.Taxi;
import com.taxi.fuber.model.enums.TaxiType;
import com.taxi.fuber.repository.BaseRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TaxiRepository extends BaseRepository<Taxi> {
	@Query(value = "SELECT a.uuid,a.lat,a.long,a.taxi_type,a.avg_speed,a.is_engaged,a.status, " +
			"111.111 *DEGREES(ACOS(LEAST(1.0, COS(RADIANS(a.lat))* COS(RADIANS(:latitude))* " +
			"COS(RADIANS(a.lat - :longitude))+ SIN(RADIANS(a.lat))* " +
			"SIN(RADIANS(:latitude))))) AS distance_in_km " +
			"FROM fub_taxi AS a where status = 'ACTIVE' and " +
			"is_engaged = false order by distance_in_km asc LIMIT 1;", nativeQuery = true)
	Taxi findNearestTaxi(BigDecimal latitude, BigDecimal longitude);
	@Query(value = "SELECT a.uuid,a.lat,a.long,a.taxi_type,a.avg_speed,a.is_engaged,a.status, " +
			"111.111 *DEGREES(ACOS(LEAST(1.0, COS(RADIANS(a.lat))* COS(RADIANS(:latitude))* " +
			"COS(RADIANS(a.lat - :longitude))+ SIN(RADIANS(a.lat))* " +
			"SIN(RADIANS(:latitude))))) AS distance_in_km " +
			"FROM fub_taxi AS a where status = 'ACTIVE' and taxi_type = 'PINK'" +
			"and is_engaged = false order by distance_in_km asc LIMIT 1;", nativeQuery = true)
	Taxi findNearestPinkTaxi(BigDecimal latitude, BigDecimal longitude);

}
