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
@Table(name = "Articulos")
@Getter
@Setter
@NoArgsConstructor // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos los argumentos
public class Articulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre_Articulo")
    @NotBlank(message = "El nombre no puede estar en blanco")
    @NotNull
    private String nombre;

    @Min(value = 1, message = "El stock no puede ser negativo")
    @NotNull
    private int stock;

    @Min(value = 0, message = "Los mililitros no pueden ser negativos")
    private double mililitros;

    @Min(value = 1, message = "Los gramos no pueden ser negativos")
    @NotNull
    private double gramos;

    @Min(value = 1, message = "El precio no puede ser negativo")
    @NotNull
    private double precio;


    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    @NotNull
    private Sucursal sucursal;


    public String agregarArticulos(int cantidad) throws Exception {

        if (cantidad <= 0){
            throw new Exception("NO SE PUEDE AGREGAR, UNA CANTIDAD NEGATIVA O IGUAL A 0(CERO) A STOCK");
        } else{
            stock+= cantidad;
            return "SE HA SUMADO LOS ARTICULOS CORRECTAMENTE";
        }

    }


    public String retirarArticulos(int cantidad) throws Exception {

        if (cantidad > stock){
            throw new Exception("NO SE PUEDE RETIRAR, CANTIDAD MAYOR A STOCK");
        } else if (cantidad == stock) {
            this.stock = 0;
            return "RETIRO EXITOSO, NO QUEDA MÁS STOCK DE: "+ this.toString();
        }else{

            this.stock = stock -cantidad;

            return "RETIRO EXITOSO "+ this.toString();

        }
    }

    @Override
    public String toString() {
        return "Articulo: "+ this.getNombre() + "  ID: "+ this.id+ "    Stock: "+ this.stock;
    }
}
