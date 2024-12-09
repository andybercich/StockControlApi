package com.ProyectoStock.proyectoStock.Controller;

import com.ProyectoStock.proyectoStock.Entities.Sucursal;
import com.ProyectoStock.proyectoStock.Services.SucursalService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sucursales")
public class SucursalController extends BaseController<Sucursal,Long, SucursalService> {
}
