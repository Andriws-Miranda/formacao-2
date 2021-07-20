package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.repository.TarefaRepository;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import com.basis.campina.xtarefas.service.mapper.TarefaMapper;
import com.basis.campina.xtarefas.service.util.ConstantesUtil;
import com.basis.campina.xtarefas.web.rest.error.BadRequestAlertException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final TarefaMapper mapper;

    public TarefaDTO salvar(TarefaDTO dto) {
        validarDuplicidade(dto);
        if(Objects.nonNull(dto.getId())){
            editar(dto);
        }
        else{
            repository.save(mapper.toEntity(dto));
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
        Tarefa Tarefa = repository.findById(id).orElseThrow(() -> new BadRequestAlertException(ConstantesUtil.TAREFA_INEXISTENTE, ConstantesUtil.TAREFA, ConstantesUtil.ERRO_CHAVE));
        return mapper.toDto(Tarefa);
    }

    public void deletar(Long id) {
        obterPorId(id);
        repository.deleteById(id);
    }

    private Boolean validarDuplicidade(TarefaDTO dto){
        return repository.validarDuplicidade(dto).get();
    }
}
