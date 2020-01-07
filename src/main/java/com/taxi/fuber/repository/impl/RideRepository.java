package com.taxi.fuber.repository.impl;

import com.taxi.fuber.model.entity.impl.Ride;
import com.taxi.fuber.repository.BaseRepository;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RideRepository extends BaseRepository<Ride> {
	Ride findOneByUuid(UUID uuid);
}
