package edu.unifacef.aluguelDeCarros.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.unifacef.aluguelDeCarros.domain.Cliente;
import edu.unifacef.aluguelDeCarros.domain.Endereco;
import edu.unifacef.aluguelDeCarros.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;
    private String nome;
    private Status status;
    private String email;
    private String carteiraDeHabilitacao;
    private String cpf;
    private String rg;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDeNascimento;
    private String telefone;
    private Endereco endereco;

    public ClienteResponseDTO (Cliente c) {
        this.id = c.getId();
        this.createdDate = c.getCreatedDate();
        this.lastModifiedDate = c.getLastModifiedDate();
        this.nome = c.getNome();
        this.status = c.getStatus();
        this.email = c.getEmail();
        this.carteiraDeHabilitacao = c.getCarteiraDeHabilitacao();
        this.cpf = c.getCpf();
        this.rg = c.getRg();
        this.dataDeNascimento = c.getDataDeNascimento();
        this.telefone = c.getTelefone();
        this.endereco = c.getEndereco();
    }

}
