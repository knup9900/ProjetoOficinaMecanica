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
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que insere a ligação entre venda e produto no MySQL
 * @author Arthur
 */
public class PVProdutoDao extends Dao{
    public void inserir(int cdvenda, Produto s, int qtd) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();

            String sql = " insert into pv_produto (cdvenda, cdproduto, quantidade, precocada) "
                    + " values (?, ?, ?, ?) ";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, cdvenda);
            ps.setInt(2, s.getCdproduto());
            ps.setInt(3, qtd);
            ps.setDouble(4, s.getPreco());

            ps.execute();

        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
    }
    
    public void excluirProdutoVenda(int cod) throws Exception {     
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            String sql = "delete from pv_produto where cdvenda = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cod);
            ps.execute();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
    }
    
    public List<Produto> consultarPorCdvenda(int cdvenda) throws Exception {
        List<Produto> pList = new ArrayList<Produto>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = " select s.cdproduto, s.nome   , oss.precocada, oss.quantidade "
                    + " from produto s, pv_produto oss "
                    + " where s.cdproduto = oss.cdproduto "
                    + "   and oss.cdvenda = ? ";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cdvenda);
            rs = ps.executeQuery();

            while (rs.next()) {
                Produto s = getProdutoFromRs(rs);
                pList.add(s);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }

        return pList;
    }
    public int consultarQuantidade(int cdvenda, int codprod) throws Exception {
        
        int qtd = 0;
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = " select oss.quantidade "
                    + " from produto s, pv_produto oss "
                    + " where s.cdproduto = oss.cdproduto "
                    + "   and oss.cdvenda = ? ";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cdvenda);
            rs = ps.executeQuery();

            while (rs.next()) {
                qtd = getQuantidadeFromRs(rs);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }

        return qtd;
    }
    private Produto getProdutoFromRs(ResultSet rs) throws SQLException {
        Produto s = new Produto();
        s.setCdproduto(rs.getInt("cdproduto"));
        s.setNome(rs.getString("nome"));
        s.setPreco(rs.getDouble("precocada"));
        s.setQuantidade(rs.getInt("quantidade"));
        return s;
    }
    private int getQuantidadeFromRs(ResultSet rs) throws SQLException {
        int s;
        s = rs.getInt("quantidade");
        return s;
    }
}
