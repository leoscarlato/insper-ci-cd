package com.insper.partida.tabela;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TabelaRepository extends MongoRepository<Tabela, String> {

    Tabela findByNome(String nome);

    
}