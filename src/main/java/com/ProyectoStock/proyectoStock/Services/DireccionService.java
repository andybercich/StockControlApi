package com.ProyectoStock.proyectoStock.Services;

import com.ProyectoStock.proyectoStock.Entities.Direccion;
import com.ProyectoStock.proyectoStock.Repositories.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DireccionService extends BaseService<Direccion, Long> {
    @Autowired
    public DireccionService(GenericRepository<Direccion, Long> repository) {
        this.repository =  repository;
    }
}
