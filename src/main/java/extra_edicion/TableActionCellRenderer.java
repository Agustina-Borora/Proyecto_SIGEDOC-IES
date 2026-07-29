
package extra_edicion;



import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import java.awt.Component;


public class TableActionCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean blnl, int i, int il) {
        Component com = super.getTableCellRendererComponent(jtable, o, bln, blnl, i, il);
        
        PanelAction action = new PanelAction();
        return action;
    }
}
