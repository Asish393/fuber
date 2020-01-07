package com.taxi.fuber.service;

import com.taxi.fuber.mapper.BaseMapper;
import com.taxi.fuber.model.dto.BaseDto;
import com.taxi.fuber.model.entity.BaseEntity;
import com.taxi.fuber.repository.BaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.taxi.fuber.model.enums.Status.ACTIVE;
import static com.taxi.fuber.model.enums.Status.INACTIVE;

@Transactional
public abstract class BaseService<E extends BaseEntity, D extends BaseDto<E>, R extends BaseRepository<E>, M extends BaseMapper<E, D>> {

	@Autowired
	protected M mapper;
	@Autowired
	protected R repository;
	private Class<E> clazz;

	public BaseService(Class<E> clazz) {
		this.clazz = clazz;
	}

	public D create(final D d) {
		try {
			E e = this.clazz.getDeclaredConstructor().newInstance();
			this.mapper.dtoToEntity(d, e);
			return this.mapper.entityToDto(this.repository.save(e));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public void deleteByUuid(final UUID uuid) {
		final E e = this.repository.findOneByUuidAndStatus(uuid, ACTIVE);
		if (e != null) {
			e.setStatus(INACTIVE);
		}
	}


}