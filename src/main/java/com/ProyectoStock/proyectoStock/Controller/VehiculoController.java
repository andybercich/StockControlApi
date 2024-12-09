package com.ProyectoStock.proyectoStock.Controller;


import com.ProyectoStock.proyectoStock.Entities.Vehiculo;
import com.ProyectoStock.proyectoStock.Services.VehiculoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController extends BaseController<Vehiculo, Long, VehiculoService> {

}
