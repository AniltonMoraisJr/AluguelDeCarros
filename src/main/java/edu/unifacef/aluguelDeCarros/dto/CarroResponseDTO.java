package edu.unifacef.aluguelDeCarros.dto;

import edu.unifacef.aluguelDeCarros.domain.Classe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarroResponseDTO {
    private Long idClasse;
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
}
