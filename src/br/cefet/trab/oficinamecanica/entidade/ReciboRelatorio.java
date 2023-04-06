/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.trab.oficinamecanica.entidade;

import java.util.Date;

/**
 * classe que cria objetos para consulta de dados no MySQL e eventualmente gerar relatórios
 * @author Arthur
 */
public class ReciboRelatorio {
    private int cdrecibo;
    private String nome;
    private Date datavenda;
    private String nmfuncionario;
    private String formapag;
    private Double precototal;

    public Double getPrecototal() {
        return precototal;
    }

    public void setPrecototal(Double precototal) {
        this.precototal = precototal;
    }

    public int getCdrecibo() {
        return cdrecibo;
    }

    public void setCdrecibo(int cdrecibo) {
        this.cdrecibo = cdrecibo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDatavenda() {
        return datavenda;
    }

    public void setDatavenda(Date datavenda) {
        this.datavenda = datavenda;
    }

    public String getNmfuncionario() {
        return nmfuncionario;
    }

    public void setNmfuncionario(String nmfuncionario) {
        this.nmfuncionario = nmfuncionario;
    }

    public String getFormapag() {
        return formapag;
    }

    public void setFormapag(String formapag) {
        this.formapag = formapag;
    }
}
