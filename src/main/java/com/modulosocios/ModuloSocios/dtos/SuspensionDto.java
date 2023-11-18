package com.modulosocios.ModuloSocios.dtos;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuspensionDto {

    private Integer id;

    private Integer socioId;

    private Date fechaHoraSuspension;

    private String motivo;
}
