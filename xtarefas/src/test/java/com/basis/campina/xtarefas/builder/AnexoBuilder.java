package com.basis.campina.xtarefas.builder;

import com.basis.campina.xtarefas.domain.Anexo;
import com.basis.campina.xtarefas.repository.AnexoRepository;
import com.basis.campina.xtarefas.service.dto.AnexoDTO;
import com.basis.campina.xtarefas.service.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.service.mapper.AnexoMapper;
import com.basis.campina.xtarefas.service.util.TesteConstantesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;

@Component
public class AnexoBuilder extends EntityBuilder<Anexo> {
    @Autowired
    private AnexoRepository repository;

    @Autowired
    private TarefaBuilder tarefaBuilder;

    @Autowired
    private AnexoMapper mapper;

    @Override
    public Anexo construirEntidade() throws ParseException {
        Anexo anexo = new Anexo();
        anexo.setNome(TesteConstantesUtils.TEXTO);
        anexo.setAnexo(TesteConstantesUtils.TEXTO);
        anexo.setTarefa(tarefaBuilder.construirEntidade());
        return persistir(anexo);
    }

    @Override
    protected Anexo persistir(Anexo entidade) {
        return repository.save(entidade);
    }

    public AnexoDTO construirDTO() throws ParseException {
        return mapper.toDto(construirEntidade());
    }

    @Override
    protected Collection<Anexo> obterTodos() {
        return null;
    }

    @Override
    protected Anexo obterPorId(Long id) {
        return null;
    }
}
