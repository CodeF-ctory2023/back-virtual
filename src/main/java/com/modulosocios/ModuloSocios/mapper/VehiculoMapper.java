package com.modulosocios.ModuloSocios.mapper;

import com.modulosocios.ModuloSocios.dtos.VehiculoDto;
import com.modulosocios.ModuloSocios.model.Vehiculo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;



@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface VehiculoMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    
    VehiculoDto toDto(final Vehiculo vehiculo);

    Vehiculo toEntity(final VehiculoDto vehiculoDto);
}
