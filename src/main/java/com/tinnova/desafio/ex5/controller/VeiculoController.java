package com.tinnova.desafio.ex5.controller;

import com.tinnova.desafio.ex5.dto.VeiculoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VeiculoController {

    @GetMapping("/veiculos")
    public List<VeiculoDto> retornaTodosVeiculos(){
        return null;
    }

    @GetMapping("/veiculos")
    public List<VeiculoDto> retornaVeiculosPorMarcaAnoCor(@RequestParam("marca") String marca,
                                                          @RequestParam("ano") int ano,
                                                          @RequestParam("cor") String cor){
        return null;
    }

    @GetMapping("/veiculos/{id]")
    public ResponseEntity<VeiculoDto> retornaVeiculoPorId(@PathVariable(value = "id") int id){
        return null;
    }

    @PostMapping("/veiculos")
    public ResponseEntity<VeiculoDto> salvaVeiculo(@RequestBody VeiculoDto veiculoDto){
        return null;
    }

    @PutMapping("/veiculos/{id]")
    public ResponseEntity<VeiculoDto> atualizaVeiculoPorId(@RequestBody VeiculoDto veiculoDto, @PathVariable(value = "id") int id){
        return null;
    }

    @PatchMapping("/veiculos/{id]")
    public ResponseEntity<VeiculoDto> atualizaParcialVeiculoPorId(@RequestBody VeiculoDto veiculoDto, @PathVariable(value = "id") int id){
        return null;
    }

    @DeleteMapping("/veiculos/{id]")
    public ResponseEntity<VeiculoDto> deletaVeiculoPorId(@PathVariable(value = "id") int id){
        return null;
    }
}
