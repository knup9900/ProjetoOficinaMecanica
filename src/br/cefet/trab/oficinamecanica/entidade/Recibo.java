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
public class Recibo {
    private int cdrecibo;
    private String formapag;
    private int cdvenda;

    public int getCdrecibo() {
        return cdrecibo;
    }

    public void setCdrecibo(int cdrecibo) {
        this.cdrecibo = cdrecibo;
    }

    public String getFormapag() {
        return formapag;
    }

    public void setFormapag(String formapag) {
        this.formapag = formapag;
    }

    public int getCdvenda() {
        return cdvenda;
    }

    public void setCdvenda(int cdvenda) {
        this.cdvenda = cdvenda;
    }
}
