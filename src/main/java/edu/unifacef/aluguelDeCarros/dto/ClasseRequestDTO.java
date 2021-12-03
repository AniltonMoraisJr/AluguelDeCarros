package edu.unifacef.aluguelDeCarros.dto;

import edu.unifacef.aluguelDeCarros.domain.Classe;
import edu.unifacef.aluguelDeCarros.domain.Cliente;
import edu.unifacef.aluguelDeCarros.enums.ClassificacaoCarro;
import edu.unifacef.aluguelDeCarros.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasseRequestDTO {
    private Long idClasse;
    @NotBlank
    private ClassificacaoCarro classificacaoCarro;
    @NotBlank
    private Double precoDiaria;
    @NotBlank
    private Double precoSemanal;
    @NotBlank
    private Double precoMensal;
    public Classe transformToDocument(){
        Classe c = new Classe();
        c.setIdClasse(this.idClasse);
        c.setClassificacaoCarro(this.classificacaoCarro);
        c.setPrecoDiaria(this.precoDiaria);
        c.setPrecoMensal(this.precoMensal);
        c.setPrecoSemanal(this.precoSemanal);
        return c;
    }

}
