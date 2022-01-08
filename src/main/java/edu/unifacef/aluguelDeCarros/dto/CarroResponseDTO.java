package edu.unifacef.aluguelDeCarros.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.unifacef.aluguelDeCarros.domain.Carro;
import edu.unifacef.aluguelDeCarros.domain.Classe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarroResponseDTO {
    private String idCarro;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    public CarroResponseDTO(Carro carro) {
        this.idCarro = carro.getId();
        this.marca = carro.getMarca();
        this.modelo = carro.getModelo();
        this.ano = carro.getAno();
        this.cor = carro.getCor();
        this.placa = carro.getPlaca();
        this.classe = carro.getClasse();
        this.createdDate = carro.getCreatedDate();
        this.lastModifiedDate = carro.getLastModifiedDate();
    }
}
