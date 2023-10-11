package com.insper.partida.tabela;

import com.insper.partida.equipe.TeamService;
import com.insper.partida.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TabelaService {

    @Autowired
    private TeamService teamService;

    @Autowired
    private GameService gameService;

    @Autowired
    private TabelaRepository tabelaRepository;

    public List<Tabela> getTabela() {

        List<Tabela> response = tabelaRepository.findAll();
        response.sort(Comparator.comparingInt(Tabela::getPontos).reversed());
        return response;

    }


    public Tabela getTabelaByNome(String nome) {
        return tabelaRepository.findByNome(nome);
    }

    public Tabela saveTabela(Tabela tabela) {
        return tabelaRepository.save(tabela);
    }
}