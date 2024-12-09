package com.ProyectoStock.proyectoStock.Entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Clientes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Persona {

    public Cliente(Long id, String mail, int edad, String nombre, String apellido, Usuario usuario){
        super(id,mail,edad,nombre,apellido,usuario);
    }


    @ManyToMany
    @JoinTable(
            name = "cliente_direccion",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "direccion_id")
    )
    private Set<Direccion> direcciones = new HashSet<>();



    public void agregarDireccion(Direccion direccion){
        direcciones.add(direccion);
    }


}
