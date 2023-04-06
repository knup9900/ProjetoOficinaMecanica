/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.trab.oficinamecanica.entidade;

/**
 * classe que cria objetos com campos necessarios para inserção e consulta de dados no MySQL
 * @author Arthur
 */
public class Produto {
    private int cdproduto;
    private String nome;
    private double preco;
    private int quantidade;
    private int qtdVenda;
    private boolean obsoleto;

    public boolean isObsoleto() {
        return obsoleto;
    }

    public void setObsoleto(boolean obsoleto) {
        this.obsoleto = obsoleto;
    }
    
    public int getQtdVenda() {
        return qtdVenda;
    }

    public void setQtdVenda(int qtdVenda) {
        this.qtdVenda = qtdVenda;
    }

    public int getCdproduto() {
        return cdproduto;
    }

    public void setCdproduto(int cdproduto) {
        this.cdproduto = cdproduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
