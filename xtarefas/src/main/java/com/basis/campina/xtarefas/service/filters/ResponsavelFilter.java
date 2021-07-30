package com.basis.campina.xtarefas.service.filters;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponsavelFilter extends DefaultFilter implements BaseFilter, Serializable {

    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        List<String> fields = new ArrayList<>();
        filterFields(fields,query, queryBuilder, "nome","email");

        addShouldTermQuery(queryBuilder, "dataNascimento", query);

        return queryBuilder;
    }
}