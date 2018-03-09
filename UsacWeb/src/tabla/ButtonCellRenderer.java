package tabla;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class ButtonCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {

        // Devolvemos el botón tal cual
        if (value instanceof JButton) {
            return (JButton) value;
        } else if (value instanceof String) {
           // System.out.println("StringggggggggggggggWW");
        }
        //return null;
        return super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);
    }

}
