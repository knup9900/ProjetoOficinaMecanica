/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.trab.oficinamecanica.dao;

import br.cefet.trab.oficinamecanica.entidade.ReciboRelatorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * classe que faz consultar de recibos no MySQL para gerar relatório
 * @author Arthur
 */
public class ReciboRelatorioDao extends Dao{
    
    
    public static String sql(){
        String sql =
                " select r.cdrecibo, v.nome, v.precototal, v.datavenda, f.nmfuncionario, r.formapag " +
                " from venda v, " +
                "   funcionario f, " +
                "   recibo r  " +
                "where v.cdfuncionario = f.cdfuncionario " +
                "   and v.cdvenda = r.cdvenda "+
                "   and v.cdvenda = ? ";
        return sql;
    }
  
   public List<ReciboRelatorio> consultar(int cdvenda) throws Exception {
        List<ReciboRelatorio> pList = new ArrayList<ReciboRelatorio>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = sql();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cdvenda);
            rs = ps.executeQuery();
            while (rs.next()) {
                ReciboRelatorio p = getReciboFromRs(rs);
                pList.add(p);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
        return pList;
    }

   

    public ReciboRelatorio getReciboFromRs(ResultSet rs) throws SQLException {
        ReciboRelatorio p = new ReciboRelatorio();
        p.setCdrecibo(rs.getInt("cdrecibo"));
        p.setNome(rs.getString("nome"));
        p.setNmfuncionario(rs.getString("nmfuncionario"));
        p.setPrecototal(rs.getDouble("precototal"));
        p.setDatavenda(rs.getDate("datavenda"));
        p.setFormapag(rs.getString("formapag"));
        
        return p;
    }    
}
