package com.basis.campina.xtarefas.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AnexoDTO {
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String anexo;
}
