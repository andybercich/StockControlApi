package com.ProyectoStock.proyectoStock.Controller;

import com.ProyectoStock.proyectoStock.Services.EmpleadoService;
import com.ProyectoStock.proyectoStock.Entities.Empleado;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/empleados")
public class EmpleadoController extends BaseController<Empleado,Long, EmpleadoService> {
}
