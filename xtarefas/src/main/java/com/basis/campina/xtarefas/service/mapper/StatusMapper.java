package com.basis.campina.xtarefas.service.mapper;

import com.basis.campina.xtarefas.domain.Status;
import com.basis.campina.xtarefas.service.dto.StatusDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    @Mapping(source = "id", target = "id")
    Status toEntity(StatusDTO dto);

    @Mapping(source = "id", target = "id")
    StatusDTO toDto(Status status);

    List<Status> toEntityList(List<StatusDTO> dtoList);

    List<StatusDTO> toDtoList(List<Status> status);
}
