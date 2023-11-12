package com.modulosocios.ModuloSocios.mapper;


import com.modulosocios.ModuloSocios.dtos.SociosDto;
import com.modulosocios.ModuloSocios.dtos.SociosNuevaDto;

import com.modulosocios.ModuloSocios.model.Socios;
import com.modulosocios.ModuloSocios.model.SociosNueva;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/* @Mapper(componentModel = "spring")
public interface SocioMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)//Control para los campos nulos
    Socios toEntity(final SociosDto sociosDto);//Mapeo de DTO a entidad
    SociosDto toDto(final Socios socios);//Mapeo de entidad a DTO 
} */
@Mapper (componentModel = "spring")
public interface SocioMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)//Control para los campos nulos
    SociosNueva toEntity(final SociosNuevaDto sociosNuevaDto);//Mapeo de DTO a entidad
    SociosNuevaDto toDto(final SociosNueva sociosNueva);//Mapeo de entidad a DTO 
}
