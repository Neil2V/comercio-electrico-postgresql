package com.comercio_electrico.service.impl;

import com.comercio_electrico.dao.ClienteDao;
import com.comercio_electrico.entity.Cliente;
import com.comercio_electrico.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    @Override
    public Mono<Cliente> registrarCliente(Cliente cliente) {
        return findByDni(cliente.getDni())
                .flatMap(e -> {
                    e.setNuevo(false);
                    return Mono.just(e);
                })
                .switchIfEmpty(Mono.just(cliente)).flatMap(e -> {
                    //e.setNuevo(true);
                    e.setFchRegistro(LocalDate.now());
                    return clienteDao.save(e);
                });
    }

    @Override
    public Mono<Cliente> actualizarCliente(Cliente cliente) {
        return findByDni(cliente.getDni())
                .flatMap(e -> {
                    return clienteDao.save(e);
                });
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
