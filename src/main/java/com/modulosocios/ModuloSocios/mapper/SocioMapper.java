package com.modulosocios.ModuloSocios.mapper;

import com.modulosocios.ModuloSocios.dtos.SocioDto;
import com.modulosocios.ModuloSocios.dtos.SocioSimpleDto;

import com.modulosocios.ModuloSocios.model.Socio;
import com.modulosocios.ModuloSocios.model.SocioSimple;

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

@Mapper(componentModel = "spring")
public interface SocioMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) // Control para los campos
                                                                                             // nulos
    SocioSimple toEntity(final SocioSimpleDto sociosNuevaDto);// Mapeo de DTO a entidad

    SocioSimpleDto toDto(final SocioSimple sociosNueva);// Mapeo de entidad a DTO
}
