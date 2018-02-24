package usacweb;

import chtml.chtml;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static usacweb.Interfaz.panelPestanias;
import static usacweb.UsacWeb.listaFavoritos;

public class PanelPrincipal extends javax.swing.JPanel {

    JTextField ruta = new JTextField();
    JTextArea prueba = new JTextArea();
    PanelSecundario panel = new PanelSecundario();
    JButton botonAtras = new JButton();
    JButton botonAdelante = new JButton();
    JButton botonEjecutar = new JButton();
    JButton botonOpciones = new JButton();
    JButton botonHistorial = new JButton();
    JButton botonFavorito = new JButton();

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
        ruta.setFont(new java.awt.Font("Verdana", 0, 12));
        ruta.setPreferredSize(new Dimension(1300, 30));
        ruta.setMaximumSize(ruta.getPreferredSize());

        crearAcciones();
        boxH1.add(botonAtras);
        boxH1.add(botonAdelante);
        boxH1.add(botonEjecutar);
        boxH1.add(ruta);

        Box box = Box.createVerticalBox();
        box.add(boxH1);
        Box boxH2 = crearBotones();

        box.add(boxH2);

        setLayout(new BorderLayout());
        add(box, BorderLayout.CENTER);
    }

    public Box crearBotones() {
        Box box = Box.createHorizontalBox();
        botonOpciones.setText("Opciones");
        botonOpciones.setFont(new java.awt.Font("Verdana", 0, 12));
        botonOpciones.setPreferredSize(new Dimension(100, 35));
        botonOpciones.setMaximumSize(botonOpciones.getPreferredSize());
        botonOpciones.setBackground(new java.awt.Color(0, 153, 153));
        botonOpciones.setForeground(new java.awt.Color(255, 255, 255));
        botonOpciones.setBorder(null);

        botonHistorial.setText("Historial");
        botonHistorial.setPreferredSize(new Dimension(100, 35));
        botonHistorial.setFont(new java.awt.Font("Verdana", 0, 12));
        botonHistorial.setMaximumSize(botonHistorial.getPreferredSize());
        botonHistorial.setBackground(new java.awt.Color(102, 0, 102));
        botonHistorial.setForeground(new java.awt.Color(255, 255, 255));
        botonHistorial.setBorder(null);

        botonFavorito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fav.png"))); // NOI18N
        botonFavorito.setText("");
        botonFavorito.setPreferredSize(new Dimension(35, 35));
        botonFavorito.setMaximumSize(botonFavorito.getPreferredSize());
        botonFavorito.setBackground(new java.awt.Color(255, 255, 255));
        botonFavorito.setBorder(null);
        agregarAcciones();
        box.add(botonOpciones);
        box.add(botonHistorial);
        for (int i = 0; i < listaFavoritos.size(); i++) {

        }

        box.add(Box.createHorizontalGlue());
        box.add(botonFavorito);

        return box;
    }

    public void crearAcciones() {
        botonEjecutar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {
                String texto = Metodo.abrir(ruta.getText());
                UsacWeb.pilaArchivo.push(Metodo.obtenerNombre(ruta.getText()));
                try {
                    chtml.analizar(texto);
                } catch (Exception ex) {
                    System.out.println("Error al analizar archivo: " + ruta.getText() + "\n" + ex);
                }
                UsacWeb.agregarHistorial(ruta.getText());
                UsacWeb.pilaArchivo.pop();
            }
        });
    }

    private void agregarAcciones() {
        botonOpciones.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {
                Box box = Box.createHorizontalBox();
                JScrollPane scroll = new JScrollPane();
                PanelOpciones panel = new PanelOpciones();
                panel.setBackground(new Color(255, 255, 255));
                scroll.setViewportView(panel);
                box.add(scroll);
                // box.add(panel);
                panelPestanias.addTab("Opciones", box);
                panelPestanias.setSelectedIndex(panelPestanias.getTabCount() - 1);
            }
        });

        botonHistorial.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {
                Box box = Box.createHorizontalBox();
                JScrollPane scroll = new JScrollPane();
                PanelHistorial panel = new PanelHistorial();
                panel.setBackground(new Color(255, 255, 255));
                scroll.setViewportView(panel);
                box.add(scroll);
                // box.add(panel);
                panelPestanias.addTab("Historial", box);
                panelPestanias.setSelectedIndex(panelPestanias.getTabCount() - 1);
            }
        });

        botonFavorito.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {

            }
        });
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
