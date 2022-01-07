package edu.unifacef.aluguelDeCarros.dto;

import edu.unifacef.aluguelDeCarros.domain.Empresa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String idMatriz;

    @NotBlank
    private String endereco;

    private Integer numero;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String CEP;

    @NotBlank
    private String estado;

    @NotBlank
    private String pais;

    @NotBlank
    private String telefone;

    private Double taxaDeEntrega;

    public Empresa transformToDocument(){

        Empresa empresa = new Empresa();

        empresa.setNome(this.nome);
        empresa.setIdMatriz(this.idMatriz);
        empresa.setEndereco(this.endereco);
        empresa.setNumero(this.numero);
        empresa.setBairro(this.bairro);
        empresa.setCidade(this.cidade);
        empresa.setCEP(this.CEP);
        empresa.setEstado(this.estado);
        empresa.setPais(this.pais);
        empresa.setTelefone(this.telefone);
        empresa.setTaxaDeEntrega(this.taxaDeEntrega);

        return empresa;
    }


}
