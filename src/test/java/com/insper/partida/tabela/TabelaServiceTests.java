package com.insper.partida.tabela;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.insper.partida.equipe.TeamService;
import com.insper.partida.game.GameService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TabelaServiceTests {

    @InjectMocks
    private TabelaService tabelaService;

    @Mock
    private TabelaRepository tabelaRepository;

    @Mock
    private TeamService teamService;

    @Mock
    private GameService gameService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTabela() {
        Tabela tabela1 = new Tabela();
        tabela1.setNome("Team1");
        tabela1.setPontos(10);

        Tabela tabela2 = new Tabela();
        tabela2.setNome("Team2");
        tabela2.setPontos(20);

        when(tabelaRepository.findAll()).thenReturn(Arrays.asList(tabela1, tabela2));

        List<Tabela> result = tabelaService.getTabela();
        assertEquals(2, result.size());
        assertEquals("Team2", result.get(0).getNome());
    }

    @Test
    public void testGetTabelaByNome() {
        Tabela tabela = new Tabela();
        tabela.setNome("Team1");
        when(tabelaRepository.findByNome("Team1")).thenReturn(tabela);

        Tabela result = tabelaService.getTabelaByNome("Team1");
        assertNotNull(result);
        assertEquals("Team1", result.getNome());
    }

    @Test
    public void testSaveTabela() {
        Tabela tabela = new Tabela();
        tabela.setNome("Team1");
        when(tabelaRepository.save(tabela)).thenReturn(tabela);

        Tabela result = tabelaService.saveTabela(tabela);
        assertNotNull(result);
        assertEquals("Team1", result.getNome());
    }
}