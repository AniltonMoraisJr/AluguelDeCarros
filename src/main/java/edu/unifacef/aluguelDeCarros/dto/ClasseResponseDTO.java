package edu.unifacef.aluguelDeCarros.dto;

import edu.unifacef.aluguelDeCarros.domain.Classe;
import edu.unifacef.aluguelDeCarros.enums.ClassificacaoCarro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasseResponseDTO {
    private Long idClasse;
    @NotBlank
    private ClassificacaoCarro classificacaoCarro;
    @NotBlank
    private Double precoDiaria;
    @NotBlank
    private Double precoSemanal;
    @NotBlank
    private Double precoMensal;

    public ClasseResponseDTO(Classe classe) {
        this.idClasse = classe.getIdClasse();
        this.classificacaoCarro = classe.getClassificacaoCarro();
        this.precoDiaria = classe.getPrecoDiaria();
        this.precoMensal = classe.getPrecoMensal();
        this.precoSemanal = classe.getPrecoSemanal();
    }
}
