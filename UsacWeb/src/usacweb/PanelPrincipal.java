package usacweb;

import chtml.chtml;
import cjs.Ejecutar.Documento;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static usacweb.Interfaz.panelPestanias;
import static usacweb.UsacWeb.listaFavoritos;

public class PanelPrincipal extends javax.swing.JPanel {

    public JTextField ruta = new JTextField();
    JTextArea prueba = new JTextArea();
    PanelSecundario panel = new PanelSecundario();
    JButton botonAtras = new JButton();
    JButton botonAdelante = new JButton();
    JButton botonEjecutar = new JButton();
    JButton botonOpciones = new JButton();
    JButton botonHistorial = new JButton();
    JButton botonFavorito = new JButton();
    static String rutaAux = "C:\\Users\\Aroche\\Documents\\Archivos\\Paso3.chtml";
    String nombrePestania = "";
    int w = 0;
    int h = 0;
    int cont = 0;

    Box boxV1 = Box.createVerticalBox();

    public PanelPrincipal(String p) {
        nombrePestania = p;
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
        ruta.setText(rutaAux);
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
        repaint();

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
                int auxNum = chtml.html.numPagina;
                ArrayList auxPaginas = chtml.html.listaPaginas;

                for (int i = 0; i < UsacWeb.listaHTML.size(); i++) {
                    HTML html = UsacWeb.listaHTML.get(i);

                    if (html.nombre.equalsIgnoreCase(nombrePestania)) {
                        html.numPagina = auxNum;
                        html.listaPaginas = auxPaginas;

                        System.out.println("Nombre = " + nombrePestania);
                        UsacWeb.listaHTML.set(i, new HTML(nombrePestania));
                        html.pilaArchivo.push(Metodos.obtenerNombre(ruta.getText()));
                        html.ruta = ruta.getText();

                        try {
                            Object result = chtml.analizar(texto, html);
                            html.codigoCHTML = texto;
                            if (result instanceof JPanel) {
                                crearHTML((JPanel) result);
                            } else if (result instanceof JScrollPane) {
                                crearHTML((JScrollPane) result);
                            }
                        } catch (Exception ex) {
                            System.out.println("Error al analizar archivo: " + ruta.getText() + "\n" + ex);
                        }
                        html.pilaArchivo.pop();
                        html.numPagina++;
                        html.listaPaginas.add(ruta.getText());

                        break;
                    }
                }
                UsacWeb.agregarHistorial(ruta.getText());
                Documento.verificarEvento("Documento", "Listo");
            }
        });

        botonAtras.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {
                int auxNum = chtml.html.numPagina;
                ArrayList auxPaginas = chtml.html.listaPaginas;
                for (int i = 0; i < UsacWeb.listaHTML.size(); i++) {
                    HTML html = UsacWeb.listaHTML.get(i);

                    if (html.nombre.equalsIgnoreCase(nombrePestania)) {
                        System.out.println("Nombre = " + nombrePestania);
                        UsacWeb.listaHTML.set(i, new HTML(chtml.html.nombre));
                        // html = chtml.html;
                        html.pilaArchivo.push(Metodos.obtenerNombre(ruta.getText()));
                        html.numPagina = auxNum;
                        html.listaPaginas = auxPaginas;
                        if (html.numPagina - 2 >= 0) {
                            String rutaAtras = html.listaPaginas.get(html.numPagina - 2);
                            String texto = Metodos.abrir(rutaAtras);
                            ruta.setText(rutaAtras);
                            try {
                                Object result = chtml.analizar(texto, html);
                                html.codigoCHTML = texto;
                                if (result instanceof JPanel) {
                                    crearHTML((JPanel) result);
                                } else if (result instanceof JScrollPane) {
                                    crearHTML((JScrollPane) result);
                                }
                                Documento.verificarEvento("Documento", "Listo");
                            } catch (Exception ex) {
                                System.out.println("Error al analizar archivo: " + rutaAtras + "\n" + ex);
                            }
                            html.pilaArchivo.pop();
                            html.numPagina--;
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, " No existe pagina anterior ", "WARNING", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        });

        botonAdelante.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {
                int auxNum = chtml.html.numPagina;
                ArrayList auxPaginas = chtml.html.listaPaginas;
                for (int i = 0; i < UsacWeb.listaHTML.size(); i++) {
                    HTML html = UsacWeb.listaHTML.get(i);

                    if (html.nombre.equalsIgnoreCase(nombrePestania)) {
                        System.out.println("Nombre = " + nombrePestania);
                        UsacWeb.listaHTML.set(i, new HTML(chtml.html.nombre));
                        //html = chtml.html;
                        html.pilaArchivo.push(Metodos.obtenerNombre(ruta.getText()));
                        html.numPagina = auxNum;
                        html.listaPaginas = auxPaginas;

                        if (html.numPagina < html.listaPaginas.size()) {

                            String rutaAdelante = html.listaPaginas.get(html.numPagina);
                            String texto = Metodos.abrir(rutaAdelante);
                            ruta.setText(rutaAdelante);
                            try {
                                Object result = chtml.analizar(texto, html);
                                html.codigoCHTML = texto;
                                if (result instanceof JPanel) {
                                    crearHTML((JPanel) result);
                                } else if (result instanceof JScrollPane) {
                                    crearHTML((JScrollPane) result);
                                }
                                Documento.verificarEvento("Documento", "Listo");
                            } catch (Exception ex) {
                                System.out.println("Error al analizar archivo: " + rutaAdelante + "\n" + ex);
                            }
                            html.pilaArchivo.pop();
                            html.numPagina++;
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, " No existe pagina siguiente ", "WARNING", JOptionPane.WARNING_MESSAGE);
                        }
                    }
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

    public void crearHTML(JPanel panel) {
        System.out.println("Tamanio panel1 = " + panel.getHeight() + ", " + panel.getWidth() + "\n");
        rutaAux = ruta.getText();
        removeAll();
        crearTODO();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        Box boxH1 = Box.createHorizontalBox();
        boxH1.add(Box.createHorizontalGlue());
        boxH1.add(panel);
        boxH1.add(Box.createHorizontalGlue());
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(boxH1);
        boxV1.add(boxH1);
        scroll.setViewportView(boxV1);
        setLayout(new BorderLayout());
        add(boxV1, BorderLayout.CENTER);
        updateUI();
    }

    public void crearHTML2(JPanel panel) {
        if (cont == 0) {
            w = panel.getWidth();
            h = panel.getHeight();
        }
        panel.setSize(w, h);
        cont++;
        System.out.println("Tamanio panel 1.1 = " + panel.getHeight() + ", " + panel.getWidth() + "\n");
        rutaAux = ruta.getText();
        removeAll();
        crearTODO();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        Box boxH1 = Box.createHorizontalBox();
        boxH1.add(Box.createHorizontalGlue());
        boxH1.add(panel);
        boxH1.add(Box.createHorizontalGlue());
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(boxH1);
        boxV1.add(boxH1);
        setLayout(new BorderLayout());
        add(boxV1, BorderLayout.CENTER);
        updateUI();
    }

    public void crearHTML(JScrollPane panel) {
        System.out.println("Tamanio panel2 = " + panel.getHeight() + ", " + panel.getWidth() + "\n");
        rutaAux = ruta.getText();
        removeAll();
        crearTODO();
        Box boxH1 = Box.createHorizontalBox();
        boxH1.add(Box.createHorizontalGlue());
        boxH1.add(panel);
        boxH1.add(Box.createHorizontalGlue());
        boxV1.add(boxH1);
        setLayout(new BorderLayout());
        add(boxV1, BorderLayout.CENTER);
        updateUI();
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
