/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.trab.oficinamecanica.dao;

import br.cefet.trab.oficinamecanica.entidade.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que insere e consulta serviços no MySQL
 * @author Arthur
 */
public class ServicoDao extends Dao{
    public int inserirservico(Servico p) throws Exception {
        int ret = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "insert into servico ( valor, descricao, obsoleto)" + "values (?, ?, ?) ";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, p.getValor());
            ps.setString(2, p.getDescricao());
            ps.setBoolean(3, p.isObsoleto());
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

    public Servico consultarPorCod(int cod) throws Exception {
        Servico p = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "select * from servico where cdservico = ? and obsoleto = 0";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                p = new Servico();
                p = getServicoFromRs(rs);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        } finally {
             conn.close();
        }

        return p;
    }

    public List<Servico> consultarTodos() throws Exception {
        List<Servico> pList = new ArrayList<Servico>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "select * from servico where obsoleto = 0";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Servico p = getServicoFromRs(rs);
                pList.add(p);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
        
        return pList;
    }

    public List<Servico> consultarPorNome(String nome) throws Exception {
        List<Servico> pList = new ArrayList<Servico>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql
                    = " select * from servico "
                    + " where descricao like ? and obsoleto = 0 ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nome + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Servico p = getServicoFromRs(rs);
                pList.add(p);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
        return pList;
    }

    public Servico getServicoFromRs(ResultSet rs) throws SQLException {
        Servico p = new Servico();
        p.setCdServico(rs.getInt("cdservico"));
        p.setDescricao(rs.getString("descricao"));
        p.setValor(rs.getDouble("valor"));
        p.setObsoleto(rs.getBoolean("obsoleto"));
        return p;
    }
    public void Alterar(Servico p) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            String sql 
                    = " update servico "
                    + " set descricao = ?, "
                    + "    valor = ?, "
                    + "  obsoleto = ?"
                    + " where cdservico = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getDescricao());
            ps.setDouble(2, p.getValor());
            ps.setBoolean(3, p.isObsoleto());
            ps.setInt(4, p.getCdServico());
            ps.execute();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
    }
}
