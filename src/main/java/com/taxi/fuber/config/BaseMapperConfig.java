package com.taxi.fuber.config;

import com.taxi.fuber.model.dto.BaseDto;
import com.taxi.fuber.model.entity.BaseEntity;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.WARN, mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface BaseMapperConfig {
	@Mapping(target = "uuid", ignore = true)
	void dtoToEntity(BaseDto source, @MappingTarget BaseEntity target);
}


