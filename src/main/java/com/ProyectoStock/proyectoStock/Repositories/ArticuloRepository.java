package com.ProyectoStock.proyectoStock.Repositories;

import com.ProyectoStock.proyectoStock.Entities.Articulo;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends GenericRepository<Articulo, Long> {
}

