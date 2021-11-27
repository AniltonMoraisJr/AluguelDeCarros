package edu.unifacef.aluguelDeCarros.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.unifacef.aluguelDeCarros.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class Pessoa extends Audit {
    @NotBlank
    private String nome;

    private Status status;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String carteiraDeHabilitacao;

    @NotBlank
    private String cpf;

    @NotBlank
    private String rg;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotBlank
    private LocalDate dataDeNascimento;

    private String telefone;

    private Endereco endereco;


}
