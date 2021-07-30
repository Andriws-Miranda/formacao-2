package com.basis.campina.xtarefas.builder;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.repository.ResponsavelRepository;
import com.basis.campina.xtarefas.service.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.service.mapper.ResponsavelMapper;
import com.basis.campina.xtarefas.service.util.TesteConstantesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;

@Component
public class ResponsavelBuilder extends EntityBuilder<Responsavel> {

    @Autowired
    private ResponsavelRepository repository;

    @Autowired
    private ResponsavelMapper mapper;

    @Override
    public Responsavel construirEntidade() throws ParseException {
        Responsavel responsavel = new Responsavel();

        responsavel.setNome(TesteConstantesUtils.TEXTO);
        responsavel.setEmail(TesteConstantesUtils.EMAIL_DEFAULT);
        responsavel.setDataNascimento(LocalDate.now());
        return persistir(responsavel);
    }

    public ResponsavelDTO construirDTO() throws ParseException {
        return mapper.toDto(construirEntidade());
    }

    @Override
    protected Responsavel persistir(Responsavel entidade) {
        return repository.save(entidade);
    }

    @Override
    protected Collection<Responsavel> obterTodos() {
        return repository.findAll();
    }

    @Override
    protected Responsavel obterPorId(Long id) {
        return repository.getById(id);
    }
}
