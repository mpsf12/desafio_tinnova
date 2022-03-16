package com.tinnova.desafio.ex5.controller;

import com.tinnova.desafio.ex5.dto.VeiculoDto;
import com.tinnova.desafio.ex5.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("/veiculos/all")
    public List<VeiculoDto> retornaTodosVeiculos(){
        return veiculoService.retornaTodosVeiculos();
    }

    @GetMapping("/veiculos")
    public List<VeiculoDto> retornaVeiculosPorMarcaAnoCor(@RequestParam(value = "marca", required = false) String marca,
                                                          @RequestParam(value = "ano", required = false) Integer ano,
                                                          @RequestParam(value = "cor", required = false) String cor){
        return veiculoService.retornaVeiculosPorMarcaAnoCor(marca, ano, cor);
    }

    @GetMapping("/veiculos/{id}")
    public ResponseEntity<VeiculoDto> retornaVeiculoPorId(@PathVariable(value = "id") long id){
        return ResponseEntity.ok().body(veiculoService.retornaVeiculoPorId(id));
    }

    @PostMapping("/veiculos")
    public ResponseEntity<VeiculoDto> salvaVeiculo(@RequestBody VeiculoDto veiculoDto){
        veiculoDto.setCreated(new Date());
        veiculoDto.setUpdated(new Date());
        return ResponseEntity.ok().body(veiculoService.salvaVeiculo(veiculoDto));
    }

    @PutMapping("/veiculos/{id}")
    public ResponseEntity<VeiculoDto> atualizaVeiculoPorId(@RequestBody VeiculoDto veiculoDto, @PathVariable(value = "id") long id){
        return ResponseEntity.ok().body(veiculoService.atualizaVeiculoPorId(veiculoDto, id));
    }

    @PatchMapping("/veiculos/{id}")
    public ResponseEntity<VeiculoDto> atualizaParcialVeiculoPorId(@RequestBody VeiculoDto veiculoDto, @PathVariable(value = "id") long id){
        return ResponseEntity.ok().body(veiculoService.atualizaParcialVeiculoPorId(veiculoDto, id));
    }

    @DeleteMapping("/veiculos/{id}")
    public void deletaVeiculoPorId(@PathVariable(value = "id") long id){
        veiculoService.deletaVeiculoPorId(id);
    }
}
