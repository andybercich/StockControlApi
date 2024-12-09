package com.ProyectoStock.proyectoStock.Repositories;

import com.ProyectoStock.proyectoStock.Entities.DetallePedido;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public interface DetallePedidoRepository extends GenericRepository<DetallePedido,Long> {
}
