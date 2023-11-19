package com.modulosocios.ModuloSocios.mapper;

import com.modulosocios.ModuloSocios.dtos.AdministradorDto;
import com.modulosocios.ModuloSocios.model.Administrador;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AdministradorMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) // Control para los campos
                                                                                             // nulos
    Administrador toEntity(final Administrador administradorDto);// Mapeo de DTO a entidad

    AdministradorDto toDto(final Administrador administrador);// Mapeo de entidad a DTO
}
