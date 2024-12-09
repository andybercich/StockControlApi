package com.ProyectoStock.proyectoStock.Controller;

import com.ProyectoStock.proyectoStock.Services.ArticuloService;
import com.ProyectoStock.proyectoStock.Entities.Articulo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articulos")
public class ArticuloController extends BaseController<Articulo, Long, ArticuloService> {
}
