package com.comercio_electrico.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(value = "producto")
public class Producto implements Persistable<Integer> {

    @Id
    @Column(value = "idProducto")
    private Integer idProducto;
    private String nombre;
    private BigDecimal precio;
    private String descripcion;
    private String categoria;
    private Integer stock;
    private String codigo;

    @Transient
    private boolean nuevo;

    @Override
    public Integer getId() {
        return idProducto;
    }

    @Override
    public boolean isNew() {
        return nuevo;
    }
}
