/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefet.trab.oficinamecanica.dao;

import br.cefet.trab.oficinamecanica.entidade.VendaRelatorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que consulta venda no MySQL para gerar relatórios
 * @author Arthur
 */
public class VendaRelatorioDao extends Dao{
    
    
    public static String sql(){
        String sql =
                " select * from " +
                " ( " +
                " select  v.cdvenda,v.nome,v.placacarro,v.precototal, svs.descricao ,svs.preco, 1 as quantidade, svs.preco as subtotal, \"SERVICO\" as tipo, f.nmfuncionario " +
                " from  " +
                "      funcionario f, "+
                "      venda v, " +
                "      sv_servico svs, " +
                "      servico s " +
                " where svs.cdvenda = v.cdvenda " +
                "   and v.cdfuncionario = f.cdfuncionario " +
                "   and svs.cdservico=s.cdservico " +
                "   " +
                "   union ALL " +
                "   " +
                " select  v.cdvenda,v.nome,v.placacarro,v.precototal, p.nome as descricao, p.preco as preco, pvp.quantidade, p.preco*pvp.quantidade as subtotal, \"PRODUTO\" as tipo, f.nmfuncionario " +
                " from  " +
                "     funcionario f, "+
                "     venda v, " +
                "     pv_produto pvp, " +
                "     produto p " +
                "     " +
                " where pvp.cdvenda = v.cdvenda " +
                "   and v.cdfuncionario = f.cdfuncionario " +
                "   and pvp.cdproduto=p.cdproduto " +
                " ) unionTable " +
                " order by cdvenda, tipo ";
        return sql;
    }
    
   public static String sqlFiltro(){
        String sql =
                " select * from " +
                " ( " +
                " select  v.cdvenda,v.nome,v.placacarro,v.precototal, svs.descricao ,svs.preco, 1 as quantidade, svs.preco as subtotal, \"SERVICO\" as tipo, f.nmfuncionario " +
                " from  " +
                "      funcionario f, "+
                "      venda v, " +
                "      sv_servico svs, " +
                "      servico s " +
                " where svs.cdvenda = v.cdvenda " +
                "   and v.cdfuncionario = f.cdfuncionario " +
                "   and svs.cdservico=s.cdservico " +
                "  and v.datavenda BETWEEN  ? AND ? "+
                "  and v.nome like ? "+
                "  and v.placacarro like ? "+
                "  and v.precototal >= ? and v.precototal <= ? "+
                "  and f.nmfuncionario like ? "+
                "   " +
                "   union ALL " +
                "   " +
                " select  v.cdvenda,v.nome,v.placacarro,v.precototal, p.nome as descricao, p.preco as preco, pvp.quantidade, p.preco*pvp.quantidade as subtotal, \"PRODUTO\" as tipo, f.nmfuncionario " +
                " from  " +
                "     funcionario f, "+
                "     venda v, " +
                "     pv_produto pvp, " +
                "     produto p " +
                "     " +
                " where pvp.cdvenda = v.cdvenda " +
                "   and v.cdfuncionario = f.cdfuncionario " +
                "   and pvp.cdproduto=p.cdproduto " +
                "  and v.datavenda BETWEEN  ? AND ? "+
                "  and v.nome like ? "+
                "  and v.placacarro like ? "+
                "  and v.precototal >= ? and v.precototal <= ? "+
                "  and f.nmfuncionario like ? "+
                " ) unionTable " +
                " order by cdvenda, tipo ";
        return sql;
    }

   public List<VendaRelatorio> consultarTodos() throws Exception {
        List<VendaRelatorio> pList = new ArrayList<VendaRelatorio>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = sql();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                VendaRelatorio p = getVendaFromRs(rs);
                pList.add(p);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
        return pList;
    }

   public List<VendaRelatorio> consultarPorFiltro(String nome, String placa, Double precomin, Double precomax, String datamin, String datamax, String nmfuncionario) throws Exception {
        List<VendaRelatorio> pList = new ArrayList<VendaRelatorio>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = sqlFiltro();
            ps = conn.prepareStatement(sql);
            ps.setString(1, datamin );
            ps.setString(2, datamax );
            ps.setString(3, nome +"%" );
            ps.setString(4, placa + "%" );
            ps.setDouble(5, precomin);
            ps.setDouble(6, precomax);
            ps.setString(7, nmfuncionario + "%");
            ps.setString(8, datamin ); 
            ps.setString(9, datamax );
            ps.setString(10,  nome + "%" );
            ps.setString(11, placa + "%" );
            ps.setDouble(12, precomin );
            ps.setDouble(13, precomax );
            ps.setString(14, nmfuncionario + "%" );
            rs = ps.executeQuery();
            while (rs.next()) {
                VendaRelatorio p = getVendaFromRs(rs);
                pList.add(p);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
        return pList;
    }

    public VendaRelatorio getVendaFromRs(ResultSet rs) throws SQLException {
        VendaRelatorio p = new VendaRelatorio();
        p.setCdvenda(rs.getInt("cdvenda"));
        p.setNome(rs.getString("nome"));
        p.setPlacacarro(rs.getString("placacarro"));
        p.setPrecototal(rs.getDouble("precototal"));
        p.setDescricao(rs.getString("descricao"));
        p.setPreco(rs.getDouble("preco"));
        p.setSubtotal(rs.getDouble("subtotal"));
        p.setTipo(rs.getString("tipo"));
        p.setNmfuncionario(rs.getString("nmfuncionario"));
        p.setQuantidade(rs.getInt("quantidade"));
        return p;
    }    
}
