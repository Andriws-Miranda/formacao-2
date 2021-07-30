package com.basis.campina.xtarefas.resource;

import com.basis.campina.xtarefas.builder.AnexoBuilder;
import com.basis.campina.xtarefas.domain.Anexo;
import com.basis.campina.xtarefas.service.AnexoService;
import com.basis.campina.xtarefas.service.dto.AnexoDTO;
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
public class AnexoITResource extends IntTestComum {

    private final String API_ANEXO = "/api/anexos";

    private final String API_ANEXO_ID = "/api/anexos/{id}";


    @Autowired
    private AnexoBuilder anexoBuilder;

    @Autowired
    private AnexoService anexoService;

    @Test
    @DisplayName("Salvar Responsável com sucesso")
    public void salvarAnexo() throws Exception {
        AnexoDTO anexoDTO = anexoBuilder.construirDTO();

        getMockMvc().perform(post(API_ANEXO).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.utils.TestUtil.convertObjectToJsonBytes(anexoDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Buscar responsável por id com sucesso")
    public void buscarPorId() throws Exception {
        Anexo anexo = anexoBuilder.construir();

        getMockMvc().perform(get(API_ANEXO_ID, anexo.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.utils.TestUtil.convertObjectToJsonBytes(anexo)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Editar responsável com sucesso")
    public void editarAnexo() throws Exception {
        AnexoDTO anexoDTO = anexoBuilder.construirDTO();
        anexoDTO.setNome("anexo nome editado");

        getMockMvc().perform(put(API_ANEXO_ID, anexoDTO.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.utils.TestUtil.convertObjectToJsonBytes(anexoDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("Excluír responsável com sucesso")
    public void excluirAnexo() throws Exception {
        Anexo anexo = anexoBuilder.construir();

        getMockMvc().perform(delete(API_ANEXO_ID, anexo.getId()))
                .andExpect(status().isOk());
    }
}
