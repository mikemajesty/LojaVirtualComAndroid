package com.br.spellsoft.Entidades;

/**
 * Created by mike on 02/01/16.
 */
public enum Sexo {
    FEMININO("Feminino"),
    MASCULINO("Masculino");

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private  String descricao;
    private Sexo(String descricao){
        this.descricao=descricao;
    }
    public static Sexo GetSexo(int pos) {
        for (Sexo sex :Sexo.values()){
            if (sex.ordinal() == pos)
                return sex;
        }
        return  null;
    }
}
