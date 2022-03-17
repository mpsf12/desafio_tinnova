package com.tinnova.desafio.ex5.repository;

import com.tinnova.desafio.ex5.model.Veiculo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VeiculoRepository extends CrudRepository<Veiculo, Long> {

    @Query("SELECT v FROM Veiculo v WHERE (:marca is null or v.marca = :marca) and (:ano is null or v.ano = :ano) and (:cor is null or v.cor = :cor)")
    List<Veiculo> findVeiculoByMarcaAndAnoAndCor(@Param("marca") String marca, @Param("ano") Integer ano, @Param("cor") String cor);
}
