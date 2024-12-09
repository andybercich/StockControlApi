package com.ProyectoStock.proyectoStock.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mail;

    private int edad;

    @NotBlank
    @NotNull
    private String nombre;

    @NotBlank
    @NotNull
    private String apellido;


    @OneToOne
    @JoinColumn(name = "usuario_id")
    @NotNull(message = "LA PERSONA DEBE TENER UN USUARIO EXISTENTE")
    private Usuario usuario;


    public void setEdad(int edad) throws Exception {
       if (edad<0){
           throw new Exception("NO SE PUEDE TENER UNA EDAD NEGATIVA");
       }else {
           this.edad = edad;
       }

    }

}
