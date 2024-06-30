package com.comercio_electrico.controller;

import com.comercio_electrico.entity.Cliente;
import com.comercio_electrico.entity.Producto;
import com.comercio_electrico.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin("*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping(path = "/findAll")
    public ResponseEntity<Flux<Producto>> findAll(){
        return new ResponseEntity<>(productoService.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/registrar")
    public ResponseEntity<Mono<Producto>> registro(@RequestBody Producto producto){
        producto.setNuevo(true);
        return new ResponseEntity<>(productoService.registrarProducto(producto), HttpStatus.OK);
    }

    @PutMapping(path = "/actualizar")
    public ResponseEntity<Mono<Producto>> actualizar(@RequestBody Producto producto){
        return new ResponseEntity<>(productoService.actualizarProducto(producto), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{idProducto}")
    public Mono<ResponseEntity<Producto>> eliminar(@PathVariable("idProducto") Integer idProducto){
        return productoService.eliminarProducto(idProducto)
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }
}
