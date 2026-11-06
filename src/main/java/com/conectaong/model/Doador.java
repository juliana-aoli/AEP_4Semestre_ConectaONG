package com.conectaong.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "doador")
public class Doador extends Usuario{

    @Column(name = "nome_responsavel", length = 100)
    private String nomeResponsavel;

    @Override
    public String exibirResumo(){
        return "Doador: " + getNomeFantasia() + " | Responsável: " + nomeResponsavel;
    }

}