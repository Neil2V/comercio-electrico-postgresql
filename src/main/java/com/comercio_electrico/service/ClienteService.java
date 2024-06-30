package com.comercio_electrico.service;

import com.comercio_electrico.entity.Cliente;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteService {

    Mono<Void> registrarCliente(Cliente cliente);

    Mono<Cliente> findByDni(String dni);

    Flux<Cliente> findAll();
}
