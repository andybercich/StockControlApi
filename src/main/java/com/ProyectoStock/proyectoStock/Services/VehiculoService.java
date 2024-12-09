package com.ProyectoStock.proyectoStock.Services;

import com.ProyectoStock.proyectoStock.Entities.Vehiculo;
import com.ProyectoStock.proyectoStock.Repositories.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService extends BaseService<Vehiculo,Long> {

    @Autowired
    public VehiculoService(GenericRepository<Vehiculo, Long> repository) {
        this.repository =  repository;
    }

}
