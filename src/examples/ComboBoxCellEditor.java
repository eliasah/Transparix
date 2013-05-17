package examples;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.*;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.TableCellEditor;

public class ComboBoxCellEditor extends AbstractCellEditor implements ActionListener, TableCellEditor, Serializable {
    
    private JComboBox comboBox;
    
    public ComboBoxCellEditor(JComboBox comboBox) {
        this.comboBox = comboBox;
        this.comboBox.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        // hitting enter in the combo box should stop cellediting (see below)
        this.comboBox.addActionListener(this);
        // remove the editor's border - the cell itself already has one
        ((JComponent) comboBox.getEditor().getEditorComponent()).setBorder(null);
    }
    
    private void setValue(Object value) {
        comboBox.setSelectedItem(value);
    }
    
    // Implementing ActionListener
    public void actionPerformed(java.awt.event.ActionEvent e) {
        // Selecting an item results in an actioncommand "comboBoxChanged".
        // We should ignore these ones.
        
        // Hitting enter results in an actioncommand "comboBoxEdited"
        if(e.getActionCommand().equals("comboBoxEdited")) {
            stopCellEditing();
        }
    }
    
    // Implementing CellEditor
    public Object getCellEditorValue() {
        return comboBox.getSelectedItem();
    }
    
    public boolean stopCellEditing() {
        if (comboBox.isEditable()) {
            // Notify the combo box that editing has stopped (e.g. User pressed F2)
            comboBox.actionPerformed(new ActionEvent(this, 0, ""));
        }
        fireEditingStopped();
        return true;
    }
    
    // Implementing TableCellEditor
    public java.awt.Component getTableCellEditorComponent(javax.swing.JTable table, Object value, boolean isSelected, int row, int column) {
        setValue(value);
        return comboBox;
    }
    
    // Implementing TreeCellEditor
//    public java.awt.Component getTreeCellEditorComponent(javax.swing.JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
//        String stringValue = tree.convertValueToText(value, isSelected, expanded, leaf, row, false);
//        setValue(stringValue);
//        return comboBox;
//    }    
}