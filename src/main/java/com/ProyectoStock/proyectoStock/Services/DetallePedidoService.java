package com.ProyectoStock.proyectoStock.Services;

import com.ProyectoStock.proyectoStock.Entities.Articulo;
import com.ProyectoStock.proyectoStock.Entities.DetallePedido;
import com.ProyectoStock.proyectoStock.Entities.Pedido;
import com.ProyectoStock.proyectoStock.Repositories.ArticuloRepository;
import com.ProyectoStock.proyectoStock.Repositories.DetallePedidoRepository;
import com.ProyectoStock.proyectoStock.Repositories.GenericRepository;
import com.ProyectoStock.proyectoStock.Repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DetallePedidoService extends BaseService<DetallePedido, Long>{
    @Autowired
    public DetallePedidoService(GenericRepository<DetallePedido, Long> repository) {
        this.repository =  repository;
    }

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private PedidoService pedidoService;


    @Override
    @Transactional
    public DetallePedido save(DetallePedido entity) throws Exception {
        try {

            if (pedidoRepository.existsById(entity.getPedido().getId())){



                if (articuloRepository.existsById(entity.getArticulo().getId())){



                    Articulo articulo = articuloService.findById(entity.getArticulo().getId());

                    articulo.retirarArticulos(entity.getCantidad());
                    articuloService.update(articulo.getId(), articulo);

                    entity.setSubTotal(entity.getCantidad()*articulo.getPrecio());
                    System.out.println(entity.getSubTotal());
                    System.out.println(entity.getCantidad());
                    System.out.println(articulo.getPrecio());

                    Pedido pedido = pedidoService.findById(entity.getPedido().getId());
                    pedido.setTotal(pedido.getTotal()+entity.getSubTotal());
                    pedidoService.update(pedido.getId(),pedido);


                    return repository.save(entity);

                }else {
                    throw new Exception("NO EXISTE UN ARTICULO CON ESE ID");
                }



            }else {
                throw new Exception("NO EXISTE UN PEDIDO CON ESE ID");
            }




        }catch (Exception e){throw new Exception(e.getMessage());}
    }



    @Transactional
    public List<DetallePedido> deleteById(Long id) throws Exception{

        try {

            if (repository.existsById(id)){

                DetallePedido detallePedido = repository.getReferenceById(id);

                Articulo articulo = articuloRepository.getReferenceById(detallePedido.getArticulo().getId());

                articulo.agregarArticulos(detallePedido.getCantidad());

                articuloService.update(detallePedido.getArticulo().getId(), articulo);

                Pedido pedido = pedidoRepository.getReferenceById(detallePedido.getPedido().getId());

                pedido.setTotal(pedido.getTotal() - detallePedido.getSubTotal());

                pedidoService.update(detallePedido.getPedido().getId(), pedido);

                repository.deleteById(id);


            }else {
                throw new Exception("ESTE ID NO EXISTE EN LOS DETALLES PEDIDO");
            }

            return repository.findAll();

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }



    @Transactional
    public DetallePedido update(Long id, DetallePedido nuevoDetalle) throws Exception {

            if (repository.existsById(id)) {



                Optional<DetallePedido> detalleExistente2 = detallePedidoRepository.findById(id);

                DetallePedido detalleExistente = detalleExistente2.get();

                boolean articulosDistintos = false;

                boolean pedidosDistintos = false;

                if (nuevoDetalle.getArticulo() != null) {
                    articulosDistintos = !Objects.equals(detalleExistente.getArticulo().getId(),
                            nuevoDetalle.getArticulo().getId());
                }
                if (nuevoDetalle.getPedido() != null) {
                     pedidosDistintos = !Objects.equals(detalleExistente.getPedido().getId(), nuevoDetalle.getPedido().getId());
                }
                boolean cantidadDistintas = detalleExistente.getCantidad() != nuevoDetalle.getCantidad() && nuevoDetalle.getCantidad() != 0;


                if (articulosDistintos && pedidosDistintos && cantidadDistintas){

                    allDiferent(detalleExistente,nuevoDetalle);

                } else if (articulosDistintos && cantidadDistintas) {

                    cantAndArt(detalleExistente,nuevoDetalle);

                } else if (pedidosDistintos && cantidadDistintas) {

                    cantAndPed(detalleExistente,nuevoDetalle);

                } else if (pedidosDistintos&&articulosDistintos) {

                    pedAndArt(detalleExistente,nuevoDetalle);

                } else if (cantidadDistintas && !pedidosDistintos && !articulosDistintos){

                    cant(detalleExistente,nuevoDetalle);

                } else if (pedidosDistintos && !cantidadDistintas && !articulosDistintos) {

                    ped(detalleExistente,nuevoDetalle);

                }else if (articulosDistintos && !cantidadDistintas && !pedidosDistintos){

                    art(detalleExistente,nuevoDetalle);

                }


                detalleExistente = super.update(detalleExistente.getId(),nuevoDetalle);

                Articulo articulo = articuloService.findById(detalleExistente.getArticulo().getId());

                detalleExistente.setSubTotal(articulo.getPrecio()*detalleExistente.getCantidad());

                return repository.save(detalleExistente);




            }else {
                throw new Exception("EL DETALLE PEDIDO CON ESE ID NO EXISTE");
            }

    }


    private void allDiferent(DetallePedido detalleExistente, DetallePedido nuevoDetalle) throws Exception {
        try{

            if (!articuloRepository.existsById(nuevoDetalle.getArticulo().getId())){
                throw new Exception("ESTE ID ARTICULO NO EXISTE");
            } else if (!pedidoRepository.existsById(nuevoDetalle.getPedido().getId())) {
                throw new Exception("ESTE ID PEDIDO NO EXISTE");
            }

            Pedido pedido = pedidoService.findById(detalleExistente.getPedido().getId());

            pedido.setTotal(pedido.getTotal() - detalleExistente.getSubTotal());

            pedidoService.update(pedido.getId(), pedido);

            Articulo articulo = articuloService.findById(detalleExistente.getArticulo().getId());
            articulo.agregarArticulos(detalleExistente.getCantidad());
            articuloService.update(articulo.getId(),articulo);

            Articulo articuloN = articuloService.findById(nuevoDetalle.getArticulo().getId());
            articuloN.retirarArticulos(nuevoDetalle.getCantidad());
            articuloService.update(articuloN.getId(),articuloN);



            Pedido pedidoN = pedidoService.findById(nuevoDetalle.getPedido().getId());

            pedidoN.setTotal(pedidoN.getTotal()+(nuevoDetalle.getCantidad()*nuevoDetalle.getArticulo().getPrecio()));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    private void cantAndArt(DetallePedido detalleExistente, DetallePedido nuevoDetalle) throws Exception {
        try{

            if (!articuloRepository.existsById(nuevoDetalle.getArticulo().getId())){
                throw new Exception("ESTE ID ARTICULO NO EXISTE");
            }

            Articulo articulo = articuloService.findById(detalleExistente.getArticulo().getId());

            articulo.agregarArticulos(detalleExistente.getCantidad());

            articuloService.update(articulo.getId(),articulo);

            Articulo articuloN = articuloService.findById(nuevoDetalle.getArticulo().getId());

            articuloN.retirarArticulos(nuevoDetalle.getCantidad());

            articuloService.update(articuloN.getId(),articuloN);

            Pedido pedido = pedidoService.findById(detalleExistente.getPedido().getId());
            pedido.setTotal(pedido.getTotal()-detalleExistente.getSubTotal());
            pedido.setTotal(pedido.getTotal()+(articuloN.getPrecio()*nuevoDetalle.getCantidad()));
            pedidoService.update(pedido.getId(),pedido);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }

    private void cantAndPed(DetallePedido detalleExistente, DetallePedido nuevoDetalle) throws Exception {
        try{

            if (!pedidoRepository.existsById(nuevoDetalle.getPedido().getId())) {
                throw new Exception("ESTE ID PEDIDO NO EXISTE");
            }
            Articulo articulo = articuloService.findById(detalleExistente.getArticulo().getId());
            articulo.agregarArticulos(detalleExistente.getCantidad());
            articulo.retirarArticulos(nuevoDetalle.getCantidad());
            articuloService.update(articulo.getId(),articulo);

            Pedido pedido = pedidoService.findById(detalleExistente.getPedido().getId());
            pedido.setTotal(pedido.getTotal()-detalleExistente.getSubTotal());
            pedidoService.update(pedido.getId(),pedido);

            Pedido pedidoN = pedidoService.findById(nuevoDetalle.getPedido().getId());
            pedidoN.setTotal(pedidoN.getTotal()+(detalleExistente.getArticulo().getPrecio()*nuevoDetalle.getCantidad()));
            pedidoService.update(pedidoN.getId(),pedidoN);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private void cant(DetallePedido detalleExistente, DetallePedido nuevoDetalle) throws Exception{
        try{
            Articulo articulo = articuloService.findById(detalleExistente.getArticulo().getId());
            System.out.println(detalleExistente.getCantidad());
            articulo.agregarArticulos(detalleExistente.getCantidad());
            articulo.retirarArticulos(nuevoDetalle.getCantidad());
            articuloService.update(articulo.getId(),articulo);

            Pedido pedido = pedidoService.findById(detalleExistente.getPedido().getId());
            pedido.setTotal(pedido.getTotal()-detalleExistente.getSubTotal());
            pedido.setTotal(pedido.getTotal()+(detalleExistente.getArticulo().getPrecio()*nuevoDetalle.getCantidad()));
            pedidoService.update(pedido.getId(),pedido);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    private void ped (DetallePedido detalleExistente, DetallePedido nuevoDetalle)throws Exception{

        try{

           if (!pedidoRepository.existsById(nuevoDetalle.getPedido().getId())) {
                throw new Exception("ESTE ID PEDIDO NO EXISTE");
           }

            Pedido pedido = pedidoService.findById(detalleExistente.getPedido().getId());
            pedido.setTotal(pedido.getTotal()-detalleExistente.getSubTotal());
            pedidoService.update(pedido.getId(),pedido);

            Pedido pedidoN = pedidoService.findById(nuevoDetalle.getPedido().getId());
            pedidoN.setTotal(pedidoN.getTotal()+detalleExistente.getSubTotal());
            pedidoService.update(pedidoN.getId(), pedidoN);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    private void pedAndArt(DetallePedido detalleExistente, DetallePedido nuevoDetalle)throws Exception{
        try{

            if (!articuloRepository.existsById(nuevoDetalle.getArticulo().getId())){
                throw new Exception("ESTE ID ARTICULO NO EXISTE");
            } else if (!pedidoRepository.existsById(nuevoDetalle.getPedido().getId())) {
                throw new Exception("ESTE ID PEDIDO NO EXISTE");
            }

            Pedido pedido = pedidoService.findById(detalleExistente.getPedido().getId());
            Pedido pedidoN = pedidoService.findById(nuevoDetalle.getPedido().getId());

            Articulo articulo = articuloService.findById(detalleExistente.getArticulo().getId());
            Articulo articuloN = articuloService.findById(nuevoDetalle.getArticulo().getId());

            pedido.setTotal(pedido.getTotal()-detalleExistente.getSubTotal());
            pedidoN.setTotal(pedidoN.getTotal()+(articuloN.getPrecio()*detalleExistente.getCantidad()));
            pedidoService.update(pedido.getId(),pedido);pedidoService.update(pedidoN.getId(),pedidoN);

            articulo.agregarArticulos(detalleExistente.getCantidad());
            articuloN.retirarArticulos(detalleExistente.getCantidad());

            articuloService.update(articulo.getId(),articulo);
            articuloService.update(articuloN.getId(),articuloN);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }

    private void art(DetallePedido detalleExistente, DetallePedido nuevoDetalle)throws Exception{
        try{

            if (!articuloRepository.existsById(nuevoDetalle.getArticulo().getId())){
                throw new Exception("ESTE ID ARTICULO NO EXISTE");
            }
            Articulo articulo = articuloService.findById(detalleExistente.getArticulo().getId());
            articulo.agregarArticulos(detalleExistente.getCantidad());
            articuloService.update(articulo.getId(),articulo);

            Articulo articuloN = articuloService.findById(nuevoDetalle.getArticulo().getId());
            articuloN.retirarArticulos(detalleExistente.getCantidad());
            articuloService.update(articuloN.getId(),articuloN);

            Pedido pedido = pedidoService.findById(detalleExistente.getPedido().getId());

            pedido.setTotal(pedido.getTotal()-(articulo.getPrecio()*detalleExistente.getCantidad()));
            pedido.setTotal(pedido.getTotal()+(articuloN.getPrecio()*detalleExistente.getCantidad()));
            pedidoService.update(pedido.getId(),pedido);



        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }




}
