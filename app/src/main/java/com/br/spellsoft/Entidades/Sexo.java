package com.br.spellsoft.Entidades;

/**
 * Created by mike on 02/01/16.
 */
public enum Sexo {
    FEMININO,
    MASCULINO;


    public static Sexo GetSexo(int pos) {
        for (Sexo sex :Sexo.values()){
            if (sex.ordinal() == pos)
                return sex;
        }
        return  null;
    }
}
