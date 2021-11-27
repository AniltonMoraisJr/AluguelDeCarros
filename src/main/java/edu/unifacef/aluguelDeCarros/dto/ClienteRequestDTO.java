package edu.unifacef.aluguelDeCarros.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.unifacef.aluguelDeCarros.domain.Cliente;
import edu.unifacef.aluguelDeCarros.domain.Endereco;
import edu.unifacef.aluguelDeCarros.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO {
    private String id;

    @NotBlank
    private String nome;

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
    private LocalDate dataDeNascimento;

    private String telefone;

    @Valid
    private Endereco endereco;

    public Cliente transformToDocument(){
        Cliente c = new Cliente();
        c.setNome(this.nome);
        c.setEmail(this.email);
        c.setCpf(this.cpf);
        c.setRg(this.rg);
        c.setCarteiraDeHabilitacao(this.carteiraDeHabilitacao);
        c.setDataDeNascimento(this.dataDeNascimento);
        c.setEndereco(this.endereco);
        c.setStatus(Status.Ativo);
        return c;
    }
}
