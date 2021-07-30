package com.basis.campina.xtarefas.repository;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.domain.elastic.ResponsavelDocument;
import com.basis.campina.xtarefas.repository.elastic.Reindexer;
import com.basis.campina.xtarefas.service.dto.ResponsavelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long>, Reindexer {

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN null ELSE true END FROM Responsavel r"
            + " WHERE LOWER(r.nome) LIKE LOWER(:#{#responsavel.nome})"
            + " AND (:#{#responsavel.id} IS null OR :#{#responsavel.id} != r.id)")
    Optional<Boolean> validarDuplicidade(@Param("responsavel") ResponsavelDTO dto);

    @Query(
            "select new com.basis.campina.xtarefas.domain.elastic.ResponsavelDocument(r.id, r.nome, r.email, r.dataNascimento)" +
                    "from Responsavel r where r.id = :id")
    ResponsavelDocument getDocument(@Param("id") Long id);

    @Query("SELECT NEW  com.basis.campina.xtarefas.domain.elastic.ResponsavelDocument(r.id, r.nome, r.email, r.dataNascimento)"
            + " from Responsavel r order by r.id")
    Page<ResponsavelDocument> reindexPage(Pageable pageable);

    @Override
    default String getEntity() {
        return "responsaveis";
    }
}
