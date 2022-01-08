package edu.unifacef.aluguelDeCarros.dto;

import edu.unifacef.aluguelDeCarros.domain.Empresa;
import edu.unifacef.aluguelDeCarros.domain.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaRequestDTO {

    @NotBlank
    private String nome;

    private String idMatriz;

    @Valid
    private Endereco endereco;

    @NotBlank
    private String telefone;

    private Double taxaDeEntrega;

    public Empresa transformToDocument(){

        Empresa empresa = new Empresa();

        empresa.setNome(this.nome);
        empresa.setIdMatriz(this.idMatriz);
        empresa.setEndereco(this.endereco);
        empresa.setTelefone(this.telefone);
        empresa.setTaxaDeEntrega(this.taxaDeEntrega);

        return empresa;
    }


}
