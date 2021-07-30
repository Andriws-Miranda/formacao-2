package com.basis.campina.xtarefas.service.dto;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TarefaDTO {

    private Long id;

    private String nome;

    private LocalDate dataInicio;

    private LocalDate dataConclusao;

    private Long statusId;

    private List<AnexoDTO> anexos;

    private Long responsavelId;
}
