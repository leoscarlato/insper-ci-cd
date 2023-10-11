package com.insper.partida.tabela;

import com.insper.partida.tabela.dto.TabelaReturnDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/tabela")
public class TabelaController {

    @Autowired
    private TabelaService tabelaService;

    @GetMapping
    public List<Tabela> getTabela() {
        return tabelaService.getTabela();
    }

    @GetMapping("/{nome}")
    public Tabela getTabelaByNome(@PathVariable String nome) {
        return tabelaService.getTabelaByNome(nome);
    }

    @PostMapping
    public TabelaReturnDTO saveTabela(Tabela tabela) {
        return TabelaReturnDTO.covert(tabelaService.saveTabela(tabela));
    }

}