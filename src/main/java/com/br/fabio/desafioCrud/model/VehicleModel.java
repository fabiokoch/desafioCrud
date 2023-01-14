package com.br.fabio.desafioCrud.model;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "vehicle")
public class VehicleModel implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "vehicle_sequence";

    @Id
    private String id;

    private String brand;

    private String model;

    private Boolean available;
}
