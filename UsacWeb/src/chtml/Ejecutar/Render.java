package chtml.Ejecutar;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Aroche
 */
public class Render extends DefaultTableCellRenderer {

    int num = 0;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        if (value instanceof JButton) {
            JButton btn = (JButton) value;
            btn.setSize(150, 100);
            //System.out.println("Este es mi boton: " + btn.getName());
            JButton nuevo = Boton.obtenerBoton(btn.getName());
                System.out.println("color = " + nuevo.getBackground().toString());
                if (nuevo != null) {
                    btn = nuevo;
                }

            if (isSelected) {
                //System.out.println("Que puchis");
                btn.setForeground(table.getSelectionForeground());
                btn.setBackground(table.getSelectionBackground());
            } else {
                btn.setForeground(table.getForeground());
                btn.setBackground(UIManager.getColor("Button.background"));
            }
            return btn;
        }

        return super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }

}
