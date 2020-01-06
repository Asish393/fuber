package com.taxi.fuber.repository;

import com.taxi.fuber.model.entity.BaseEntity;
import com.taxi.fuber.model.enums.Status;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends PagingAndSortingRepository<E, UUID>, JpaSpecificationExecutor<E> {

	E findOneByUuidAndStatus(UUID uuid, Status status);

}