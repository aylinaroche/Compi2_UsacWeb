package usacweb;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author Aroche
 */
public class PanelHistorial extends javax.swing.JPanel {
    
    JScrollPane scroll = new JScrollPane();
    JLabel titulo = new JLabel();
    
    public PanelHistorial() {
        Box boxV1 = Box.createVerticalBox();
        titulo.setText("HISTORIAL");
        titulo.setFont(new java.awt.Font("Tempus Sans ITC", 1, 20));
        titulo.setPreferredSize(new Dimension(500, 40));
        titulo.setMaximumSize(titulo.getPreferredSize());
        titulo.setForeground(new Color(102, 0, 51));
        boxV1.add(titulo);
        
        for (int i = 0; i < UsacWeb.listaHistorial.size(); i++) {
            Box boxH = Box.createHorizontalBox();
            Historial h = UsacWeb.listaHistorial.get(i);
            JLabel ruta = new JLabel();
            ruta.setFont(new Font("Candara", 0, 18));
            ruta.setText(h.fecha + "        " + h.ruta + "        ");
            JButton boton = new JButton();
            boton.setText("IR");
            boton.setBounds(10, 10, 40, 40);
            boton.setBackground(new Color(102, 204, 0));
            boton.setForeground(new Color(255, 255, 255));
            boton.setFont(new Font("MS Reference Sans Serif", 0, 14));
            
            boton.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ActionPerformed(evt);
                }
                
                private void ActionPerformed(ActionEvent evt) {
                    Metodo.crearPestania(h.ruta);
                }
            });
            
            boxH.add(ruta);
            boxH.add(boton);
            boxV1.add(boxH);
            
        }
        
        add(boxV1);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
