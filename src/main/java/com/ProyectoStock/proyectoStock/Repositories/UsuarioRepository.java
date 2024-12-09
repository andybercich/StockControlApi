package com.ProyectoStock.proyectoStock.Repositories;

import com.ProyectoStock.proyectoStock.Entities.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends GenericRepository<Usuario, Long> {
}
