package com.insper.partida.equipe.exception;

public class TeamNotFoundException extends RuntimeException{

    public TeamNotFoundException(){
        super("Time não encontrado!");
    }
    
}
