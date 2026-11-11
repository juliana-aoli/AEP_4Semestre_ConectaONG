
package com.conectaong.model;

public enum StatusDoacao {

    AGUARDANDO_ACEITE("Aguardando Aceite"),
    PREPARANDO_DOACAO("Preparando Doação"),
    AGUARDANDO_RETIRADA("Aguardando Retirada"),
    EM_TRANSPORTE("Em Transporte"),
    CONCLUIDA("Concluída"),
    CANCELADA("Cancelada");

    private final String descricao;

    StatusDoacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static boolean ehValido(String status) {
        if (status == null) {
            return false;
        }

        for (StatusDoacao etapa : values()) {
            if (etapa.descricao.equalsIgnoreCase(status.trim())) {
                return true;
            }
        }

        return false;
    }

    public static String statusInicial() {
        return AGUARDANDO_ACEITE.descricao;
    }
}
