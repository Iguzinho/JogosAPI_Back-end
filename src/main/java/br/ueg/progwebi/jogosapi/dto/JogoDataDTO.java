package br.ueg.progwebi.jogosapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class JogoDataDTO {
    private String titulo;
    private String genero;
    private String plataforma;
    private String desenvolvedora;
    private LocalDate dataLancamento;
    private Boolean multiplayer;
    private String classificacaoEtaria;
}
