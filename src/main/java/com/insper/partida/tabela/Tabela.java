package com.insper.partida.tabela;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("tabela")
public class Tabela {

    @Id
    public String id;
    public String nome;
    public Integer pontos = 0;
    public Integer golsPro = 0;
    public Integer golsContra = 0;
    public Integer vitorias = 0;
    public Integer derrotas = 0;
    public Integer empates = 0;
    public Integer jogos = 0;
    
}