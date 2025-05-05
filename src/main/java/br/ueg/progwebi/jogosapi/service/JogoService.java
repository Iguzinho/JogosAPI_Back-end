package br.ueg.progwebi.jogosapi.service;

import br.ueg.progwebi.jogosapi.model.Jogo;

import java.util.List;

public interface JogoService {
    List<Jogo> listAll();
    Jogo getById(Long id);
    Jogo create(Jogo jogo);
    Jogo update(Long id, Jogo jogo);
    Jogo delete(Long id);
    List<Jogo> listByGenero(String genero);
}
