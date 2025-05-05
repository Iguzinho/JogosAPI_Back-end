package br.ueg.progwebi.jogosapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Jogo {

    public static final String SEQUENCE_NAME = "JOGO_ID_GENERATE";

    @Id
    @Column(name = "codigo")
    @SequenceGenerator(
            name = SEQUENCE_NAME,
            sequenceName = SEQUENCE_NAME + "_bd",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = SEQUENCE_NAME
    )
    private Long id;

    @Column(name = "TITULO", length = 200, nullable = false, unique = true)
    private String titulo;

    @Column(name = "GENERO", length = 100, nullable = false)
    private String genero;

    @Column(name = "PLATAFORMA", length = 100, nullable = false)
    private String plataforma;

    @Column(name = "DESENVOLVEDORA", length = 150, nullable = false)
    private String desenvolvedora;

    @Column(name = "DATA_LANCAMENTO", nullable = false)
    private LocalDate dataLancamento;

    @Column(name = "MULTIPLAYER", nullable = false)
    private Boolean multiplayer;

    @Column(name = "CLASSIFICACAO_ETARIA", length = 50, nullable = false)
    private String classificacaoEtaria;
}
