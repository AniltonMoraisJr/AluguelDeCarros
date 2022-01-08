package edu.unifacef.aluguelDeCarros.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document
public class Carro extends Audit implements Serializable {
    @NotBlank
    private String marca;
    @NotBlank
    private String modelo;
    @NotBlank
    private Long ano;
    @NotBlank
    private String cor;
    @NotBlank
    private String placa;
    @NotBlank
    private Classe classe;

    public Carro(){
    }
}
