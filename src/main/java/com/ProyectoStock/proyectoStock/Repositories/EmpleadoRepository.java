package com.ProyectoStock.proyectoStock.Repositories;

import com.ProyectoStock.proyectoStock.Entities.Empleado;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends GenericRepository<Empleado, Long> {
}
