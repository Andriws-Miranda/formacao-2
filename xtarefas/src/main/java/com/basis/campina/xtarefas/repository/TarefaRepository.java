package com.basis.campina.xtarefas.repository;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN null ELSE true END FROM Tarefa t"
            + " WHERE LOWER(t.nome) LIKE LOWER(:#{#tarefa.nome})"
            + " AND (:#{#tarefa.id} IS null OR :#{#tarefa.id} != t.id)")
    Optional<Boolean> validarDuplicidade(@Param("tarefa") TarefaDTO dto);
}
