package com.ProyectoStock.proyectoStock.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Empleados")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Empleado extends Persona {

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    @NotNull
    private Sucursal sucursal;


    @ManyToMany
    @JoinTable(
            name = "empleado_direccion",
            joinColumns = @JoinColumn(name = "empleado_id"),
            inverseJoinColumns = @JoinColumn(name = "direccion_id")
    )
    private Set<Direccion> direcciones = new HashSet<>();

    @Override
    public void setEdad(int edad) throws Exception {
        if (edad < 18) {
            throw new Exception("NO PUEDE TENER MENOS DE 18 AÑOS");
        }
        super.setEdad(edad);
    }



    public void agregarDireccion(Direccion direccion) {
        direcciones.add(direccion);
    }
}
