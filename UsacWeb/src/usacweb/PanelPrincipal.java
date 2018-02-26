package usacweb;

import chtml.Ejecutar.Elementos;
import chtml.chtml;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
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
    String rutaAux = "";

    Box boxV1 = Box.createVerticalBox();

    public PanelPrincipal(String r) {
        accionesBoton();
        accionesBarra();
        crearTODO();
        setLayout(new BorderLayout());
        add(boxV1, BorderLayout.CENTER);
    }

    public void crearTODO() {
        boxV1.removeAll();
        Box boxH1 = Box.createHorizontalBox();
        crearBotones();
        ruta.setText("C:\\Users\\Aroche\\Documents\\Archivos\\Paso1.chtml");
        ruta.setFont(new java.awt.Font("Verdana", 0, 12));
        ruta.setPreferredSize(new Dimension(1300, 30));
        ruta.setMaximumSize(ruta.getPreferredSize());

        boxH1.add(botonAtras);
        boxH1.add(botonAdelante);
        boxH1.add(botonEjecutar);
        boxH1.add(ruta);

        boxV1.add(boxH1);
        Box boxH2 = crearBarra();
        boxV1.add(boxH2);

    }

    public void crearBotones() {
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
    }

    public Box crearBarra() {
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
        //accionesBarra();
        box.add(botonOpciones);
        box.add(botonHistorial);
        for (int i = 0; i < listaFavoritos.size(); i++) {
            String fav = listaFavoritos.get(i);
            JButton botonFav = new JButton();
            String nombre = Metodos.obtenerNombre(fav);
            botonFav.setText(nombre);
            botonFav.setPreferredSize(new Dimension(150, 35));
            botonFav.setFont(new java.awt.Font("Verdana", 0, 12));
            botonFav.setMaximumSize(botonFav.getPreferredSize());
            botonFav.setBackground(new java.awt.Color(204, 204, 204));
            botonFav.setForeground(new java.awt.Color(255, 255, 255));

            botonFav.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ActionPerformed(evt);
                }

                private void ActionPerformed(ActionEvent evt) {
                    Metodos.crearPestania(fav);
                }
            });

            box.add(botonFav);
        }

        box.add(Box.createHorizontalGlue());
        box.add(botonFavorito);

        return box;
    }

    public void accionesBoton() {
        botonEjecutar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {
                String texto = Metodos.abrir(ruta.getText());
                UsacWeb.pilaArchivo.push(Metodos.obtenerNombre(ruta.getText()));
                try {
                    chtml.analizar(texto);
                } catch (Exception ex) {
                    System.out.println("Error al analizar archivo: " + ruta.getText() + "\n" + ex);
                }
                UsacWeb.agregarHistorial(ruta.getText());
                UsacWeb.pilaArchivo.pop();
            }
        });

        botonAtras.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {
                try {
                    rutaAux = ruta.getText();
                    removeAll();
                    crearTODO();
                    JPanel panel = (JPanel) Elementos.dibujar(Elementos.compPrueba());
                    boxV1.add(panel);
                    setLayout(new BorderLayout());
                    add(boxV1, BorderLayout.CENTER);
                    updateUI();
                } catch (Exception e) {
                }
            }
        });
    }

    private void accionesBarra() {
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
                scroll.setPreferredSize(new Dimension(1500, 200));
                scroll.setMaximumSize(scroll.getPreferredSize());
                box.add(scroll);
                box.add(Box.createVerticalGlue());
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
                UsacWeb.listaFavoritos.add(ruta.getText());
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
