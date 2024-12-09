package com.ProyectoStock.proyectoStock.Controller;


import com.ProyectoStock.proyectoStock.Entities.DetallePedido;
import com.ProyectoStock.proyectoStock.Services.DetallePedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detallePedidos")
public class DetallePedidoController extends BaseController<DetallePedido,Long, DetallePedidoService>{

}
