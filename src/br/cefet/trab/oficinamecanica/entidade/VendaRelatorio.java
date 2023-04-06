/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.trab.oficinamecanica.entidade;

/**
 * Classe que cria objetos com valores necessarios para relatorio
 * @author Arthur
 */
public class VendaRelatorio {
    private int cdvenda;
    private String nome;
    private String placacarro;
    private Double precototal;
    private String descricao;
    private Double preco;
    private Double subtotal;
    private String tipo;    
    private String nmfuncionario;
    private int quantidade;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNmfuncionario() {
        return nmfuncionario;
    }

    public void setNmfuncionario(String nmfuncionario) {
        this.nmfuncionario = nmfuncionario;
    }

    public int getCdvenda() {
        return cdvenda;
    }

    public void setCdvenda(int cdvenda) {
        this.cdvenda = cdvenda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPlacacarro() {
        return placacarro;
    }

    public void setPlacacarro(String placacarro) {
        this.placacarro = placacarro;
    }

    public Double getPrecototal() {
        return precototal;
    }

    public void setPrecototal(Double precototal) {
        this.precototal = precototal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
