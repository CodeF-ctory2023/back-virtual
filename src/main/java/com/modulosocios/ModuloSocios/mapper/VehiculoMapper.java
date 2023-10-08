package com.modulosocios.ModuloSocios.mapper;


import com.modulosocios.ModuloSocios.dtos.VehiculoDto;
import com.modulosocios.ModuloSocios.model.Vehiculo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface VehiculoMapper {
    Vehiculo toEntity(final VehiculoDto vehiculoDto);

    VehiculoDto toDto(final Vehiculo vehiculo);
}
