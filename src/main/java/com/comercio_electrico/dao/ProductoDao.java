package com.comercio_electrico.dao;

import com.comercio_electrico.entity.Cliente;
import com.comercio_electrico.entity.Producto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ProductoDao extends ReactiveCrudRepository<Producto, Integer> {

    Mono<Producto> findByCodigo(String codigo);
}
