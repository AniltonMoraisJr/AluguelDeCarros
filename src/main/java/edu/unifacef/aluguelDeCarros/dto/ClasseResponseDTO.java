package edu.unifacef.aluguelDeCarros.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.unifacef.aluguelDeCarros.domain.Classe;
import edu.unifacef.aluguelDeCarros.enums.ClassificacaoCarro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasseResponseDTO {
    private String idClasse;
    @NotBlank
    private ClassificacaoCarro classificacaoCarro;
    @NotBlank
    private Double precoDiaria;
    @NotBlank
    private Double precoSemanal;
    @NotBlank
    private Double precoMensal;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    public ClasseResponseDTO(Classe classe) {
        this.idClasse = classe.getId();
        this.classificacaoCarro = classe.getClassificacaoCarro();
        this.precoDiaria = classe.getPrecoDiaria();
        this.precoMensal = classe.getPrecoMensal();
        this.precoSemanal = classe.getPrecoSemanal();
        this.createdDate = classe.getCreatedDate();
        this.lastModifiedDate = classe.getLastModifiedDate();
    }
}
