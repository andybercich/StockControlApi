package com.ProyectoStock.proyectoStock.Services;

import com.ProyectoStock.proyectoStock.Entities.Usuario;
import com.ProyectoStock.proyectoStock.Repositories.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends BaseService<Usuario, Long> {
    @Autowired
    public UsuarioService(GenericRepository<Usuario, Long> repository) {
        this.repository =  repository;
    }
}
