package com.ProyectoStock.proyectoStock;

import com.ProyectoStock.proyectoStock.Entities.*;
import com.ProyectoStock.proyectoStock.Repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class ProyectoStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoStockApplication.class, args);
		System.out.println("APLICACION INICIADA");
	}

	@Bean
	@Transactional
	CommandLineRunner init(
			UsuarioRepository usuarioRepository,
			EmpleadoRepository empleadoRepository,
			DireccionRepository direccionRepository,
			SucursalRepository sucursalRepository,
			ClienteRepository clienteRepository,
			PedidoRepository pedidoRepository,
			ArticuloRepository articuloRepository,
			DetallePedidoRepository detallePedidoRepository) {

		return args -> {

			Usuario usuarioAdmin = new Usuario();
			usuarioAdmin.setName("Andy204");
			usuarioAdmin.setPassword("20042023");
			usuarioAdmin.setRolEmpleado(Rol.ADMINISTRADOR);
			usuarioRepository.save(usuarioAdmin);

			Usuario usuarioCliente = new Usuario();
			usuarioCliente.setName("AndyCliente");
			usuarioCliente.setPassword("2004");
			usuarioCliente.setRolEmpleado(Rol.CLIENTE);
			usuarioRepository.save(usuarioCliente);

			Direccion direccionSucursal = new Direccion();
			direccionSucursal.setCalle("Rio Juramento");
			direccionSucursal.setNumero(4785);
			direccionRepository.save(direccionSucursal);

			Direccion direccionEmpleado = new Direccion();
			direccionEmpleado.setCalle("Rio Juramento");
			direccionEmpleado.setNumero(4907);
			direccionRepository.save(direccionEmpleado);

			Sucursal sucursalPrueba = new Sucursal();
			sucursalPrueba.setNombre("Coto Central Las Heras");
			sucursalPrueba.setDireccion(direccionSucursal);
			sucursalRepository.save(sucursalPrueba);

			Empleado empleadoAdmin = new Empleado();
			empleadoAdmin.setUsuario(usuarioAdmin);
			empleadoAdmin.setNombre("Andrés");
			empleadoAdmin.setApellido("Bercich");
			empleadoAdmin.setEdad(20);
			empleadoAdmin.setSucursal(sucursalPrueba);
			empleadoAdmin.agregarDireccion(direccionEmpleado);
			empleadoRepository.save(empleadoAdmin);

			Cliente cliente = new Cliente();
			cliente.setNombre("Andy CLiente");
			cliente.setApellido("Bercich");
			cliente.setEdad(25);
			cliente.setUsuario(usuarioCliente);
			clienteRepository.save(cliente);

			Pedido pedido = new Pedido();
			pedido.setCliente(cliente);
			pedido.setPagado(true);
			pedido.setTotal(10);
			pedido.setSucursal(sucursalPrueba);
			pedidoRepository.save(pedido);

			Pedido pedido1 = new Pedido();
			pedido1.setCliente(cliente);
			pedido1.setPagado(true);
			pedido1.setTotal(10);
			pedido1.setSucursal(sucursalPrueba);
			pedidoRepository.save(pedido1);


			Articulo articulo = new Articulo();
			articulo.setStock(100);
			articulo.setNombre("Remera");
			articulo.setGramos(155);
			articulo.setPrecio(155);
			articulo.setSucursal(sucursalPrueba);
			articuloRepository.save(articulo);


			Articulo articulo1 = new Articulo();
			articulo1.setStock(10);
			articulo1.setNombre("Pantalon");
			articulo1.setGramos(155);
			articulo1.setPrecio(800);
			articulo1.setSucursal(sucursalPrueba);
			articuloRepository.save(articulo1);




		};



	}
}
