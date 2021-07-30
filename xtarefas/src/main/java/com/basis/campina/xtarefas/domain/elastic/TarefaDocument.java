package com.basis.campina.xtarefas.domain.elastic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Document(indexName = "index-tarefa")
@NoArgsConstructor
public class TarefaDocument extends BaseDocument {

    public TarefaDocument(Long id, String nome, LocalDate dataInicio, LocalDate dataConclusao, String status, String responsavel) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio != null ?
                dataInicio.format(DateTimeFormatter.ofPattern(DATE_PATTERN)) : null ;
        this.dataConclusao = dataConclusao != null ?
                dataConclusao.format(DateTimeFormatter.ofPattern(DATE_PATTERN)) : null ;
        this.status = status;
        this.responsavel = responsavel;
    }

    @MultiField(mainField = @Field(type = FieldType.Text, store = true,
            fielddata = true), otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String nome;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true,
            fielddata = true), otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE,fielddata = true)})
    private String dataInicio;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true,
            fielddata = true), otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE,fielddata = true)})
    private String dataConclusao;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true,
            fielddata = true), otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String status;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true,
            fielddata = true), otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String anexos;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true,
            fielddata = true), otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String responsavel;
}
