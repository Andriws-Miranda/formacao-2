package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.service.ResponsavelService;
import com.basis.campina.xtarefas.service.dto.ResponsavelDTO;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/responsaveis")
@Slf4j
public class ResponsavelResource {
    private final ResponsavelService service;

    @PostMapping
    @Timed
    public ResponseEntity<Void> salvar(@RequestBody @Valid ResponsavelDTO dto) {
        log.debug("Requisição REST para salvar uma Tarefa: {}", dto);
        service.salvar(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Timed
    public ResponseEntity<Void> editar(@RequestBody @Valid ResponsavelDTO dto){
        log.debug("Requisição REST para editar uma Tarefa: {}", dto);
        service.salvar(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Timed
    public ResponseEntity<List<ResponsavelDTO>> buscar() {
        log.debug("Requisição REST para buscar todas as Tarefas");
        return ResponseEntity.ok(service.buscar());
    }

    @GetMapping("/{id}")
    @Timed
    public ResponseEntity<ResponsavelDTO> obterPorId(@PathVariable Long id) {
        log.debug("Requisição REST para buscar uma Tarefa pelo id: {}", id);
        return ResponseEntity.ok(service.obterPorId(id));
    }

    @DeleteMapping("/{id}")
    @Timed
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        log.debug("Requisiçao REST para deletar uma Tarefa: {}", id);
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}
