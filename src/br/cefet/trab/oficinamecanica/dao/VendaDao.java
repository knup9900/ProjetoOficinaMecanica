/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.trab.oficinamecanica.dao;

import br.cefet.trab.oficinamecanica.entidade.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * classe que insere e consulta venda no MySQL
 * @author Arthur
 */
public class VendaDao extends Dao{
    public int inserirvenda(Venda p) throws Exception {
        int ret = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "insert into venda (nome, placacarro, modelo, precototal, cdfuncionario, situacao, datavenda, obsoleto)" 
                          + "values (?, ?, ?, ?, ?, ?, ?, ?) ";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNome());
            ps.setString(2, p.getPlacaCarro());
            ps.setString(3, p.getModelo());
            ps.setDouble(4, p.getPrecoTotal());
            ps.setInt(5, p.getCdFuncionario());
            ps.setString(6, p.getSituacao());
            ps.setTimestamp(7, new Timestamp(p.getDatavenda().getTime()));
            ps.setBoolean(8, p.isObsoleto());
            
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

    public Venda consultarPorCod(int cod) throws Exception {
        Venda p = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "select * from venda where cdvenda = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                p = new Venda();
                p = getVendaFromRs(rs);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        } finally {
             conn.close();
        }

        return p;
    }

    public List<Venda> consultarTodos() throws Exception {
        List<Venda> pList = new ArrayList<Venda>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "select * from venda where obsoleto = 0";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venda p = getVendaFromRs(rs);
                pList.add(p);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
        return pList;
    }
    
    public List<Venda> consultarPorVendedor(int cdfuncionario) throws Exception {
        List<Venda> pList = new ArrayList<Venda>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "select * from venda where obsoleto = 0 and cdfuncionario = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cdfuncionario);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venda p = getVendaFromRs(rs);
                pList.add(p);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
        return pList;
    }

    public List<Venda> consultarPorNome(String nome) throws Exception {
        List<Venda> pList = new ArrayList<Venda>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql
                    = " select * from venda "
                    + " where nome like ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nome + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Venda p = getVendaFromRs(rs);
                pList.add(p);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
        return pList;
    }

    public Venda getVendaFromRs(ResultSet rs) throws SQLException {
        Venda p = new Venda();
        p.setCdVenda(rs.getInt("cdvenda"));
        p.setNome(rs.getString("nome"));
        p.setPlacaCarro(rs.getString("placacarro"));
        p.setModelo(rs.getString("modelo"));
        p.setPrecoTotal(rs.getDouble("precototal"));
        p.setCdFuncionario(rs.getInt("cdfuncionario"));
        p.setSituacao(rs.getString("situacao"));
        p.setDatavenda(rs.getDate("datavenda"));
        p.setObsoleto(rs.getBoolean("obsoleto"));
        return p;
    }
    public void Alterar(Venda p) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            String sql 
                    = " update venda "
                    + " set nome = ?, "
                    + "    placacarro = ?, "
                    + "    modelo = ?, "
                    + "    precototal = ?, "
                    + "    cdfuncionario = ?, "
                    + "    situacao = ?, "
                    + "    datavenda = ?, "
                    + "    obsoleto = ?"
                    + " where cdvenda = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setString(2, p.getPlacaCarro());
            ps.setString(3, p.getModelo());
            ps.setDouble(4, p.getPrecoTotal());
            ps.setInt(5, p.getCdFuncionario());
            ps.setString(6, p.getSituacao());
            ps.setTimestamp(7, new Timestamp(p.getDatavenda().getTime()));
            ps.setBoolean(8, p.isObsoleto());
            ps.setInt(9, p.getCdVenda());
            ps.execute();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
    }
     
}
