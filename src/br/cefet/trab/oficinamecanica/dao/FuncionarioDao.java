/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.trab.oficinamecanica.dao;

import br.cefet.trab.oficinamecanica.entidade.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que insere e consulta funcionarios no MySQL
 * @author Arthur
 */
public class FuncionarioDao extends Dao{
    public int inserirFuncionario(Funcionario p) throws Exception {
        int ret = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "insert into funcionario (nmfuncionario, endereco, tel, obsoleto)" + "values (?, ?, ?, ?) ";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNmfuncionario());
            ps.setString(2, p.getEndereco());
            ps.setString(3, p.getTel());
            ps.setBoolean(4, p.isObsoleto());
            ps.execute();
            
            
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ret = rs.getInt(1);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }finally {
             conn.close();
        }
        return ret;
    }

    public Funcionario consultarPorCod(int cod) throws Exception {
        Funcionario p = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "select * from funcionario where cdfuncionario = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                p = new Funcionario();
                p = getFuncionarioFromRs(rs);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        } finally {
             conn.close();
        }

        return p;
    }

    public List<Funcionario> consultarTodos() throws Exception {
        List<Funcionario> pList = new ArrayList<Funcionario>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "select * from funcionario where obsoleto = 0";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Funcionario p = getFuncionarioFromRs(rs);
                pList.add(p);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
        
        return pList;
    }

    public List<Funcionario> consultarPorNome(String nome) throws Exception {
        List<Funcionario> pList = new ArrayList<Funcionario>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql
                    = " select * from funcionario "
                    + " where nmfuncionario like ?  and obsoleto = 0";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nome + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Funcionario p = getFuncionarioFromRs(rs);
                pList.add(p);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
        return pList;
    }

    public Funcionario getFuncionarioFromRs(ResultSet rs) throws SQLException {
        Funcionario p = new Funcionario();
        p.setCdfuncionario(rs.getInt("cdfuncionario"));
        p.setNmfuncionario(rs.getString("nmfuncionario"));
        p.setEndereco(rs.getString("endereco"));
        p.setTel(rs.getString("tel"));
        p.setObsoleto(rs.getBoolean("obsoleto"));
        return p;
    }
    public void Alterar(Funcionario p) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            String sql 
                    = " update funcionario "
                    + " set nmfuncionario = ?, "
                    + "    endereco = ?, "
                    + "    tel = ? ,"
                    + "    obsoleto = ?"
                    + " where cdfuncionario = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNmfuncionario());
            ps.setString(2, p.getEndereco());
            ps.setString(3, p.getTel());
            ps.setBoolean(4, p.isObsoleto());
            ps.setInt(5, p.getCdfuncionario());
            ps.execute();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
    }
}
