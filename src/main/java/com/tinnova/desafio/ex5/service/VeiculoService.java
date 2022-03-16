package com.tinnova.desafio.ex5.service;

import com.tinnova.desafio.ex5.dto.VeiculoDto;

import java.util.List;

public interface VeiculoService {

    List<VeiculoDto> retornaTodosVeiculos();
    List<VeiculoDto> retornaVeiculosPorMarcaAnoCor(String marca, Integer ano, String cor);
    VeiculoDto retornaVeiculoPorId(long id);
    VeiculoDto salvaVeiculo(VeiculoDto veiculoDto);
    VeiculoDto atualizaVeiculoPorId(VeiculoDto veiculoDto, long id);
    VeiculoDto atualizaParcialVeiculoPorId(VeiculoDto veiculoDto, long id);
    void deletaVeiculoPorId(long id);
}
