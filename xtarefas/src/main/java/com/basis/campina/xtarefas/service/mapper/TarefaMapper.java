package com.basis.campina.xtarefas.service.mapper;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaMapper {
    Tarefa toEntity(TarefaDTO dto);

    TarefaDTO toDto(Tarefa tarefa);

    List<Tarefa> toEntityList(List<TarefaDTO> dtoList);

    List<TarefaDTO> toDtoList(List<Tarefa> tarefas);
}
