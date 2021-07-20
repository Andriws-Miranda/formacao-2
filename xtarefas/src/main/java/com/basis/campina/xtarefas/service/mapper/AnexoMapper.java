package com.basis.campina.xtarefas.service.mapper;

import com.basis.campina.xtarefas.domain.Anexo;
import com.basis.campina.xtarefas.service.dto.AnexoDTO;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AnexoMapper {
    Anexo toEntity(AnexoDTO dto);

    AnexoDTO toDto(Anexo tarefa);

    List<Anexo> toEntityList(List<AnexoDTO> dtoList);

    List<AnexoDTO> toDtoList(List<Anexo> tarefas);
}
