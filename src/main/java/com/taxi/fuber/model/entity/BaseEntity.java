package com.taxi.fuber.model.entity;

import com.taxi.fuber.model.enums.PostgreSQLEnumType;
import com.taxi.fuber.model.enums.Status;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@TypeDef(name = "pg_enum", typeClass = PostgreSQLEnumType.class)
public class BaseEntity {
	@Id
	@GeneratedValue(generator = "UUID")
	@Column(name = "UUID")
	private UUID uuid;
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	@Type(type = "pg_enum")
	private Status status = Status.ACTIVE;
}
