package edu.unifacef.aluguelDeCarros.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.unifacef.aluguelDeCarros.domain.Aluguel;
import edu.unifacef.aluguelDeCarros.domain.Carro;
import edu.unifacef.aluguelDeCarros.domain.Empresa;
import edu.unifacef.aluguelDeCarros.enums.EstadoCarro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AluguelRequestDTO {

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

    public Aluguel transformToDocument(){
        Aluguel aluguel = new Aluguel();
        aluguel.setCarro(this.getCarro());
        aluguel.setEmpresaSaida(this.getEmpresaSaida());
        aluguel.setEmpresaChegada(this.getEmpresaChegada());
        aluguel.setDataSaida(this.dataSaida);
        aluguel.setDataDevolucao(this.dataDevolucao);
        aluguel.setTaxaEntrega(this.getTaxaEntrega());
        aluguel.setOdometroSaida(this.getOdometroSaida());
        aluguel.setOdometroDevolucao(this.getOdometroDevolucao());
        aluguel.setVolumeTanqueDevolucao(this.getVolumeTanqueDevolucao());
        aluguel.setStatusAluguel(this.getStatusAluguel());
        aluguel.setEstadoCarroSaida(this.getEstadoCarroSaida());
        aluguel.setEstadoCarroChegada(this.getEstadoCarroChegada());
        return aluguel;
    }
}
