package com.basis.campina.xtarefas.repository;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.domain.elastic.ResponsavelDocument;
import com.basis.campina.xtarefas.domain.elastic.TarefaDocument;
import com.basis.campina.xtarefas.repository.elastic.Reindexer;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long>, Reindexer {
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN null ELSE true END FROM Tarefa t"
            + " WHERE LOWER(t.nome) LIKE LOWER(:#{#tarefa.nome})"
            + " AND (:#{#tarefa.id} IS null OR :#{#tarefa.id} != t.id)")
    Optional<Boolean> validarDuplicidade(@Param("tarefa") TarefaDTO dto);

    @Query(
            "select new com.basis.campina.xtarefas.domain.elastic.TarefaDocument(t.id, t.nome, t.dataInicio, t.dataConclusao, t.status.descricao, t.responsavel.nome)" +
                    "from Tarefa t where t.id = :id")
    TarefaDocument getDocument(@Param("id") Long id);

    @Query("SELECT NEW  com.basis.campina.xtarefas.domain.elastic.ResponsavelDocument(r.id, r.nome, r.email, r.dataNascimento)"
            + " from Responsavel r order by r.id")
    Page<ResponsavelDocument> reindexPage(Pageable pageable);

    @Override
    default String getEntity() {
        return "responsaveis";
    }
}
