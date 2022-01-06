package edu.unifacef.aluguelDeCarros.dto;

import edu.unifacef.aluguelDeCarros.domain.Empresa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaResponseDTO {

    private String id;

    @NotBlank
    private String nome;

    @NotBlank
    private String idMatriz;

    @NotBlank
    private String endereco;

    @NotBlank
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

    @NotBlank
    private Double taxaDeEntrega;

    public EmpresaResponseDTO(Empresa empresa) {

        this.id = empresa.getId();
        this.nome = empresa.getNome();
        this.idMatriz = empresa.getIdMatriz();
        this.endereco = empresa.getEndereco();
        this.numero = empresa.getNumero();
        this.bairro = empresa.getBairro();
        this.cidade = empresa.getCidade();
        this.CEP = empresa.getCEP();
        this.estado = empresa.getEstado();
        this.pais = empresa.getPais();
        this.telefone = empresa.getTelefone();
        this.taxaDeEntrega = empresa.getTaxaDeEntrega();

    }
}
