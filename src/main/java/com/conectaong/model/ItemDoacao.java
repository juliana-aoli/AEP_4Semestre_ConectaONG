
package com.conectaong.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "item_doacao")
public class ItemDoacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Integer idItemDoacao;

    @Column(name = "descricao_alimento", length = 150)
    private String descricao;

    @Column(name = "categoria", length = 100)
    private String categoria;

    @Column(name = "quantidade", precision = 10, scale = 2)
    private BigDecimal quantidade;

    @Column(name = "unidade_medida", length = 60)
    private String unidadeMedida;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @ManyToOne
    @JoinColumn(name = "id_doacao", nullable = false)
    private Doacao doacao;
}
