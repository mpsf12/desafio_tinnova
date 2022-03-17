package com.tinnova.desafio;


import com.tinnova.desafio.ex5.dto.VeiculoDto;
import com.tinnova.desafio.ex5.model.Veiculo;
import com.tinnova.desafio.ex5.repository.VeiculoRepository;
import com.tinnova.desafio.ex5.service.VeiculoService;
import com.tinnova.desafio.ex5.service.impl.VeiculoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class VeiculoServiceTest {

    @Mock
    VeiculoRepository veiculoRepository;

    @InjectMocks
    VeiculoServiceImpl veiculoService;

    @Test
    void shouldSaveAVeiculo(){
        Veiculo veiculo = new Veiculo();
        veiculo.setVeiculo("teste veiculo");
        VeiculoDto veiculoDto = new VeiculoDto(veiculo);

        Mockito.when(veiculoRepository.save(Mockito.any(Veiculo.class))).thenReturn(veiculo);

        VeiculoDto created = veiculoService.salvaVeiculo(veiculoDto);

        Assertions.assertTrue(created.equals(veiculoDto));
    }
}
