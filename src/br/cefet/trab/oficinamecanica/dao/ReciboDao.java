/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.trab.oficinamecanica.dao;

import br.cefet.trab.oficinamecanica.entidade.Recibo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Classe faz inserção e consulta  de recibos no MySQL
 * @author Arthur
 */
public class ReciboDao extends Dao{
    public int inserirRecibo(Recibo p) throws Exception {
        int ret = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "insert into recibo ( formapag, cdvenda)" + "values (?, ?) ";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getFormapag());
            ps.setInt(2, p.getCdvenda());
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
}
