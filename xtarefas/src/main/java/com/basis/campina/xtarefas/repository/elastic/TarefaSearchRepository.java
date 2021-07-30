package com.basis.campina.xtarefas.repository.elastic;

import com.basis.campina.xtarefas.domain.elastic.TarefaDocument;

public interface TarefaSearchRepository extends ElasticEntity<TarefaDocument, Long> {

    @Override
    default Class<TarefaDocument> getEntityClass() {
        return TarefaDocument.class;
    }
}
