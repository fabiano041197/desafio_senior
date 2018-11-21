/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author fabia
 */
public class Item {

    int id;
    String nome;
    String un_medida;
    float preco;
    boolean perecivel;
    int quantidade;
    String data_validade;
    String data_fabricacao;

    public Item(int id, String nome, String un_medida, float preco, boolean perecivel, int quantidade, String data_validade, String data_fabricacao) {
        this.id = id;
        this.nome = nome;
        this.un_medida = un_medida;
        this.preco = preco;
        this.perecivel = perecivel;
        this.quantidade = quantidade;
        this.data_validade = data_validade;
        this.data_fabricacao = data_fabricacao;
    }

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

    public String getUn_medida() {
        return un_medida;
    }

    public void setUn_medida(String un_medida) {
        this.un_medida = un_medida;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public boolean isPerecivel() {
        return perecivel;
    }

    public void setPerecivel(boolean perecivel) {
        this.perecivel = perecivel;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getData_validade() {
        return data_validade;
    }

    public void setData_validade(String data_validade) {
        this.data_validade = data_validade;
    }

    public String getData_fabricacao() {
        return data_fabricacao;
    }

    public void setData_fabricacao(String data_fabricacao) {
        this.data_fabricacao = data_fabricacao;
    }

}
