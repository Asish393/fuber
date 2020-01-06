package com.taxi.fuber.model.entity.impl;

import com.taxi.fuber.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "FUB_USER")
public class User extends BaseEntity {
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "PHONE_NUMBER", nullable = false)
	private String phoneNumber;
}
