package com.ProyectoStock.proyectoStock.Controller;


import com.ProyectoStock.proyectoStock.Services.DireccionService;
import com.ProyectoStock.proyectoStock.Entities.Direccion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direcciones")
public class DireccionController extends BaseController<Direccion, Long, DireccionService> {
}
