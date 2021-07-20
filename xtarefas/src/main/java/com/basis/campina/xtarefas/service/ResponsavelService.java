package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.repository.ResponsavelRepository;
import com.basis.campina.xtarefas.service.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.service.mapper.ResponsavelMapper;
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
public class ResponsavelService {
    private final ResponsavelRepository repository;

    private final ResponsavelMapper mapper;

    public ResponsavelDTO salvar(ResponsavelDTO dto) {
        if(Objects.nonNull(dto.getId())){
            editar(dto);
        }
        else{
            repository.save(mapper.toEntity(dto));
        }
        return dto;
    }

    public ResponsavelDTO editar(ResponsavelDTO dto) {
        validarDuplicidade(dto);
        repository.save(mapper.toEntity(dto));
        return dto;
    }

    public List<ResponsavelDTO> buscar() {
        return mapper.toDtoList(repository.findAll());
    }

    public ResponsavelDTO obterPorId(Long id){
        Responsavel responsavel = repository.findById(id).orElseThrow(() -> new BadRequestAlertException(ConstantesUtil.RESPONSAVEL_INEXISTENTE, ConstantesUtil.RESPONSAVEL, ConstantesUtil.ERRO_CHAVE));
        return mapper.toDto(responsavel);
    }

    public void deletar(Long id) {
        obterPorId(id);
        repository.deleteById(id);
    }

    private Boolean validarDuplicidade(ResponsavelDTO dto){
        return repository.validarDuplicidade(dto).get();
    }
}
