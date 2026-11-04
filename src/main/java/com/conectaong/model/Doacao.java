package com.conectaong.model;

@Data
@Entity
@Table(name = "doacao")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doacao")
    private Integer idDoacao;

    @ManyToOne
    @JoinColumn(name = "id_doador", nullable = false)
    private Doador doador;

    @ManyToOne
    @JoinColumn(name = "id_entidade", nullable = false)
    private EntidadeBeneficente entidadeBeneficente;

    @Column(name = "data_doacao")
    private LocalDate dataDoacao;

    @Column(length = 50)
    private String status;
}
