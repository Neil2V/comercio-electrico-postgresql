package com.comercio_electrico.dao;

import com.comercio_electrico.entity.Cliente;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ClienteDao extends ReactiveCrudRepository<Cliente, Integer> {

    Mono<Cliente> findByDni(String dni);
}
