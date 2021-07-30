package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.domain.elastic.ResponsavelDocument;
import com.basis.campina.xtarefas.repository.ResponsavelRepository;
import com.basis.campina.xtarefas.repository.elastic.ResponsavelSearchRepository;
import com.basis.campina.xtarefas.service.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.service.event.ResponsavelEvent;
import com.basis.campina.xtarefas.service.exception.RegraNegocioException;
import com.basis.campina.xtarefas.service.filters.ResponsavelFilter;
import com.basis.campina.xtarefas.service.mapper.ResponsavelMapper;
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
public class ResponsavelService {
    private final ResponsavelRepository repository;
    private final ResponsavelMapper mapper;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ResponsavelSearchRepository searchRepository;

    public ResponsavelDTO salvar(ResponsavelDTO dto) {
        validarDuplicidade(dto);
        Responsavel responsavel = mapper.toEntity(dto);
        if(Objects.nonNull(dto.getId())){
            editar(dto);
        }
        else{
            repository.save(responsavel);
            applicationEventPublisher.publishEvent(new ResponsavelEvent(responsavel.getId()));
        }
        return dto;
    }

    public ResponsavelDTO editar(ResponsavelDTO dto) {
        validarDuplicidade(dto);
        Responsavel responsavel = mapper.toEntity(dto);
        repository.save(responsavel);
        return dto;
    }

    public List<ResponsavelDTO> buscar() {
        return mapper.toDtoList(repository.findAll());
    }

    public ResponsavelDTO obterPorId(Long id){
        Responsavel responsavel = repository.findById(id).orElseThrow(() -> new RegraNegocioException("Não existe nenhuma entidade com esse id"));
        return mapper.toDto(responsavel);
    }

    public void deletar(Long id) {
        obterPorId(id);
        repository.deleteById(id);
    }

    private Boolean validarDuplicidade(ResponsavelDTO dto){
        return repository.validarDuplicidade(dto).get();
    }

    public Page<ResponsavelDocument> search(ResponsavelFilter filter, Pageable pageable){
        return searchRepository.search(filter.getFilter(), pageable);
    }
}
