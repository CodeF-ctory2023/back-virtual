package com.modulosocios.ModuloSocios.mapper;


import com.modulosocios.ModuloSocios.dtos.SociosDto;
import com.modulosocios.ModuloSocios.model.Socios;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper (unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SocioMapper {
    Socios toEntity(final SociosDto sociosDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SociosDto toDto(final Socios socios);
}
