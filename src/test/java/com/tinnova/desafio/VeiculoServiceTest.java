package com.tinnova.desafio;


import com.tinnova.desafio.ex5.dto.VeiculoDto;
import com.tinnova.desafio.ex5.exception.VeiculoNotFoundException;
import com.tinnova.desafio.ex5.model.Veiculo;
import com.tinnova.desafio.ex5.repository.VeiculoRepository;
import com.tinnova.desafio.ex5.service.impl.VeiculoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VeiculoServiceTest {

    @Mock
    VeiculoRepository veiculoRepository;

    @InjectMocks
    VeiculoServiceImpl veiculoService;

    @Test
    void deveRetornarUmaListaDeVeiculosDto(){
        List<Veiculo> veiculoList = generateRandomListOfVeiculos(10);
        when(veiculoRepository.findAll()).thenReturn(veiculoList);

        List<VeiculoDto> result = veiculoService.retornaTodosVeiculos();

        assertTrue(veiculoList.size() == result.size());
    }

    @Test
    void deveRetornarUmVeiculoDeIdEspecificado(){
        Veiculo veiculo = generateRandomVeiculo();
        when(veiculoRepository.findById(any(long.class))).thenReturn(Optional.of(veiculo));

        VeiculoDto result = veiculoService.retornaVeiculoPorId(1L);

        assertTrue(result.getId() == veiculo.getId());
    }

    @Test
    void deveRetornarAExcecaoVeiculoNotFoundAoProcurarUmIdQueNaoExiste(){
            when(veiculoRepository.findById(any(long.class))).thenThrow(VeiculoNotFoundException.class);

            assertThrows(VeiculoNotFoundException.class, () -> veiculoService.retornaVeiculoPorId(1L));
    }

    @Test
    void deveSalvarVeiculo(){
        Veiculo veiculo = generateRandomVeiculo();
        VeiculoDto veiculoDto = new VeiculoDto(veiculo);

        when(veiculoRepository.save(Mockito.any(Veiculo.class))).thenReturn(veiculo);

        VeiculoDto created = veiculoService.salvaVeiculo(veiculoDto);

        assertTrue(created.equals(veiculoDto));
    }

    @Test
    void deveAtualizarOVeiculoPeloId(){
        Veiculo veiculo = generateRandomVeiculo();

        when(veiculoRepository.findById(any(long.class))).thenReturn(Optional.of(veiculo));
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);

        VeiculoDto veiculoDto = new VeiculoDto(veiculo);

        VeiculoDto atualizado = veiculoService.atualizaVeiculoPorId(veiculoDto, veiculo.getId());

        assertTrue(veiculoDto.getId() == atualizado.getId());
        assertFalse(atualizado.equals(veiculoDto));
    }

    @Test
    void deveDespararAExcecaoVeiculoNotFoundAoTentarAtualizarUmIdQueNaoExiste(){
            when(veiculoRepository.findById(any(long.class))).thenThrow(VeiculoNotFoundException.class);
            assertThrows(VeiculoNotFoundException.class, () -> veiculoService.retornaVeiculoPorId(1L));
    }

    @Test
    void deveDispararAExcecaoVeiculoNotFoundAoTentarBuscarIdQueNAoExisteAoAtualizarVeiculo(){
        when(veiculoRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(VeiculoNotFoundException.class, () -> veiculoService.atualizaVeiculoPorId(new VeiculoDto(),1L));
    }

    @Test
    void deveDispararAExcecaoVeiculoNotFoundAoTentarBuscarIdQueNAoExisteAoAtualizarVeiculoParcial(){
        when(veiculoRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(VeiculoNotFoundException.class, () -> veiculoService.atualizaParcialVeiculoPorId(new VeiculoDto(), 1L));
    }

    @Test
    void deveDispararAExcecaoVeiculoNotFoundAoTentarBuscarIdQueNAoExisteAoDeletarVeiculo(){
        when(veiculoRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(VeiculoNotFoundException.class, () -> veiculoService.deletaVeiculoPorId(1L));
    }

    @Test
    void deveDeletarUmVeiculo(){
        doNothing().when(veiculoRepository).delete(any(Veiculo.class));
        when(veiculoRepository.findById(anyLong())).thenReturn(Optional.of(generateRandomVeiculo()));
        veiculoService.deletaVeiculoPorId(1L);
        verify(veiculoRepository).delete(any(Veiculo.class));
    }

    private List<Veiculo> generateRandomListOfVeiculos(int quant){
        ArrayList<Veiculo> veiculosList = new ArrayList<>();
        for (int i = 0; i < quant; i++) {
            veiculosList.add(generateRandomVeiculo(i));
        }
        return veiculosList;
    }

    private Veiculo generateRandomVeiculo(){
        return generateRandomVeiculo(0);
    }

    private Veiculo generateRandomVeiculo(int id){
        int randomNum = ThreadLocalRandom.current().nextInt(1, 1000);
        Veiculo veiculo = new Veiculo();

        veiculo.setId(id == 0 ? randomNum : id);
        veiculo.setVeiculo("veiculo teste " + veiculo.getId());
        veiculo.setVendido(randomNum % 2 == 0 ? true : false);
        veiculo.setMarca("marca teste " + veiculo.getId());
        veiculo.setDescricao("descricao teste " + veiculo.getId());
        veiculo.setAno(randomNum);

        veiculo.setUpdated(new Date());
        veiculo.setCreated(new Date());
        return veiculo;
    }
}
