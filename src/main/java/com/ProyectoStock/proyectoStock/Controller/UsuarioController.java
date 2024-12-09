package com.ProyectoStock.proyectoStock.Controller;

import com.ProyectoStock.proyectoStock.Entities.Usuario;
import com.ProyectoStock.proyectoStock.Services.UsuarioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends BaseController<Usuario, Long, UsuarioService> {
}
