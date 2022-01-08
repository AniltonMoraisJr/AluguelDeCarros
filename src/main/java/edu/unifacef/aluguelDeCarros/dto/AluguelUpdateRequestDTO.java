package edu.unifacef.aluguelDeCarros.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.unifacef.aluguelDeCarros.domain.Aluguel;
import edu.unifacef.aluguelDeCarros.domain.Carro;
import edu.unifacef.aluguelDeCarros.domain.Empresa;
import edu.unifacef.aluguelDeCarros.enums.AluguelStatus;
import edu.unifacef.aluguelDeCarros.enums.EstadoCarro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AluguelUpdateRequestDTO {
    @NotBlank
    private String carroId;

    @NotNull
    private AluguelStatus status; //em andamento, finalizado

    // Saida

    @NotBlank
    private String empresaSaidaId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate dataSaida;

    @NotNull
    @PositiveOrZero
    private Long odometroSaida;

    @NotNull
    private EstadoCarro estadoCarroSaida;

    // Chegada

    @NotBlank
    private String empresaChegadaId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate dataDevolucao;

    private Double taxaEntrega;

    @NotNull
    @PositiveOrZero
    private Long odometroDevolucao;

    private Long volumeTanqueDevolucao; //%0, %25, %50

    @NotNull
    private EstadoCarro estadoCarroChegada;

    public Aluguel transformToDocument(Aluguel aluguel){
        aluguel.setDataSaida(this.dataSaida);
        aluguel.setOdometroSaida(this.getOdometroSaida());
        aluguel.setEstadoCarroSaida(this.getEstadoCarroSaida());
        aluguel.setDataSaida(this.dataSaida);
        aluguel.setDataDevolucao(this.dataDevolucao);
        aluguel.setTaxaEntrega(this.getTaxaEntrega());
        aluguel.setOdometroDevolucao(this.getOdometroDevolucao());
        aluguel.setVolumeTanqueDevolucao(this.getVolumeTanqueDevolucao());
        aluguel.setStatus(this.getStatus());
        aluguel.setEstadoCarroChegada(this.getEstadoCarroChegada());
        return aluguel;
    }
}
