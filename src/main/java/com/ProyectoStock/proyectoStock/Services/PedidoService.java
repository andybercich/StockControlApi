package com.ProyectoStock.proyectoStock.Services;

import com.ProyectoStock.proyectoStock.Entities.Pedido;
import com.ProyectoStock.proyectoStock.Repositories.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService extends BaseService<Pedido, Long>{
    @Autowired
    public PedidoService(GenericRepository<Pedido, Long> repository) {
        this.repository =  repository;
    }
}
