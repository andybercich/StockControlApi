package com.ProyectoStock.proyectoStock.Controller;

import com.ProyectoStock.proyectoStock.Entities.Pedido;
import com.ProyectoStock.proyectoStock.Services.PedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController extends BaseController<Pedido, Long, PedidoService>{
}
