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
public class CarroRequestDTO {
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

    public Carro transformToDocument(){
        Carro c = new Carro();
        c.setAno(this.ano);
        c.setCor(this.cor);
        c.setMarca(this.marca);
        c.setModelo(this.modelo);
        c.setPlaca(this.placa);
        c.setClasse(this.classe);
        return c;
    }
}
