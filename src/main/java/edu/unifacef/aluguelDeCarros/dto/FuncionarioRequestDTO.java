package edu.unifacef.aluguelDeCarros.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.unifacef.aluguelDeCarros.domain.Empresa;
import edu.unifacef.aluguelDeCarros.domain.Endereco;
import edu.unifacef.aluguelDeCarros.domain.Funcionario;
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
public class FuncionarioRequestDTO {
    private String id;

    @NotBlank
    private String empresaId;

    @NotBlank
    private String nome;

    @NotBlank
    private String funcao;

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

    public Funcionario transformToDocument(){
        Funcionario f = new Funcionario();
        f.setNome(this.nome);
        f.setFuncao(this.funcao);
        f.setEmail(this.email);
        f.setCpf(this.cpf);
        f.setRg(this.rg);
        f.setCarteiraDeHabilitacao(this.carteiraDeHabilitacao);
        f.setDataDeNascimento(this.dataDeNascimento);
        f.setEndereco(this.endereco);
        f.setStatus(Status.Ativo);

        Empresa e = new Empresa();
        e.setId(this.empresaId);

        f.setEmpresa(e);
        return f;
    }
}
