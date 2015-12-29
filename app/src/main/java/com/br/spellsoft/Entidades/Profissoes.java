package com.br.spellsoft.Entidades;

/**
 * Created by mike on 28/12/15.
 */
public enum Profissoes {

    PROGRAMADOR("Arquiteto de software"),
    ANALISTA("Pedreiro"),
    ENGENHEIRO("Engenheiro de software"),
    ARQUITETO("Arquiteto de software"),
    CARTEIRO("Carteiro");

    private String descricao;
    public String getDescricao() {
        return descricao;
    }


    private Profissoes(String descricao){
        this.descricao = descricao;
    }
}

