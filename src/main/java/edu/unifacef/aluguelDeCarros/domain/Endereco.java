package edu.unifacef.aluguelDeCarros.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @NotBlank
    private String cep;

    @NotBlank
    private String rua;

    @NotBlank
    private String cidade;

    @NotBlank
    private String bairro;

    @NotBlank
    private String estado;

    @NotBlank
    private String pais;

    @PositiveOrZero
    private Integer numero;

    private String complemento;
}
