package com.basis.campina.xtarefas.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "TB_STATUS")
public class Status {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;
}
