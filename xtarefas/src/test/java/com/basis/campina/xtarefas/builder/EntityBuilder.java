package com.basis.campina.xtarefas.builder;

import java.text.ParseException;
import java.util.Collection;

public abstract class EntityBuilder<E> {

    private CustomizacaoEntidade<E> customizacao;

    public E construir() throws ParseException {
        final E entidade = construirEntidade();
        if (isCustomizado()) {
            customizacao.executar(entidade);
        }
        return persistir(entidade);
    }

    public EntityBuilder<E> customizar(CustomizacaoEntidade<E> customizacao) {
        this.customizacao = customizacao;
        return this;
    }

    public abstract E construirEntidade() throws ParseException;

    protected abstract E persistir(E entidade);

    protected abstract Collection<E> obterTodos();

    protected abstract E obterPorId(Long id);

    public boolean isCustomizado() {
        return this.customizacao != null;
    }

    public void setCustomizacao(CustomizacaoEntidade<E> customizacao) {
        this.customizacao = customizacao;
    }
}
