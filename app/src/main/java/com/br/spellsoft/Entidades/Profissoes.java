package com.br.spellsoft.Entidades;

/**
 * Created by mike on 28/12/15.
 */
public enum Profissoes {

    PROGRAMADOR("Arquiteto de software"),
    ANALISTA("Analista"),
    ENGENHEIRO("Engenheiro de software"),
    ARQUITETO("Arquiteto de software"),
    CARTEIRO("Carteiro");

    private String descricao;
    public String getDescricao() {
        return descricao;
    }

        public static Profissoes GetProfissao(int posicao)
        {
            for (Profissoes p: Profissoes.values()){
                if (p.ordinal() == posicao){
                    return p;
                }
            }
            return null;
        }

    private Profissoes(String descricao){
        this.descricao = descricao;
    }
}

