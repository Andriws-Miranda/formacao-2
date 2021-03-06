package com.basis.campina.xtarefas.resource;

import com.basis.campina.xtarefas.builder.ResponsavelBuilder;
import com.basis.campina.xtarefas.config.containers.ContainersFactory;
import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.service.ResponsavelService;
import com.basis.campina.xtarefas.service.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.service.elastic.ElasticsearchService;
import com.basis.campina.xtarefas.service.event.ResponsavelEvent;
import com.basis.campina.xtarefas.service.filters.ResponsavelFilter;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@Transactional
@ExtendWith(SpringExtension.class)
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResponsavelITResource extends IntTestComum {

    private final String API_RESPONSAVEL = "/api/responsaveis";

    private final String API_RESPONSAVEL_ID = "/api/responsaveis/{id}";


    private final String LISTAR_RESPONSAVEIS = API_RESPONSAVEL + "/search";

    @Autowired
    private ResponsavelBuilder responsavelBuilder;

    @Autowired
    private ResponsavelService responsavelService;

    @Autowired
    private ElasticsearchService elasticSearchService;

    @Container
    private static ContainersFactory containersFactory = ContainersFactory.getInstance();

    @Test
    @DisplayName("Salvar Respons??vel com sucesso")
    public void salvarResponsavel() throws Exception {
        ResponsavelDTO responsavelDTO = responsavelBuilder.construirDTO();

        getMockMvc().perform(post(API_RESPONSAVEL).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.utils.TestUtil.convertObjectToJsonBytes(responsavelDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Buscar respons??vel por id com sucesso")
    public void buscarPorId() throws Exception {
        Responsavel responsavel = responsavelBuilder.construir();

        getMockMvc().perform(get(API_RESPONSAVEL_ID, responsavel.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.utils.TestUtil.convertObjectToJsonBytes(responsavel)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Editar respons??vel com sucesso")
    public void editarResponsavel() throws Exception {
        ResponsavelDTO responsavelDTO = responsavelBuilder.construirDTO();
        responsavelDTO.setNome("Responsavel nome editado");

        getMockMvc().perform(put(API_RESPONSAVEL_ID, responsavelDTO.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.utils.TestUtil.convertObjectToJsonBytes(responsavelDTO)))
                .andExpect(status().isOk());
    }


    @Test
    @Transactional
    @DisplayName("Listar Respons??vel com sucesso")
    public void listarResponsaveis() throws Exception {
        ResponsavelDTO responsavelDTO= this.responsavelBuilder.construirDTO();

        this.elasticSearchService.reindex();
        new ResponsavelEvent(responsavelDTO.getId());

        ResponsavelFilter filtro = new ResponsavelFilter();
        filtro.setQuery(responsavelDTO.getNome());

        getMockMvc().perform(post(LISTAR_RESPONSAVEIS).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.utils.TestUtil.convertObjectToJsonBytes(filtro)))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("Exclu??r respons??vel com sucesso")
    public void excluirResponsavel() throws Exception {
        Responsavel responsavel = responsavelBuilder.construir();

        getMockMvc().perform(delete(API_RESPONSAVEL_ID, responsavel.getId()))
                .andExpect(status().isOk());
    }
}
