package com.comercio_electrico.dao;

import com.comercio_electrico.entity.Cliente;
import com.comercio_electrico.entity.Producto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductoDao extends ReactiveCrudRepository<Producto, Integer> {
}
