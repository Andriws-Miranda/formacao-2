package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.service.AnexoService;
import com.basis.campina.xtarefas.service.dto.AnexoDTO;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/anexos")
@Slf4j
public class AnexoResource {
    private final AnexoService service;

    @PostMapping
    @Timed
    public ResponseEntity<Void> salvar(@RequestBody @Valid AnexoDTO dto) {
        log.debug("Requisição REST para salvar uma Tarefa: {}", dto);
        service.salvar(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Timed
    public ResponseEntity<Void> editar(@PathVariable Long id, @RequestBody @Valid AnexoDTO dto){
        log.debug("Requisição REST para editar uma Tarefa: {}", dto);
        service.salvar(dto);
        dto.setId(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Timed
    public ResponseEntity<List<AnexoDTO>> buscar() {
        log.debug("Requisição REST para buscar todas as Tarefas");
        return ResponseEntity.ok(service.buscar());
    }

    @GetMapping("/{id}")
    @Timed
    public ResponseEntity<AnexoDTO> obterPorId(@PathVariable Long id) {
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

    @GetMapping("/document")
    @Timed
    public ResponseEntity<String> getString() {
        return ResponseEntity.ok(service.getString());
    }
}
