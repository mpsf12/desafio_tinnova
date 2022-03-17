package com.tinnova.desafio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinnova.desafio.ex5.controller.VeiculoController;
import com.tinnova.desafio.ex5.dto.VeiculoDto;
import com.tinnova.desafio.ex5.exception.VeiculoNotFoundException;
import com.tinnova.desafio.ex5.service.VeiculoService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VeiculoController.class)
public class VeiculoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VeiculoService veiculoService;

    @Test
    void shouldFindAllVeiculos() throws Exception {
        ArrayList<VeiculoDto> veiculos = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            VeiculoDto veiculo = new VeiculoDto(i,"teste " + i, "marca", 2000 + i, "descricao", false, new Date(), new Date());
            veiculos.add(veiculo);
        }

        Mockito.when(veiculoService.retornaTodosVeiculos()).thenReturn(veiculos);

        MvcResult result = mvc.perform(get("/veiculos/all")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertTrue(response.getStatus() == HttpServletResponse.SC_OK);

        ArrayList responseList = new ObjectMapper().readValue(response.getContentAsString(), ArrayList.class);
        assertTrue(responseList.size() == veiculos.size());
    }

    @Test
    void deveRetornarTodosOsVeiculos() throws Exception {
        ArrayList<VeiculoDto> veiculos = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            VeiculoDto veiculo = new VeiculoDto(i,"teste " + i, "marca", 2000 + i, "descricao", false, new Date(), new Date());
            veiculos.add(veiculo);
        }

        Mockito.when(veiculoService.retornaTodosVeiculos()).thenReturn(veiculos);

        MvcResult result = mvc.perform(get("/veiculos/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertTrue(response.getStatus() == HttpServletResponse.SC_OK);

        ArrayList responseList = new ObjectMapper().readValue(response.getContentAsString(), ArrayList.class);
        assertTrue(responseList.size() == veiculos.size());
    }

    @Test
    void deveRetornarUmVeiculoEspcifico() throws Exception {
        VeiculoDto veiculoDto = new VeiculoDto();
        veiculoDto.setId(123L);
        veiculoDto.setVeiculo("Teste find by id");

        Mockito.when(veiculoService.retornaVeiculoPorId(123L)).thenReturn(veiculoDto);

        MvcResult result = mvc.perform(get("/veiculos/123")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertTrue(response.getStatus() == HttpServletResponse.SC_OK);

        VeiculoDto veiculoResponse = new ObjectMapper().readValue(response.getContentAsString(), VeiculoDto.class);
        assertTrue(veiculoDto.equals(veiculoResponse));
    }

    @Test
    void deveRetornarUmVeiculoSalvoNoBanco() throws Exception {
        VeiculoDto veiculoDto = new VeiculoDto();
        veiculoDto.setId(123L);
        veiculoDto.setVeiculo("Teste find by id");

        Mockito.when(veiculoService.salvaVeiculo(any(VeiculoDto.class))).thenReturn(veiculoDto);

        String jsonVeiculoDto = new ObjectMapper().writeValueAsString(veiculoDto);

        MvcResult result = mvc.perform(post("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonVeiculoDto)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertTrue(response.getStatus() == HttpServletResponse.SC_OK);

        VeiculoDto veiculoResponse = new ObjectMapper().readValue(response.getContentAsString(), VeiculoDto.class);
        assertTrue(veiculoDto.equals(veiculoResponse));
    }

    @Test
    void deveRetornarUmVeiculoAtualizadoNoBanco() throws Exception {
        VeiculoDto veiculoDto = new VeiculoDto();
        veiculoDto.setId(123L);
        veiculoDto.setVeiculo("Teste find by id");

        Mockito.when(veiculoService.atualizaVeiculoPorId(any(VeiculoDto.class), any(long.class))).thenReturn(veiculoDto);

        String jsonVeiculoDto = new ObjectMapper().writeValueAsString(veiculoDto);

        MvcResult result = mvc.perform(put("/veiculos/123")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonVeiculoDto)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertTrue(response.getStatus() == HttpServletResponse.SC_OK);

        VeiculoDto veiculoResponse = new ObjectMapper().readValue(response.getContentAsString(), VeiculoDto.class);
        assertTrue(veiculoDto.equals(veiculoResponse));
    }

    @Test
    void deveRetornarUmVeiculoAtualizadoParcialNoBanco() throws Exception {
        VeiculoDto veiculoDto = new VeiculoDto();
        veiculoDto.setId(123L);
        veiculoDto.setVeiculo("Teste find by id");

        Mockito.when(veiculoService.atualizaParcialVeiculoPorId(any(VeiculoDto.class), any(long.class))).thenReturn(veiculoDto);

        String jsonVeiculoDto = new ObjectMapper().writeValueAsString(veiculoDto);

        MvcResult result = mvc.perform(patch("/veiculos/123")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonVeiculoDto)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertTrue(response.getStatus() == HttpServletResponse.SC_OK);

        VeiculoDto veiculoResponse = new ObjectMapper().readValue(response.getContentAsString(), VeiculoDto.class);
        assertTrue(veiculoDto.equals(veiculoResponse));
    }

    @Test
    void deveDeletarUmVeiculo() throws Exception {
        Mockito.doNothing().when(veiculoService).deletaVeiculoPorId(any(long.class));

        MvcResult result = mvc.perform(delete("/veiculos/123")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().string(""))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deveDarExcecaoVeiculoNotFoundQuandoDeletarVeiculoQueNaoExiste() throws Exception {
        Mockito.doThrow(VeiculoNotFoundException.class).when(veiculoService).deletaVeiculoPorId(any(long.class));

        MvcResult result = mvc.perform(delete("/veiculos/123")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(result1 -> assertTrue(result1.getResolvedException() instanceof VeiculoNotFoundException))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void deveDarExcecaoVeiculoNotFoundQuandoNaoExisteVeiculoComIdEspecifico() throws Exception {
        Mockito.doThrow(VeiculoNotFoundException.class).when(veiculoService).retornaVeiculoPorId(any(long.class));

        MvcResult result = mvc.perform(get("/veiculos/123")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(result1 -> assertTrue(result1.getResolvedException() instanceof VeiculoNotFoundException))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void deveDarExcecaoVeiculoNotFoundQuandoNaoExisteVeiculoComIdEspecificoParaAtualizacao() throws Exception {
        VeiculoDto veiculoDto = new VeiculoDto();
        veiculoDto.setVeiculo("Teste find by id");

        Mockito.doThrow(VeiculoNotFoundException.class).when(veiculoService).atualizaVeiculoPorId(any(VeiculoDto.class), any(long.class));

        String jsonVeiculoDto = new ObjectMapper().writeValueAsString(veiculoDto);

        MvcResult result = mvc.perform(put("/veiculos/123")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonVeiculoDto)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(result1 -> assertTrue(result1.getResolvedException() instanceof VeiculoNotFoundException))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void deveDarExcecaoVeiculoNotFoundQuandoNaoExisteVeiculoComIdEspecificoParaAtualizacaoParcial() throws Exception {
        VeiculoDto veiculoDto = new VeiculoDto();
        veiculoDto.setVeiculo("Teste find by id");

        Mockito.doThrow(VeiculoNotFoundException.class).when(veiculoService).atualizaParcialVeiculoPorId(any(VeiculoDto.class), any(long.class));

        String jsonVeiculoDto = new ObjectMapper().writeValueAsString(veiculoDto);

        MvcResult result = mvc.perform(patch("/veiculos/123")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonVeiculoDto)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(result1 -> assertTrue(result1.getResolvedException() instanceof VeiculoNotFoundException))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
