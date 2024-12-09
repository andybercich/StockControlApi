package com.ProyectoStock.proyectoStock.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "DetallesPedido")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DetallePedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Getter
    private int cantidad;

    @ManyToOne
    @NotNull
    @Getter
    private Articulo articulo;

    @ManyToOne
    @NotNull
    @Setter
    @Getter
    private Pedido pedido;

    @Getter
    private double subTotal;

    // Método para calcular el subtotal
    public void calculateSubTotal() {
        this.subTotal = this.articulo.getPrecio() * this.cantidad;
    }

}
