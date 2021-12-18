package edu.unifacef.aluguelDeCarros.dto;

import edu.unifacef.aluguelDeCarros.domain.Carro;
import edu.unifacef.aluguelDeCarros.domain.Classe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarroResponseDTO {
    private Long idCarro;
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
    private Classe classe;;

    public CarroResponseDTO(Carro carro) {
        this.idCarro = carro.getIdCarro();
        this.marca = carro.getMarca();
        this.modelo = carro.getModelo();
        this.ano = carro.getAno();
        this.cor = carro.getCor();
        this.placa = carro.getPlaca();
        this.classe = carro.getClasse();
    }
}
