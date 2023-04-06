package br.cefet.trab.oficinamecanica.view.botoes;

import br.cefet.trab.oficinamecanica.dao.ProdutoDao;
import br.cefet.trab.oficinamecanica.entidade.Produto;
import br.cefet.trab.oficinamecanica.view.venda.AlterarVendaJDialog;
import br.cefet.trab.oficinamecanica.view.venda.InserirVendaJDialog;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * Classe que gera botoes na tabela de alterar e inserir venda
 * @author arthur
 */

public class ButtonColumn extends AbstractCellEditor
        implements TableCellRenderer, TableCellEditor, ActionListener {
    
    

    JTable table;
    JButton renderButton;
    JButton editButton;
    String text;
    List lista; 
    InserirVendaJDialog jDialog;
    AlterarVendaJDialog jDialog2;

    public ButtonColumn(JTable table, int column, List lista, InserirVendaJDialog jDialog, AlterarVendaJDialog jDialog2) throws IOException {
        super();
        this.jDialog2 = jDialog2;
        this.jDialog = jDialog;
        this.lista = lista;
        this.table = table;
        renderButton = new JButton();
        editButton = new JButton();
        editButton.setFocusPainted(false);
        editButton.addActionListener(this);
       ImageIcon icone = new ImageIcon("../src/br/cefet/trab/oficinamecanica/images/icons8-remover-32.png");
        renderButton.setIcon(icone);
        renderButton.setPreferredSize(new Dimension(60, 48));

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);
        columnModel.getColumn(column).setCellEditor(this);
    }

    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (hasFocus) {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        } else if (isSelected) {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setBackground(table.getSelectionBackground());
        } else {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        }

        renderButton.setText((value == null) ? "" : value.toString());
        return renderButton;
    }

    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column) {
        text = (value == null) ? "" : value.toString();
        editButton.setText(text);

        return editButton;
    }

    public Object getCellEditorValue() {
        return text;
    }

    public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
        DefaultTableModel tModel = (DefaultTableModel) table.getModel();
        
        if(jDialog != null){
            Double precototal = jDialog.getPrecoTotal();
            String precoSub = (String) table.getValueAt(table.getSelectedRow(), 2);
            precoSub = precoSub.replace(",", ".");
            Double precoSubtrair = Double.parseDouble(precoSub);
            precototal = precototal - precoSubtrair;
            jDialog.atualizarTotal(precototal);
            jDialog.setValor(precototal);
            if(table.getColumnName(3).equalsIgnoreCase("Quantidade")){
                ProdutoDao produtoDao = new ProdutoDao();
                int cod = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0));
                Produto produto = new Produto();
                try {
                    produto = produtoDao.consultarPorCod(cod);
                } catch (Exception ex) {
                    Logger.getLogger(ButtonColumn.class.getName()).log(Level.SEVERE, null, ex);
                }
                int quant = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 3));
                produto.setQuantidade(produto.getQuantidade() + quant );
                try {
                    produtoDao.Alterar(produto);
                } catch (Exception ex) {
                    Logger.getLogger(ButtonColumn.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if(jDialog2 != null){
            Double precototal = jDialog2.getPrecoTotal();
            String precoSub = (String) table.getValueAt(table.getSelectedRow(), 2);
            precoSub = precoSub.replace(",", ".");
            Double precoSubtrair = Double.parseDouble(precoSub);
            precototal = precototal - precoSubtrair;
            jDialog2.atualizarTotal(precototal);
            jDialog2.setValor(precototal);
            if(table.getColumnName(3).equalsIgnoreCase("Quantidade")){
                ProdutoDao produtoDao = new ProdutoDao();
                int cod = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0));
                Produto produto = new Produto();
                try {
                    produto = produtoDao.consultarPorCod(cod);
                } catch (Exception ex) {
                    Logger.getLogger(ButtonColumn.class.getName()).log(Level.SEVERE, null, ex);
                }
                int quant = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 3));
                produto.setQuantidade(produto.getQuantidade() + quant );
                try {
                    produtoDao.Alterar(produto);
                } catch (Exception ex) {
                    Logger.getLogger(ButtonColumn.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        lista.remove(table.getSelectedRow());     
        tModel.removeRow(table.getSelectedRow());       
        table.setModel(tModel);
        
        
    }
}
