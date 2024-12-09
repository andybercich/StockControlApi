package com.ProyectoStock.proyectoStock.Entities;


import com.ProyectoStock.proyectoStock.Entities.Direccion;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="Sucursales")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Sucursal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotNull
    private Direccion direccion;

    @NotNull
    @NotBlank
    private String nombre;



}
