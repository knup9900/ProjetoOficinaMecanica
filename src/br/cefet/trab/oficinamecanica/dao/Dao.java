/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.trab.oficinamecanica.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que faz a ligação do netbenas com o MySQL
 * @author Arthur
 */
public class Dao {
    public Connection getConnection() throws Exception {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/oficina", "root", "123456");
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage(), ex);
        }

        return conn;
    }

    public void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {

        }
    }
}
