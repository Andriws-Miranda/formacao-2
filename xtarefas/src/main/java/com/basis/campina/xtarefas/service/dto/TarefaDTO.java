package com.basis.campina.xtarefas.service.dto;

import com.basis.campina.xtarefas.domain.Anexo;
import com.basis.campina.xtarefas.domain.Responsavel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TarefaDTO {

    private Long id;

    private String nome;

    private LocalDate dataInicio;

    private LocalDate dataConclusao;

    private Long status;

    private List<AnexoDTO> anexos = new ArrayList<>();

    private ResponsavelDTO responsavel;
}
