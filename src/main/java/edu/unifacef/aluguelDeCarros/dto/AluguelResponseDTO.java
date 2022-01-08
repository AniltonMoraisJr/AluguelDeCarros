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
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AluguelResponseDTO {

    private String id;

    private Carro carro;

    private Empresa empresaSaida;
    private Empresa empresaChegada;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataSaida;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDevolucao;
    private Double taxaEntrega;
    private Long odometroSaida;
    private Long odometroDevolucao;
    private Long volumeTanqueDevolucao; //%0, %25, %50
    private AluguelStatus status; //aberto, em andamento, finalizado
    private EstadoCarro estadoCarroSaida;
    private EstadoCarro estadoCarroChegada;

    public AluguelResponseDTO(Aluguel aluguel) {
        this.id = aluguel.getId();
        this.carro = aluguel.getCarro();
        this.empresaSaida = aluguel.getEmpresaSaida();
        this.empresaChegada = aluguel.getEmpresaChegada();
        this.dataSaida = aluguel.getDataSaida();
        this.dataDevolucao = aluguel.getDataDevolucao();
        this.taxaEntrega = aluguel.getTaxaEntrega();
        this.odometroSaida = aluguel.getOdometroSaida();
        this.odometroDevolucao = aluguel.getOdometroDevolucao();
        this.volumeTanqueDevolucao = aluguel.getVolumeTanqueDevolucao();
        this.status = aluguel.getStatus();
        this.estadoCarroSaida = aluguel.getEstadoCarroSaida();
        this.estadoCarroChegada = aluguel.getEstadoCarroChegada();
    }
}
