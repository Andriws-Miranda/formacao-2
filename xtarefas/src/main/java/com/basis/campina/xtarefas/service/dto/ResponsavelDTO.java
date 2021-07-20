package com.basis.campina.xtarefas.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class ResponsavelDTO {
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotNull
    private LocalDate dataNascimento;
}
