package com.ProyectoStock.proyectoStock;

import com.ProyectoStock.proyectoStock.Entities.*;
import com.ProyectoStock.proyectoStock.Services.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProyectoStockApplicationTests {

	@Autowired
	DireccionService direccionService;

	@Autowired
	SucursalService sucursalService;

	@Autowired
	PedidoService pedidoService;

	@Autowired
	ArticuloService articuloService;

	@Autowired
	ClienteService clienteService;

	@Autowired
	DetallePedidoService detallePedidoService;


	@Autowired
	UsuarioService usuarioService;
	@Test
	void contextLoads() throws Exception {

		Direccion direccion =new Direccion(1L,"Rio", 450,0,0);

		direccionService.save(direccion);
		Sucursal sucursal = new Sucursal(1L,direccion,"Sucursal Coto Las Heras");

		sucursalService.save(sucursal);

		Articulo articulo = new Articulo(1L,"Remera",50,0,150,144,sucursal);

		articuloService.save(articulo);

		Usuario usuario = new Usuario(1L,"awdaerv","45dc",Rol.CLIENTE);

		usuarioService.save(usuario);

		Cliente cliente = new Cliente(1L,"adjnaed",18,"a","b",usuario);

		cliente.agregarDireccion(direccion);

		clienteService.save(cliente);

		Pedido pedido = new Pedido(1L,cliente,true ,0,sucursal);


		pedidoService.save(pedido);

		DetallePedido detallePedido1 = new DetallePedido(1L,7,articulo,pedido,0);

		detallePedidoService.save(detallePedido1);

		detallePedido1 = new DetallePedido(1L, 0, articulo,pedido,0);

		detallePedidoService.update(1L, detallePedido1);



	}



}
