package edu.unifacef.aluguelDeCarros.enums;

public enum ClassificacaoCarro {
    SUBCOMPACTO(1L,"SUBCOMPACTO"),COMPACTO(2L,"COMPACTO"),SEDAN(3L,"SEDAN"),LUXO(4L,"LUXO"),ECONOMICO(5L,"ECONOMICO");

    private Long idClasse;
    private String descricao;

    ClassificacaoCarro(Long id, String descricao){
        this.idClasse = id;
        this.descricao = descricao;
    }

    public Long getIdClasse() {
        return idClasse;
    }

    public String getDescricao() {
        return descricao;
    }
}
