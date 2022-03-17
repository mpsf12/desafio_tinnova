package com.tinnova.desafio.ex5.dto;

import com.tinnova.desafio.ex5.model.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDto {

    private long id;
    private String veiculo;
    private String marca;
    private String cor;
    private int ano;
    private String descricao;
    private boolean vendido;
    private Date created;
    private Date updated;

    public VeiculoDto(Veiculo veiculo){
        BeanUtils.copyProperties(veiculo, this);
    }

}
