package usacweb;

import chtml.chtml;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelPrincipal extends javax.swing.JPanel {

    JTextField ruta = new JTextField();
    JTextArea prueba = new JTextArea();
    JTabbedPane pestanias = new JTabbedPane();
    PanelSecundario panel = new PanelSecundario();
    JButton botonAtras = new JButton();
    JButton botonAdelante = new JButton();
    JButton botonEjecutar = new JButton();

    public PanelPrincipal() {
        Box boxH1 = Box.createHorizontalBox();

        botonAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/izquierda.jpg"))); // NOI18N
        botonAtras.setText("");
        botonAtras.setPreferredSize(new Dimension(35, 35));
        botonAtras.setMaximumSize(botonAtras.getPreferredSize());
        botonAtras.setBackground(new java.awt.Color(255, 255, 255));
        botonAtras.setBorder(null);

        botonAdelante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/derecha.jpg"))); // NOI18N
        botonAdelante.setText("");
        botonAdelante.setPreferredSize(new Dimension(35, 35));
        botonAdelante.setMaximumSize(botonAdelante.getPreferredSize());
        botonAdelante.setBackground(new java.awt.Color(255, 255, 255));
        botonAdelante.setBorder(null);

        botonEjecutar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ejecutar.jpg"))); // NOI18N
        botonEjecutar.setText("");
        botonEjecutar.setPreferredSize(new Dimension(35, 35));
        botonEjecutar.setMaximumSize(botonEjecutar.getPreferredSize());
        botonEjecutar.setBackground(new java.awt.Color(255, 255, 255));
        botonEjecutar.setBorder(null);

        ruta.setText("C:\\Users\\Aroche\\Documents\\Archivos\\Paso1.chtml");
        ruta.setPreferredSize(new Dimension(1300, 30));
        ruta.setMaximumSize(ruta.getPreferredSize());

        crearAcciones();
        boxH1.add(botonAtras);
        boxH1.add(botonAdelante);
        boxH1.add(botonEjecutar);
        boxH1.add(ruta);

        Box box = Box.createVerticalBox();
        box.add(boxH1);
        pestanias.setBackground(Color.WHITE);
        pestanias.setPreferredSize(new Dimension(1300, 700));
        pestanias.setMaximumSize(ruta.getPreferredSize());
        crearPestanias();
        box.add(pestanias);

        setLayout(new BorderLayout());
        add(box, BorderLayout.CENTER);
    }

    public void crearPestanias() {
        pestanias.addTab("Opciones", panel);
        pestanias.addTab("Historial", new JPanel());
    }

    public void crearAcciones() {
        botonEjecutar.addActionListener(this::EjecutarActionPerformed);
    }

    private void EjecutarActionPerformed(java.awt.event.ActionEvent evt) {
        String texto = Metodo.abrir(ruta.getText());

        try {
            chtml.analizar(texto);
        } catch (Exception ex) {
            System.out.println("Error al analizar archivo: " + ruta.getText() + "\n" + ex);
        }
        UsacWeb.agregarHistorial(ruta.getText());
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
