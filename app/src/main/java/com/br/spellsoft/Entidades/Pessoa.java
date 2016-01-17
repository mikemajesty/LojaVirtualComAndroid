package com.br.spellsoft.Entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mike on 02/01/16.
 */
public class Pessoa  implements Serializable{
    private int id;
    private String nome;
    private String endereco;
    private String cpfCnpj;
    private Profissoes profissao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Profissoes getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissoes profissao) {
        this.profissao = profissao;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    private TipoPessoa tipoPessoa;
    private Sexo sexo;
    private Date dtNasc;

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", profissao=" + profissao +
                ", tipoPessoa=" + tipoPessoa +
                ", sexo=" + sexo +
                ", dtNasc=" + dtNasc +
                '}';
    }
}
