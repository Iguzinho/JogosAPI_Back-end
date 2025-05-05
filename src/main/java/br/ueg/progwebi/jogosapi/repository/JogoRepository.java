package br.ueg.progwebi.jogosapi.repository;

import br.ueg.progwebi.jogosapi.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {

    Optional<Jogo> findByTitulo(String titulo);

    @Query("from Jogo j where j.genero = :genero")
    Optional<List<Jogo>> findAllJogosPorGenero(String genero);

    Optional<Jogo> findTopByOrderByIdDesc();
}
