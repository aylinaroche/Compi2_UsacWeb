package usacweb;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Interfaz extends javax.swing.JFrame {

    JButton botonMas = new JButton();
    JButton botonMenos = new JButton();
    public static JTabbedPane panelPestanias = new JTabbedPane();
    public Image imagenFondo;
    public URL fondo;
    public int contPestania = 2;

    public Interfaz() {
        setTitle("USAC WEB");
        setLocationRelativeTo(null);
        fondo = this.getClass().getResource("/Imagenes/fondo1.jpg");
        imagenFondo = new ImageIcon(fondo).getImage();
        Container contenedor = getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

        Box boxH1 = Box.createHorizontalBox();
        botonMas.setText("+");
        botonMas.setBounds(10, 10, 40, 40);
        botonMas.setBackground(new Color(102, 0, 51));
        botonMas.setForeground(new Color(255, 255, 255));
        botonMas.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 14));

        botonMenos.setText("x");
        botonMenos.setBounds(10, 10, 40, 40);
        botonMenos.setBackground(new Color(102, 0, 51));
        botonMenos.setForeground(new Color(255, 255, 255));
        botonMenos.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 14));

        agregarAccion();
        boxH1.add(Box.createHorizontalGlue());
        boxH1.add(botonMas);
        boxH1.add(botonMenos);

        Box boxH2 = Box.createHorizontalBox();
        PanelPrincipal principal = new PanelPrincipal("Pestania1");
        panelPestanias.add("Pestania1", principal);
        panelPestanias.setBackground(new Color(102, 0, 51));
        panelPestanias.setForeground(new Color(255, 255, 255));
        panelPestanias.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 14));
        panelPestanias.setName("Pestania1");
        accionPanelPestanias();
        boxH2.add(panelPestanias);

        Box boxV1 = Box.createVerticalBox();
        boxV1.add(boxH1);
        boxV1.add(boxH2);

        panel.add(boxV1);
        contenedor.add(panel);
        initComponents();
        setSize(500, 300);

        HTML h = new HTML(panelPestanias.getName());
        try {
            UsacWeb.listaHTML.add(h);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JPanel panel = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    };

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Interfaz().setVisible(true);
        });
    }

    private void agregarAccion() {
        botonMas.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasActionPerformed(evt);
            }

            private void MasActionPerformed(ActionEvent evt) {
                Box boxH = Box.createHorizontalBox();
                panelPestanias.setName("Pestania" + contPestania);
                PanelPrincipal panel = new PanelPrincipal("Pestania" + contPestania);
                panel.setBackground(Color.BLACK);
                panel.setPreferredSize(new Dimension(30000, 30000));
                panel.setMaximumSize(panel.getPreferredSize());
                boxH.add(panel);
                panelPestanias.addTab("Pestania" + contPestania, boxH);
                panelPestanias.setSelectedIndex(panelPestanias.getTabCount() - 1);
                UsacWeb.listaHTML.add(new HTML(panelPestanias.getName()));

                contPestania++;
            }
        });

        botonMenos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosActionPerformed(evt);
            }

            private void MenosActionPerformed(ActionEvent evt) {
                ArrayList<HTML> listaNueva = new ArrayList();
                for (int i = 0; i < UsacWeb.listaHTML.size(); i++) {
                    if (i != panelPestanias.getSelectedIndex()) {
                        listaNueva.add(UsacWeb.listaHTML.get(i));
                    }
                }
                UsacWeb.listaHTML.clear();
                UsacWeb.listaHTML = listaNueva;
                panelPestanias.remove(panelPestanias.getSelectedIndex());
            }
        });
    }

    private void accionPanelPestanias() {
        panelPestanias.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PestaniasMouseClicked(evt);
            }

            private void PestaniasMouseClicked(MouseEvent evt) {
                
                UsacWeb.verificarPestania();
             
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
