package com.comercio_electrico.controller;

import com.comercio_electrico.entity.Cliente;
import com.comercio_electrico.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping(path = "/findAll")
    public ResponseEntity<Flux<Cliente>> findAll(){
        return new ResponseEntity<>(clienteService.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/registrar")
    public ResponseEntity<Mono<Cliente>> registro(@RequestBody Cliente cliente){
        cliente.setNuevo(true);
        return new ResponseEntity<>(clienteService.registrarCliente(cliente), HttpStatus.OK);
    }
}
