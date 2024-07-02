package com.comercio_electrico.service.impl;

import com.comercio_electrico.dao.ClienteDao;
import com.comercio_electrico.entity.Cliente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class ClienteServiceImplTest {

    @Mock
    private ClienteDao clienteDao;

    @Spy
    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Nested
    class registroCliente {

        @DisplayName("test para cliente que ya esta registrado")
        @Test
        public void registrarClienteCaso2() {

            Cliente cliente = Cliente.builder()
                    .idCliente(1)
                    .nombre("Neil")
                    .apellido("Vara")
                    .telefono("123456789")
                    .dni("741")
                    .nuevo(false)
                    .build();

            when(clienteService.findByDni(cliente.getDni())).thenReturn(Mono.just(cliente));

            Mono<Cliente> resultado = clienteService.registrarCliente(cliente);

            verify(clienteDao).findByDni(any(String.class));

            resultado.subscribe(e -> {
                assert e.getIdCliente().equals(cliente.getIdCliente());
            });

        }
    }

    @Nested
    class findByDni {

        @DisplayName("FindById  de un cliente registrado")
        @Test
        void findByDniCaso1() {
            Cliente cliente = Cliente.builder()
                    .idCliente(1)
                    .nombre("Neil")
                    .apellido("Vara")
                    .telefono("123456789")
                    .dni("741")
                    .nuevo(false)
                    .build();
            when(clienteDao.findByDni(anyString())).thenReturn(Mono.just(cliente));

            Mono<Cliente> resultado = clienteService.findByDni(anyString());

            resultado.subscribe(e -> {
                assert e.getDni().equals(cliente.getDni());
            });

        }

        @DisplayName("FindById  de un cliente no registrado")
        @Test
        void findByDniCaso2() {
            when(clienteDao.findByDni(anyString())).thenReturn(Mono.empty());

            Mono<Cliente> resultado = clienteService.findByDni(anyString());

            StepVerifier.create(resultado)
                    .expectNextCount(0)
                    .verifyComplete();
        }

    }

    @Test
    void findAll() {

        Cliente cliente = Cliente.builder()
                .idCliente(1)
                .nombre("Neil")
                .apellido("Vara")
                .telefono("123456789")
                .dni("741")
                .nuevo(false)
                .build();

        when(clienteDao.findAll()).thenReturn(Flux.fromIterable(Collections.singletonList(cliente)));

        Flux<Cliente> resultado = clienteService.findAll();

        StepVerifier.create(resultado)
                .expectNext(cliente)
                .verifyComplete();

    }
}
