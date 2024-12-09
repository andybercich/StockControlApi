package com.ProyectoStock.proyectoStock.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Cliente cliente;

    @NotNull
    private boolean pagado;

    @NotNull
    private double total = 0;

    @ManyToOne
    @NotNull
    private Sucursal sucursal;


}
