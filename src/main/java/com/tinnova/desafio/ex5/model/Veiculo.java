package com.tinnova.desafio.ex5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    private String veiculo;
    private String marca;
    private int ano;
    @Lob
    private String descricao;
    private boolean vendido;
    private Date created;
    private Date updated;

}
