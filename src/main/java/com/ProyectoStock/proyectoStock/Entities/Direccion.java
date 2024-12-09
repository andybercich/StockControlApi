package com.ProyectoStock.proyectoStock.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Direcciones")
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
public class Direccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "LA CALLE DEBE TENER UN NOMBRE")
    @NotNull
    private String calle;

    @Min(value = 0,message = "NO PUEDE SER UN VALOR NEGATIVO")
    @NotNull
    private int numero;

    private int piso;

    private int numeroDepartamento;



}
