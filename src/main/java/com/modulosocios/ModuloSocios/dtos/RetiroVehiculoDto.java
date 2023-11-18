package com.modulosocios.ModuloSocios.dtos;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RetiroVehiculoDto {

    private Integer id;

    private Integer socioid;

    private Integer vehiculoid;

    private Date fechaHoraRetiro;

    private String justificacion;
}
