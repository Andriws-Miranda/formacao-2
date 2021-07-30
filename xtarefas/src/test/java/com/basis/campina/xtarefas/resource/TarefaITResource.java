package com.basis.campina.xtarefas.resource;

import com.basis.campina.xtarefas.builder.TarefaBuilder;
import com.basis.campina.xtarefas.config.containers.ContainersFactory;
import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.service.TarefaService;
import com.basis.campina.xtarefas.service.dto.TarefaDTO;
import com.basis.campina.xtarefas.service.elastic.ElasticsearchService;
import com.basis.campina.xtarefas.service.event.TarefaEvent;
import com.basis.campina.xtarefas.service.filters.TarefaFilter;
import com.basis.campina.xtarefas.utils.IntTestComum;
import com.basis.campina.xtarefas.utils.TestUtil;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@Transactional
@ExtendWith(SpringExtension.class)
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TarefaITResource extends IntTestComum {

    private final String API_TAREFA = "/api/tarefas";

    private final String API_TAREFA_ID = "/api/tarefas/{id}";


    private final String LISTAR_TAREFAS = API_TAREFA + "/search";

    @Autowired
    private TarefaBuilder tarefaBuilder;

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private ElasticsearchService elasticSearchService;

    @Container
    private static ContainersFactory containersFactory = ContainersFactory.getInstance();

    @Test
    @DisplayName("Salvar Tarefa com sucesso")
    public void salvarTarefa() throws Exception {
        TarefaDTO tarefaDTO = tarefaBuilder.construirDTO();

        getMockMvc().perform(post(API_TAREFA).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.utils.TestUtil.convertObjectToJsonBytes(tarefaDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Buscar Tarefa por id com sucesso")
    public void buscarPorId() throws Exception {
        Tarefa tarefa = tarefaBuilder.construir();

        getMockMvc().perform(get(API_TAREFA_ID, tarefa.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.utils.TestUtil.convertObjectToJsonBytes(tarefa)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Editar Tarefa com sucesso")
    public void editarTarefa() throws Exception {
        TarefaDTO tarefaDTO = tarefaBuilder.construirDTO();
        tarefaDTO.setNome("Tarefa nome editado");

        getMockMvc().perform(put(API_TAREFA_ID, tarefaDTO.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.utils.TestUtil.convertObjectToJsonBytes(tarefaDTO)))
                .andExpect(status().isOk());
    }


    @Test
    @Transactional
    @DisplayName("Listar Tarefa com sucesso")
    public void listartarefas() throws Exception {
        TarefaDTO tarefaDTO= this.tarefaBuilder.construirDTO();

        this.elasticSearchService.reindex();
        new TarefaEvent(tarefaDTO.getId());

        TarefaFilter filtro = new TarefaFilter();
        filtro.setQuery(tarefaDTO.getNome());

        getMockMvc().perform(post(LISTAR_TAREFAS).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.utils.TestUtil.convertObjectToJsonBytes(filtro)))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("Excluír Tarefa com sucesso")
    public void excluirTarefa() throws Exception {
        Tarefa Tarefa = tarefaBuilder.construir();

        getMockMvc().perform(delete(API_TAREFA_ID, Tarefa.getId()))
                .andExpect(status().isOk());
    }
}
