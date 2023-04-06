/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.trab.oficinamecanica.dao;


import br.cefet.trab.oficinamecanica.entidade.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que insere e consultar produtos no MySQL
 * @author Arthur
 */
public class ProdutoDao extends Dao{
    public int inserirProduto(Produto p) throws Exception {
        int ret = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "insert into produto ( nome, preco, quantidade, obsoleto)" + "values (?, ?, ?, ?) ";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco());
            ps.setInt(3, p.getQuantidade());
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

    public Produto consultarPorCod(int cod) throws Exception {
        Produto p = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "select * from produto where cdproduto = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                p = new Produto();
                p = getProdutoFromRs(rs);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        } finally {
             conn.close();
        }

        return p;
    }

    public List<Produto> consultarTodos() throws Exception {
        List<Produto> pList = new ArrayList<Produto>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "select * from produto where obsoleto = 0";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Produto p = getProdutoFromRs(rs);
                pList.add(p);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
        
        return pList;
    }

    public List<Produto> consultarPorNome(String nome) throws Exception {
        List<Produto> pList = new ArrayList<Produto>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql
                    = " select * from produto "
                    + " where nome like ? and obsoleto = 0";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nome + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Produto p = getProdutoFromRs(rs);
                pList.add(p);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
        return pList;
    }
     public List<Produto> consultarRelatorio(String nome, double precomin, double precomax, int qtdmin, int qtdmax) throws Exception {
        List<Produto> pList = new ArrayList<Produto>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql
                    = " select * from produto "
                    + " where quantidade >= ? and quantidade <= ?"
                    + " and preco >= ? and preco <= ?"
                    + " and nome  like ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, qtdmin);
            ps.setInt(2, qtdmax);
            ps.setDouble(3, precomin);
            ps.setDouble(4, precomax);
            ps.setString(5,  nome + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Produto p = getProdutoFromRs(rs);
                pList.add(p);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
        return pList;
    }

    public Produto getProdutoFromRs(ResultSet rs) throws SQLException {
        Produto p = new Produto();
        p.setCdproduto(rs.getInt("cdproduto"));
        p.setNome(rs.getString("nome"));
        p.setPreco(rs.getDouble("preco"));
        p.setQuantidade(rs.getInt("quantidade"));
        p.setObsoleto(rs.getBoolean("obsoleto"));

        return p;
    }
    public void Alterar(Produto p) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            String sql 
                    = " update produto "
                    + " set nome = ?, "
                    + "    preco = ?, "
                    + "    quantidade = ?, "
                    + "    obsoleto = ?"
                    + " where cdproduto = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco());
            ps.setInt(3, p.getQuantidade());
            ps.setBoolean(4, p.isObsoleto());
            ps.setInt(5, p.getCdproduto());
            ps.execute();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
    }
}
