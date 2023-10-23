package com.modulosocios.ModuloSocios.mapper;


import com.modulosocios.ModuloSocios.dtos.SociosDto;
import com.modulosocios.ModuloSocios.dtos.SociosNuevaDto;

import com.modulosocios.ModuloSocios.model.Socios;
import com.modulosocios.ModuloSocios.model.SociosNueva;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/* @Mapper (unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SocioMapper {
    Socios toEntity(final SociosDto sociosDto);

    SociosDto toDto(final Socios socios);
} */
@Mapper (unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SocioMapper {
    SociosNueva toEntity(final SociosNuevaDto sociosNuevaDto);

    SociosNuevaDto toDto(final SociosNueva sociosNueva);
}
