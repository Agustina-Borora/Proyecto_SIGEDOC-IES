
package extra_edicion;



import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.Cursor;

public class ButtonAction extends JButton {
    
    public ButtonAction() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(3, 3, 3, 3));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}