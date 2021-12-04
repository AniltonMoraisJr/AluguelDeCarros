package edu.unifacef.aluguelDeCarros.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.unifacef.aluguelDeCarros.domain.Cliente;
import edu.unifacef.aluguelDeCarros.domain.Empresa;
import edu.unifacef.aluguelDeCarros.domain.Endereco;
import edu.unifacef.aluguelDeCarros.domain.Funcionario;
import edu.unifacef.aluguelDeCarros.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioResponseDTO {
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;
    private String nome;
    private String funcao;
    private Status status;
    private String email;
    private String carteiraDeHabilitacao;
    private String cpf;
    private String rg;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDeNascimento;
    private String telefone;
    private Endereco endereco;
    private Empresa empresa;

    public FuncionarioResponseDTO(Funcionario f) {
        this.id = f.getId();
        this.createdDate = f.getCreatedDate();
        this.lastModifiedDate = f.getLastModifiedDate();
        this.nome = f.getNome();
        this.funcao = f.getFuncao();
        this.empresa = f.getEmpresa();
        this.status = f.getStatus();
        this.email = f.getEmail();
        this.carteiraDeHabilitacao = f.getCarteiraDeHabilitacao();
        this.cpf = f.getCpf();
        this.rg = f.getRg();
        this.dataDeNascimento = f.getDataDeNascimento();
        this.telefone = f.getTelefone();
        this.endereco = f.getEndereco();
    }

}
