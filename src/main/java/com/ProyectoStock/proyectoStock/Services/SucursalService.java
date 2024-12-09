package com.ProyectoStock.proyectoStock.Services;

import com.ProyectoStock.proyectoStock.Entities.Sucursal;
import com.ProyectoStock.proyectoStock.Repositories.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SucursalService extends BaseService<Sucursal, Long> {

    @Autowired
    public SucursalService(GenericRepository<Sucursal, Long> repository) {
        this.repository =  repository;
    }
}
