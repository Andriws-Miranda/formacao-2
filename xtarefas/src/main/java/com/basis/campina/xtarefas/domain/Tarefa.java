package com.basis.campina.xtarefas.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TB_TAREFA")
public class Tarefa implements Serializable {
    @Id
    @Column(name = "CDN_TAREFA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TAREFA")
    @SequenceGenerator(name = "SEQ_TAREFA", sequenceName = "SEQ_TAREFA", allocationSize = 1)
    private Long id;

    @Column(name = "NOM_TAREFA")
    private String nome;

    @Column(name = "DT_INICIO")
    private LocalDate dataInicio;

    @Column(name = "DT_CONCLUSAO")
    private LocalDate dataConclusao;

    @Column(name = "TIP_STATUS")
    private Long status;

    @OneToMany(mappedBy = "anexo", targetEntity = Anexo.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Anexo> anexos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CDN_TAREFA_RESPONSAVEL", nullable = false)
    private Responsavel responsavel;

}
