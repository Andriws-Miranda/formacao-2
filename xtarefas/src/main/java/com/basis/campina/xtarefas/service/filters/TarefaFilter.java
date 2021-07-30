package com.basis.campina.xtarefas.service.filters;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TarefaFilter extends DefaultFilter implements BaseFilter, Serializable {

    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        List<String> fields = new ArrayList<>();
        filterFields(fields, query, queryBuilder, "nome","dataInicio", "status", "responsavel");

        addShouldTermQuery(queryBuilder, "dataConclusao", query);

        return queryBuilder;
    }
}
