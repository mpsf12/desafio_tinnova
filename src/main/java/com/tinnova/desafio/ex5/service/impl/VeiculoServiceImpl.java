package com.tinnova.desafio.ex5.service.impl;

import com.tinnova.desafio.ex5.dto.VeiculoDto;
import com.tinnova.desafio.ex5.exception.VeiculoNotFoundException;
import com.tinnova.desafio.ex5.model.Veiculo;
import com.tinnova.desafio.ex5.repository.VeiculoRepository;
import com.tinnova.desafio.ex5.service.VeiculoService;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class VeiculoServiceImpl implements VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<VeiculoDto> retornaTodosVeiculos(){
        return ((List<Veiculo>) veiculoRepository.findAll())
                .stream()
                .map(veiculo -> new VeiculoDto(veiculo))
                .collect(Collectors.toList());
    }

    // TODO: 16/03/2022 - Criar metodo para buscar por marca, ano e/ou cor
    public List<VeiculoDto> retornaVeiculosPorMarcaAnoCor(String marca, Integer ano, String cor){
        return retornaTodosVeiculos();
    }

    public VeiculoDto retornaVeiculoPorId(long id){
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);

        if (veiculo.isPresent()) {
            return new VeiculoDto(veiculo.get());
        } else {
            throw new VeiculoNotFoundException("Não foi possível encontrar o veiculo de id " + id);
        }
    }

    public VeiculoDto salvaVeiculo(VeiculoDto veiculoDto){
        return new VeiculoDto(veiculoRepository.save(dtoToEntity(veiculoDto)));
    }

    public VeiculoDto atualizaVeiculoPorId(VeiculoDto veiculoDto, long id){
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);

        if (veiculo.isPresent()) {
            Date created = veiculo.get().getCreated();
            BeanUtils.copyProperties(veiculoDto, veiculo.get());
            veiculo.get().setCreated(created);
            veiculo.get().setUpdated(new Date());
            veiculo.get().setId(id);
            return new VeiculoDto(veiculoRepository.save(veiculo.get()));

        } else {
            throw new VeiculoNotFoundException("Não foi possível encontrar o veiculo de id " + id);
        }
    }

    public VeiculoDto atualizaParcialVeiculoPorId(VeiculoDto veiculoDto, long id){
        Optional<Veiculo> veiculoOptional = veiculoRepository.findById(id);

        if (veiculoOptional.isPresent()) {
            Veiculo veiculo = veiculoOptional.get();

            veiculo.setVeiculo(Strings.isBlank(veiculoDto.getVeiculo()) ? veiculo.getVeiculo() : veiculoDto.getVeiculo());
            veiculo.setAno(veiculoDto.getAno() == 0 ? veiculo.getAno() : veiculoDto.getAno());
            veiculo.setDescricao(Strings.isBlank(veiculoDto.getDescricao()) ? veiculo.getDescricao() : veiculoDto.getDescricao());
            veiculo.setVendido(veiculoDto.isVendido());
            veiculo.setMarca(Strings.isBlank(veiculoDto.getMarca()) ? veiculo.getMarca() : veiculoDto.getMarca());
            veiculo.setUpdated(new Date());

            return new VeiculoDto(veiculoRepository.save(veiculo));

        } else {
            throw new VeiculoNotFoundException("Não foi possível encontrar o veiculo de id " + id);
        }
    }

    public void deletaVeiculoPorId(long id){
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);

        if (veiculo.isPresent()) {
            veiculoRepository.delete(veiculo.get());
        } else {
            throw new VeiculoNotFoundException("Não foi possível encontrar o veiculo de id " + id);
        }
    }

    private Veiculo dtoToEntity(VeiculoDto veiculoDto){
        Veiculo veiculo = new Veiculo();
        BeanUtils.copyProperties(veiculoDto, veiculo);
        return veiculo;
    }
}
