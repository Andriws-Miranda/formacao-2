package com.basis.campina.xtarefas.service.filters;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.BoolQueryBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DefaultFilter implements BaseFilter{

    protected String query;

    @Override
    public BoolQueryBuilder getFilter() {
        return null;
    }

    @Override
    public String getFilterString(String value) {
        return BaseFilter.super.getFilterString(value);
    }

    @Override
    public String wrapStar(String value) {
        return BaseFilter.super.wrapStar(value);
    }

    @Override
    public void addWildcard(BoolQueryBuilder queryBuilder, String field, String value) {
        BaseFilter.super.addWildcard(queryBuilder, field, value);
    }

    @Override
    public void addRangeQueryLocalDate(BoolQueryBuilder queryBuilder, String field, LocalDate value1, LocalDate value2) {
        BaseFilter.super.addRangeQueryLocalDate(queryBuilder, field, value1, value2);
    }

    @Override
    public void addShouldWildCard(BoolQueryBuilder queryBuilder, List<String> fields, String query) {
        BaseFilter.super.addShouldWildCard(queryBuilder, fields, query);
    }

    @Override
    public void addMustNotMatchQuery(BoolQueryBuilder queryBuilder, String field, String value) {
        BaseFilter.super.addMustNotMatchQuery(queryBuilder, field, value);
    }

    @Override
    public void addMustTermQuery(BoolQueryBuilder queryBuilder, String field, Object value) {
        BaseFilter.super.addMustTermQuery(queryBuilder, field, value);
    }

    @Override
    public void addMustNotTermQuery(BoolQueryBuilder queryBuilder, String field, String value) {
        BaseFilter.super.addMustNotTermQuery(queryBuilder, field, value);
    }

    @Override
    public void addMustTermQuery(BoolQueryBuilder queryBuilder, String field, String value) {
        BaseFilter.super.addMustTermQuery(queryBuilder, field, value);
    }

    @Override
    public void addShouldTermQuery(BoolQueryBuilder queryBuilder, String field, String value) {
        BaseFilter.super.addShouldTermQuery(queryBuilder, field, value);
    }

    @Override
    public void filterFields(List<String> fields, String query, BoolQueryBuilder queryBuilder, String... args) {
        BaseFilter.super.filterFields(fields, query, queryBuilder, args);
    }
}
