package extra_edicion;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import java.awt.Component;

public class TableActionCellEditor extends DefaultCellEditor {

    private TableActionEvent event;

    public TableActionCellEditor(TableActionEvent event) {
        super(new JCheckBox()); // Constructor requerido por DefaultCellEditor
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelAction action = new PanelAction();
        action.initEvent(event, row); // Le pasamos la fila actual y el evento
        action.setBackground(jtable.getSelectionBackground()); // Para que combine al seleccionar
        return action;
    }
}