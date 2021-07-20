package com.basis.campina.xtarefas.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "TB_RESPONSAVEL")
public class Responsavel implements Serializable {

    @Id
    @Column(name = "CDN_RESPONSAVEL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESPONSAVEL")
    @SequenceGenerator(name = "SEQ_RESPONSAVEL", sequenceName = "SEQ_RESPONSAVEL", allocationSize = 1)
    private Long id;

    @Column(name = "NOM_RESPONSAVEL", nullable = false)
    private String nome;

    @Column(name = "NOM_EMAIL", nullable = false)
    private String email;

    @Column(name = "DT_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;
}
