package com.modulosocios.ModuloSocios.mapper;

import com.modulosocios.ModuloSocios.dtos.SocioSimpleDto;

import com.modulosocios.ModuloSocios.model.Socio;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface SocioMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) // Control para los campos
                                                                                             // nulos
    Socio toEntity(final SocioSimpleDto socioDto);// Mapeo de DTO a entidad

    SocioSimpleDto toDto(final Socio socio);// Mapeo de entidad a DTO
}
