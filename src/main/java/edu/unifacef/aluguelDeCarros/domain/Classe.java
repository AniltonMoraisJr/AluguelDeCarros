package edu.unifacef.aluguelDeCarros.domain;

import edu.unifacef.aluguelDeCarros.enums.ClassificacaoCarro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document
public class Classe extends Audit implements Serializable {
    @NotBlank
    private ClassificacaoCarro classificacaoCarro;

    @NotBlank
    private Double precoDiaria;

    @NotBlank
    private Double precoSemanal;

    @NotBlank
    private Double precoMensal;

    public Classe() {
    }
}
