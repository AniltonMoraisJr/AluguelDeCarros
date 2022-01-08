package edu.unifacef.aluguelDeCarros.dto;

import edu.unifacef.aluguelDeCarros.domain.Empresa;
import edu.unifacef.aluguelDeCarros.domain.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaResponseDTO {

    private String id;

    private String nome;

    private String idMatriz;

    private Endereco endereco;

    private String telefone;

    private Double taxaDeEntrega;

    public EmpresaResponseDTO(Empresa empresa) {

        this.id = empresa.getId();
        this.nome = empresa.getNome();
        this.idMatriz = empresa.getIdMatriz();
        this.endereco = empresa.getEndereco();
        this.telefone = empresa.getTelefone();
        this.taxaDeEntrega = empresa.getTaxaDeEntrega();

    }
}
