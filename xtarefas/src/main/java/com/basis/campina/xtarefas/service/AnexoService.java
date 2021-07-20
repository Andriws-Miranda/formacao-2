package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.domain.Anexo;
import com.basis.campina.xtarefas.repository.AnexoRepository;
import com.basis.campina.xtarefas.service.dto.AnexoDTO;
import com.basis.campina.xtarefas.service.mapper.AnexoMapper;
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
public class AnexoService {
    private final AnexoRepository repository;

    private final AnexoMapper mapper;

    public AnexoDTO salvar(AnexoDTO dto) {
        if(Objects.nonNull(dto.getId())){
            editar(dto);
        }else{
            repository.save(mapper.toEntity(dto));
        }
        return dto;
    }

    public AnexoDTO editar(AnexoDTO dto) {
        repository.save(mapper.toEntity(dto));
        return dto;
    }

    public List<AnexoDTO> buscar() {
        return mapper.toDtoList(repository.findAll());
    }

    public AnexoDTO obterPorId(Long id) {
        Anexo anexo = repository.findById(id).orElseThrow(() -> new BadRequestAlertException(ConstantesUtil.ANEXO_INEXISTENTE, ConstantesUtil.ANEXO, ConstantesUtil.ERRO_CHAVE));
        return mapper.toDto(anexo);
    }

    public void deletar(Long id) {
        obterPorId(id);
        repository.deleteById(id);
    }
}
