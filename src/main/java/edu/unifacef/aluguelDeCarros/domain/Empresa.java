package edu.unifacef.aluguelDeCarros.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document
public class Empresa extends Audit implements Serializable {

    @NotBlank
    private String Nome;

    @NotBlank
    private String idMatriz;

    @NotBlank
    private String endereco;

    private Integer numero;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String CEP;

    @NotBlank
    private String estado;

    @NotBlank
    private String pais;

    @NotBlank
    private String telefone;

    private Double taxaDeEntrega;


    public Empresa() {

    }
}
