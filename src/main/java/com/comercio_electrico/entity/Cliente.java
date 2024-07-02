package com.comercio_electrico.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(value = "cliente")
public class Cliente implements Persistable<Integer> {

    @Id
    @Column(value = "idCliente")
    private Integer idCliente;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(value = "fchRegistro")
    private LocalDate fchRegistro;

    private String nombre;

    private String apellido;

    private String telefono;

    private String dni;

    @Transient
    private boolean nuevo;

    @Override
    public Integer getId() {
        return idCliente;
    }

    @Override
    public boolean isNew() {
        return nuevo;
    }
}
