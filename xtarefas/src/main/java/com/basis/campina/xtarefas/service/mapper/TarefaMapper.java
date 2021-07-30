package com.basis.campina.xtarefas.service.mapper;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AnexoMapper.class, ResponsavelMapper.class})
public interface TarefaMapper {
    @Mapping(source = "statusId", target = "status.id")
    @Mapping(source = "responsavelId", target = "responsavel.id")
    Tarefa toEntity(TarefaDTO dto);

    @Mapping(source = "status.id", target = "statusId")
    @Mapping(source = "responsavel.id", target = "responsavelId")
    TarefaDTO toDto(Tarefa tarefa);

    List<Tarefa> toEntityList(List<TarefaDTO> dtoList);

    List<TarefaDTO> toDtoList(List<Tarefa> tarefas);
}
