package com.comercio_electrico.service.impl;

import com.comercio_electrico.dao.ClienteDao;
import com.comercio_electrico.entity.Cliente;
import com.comercio_electrico.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    @Override
    public Mono<Void> registrarCliente(Cliente cliente) {
        return findByDni(cliente.getDni())
                .switchIfEmpty(Mono.just(cliente)).flatMap(e -> {
                    e.setFchRegistro(LocalDate.now());
                    return clienteDao.save(e);
                })
                .then();
    }

    @Override
    public Mono<Cliente> findByDni(String dni) {
        return clienteDao.findByDni(dni);
    }

    @Override
    public Flux<Cliente> findAll() {
        return clienteDao.findAll();
    }
}
