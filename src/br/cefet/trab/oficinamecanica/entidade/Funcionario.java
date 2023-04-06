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
public class Funcionario {
    private int cdfuncionario;

    public int getCdfuncionario() {
        return cdfuncionario;
    }

    public void setCdfuncionario(int cdfuncionario) {
        this.cdfuncionario = cdfuncionario;
    }

    public String getNmfuncionario() {
        return nmfuncionario;
    }

    public void setNmfuncionario(String nmfuncionario) {
        this.nmfuncionario = nmfuncionario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereço) {
        this.endereco = endereço;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    private String nmfuncionario;
    private String endereco;

    public boolean isObsoleto() {
        return obsoleto;
    }

    public void setObsoleto(boolean obsoleto) {
        this.obsoleto = obsoleto;
    }
    private String tel;
    private boolean obsoleto;
}
