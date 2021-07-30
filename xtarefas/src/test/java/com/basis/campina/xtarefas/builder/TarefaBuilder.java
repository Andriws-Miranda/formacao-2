package com.basis.campina.xtarefas.builder;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.repository.TarefaRepository;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import com.basis.campina.xtarefas.service.mapper.TarefaMapper;
import com.basis.campina.xtarefas.service.util.TesteConstantesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;

@Component
public class TarefaBuilder extends EntityBuilder<Tarefa> {
    @Autowired
    private ResponsavelBuilder responsavelBuilder;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private StatusBuilder statusBuilder;

    @Autowired
    private TarefaMapper mapper;

    @Override
    public Tarefa construirEntidade() throws ParseException {
        Tarefa tarefa = new Tarefa();
        tarefa.setNome(TesteConstantesUtils.TEXTO);
        tarefa.setResponsavel(responsavelBuilder.construir());
        tarefa.setStatus(statusBuilder.obterPorId(TesteConstantesUtils.ID_PADRAO_VALIDO));
        tarefa.setDataInicio(LocalDate.now());
        tarefa.setDataConclusao(LocalDate.now());
        return persistir(tarefa);
    }

    public TarefaDTO construirDTO() throws ParseException {
        return mapper.toDto(construirEntidade());
    }

    @Override
    protected Tarefa persistir(Tarefa entidade) {
        return tarefaRepository.save(entidade);
    }

    @Override
    protected Collection<Tarefa> obterTodos() {
        return tarefaRepository.findAll();
    }

    @Override
    protected Tarefa obterPorId(Long id) {
        return tarefaRepository.getById(id);
    }
}
