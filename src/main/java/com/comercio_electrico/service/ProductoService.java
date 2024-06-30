package com.comercio_electrico.service;

import com.comercio_electrico.entity.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoService {

    Flux<Producto> findAll();

    Mono<Producto> findByCodigo(String codigo);

    Mono<Producto> registrarProducto(Producto producto);

    Mono<Producto> actualizarProducto(Producto producto);

    Mono<Producto> eliminarProducto(Integer idProducto);
}
