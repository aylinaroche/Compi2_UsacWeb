package tabla;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class MyTable extends JTable {

    /**
     * Constructor.
     */
    public MyTable() {

        this.setRowHeight(30);

        JComboBox states = new JComboBox(new String[]{"España",
            "Argentina",
            "EEUU"}
        );

        JButton b1 = new JButton();
        b1.setSize(150, 30);
        b1.setText("Boton");
        b1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {
                System.out.println("Acciiiiiiioooooooooooon");
            }

        });
        b1.setBackground(Color.BLACK);
        String[] columnNames = new String[]{"Nombre", "email", "Fumador", "Nacionalidad", ""};
        Object[][] data = new Object[][]{
            {"Angel", "angelarcosheredia@gmail.com", false, "Click para elegir", b1},
            {"Juan", "juan@gmail.com", false, "Click para elegir", new JButton("Reset")},
            {"Ana", "ana@hotmail.com", false, "Click para elegir", new JButton("Reset")}
        };

        MyTableModel model = new MyTableModel(columnNames, data);

        // Establecemos el modelo
        this.setModel(model);

        // Establecemos el renderer y editor que usaremos para el boton
        this.setDefaultRenderer(JButton.class, new ButtonCellRenderer());
        this.setDefaultEditor(JButton.class, new ButtonCellEditor());

        // Editores para cada tipo de objeto, estos nos permitirán darles el comportamiento adecuado
        this.getColumn("Nacionalidad").setCellEditor(new DefaultCellEditor(states));
        this.setDefaultEditor(JCheckBox.class, new DefaultCellEditor(new JCheckBox()));

    }

}
