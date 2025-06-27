package br.ueg.progwebi.jogosapi.controller;

import br.ueg.progwebi.jogosapi.dto.JogoDataDTO;
import br.ueg.progwebi.jogosapi.model.Jogo;
import br.ueg.progwebi.jogosapi.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/jogo")
@CrossOrigin(origins = "http://localhost:4200")
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @GetMapping
    public List<Jogo> listAll() {
        return jogoService.listAll();
    }

    @PostMapping
    public ResponseEntity<Jogo> create(@RequestBody JogoDataDTO jogoDTO) {
        Jogo novoJogo = fromDTO(jogoDTO);
        return ResponseEntity.ok(jogoService.create(novoJogo));
    }

    @PutMapping(path = "/{id}")
    public Jogo update(@PathVariable Long id, @RequestBody JogoDataDTO jogoDTO) {
        Jogo jogoAtualizado = fromDTO(jogoDTO);
        jogoAtualizado.setId(id);
        return jogoService.update(id, jogoAtualizado);
    }

    @GetMapping(path = "/genero/{genero}")
    public List<Jogo> listByGenero(@PathVariable String genero) {
        return jogoService.listByGenero(genero);
    }

    @GetMapping(path = "/{id}")
    public Jogo getById(@PathVariable Long id) {
        return jogoService.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    public Jogo delete(@PathVariable Long id) {
        return jogoService.delete(id);
    }

    private static Jogo fromDTO(JogoDataDTO dto) {
        return Jogo.builder()
                .titulo(dto.getTitulo())
                .genero(dto.getGenero())
                .plataforma(dto.getPlataforma())
                .desenvolvedora(dto.getDesenvolvedora())
                .dataLancamento(dto.getDataLancamento())
                .multiplayer(dto.getMultiplayer())
                .classificacaoEtaria(dto.getClassificacaoEtaria())
                .build();
    }
}
