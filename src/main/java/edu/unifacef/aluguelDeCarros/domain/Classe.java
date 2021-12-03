package edu.unifacef.aluguelDeCarros.domain;

import edu.unifacef.aluguelDeCarros.enums.ClassificacaoCarro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document
public class Classe implements Serializable {

    private Long idClasse;
    private ClassificacaoCarro classificacaoCarro;
    private Double precoDiaria;
    private Double precoSemanal;
    private Double precoMensal;

    public Classe() {
    }
}
