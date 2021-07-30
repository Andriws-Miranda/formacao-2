package com.basis.campina.xtarefas.builder;

import com.basis.campina.xtarefas.domain.Status;
import com.basis.campina.xtarefas.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;

@Component
public class StatusBuilder extends EntityBuilder<Status>{

    @Autowired
    private StatusRepository repository;

    @Override
    public Status construirEntidade() throws ParseException {
        return null;
    }

    @Override
    protected Status persistir(Status entidade) {
        return null;
    }

    @Override
    protected Collection<Status> obterTodos() {
        return null;
    }

    @Override
    protected Status obterPorId(Long id) {
        return repository.getById(id);
    }
}
