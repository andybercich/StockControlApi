package com.ProyectoStock.proyectoStock.Entities;


import com.ProyectoStock.proyectoStock.Entities.Sucursal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="Vehiculos")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Vehiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String matricula;

    @NotNull
    @NotBlank
    private String marca;

    @NotBlank
    @NotNull
    private String modelo;

    @NotNull
    private double pesoMaximo;

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    @NotNull
    private Sucursal sucursal;

    @NotNull
    private int cantidadPasajeros;

    @NotNull(message = "Caracteristica must not be null")
    @NotBlank(message = "Caracteristica must not be blank")
    private String caracteristica;

}
