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
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que insere a ligação entre serviços e vendas no MySQL
 * @author Arthur
 */
public class SVServicoDao extends Dao{
     public void inserir(int cdvenda, Servico s) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();

            String sql = " insert into sv_servico (cdvenda, cdservico, descricao, preco) "
                    + " values (?, ?, ?, ?) ";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, cdvenda);
            ps.setInt(2, s.getCdServico());
            ps.setString(3, s.getDescricao());
            ps.setDouble(4, s.getValor());
            ps.execute();

        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
    }

    public List<Servico> consultarPorCdvenda(int cdvenda) throws Exception {
        List<Servico> pList = new ArrayList<Servico>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = " select s.cdservico, s.descricao, s.valor "
                    + " from servico s, sv_servico oss "
                    + " where s.cdservico = oss.cdservico "
                    + "   and oss.cdvenda = ? ";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cdvenda);
            rs = ps.executeQuery();

            while (rs.next()) {
                Servico s = getServicoFromRs(rs);
                pList.add(s);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }

        return pList;
    }
    
    public void excluirServicoVenda(int cod) throws Exception {     
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            String sql = "delete from sv_servico where cdvenda = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cod);
            ps.execute();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
    }
    
    private Servico getServicoFromRs(ResultSet rs) throws SQLException {
        Servico s = new Servico();
        s.setCdServico(rs.getInt("cdservico"));
        s.setDescricao(rs.getString("descricao"));
        s.setValor(rs.getDouble("valor"));

        return s;
    }
}
