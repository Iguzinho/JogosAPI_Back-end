package br.ueg.progwebi.jogosapi.service.impl;

import br.ueg.progwebi.jogosapi.model.Jogo;
import br.ueg.progwebi.jogosapi.repository.JogoRepository;
import br.ueg.progwebi.jogosapi.service.JogoService;
import br.ueg.progwebi.jogosapi.service.exceptions.BusinessException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JogoServiceImpl implements JogoService {

    @Autowired
    private JogoRepository repository;

    @Override
    public List<Jogo> listAll() {
        return repository.findAll();
    }

    @Override
    public Jogo getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException("Jogo com id " + id + " não encontrado", 404));
    }

    @Override
    public Jogo create(Jogo jogo) {
        validarCriacao(jogo);
        return repository.save(jogo);
    }

    private void validarCriacao(Jogo jogo) {
        if (Strings.isEmpty(jogo.getTitulo())) {
            throw new BusinessException("O título do jogo não pode ser vazio.");
        }

        if (jogo.getDataLancamento() == null) {
            throw new BusinessException("A data de lançamento deve ser informada.");
        }

        if (repository.findByTitulo(jogo.getTitulo()).isPresent()) {
            throw new BusinessException("Já existe um jogo com esse título: " + jogo.getTitulo());
        }
    }

    @Override
    public Jogo update(Long id, Jogo jogoAtualizado) {
        Jogo jogoExistente = getById(id);
        validarAtualizacao(jogoAtualizado);

        jogoExistente.setTitulo(jogoAtualizado.getTitulo());
        jogoExistente.setGenero(jogoAtualizado.getGenero());
        jogoExistente.setPlataforma(jogoAtualizado.getPlataforma());
        jogoExistente.setDesenvolvedora(jogoAtualizado.getDesenvolvedora());
        jogoExistente.setDataLancamento(jogoAtualizado.getDataLancamento());
        jogoExistente.setMultiplayer(jogoAtualizado.getMultiplayer());
        jogoExistente.setClassificacaoEtaria(jogoAtualizado.getClassificacaoEtaria());

        return repository.save(jogoExistente);
    }

    private void validarAtualizacao(Jogo jogo) {
        if (Strings.isEmpty(jogo.getTitulo()) || Objects.isNull(jogo.getId()) || jogo.getId() <= 0) {
            throw new BusinessException("Dados de jogo inválidos para atualização.");
        }
    }

    @Override
    public Jogo delete(Long id) {
        Jogo jogo = getById(id);
        try {
            repository.delete(jogo);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("O jogo com id " + id + " não pode ser removido por dependências.");
        }
        return jogo;
    }

    @Override
    public List<Jogo> listByGenero(String genero) {
        return repository.findAllJogosPorGenero(genero).orElseGet(List::of);
    }
}
