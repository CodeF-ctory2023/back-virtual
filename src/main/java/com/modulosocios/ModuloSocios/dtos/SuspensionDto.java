/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modulosocios.ModuloSocios.dtos;

import java.util.Date;

import lombok.*;

/**
 *
 * @author anima
 */
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
