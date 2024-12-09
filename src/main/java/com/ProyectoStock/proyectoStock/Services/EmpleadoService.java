package com.ProyectoStock.proyectoStock.Services;

import com.ProyectoStock.proyectoStock.Entities.Empleado;
import com.ProyectoStock.proyectoStock.Repositories.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService extends BaseService<Empleado, Long> {
    @Autowired
    public EmpleadoService(GenericRepository<Empleado, Long> repository) {
        this.repository =  repository;
    }
}