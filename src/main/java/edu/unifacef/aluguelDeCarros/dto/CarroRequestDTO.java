package edu.unifacef.aluguelDeCarros.dto;

import edu.unifacef.aluguelDeCarros.domain.Carro;
import edu.unifacef.aluguelDeCarros.domain.Classe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarroRequestDTO {

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotNull
    @Positive
    @Min(1940)
    private Long ano;

    @NotBlank
    private String cor;

    @NotBlank
    private String placa;

    @NotBlank
    private String idClasse;

    public Carro transformToDocument(){
        Carro c = new Carro();
        c.setAno(this.ano);
        c.setCor(this.cor);
        c.setMarca(this.marca);
        c.setModelo(this.modelo);
        c.setPlaca(this.placa);
        return c;
    }
}
