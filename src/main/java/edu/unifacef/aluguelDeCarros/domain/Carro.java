package edu.unifacef.aluguelDeCarros.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document
public class Carro {
    private Long idCarro;
    private String marca;
    private String modelo;
    private Long ano;
    private String cor;
    private String placa;
    private Classe classe;

    public Carro(){

    }
}
