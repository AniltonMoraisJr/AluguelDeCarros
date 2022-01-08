package edu.unifacef.aluguelDeCarros.enums;

public enum EstadoCarro {
    EXECELENTE(1L, "EXECELENTE"), BOM(2L,"BOM"),RASOAVEL(3L,"RASOAVEL"), RUIM(4L,"RUIM");

    private Long idEstadoCarro;
    private String descricaoidEstadoCarro;

    EstadoCarro(Long idEstadoCarro, String descricaoidEstadoCarro) {
        this.idEstadoCarro = idEstadoCarro;
        this.descricaoidEstadoCarro = descricaoidEstadoCarro;
    }

    public Long getIdEstadoCarro() {
        return idEstadoCarro;
    }

    public String getDescricaoidEstadoCarro() {
        return descricaoidEstadoCarro;
    }
}
