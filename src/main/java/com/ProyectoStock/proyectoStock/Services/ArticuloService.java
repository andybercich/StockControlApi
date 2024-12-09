package com.ProyectoStock.proyectoStock.Services;

import com.ProyectoStock.proyectoStock.Entities.Articulo;
import com.ProyectoStock.proyectoStock.Repositories.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloService extends BaseService<Articulo, Long> {

    @Autowired
    public ArticuloService(GenericRepository<Articulo, Long> repository) {
        this.repository =  repository;
    }


}
