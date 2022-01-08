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
    private String id;

    private String marca;

    private String modelo;

    private Long ano;

    private String cor;

    private String placa;

    private Classe classe;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    public CarroResponseDTO(Carro carro) {
        this.id = carro.getId();
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
