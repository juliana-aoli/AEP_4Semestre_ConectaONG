
package com.conectaong.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "entidade_beneficente")
@AttributeOverride(
        name = "id",
        column = @Column(name = "id_entidade")
)
public class EntidadeBeneficente extends Usuario {

    @Column(name = "responsavel_tecnico", length = 100)
    private String nomeResponsavelTecnico;

    @Override
    public String exibirResumo() {
        return "Entidade Beneficente: " + getNomeFantasia()
                + " | Responsável técnico: " + nomeResponsavelTecnico;
    }
}
