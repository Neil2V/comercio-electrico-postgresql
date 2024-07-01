package com.comercio_electrico.service.impl;

import com.comercio_electrico.dao.ProductoDao;
import com.comercio_electrico.entity.Cliente;
import com.comercio_electrico.entity.Producto;
import com.comercio_electrico.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Override
    public Flux<Producto> findAll() {
        return productoDao.findAll();
    }

    @Override
    public Mono<Producto> registrarProducto(Producto producto) {
        return productoDao.findByCodigo(producto.getCodigo())
                .flatMap(e -> {
                    e.setNuevo(false);
                    return Mono.just(e);
                })
                .switchIfEmpty(Mono.just(producto)).flatMap(e -> {
                    return productoDao.save(e);
                });
    }

    @Override
    public Mono<Producto> actualizarProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto())
                .flatMap(e -> {
                    e.setNombre(producto.getNombre());
                    e.setPrecio(producto.getPrecio());
                    e.setDescripcion(producto.getDescripcion());
                    e.setCategoria(producto.getCategoria());
                    e.setCodigo(producto.getCodigo());
                    e.setStock(producto.getStock());
                    return productoDao.save(e);
                });
    }

    @Override
    public Mono<Producto> findByCodigo(String codigo) {
        return productoDao.findByCodigo(codigo);
    }

    @Override
    public Mono<Producto> eliminarProducto(Integer idProducto) {
        return productoDao.findById(idProducto)
                .flatMap(e -> productoDao.deleteById(idProducto).then(Mono.just(e)));
    }
}
