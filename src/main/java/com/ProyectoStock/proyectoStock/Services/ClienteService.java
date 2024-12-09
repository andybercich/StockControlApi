package com.ProyectoStock.proyectoStock.Services;

import com.ProyectoStock.proyectoStock.Entities.Cliente;
import com.ProyectoStock.proyectoStock.Repositories.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends BaseService<Cliente,Long> {
    @Autowired
    public ClienteService(GenericRepository<Cliente, Long> repository) {
        this.repository =  repository;
    }

}
