package br.cefet.trab.oficinamecanica.view.botoes;

import br.cefet.trab.oficinamecanica.dao.ProdutoDao;
import br.cefet.trab.oficinamecanica.entidade.Produto;
import br.cefet.trab.oficinamecanica.view.produto.ProdutoAlterarJDialog;
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * Classe que gera botoes na tabela de cadastro de produtos
 *
 * @author arthur
 */
public class ButtonColumnProd extends AbstractCellEditor
        implements TableCellRenderer, TableCellEditor, ActionListener {

    JTable table;
    JButton renderButton;
    JButton editButton;
    String text;
    List lista;
    String nomecoluna;

    public ButtonColumnProd(JTable table, int column, List lista) throws IOException {
        super();
        this.lista = lista;
        this.table = table;
        renderButton = new JButton();
        editButton = new JButton();
        editButton.setFocusPainted(false);
        editButton.addActionListener(this);
        nomecoluna = table.getColumnName(column);
        Icon icone = null;
        if (nomecoluna.equalsIgnoreCase("Excluir")) {
            icone = new ImageIcon("../src/br/cefet/trab/oficinamecanica/images/icons8-remover-32.png");
        } else if (nomecoluna.equalsIgnoreCase("Alterar")) {
            icone = new ImageIcon("../src/br/cefet/trab/oficinamecanica/images/icons8-editar-32.png");
        }
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
        int codigo = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0));
        if (nomecoluna.equalsIgnoreCase("Excluir")) {
            int opt = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do produto de código " + codigo + "?");
            if (opt == JOptionPane.OK_OPTION) {
                ProdutoDao fDao = new ProdutoDao();
                try {
                    Produto f = fDao.consultarPorCod(codigo);
                    f.setObsoleto(true);
                    fDao.Alterar(f);
                } catch (Exception ex) {
                    Logger.getLogger(ButtonColumnFunc.class.getName()).log(Level.SEVERE, null, ex);
                }
                lista.remove(table.getSelectedRow());
                tModel.removeRow(table.getSelectedRow());
                table.setModel(tModel);
            }

        } else if (nomecoluna.equalsIgnoreCase("Alterar")) {
            ProdutoAlterarJDialog alterar = new ProdutoAlterarJDialog(null, true, codigo);
            alterar.setVisible(true);
        }
    }
}
