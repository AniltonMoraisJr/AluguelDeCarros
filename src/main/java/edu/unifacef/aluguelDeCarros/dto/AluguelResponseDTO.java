package edu.unifacef.aluguelDeCarros.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.unifacef.aluguelDeCarros.domain.Aluguel;
import edu.unifacef.aluguelDeCarros.domain.Carro;
import edu.unifacef.aluguelDeCarros.domain.Empresa;
import edu.unifacef.aluguelDeCarros.enums.EstadoCarro;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class AluguelResponseDTO {

    private String id;
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
        this.statusAluguel = aluguel.getStatusAluguel();
        this.estadoCarroSaida = aluguel.getEstadoCarroSaida();
        this.estadoCarroChegada = aluguel.getEstadoCarroChegada();
    }
}
