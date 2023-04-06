/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.trab.oficinamecanica.dao;

import br.cefet.trab.oficinamecanica.entidade.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que consultar dados de login no MySQL
 * @author Arthur
 */
public class LoginDao extends Dao{
    
    public int inserirLogin(Login l) throws Exception {
        int ret = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "insert into login (usuario, senha, cdfuncionario)" + "values (?, ?, ?) ";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, l.getUsuario());
            ps.setString(2, l.getSenha());
            ps.setInt(3, l.getCdFuncionario());
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
    
    public Login consultarTodos(String usuario, String senha) throws Exception {
        Login login = new Login();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "select * from login where usuario = ? and senha = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            rs = ps.executeQuery();
            while (rs.next()) {
                login = getLoginFromRs(rs);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
        
        return login;
    }
    public List<Login> consultarTodos2() throws Exception {
        List<Login> pList = new ArrayList<Login>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "select * from login ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Login p = getLoginFromRs(rs);
                pList.add(p);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
        
        return pList;
    }
     
     public Login getLoginFromRs(ResultSet rs) throws SQLException {
        Login p = new Login();
        p.setIdlogin(rs.getInt("idlogin"));
        p.setUsuario(rs.getString("usuario"));
        p.setSenha(rs.getString("senha"));
        p.setCdFuncionario(rs.getInt("cdfuncionario"));
        return p;
    }
}
