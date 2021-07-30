package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.domain.elastic.ResponsavelDocument;
import com.basis.campina.xtarefas.domain.elastic.TarefaDocument;
import com.basis.campina.xtarefas.repository.TarefaRepository;
import com.basis.campina.xtarefas.repository.elastic.TarefaSearchRepository;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import com.basis.campina.xtarefas.service.event.TarefaEvent;
import com.basis.campina.xtarefas.service.exception.RegraNegocioException;
import com.basis.campina.xtarefas.service.filters.ResponsavelFilter;
import com.basis.campina.xtarefas.service.filters.TarefaFilter;
import com.basis.campina.xtarefas.service.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TarefaService {
    private final TarefaRepository repository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final TarefaSearchRepository searchRepository;
    private final TarefaMapper mapper;

    public TarefaDTO salvar(TarefaDTO dto) {
        validarDuplicidade(dto);
        Tarefa tarefa = mapper.toEntity(dto);
        if(Objects.nonNull(dto.getId())){
            editar(dto);
        }
        else{
            repository.save(tarefa);
            applicationEventPublisher.publishEvent(new TarefaEvent(tarefa.getId()));
        }
        return dto;
    }

    public TarefaDTO editar(TarefaDTO dto) {
        repository.save(mapper.toEntity(dto));
        return dto;
    }

    public List<TarefaDTO> buscar() {
        return mapper.toDtoList(repository.findAll());
    }

    public TarefaDTO obterPorId(Long id){
        Tarefa Tarefa = repository.findById(id).orElseThrow(() -> new RegraNegocioException("Não existe nenhuma entidade com esse id"));
        return mapper.toDto(Tarefa);
    }

    public void deletar(Long id) {
        obterPorId(id);
        repository.deleteById(id);
    }

    private Boolean validarDuplicidade(TarefaDTO dto){
        return repository.validarDuplicidade(dto).get();
    }

    public Page<TarefaDocument> search(TarefaFilter filter, Pageable pageable){
        return searchRepository.search(filter.getFilter(), pageable);
    }
}
