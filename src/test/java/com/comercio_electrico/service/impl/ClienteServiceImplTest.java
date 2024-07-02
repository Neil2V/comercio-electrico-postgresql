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
import reactor.core.publisher.Mono;

import java.time.LocalDate;

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

            when(clienteDao.findByDni(cliente.getDni())).thenReturn(Mono.just(cliente));

            Mono<Cliente> resultado = clienteService.registrarCliente(cliente);

            verify(clienteDao).findByDni(any(String.class));

            resultado.subscribe(e -> {
                assert e.getIdCliente().equals(cliente.getIdCliente());
            });

        }
    }
}
