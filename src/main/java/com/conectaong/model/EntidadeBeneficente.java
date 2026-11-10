package com.conectaong.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "entidade_beneficente")
public class EntidadeBeneficente extends Usuario {

    @Column(name = "nome_responsavel_tecnico", length = 100)
    private String nomeResponsavelTecnico;

    @Override
    public String exibirResumo() {
        return "Entidade Beneficente: " + getNomeFantasia()
                + " | Responsável técnico: " + nomeResponsavelTecnico;
    }
}