package edu.unifacef.aluguelDeCarros.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document
public class Empresa extends Audit implements Serializable {

    @NotBlank
    private String nome;

    @NotBlank
    private String idMatriz;

    private Endereco endereco;

    @NotBlank
    private String telefone;

    private Double taxaDeEntrega;


    public Empresa() {

    }
}
