package com.modulosocios.ModuloSocios.mapper;

import com.modulosocios.ModuloSocios.dtos.AdministradorDto;
import com.modulosocios.ModuloSocios.model.Administrador;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AdministradorMapper {
    Administrador toEntity(final Administrador administradorDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AdministradorDto toDto(final Administrador administrador);
}
