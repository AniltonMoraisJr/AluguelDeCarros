package edu.unifacef.aluguelDeCarros.dto;

import edu.unifacef.aluguelDeCarros.domain.Classe;
import edu.unifacef.aluguelDeCarros.enums.ClassificacaoCarro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasseRequestDTO {
    @NotNull
    private ClassificacaoCarro classificacaoCarro;

    @NotNull
    @PositiveOrZero
    private Double precoDiaria;

    @NotNull
    @PositiveOrZero
    private Double precoSemanal;

    @NotNull
    @PositiveOrZero
    private Double precoMensal;

    public Classe transformToDocument(){
        Classe c = new Classe();
        c.setClassificacaoCarro(this.classificacaoCarro);
        c.setPrecoDiaria(this.precoDiaria);
        c.setPrecoMensal(this.precoMensal);
        c.setPrecoSemanal(this.precoSemanal);
        return c;
    }
}
