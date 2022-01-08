package edu.unifacef.aluguelDeCarros.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.unifacef.aluguelDeCarros.enums.EstadoCarro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document
public class Aluguel extends Audit implements Serializable {
    @NotBlank
    private Carro carro;
    @NotBlank
    private Empresa empresaSaida;
    private Empresa empresaChegada;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotBlank
    private LocalDate dataSaida;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDevolucao;
    @NotBlank
    private Double taxaEntrega;
    @NotBlank
    private Long odometroSaida;
    private Long odometroDevolucao;
    private Long volumeTanqueDevolucao; //%0, %25, %50
    @NotBlank
    private String statusAluguel; //aberto, em andamento, finalizado
    @NotBlank
    private EstadoCarro estadoCarroSaida;
    private EstadoCarro estadoCarroChegada;

    public Aluguel() {
    }
}
