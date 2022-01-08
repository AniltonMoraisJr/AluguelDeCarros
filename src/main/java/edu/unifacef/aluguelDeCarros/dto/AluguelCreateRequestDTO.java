package edu.unifacef.aluguelDeCarros.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.unifacef.aluguelDeCarros.domain.Aluguel;
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
public class AluguelCreateRequestDTO {

    @NotBlank
    private String carroId;

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

    public Aluguel transformToDocument(){
        Aluguel aluguel = new Aluguel();
        aluguel.setDataSaida(this.dataSaida);
        aluguel.setOdometroSaida(this.getOdometroSaida());
        aluguel.setEstadoCarroSaida(this.getEstadoCarroSaida());
        return aluguel;
    }
}
