package usacweb;

import static chtml.chtml.html;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author Aroche
 */
public class PanelOpciones extends javax.swing.JPanel {

    JButton botonCHTML = new JButton();
    JButton botonCJS = new JButton();
    JButton botonCCSS = new JButton();
    JButton botonSalida = new JButton();
    JButton botonErrores = new JButton();
    JPanel panelCHTML = new JPanel();
    JPanel panelCJS = new JPanel();
    JPanel panelCCSS = new JPanel();
    JPanel panelSalida = new JPanel();
    JPanel panelErrores = new JPanel();
    JLabel prueba = new JLabel();
    Box boxH1 = Box.createHorizontalBox();
    Box boxV1 = Box.createVerticalBox();

    public PanelOpciones() {

        crearPaneles();
        crearAcciones();
        crearBotones();
        crearTODO(1);
    }

    public void crearTODO(int opcion) {
        //setLayout(null);
        boxH1.add(boxV1);
        switch (opcion) {
            case 1:
                boxH1.add(panelCHTML);
                break;
            case 2:
                boxH1.add(panelCJS);
                break;
            case 3:
                boxH1.add(panelCCSS);
                break;
            case 4:
                boxH1.add(panelSalida);
                break;
            case 5:
                boxH1.add(panelErrores);
                break;
            default:
                break;
        }
        setLayout(new BorderLayout());
        add(boxH1);
    }

    public Box crearBotones() {
        boxV1 = Box.createVerticalBox();
        botonCHTML.setText("Ver codigo CHTML");
        botonCHTML.setBounds(10, 10, 40, 50);
        botonCHTML.setBackground(new Color(102, 0, 51));
        botonCHTML.setForeground(new Color(255, 255, 255));
        botonCHTML.setFont(new Font("MS Reference Sans Serif", 0, 14));
        botonCHTML.setName("botonCHTML");

        botonCJS.setText("Ver codigo CJS");
        botonCJS.setBounds(10, 10, 40, 40);
        botonCJS.setBackground(new Color(102, 0, 51));
        botonCJS.setForeground(new Color(255, 255, 255));
        botonCJS.setFont(new Font("MS Reference Sans Serif", 0, 14));
        botonCJS.setName("botonCJS");

        botonCCSS.setText("Ver codigo CCSS");
        botonCCSS.setBounds(10, 10, 40, 40);
        botonCCSS.setBackground(new Color(102, 0, 51));
        botonCCSS.setForeground(new Color(255, 255, 255));
        botonCCSS.setFont(new Font("MS Reference Sans Serif", 0, 14));
        botonCCSS.setName("botonCCSS");

        botonSalida.setText("Consola Salida");
        botonSalida.setBounds(10, 10, 40, 40);
        botonSalida.setBackground(new Color(102, 0, 51));
        botonSalida.setForeground(new Color(255, 255, 255));
        botonSalida.setFont(new Font("MS Reference Sans Serif", 0, 14));
        botonSalida.setName("botonSalida");

        botonErrores.setText("Consola Errores");
        botonErrores.setBounds(10, 10, 40, 40);
        botonErrores.setBackground(new Color(102, 0, 51));
        botonErrores.setForeground(new Color(255, 255, 255));
        botonErrores.setFont(new Font("MS Reference Sans Serif", 0, 14));
        botonErrores.setName("botonErrores");

        boxV1.add(botonCHTML);
        boxV1.add(botonCJS);
        boxV1.add(botonCCSS);
        boxV1.add(botonSalida);
        boxV1.add(botonErrores);

        return boxV1;
    }

    public void crearPaneles() {
        //prueba();
        JTextArea areaChtml = new JTextArea();
        areaChtml.setText(html.codigoCHTML);
        JScrollPane scroll1 = new JScrollPane();
        scroll1.setViewportView(areaChtml);
        scroll1.setPreferredSize(new Dimension(1200, 180));
        scroll1.setMaximumSize(scroll1.getPreferredSize());
        panelCHTML.add(scroll1);
        panelCHTML.setName("panelCHTML");

        JTextArea areaCjs = new JTextArea();
        areaCjs.setText(html.codigoCJS);
        JScrollPane scroll2 = new JScrollPane();
        scroll2.setViewportView(areaCjs);
        scroll2.setPreferredSize(new Dimension(1200, 180));
        scroll2.setMaximumSize(scroll2.getPreferredSize());
        panelCJS.setName("panelCJS");
        panelCJS.add(scroll2);

        JTextArea areaCcss = new JTextArea();
        areaCcss.setText(html.codigoCCSS);
        JScrollPane scroll3 = new JScrollPane();
        scroll3.setViewportView(areaCcss);
        scroll3.setPreferredSize(new Dimension(1200, 180));
        scroll3.setMaximumSize(scroll3.getPreferredSize());
        panelCCSS.setName("panelCCSS");
        panelCCSS.add(scroll3);

        String[] tituloSalida = {"ARCHIVO", "FILA", "COLUMNA", "SALIDA"};
        Object[][] objeto1 = Datos.obtenerSalida();
        JTable tablaSalida = new JTable(objeto1, tituloSalida);
        tablaSalida.setFont(new Font("Comic Sans MS", 0, 14));
        tablaSalida.setForeground(new Color(102, 0, 0));
        tablaSalida.setBackground(new Color(255, 255, 255));
        tablaSalida.getTableHeader().setBackground(new Color(102, 0, 0));
        tablaSalida.getTableHeader().setForeground(new Color(255, 255, 255));
        JScrollPane scroll4 = new JScrollPane();
        scroll4.setViewportView(tablaSalida);
        scroll4.setPreferredSize(new Dimension(1100, 180));
        scroll4.setMaximumSize(scroll4.getPreferredSize());
        panelSalida.add(scroll4);

        String[] tituloError = {"ARCHIVO", "FILA", "COLUMNA", "TIPO", "DESCRIPCION"};
        Object[][] objeto2 = Datos.obtenerErrores();
        JTable tablaError = new JTable(objeto2, tituloError);
        tablaError.setFont(new Font("Comic Sans MS", 0, 14));
        tablaError.setForeground(new Color(102, 0, 0));
        tablaError.setBackground(new Color(255, 255, 255));
        tablaError.getTableHeader().setBackground(new Color(102, 0, 0));
        tablaError.getTableHeader().setForeground(new Color(255, 255, 255));

        JScrollPane scroll5 = new JScrollPane();
        scroll5.setViewportView(tablaError);
        scroll5.setPreferredSize(new Dimension(1100, 180));
        scroll5.setMaximumSize(scroll5.getPreferredSize());
        panelErrores.add(scroll5);
    }

    public void crearAcciones() {
        botonCHTML.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {
                removeAll();
                boxH1.removeAll();
                crearTODO(1);
                updateUI();

            }
        });

        botonCJS.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {
                removeAll();
                boxH1.removeAll();
                crearTODO(2);
                updateUI();
            }
        });

        botonCCSS.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {
                removeAll();
                boxH1.removeAll();
                crearTODO(3);
                updateUI();
            }
        });

        botonSalida.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {
                removeAll();
                boxH1.removeAll();
                crearTODO(4);
                updateUI();
            }
        });

        botonErrores.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {
                removeAll();
                boxH1.removeAll();
                crearTODO(5);
                updateUI();
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

    private void prueba() {
       html.codigoCHTML = "//inicio - panel 5 interno \n"
                + "\n"
                + "PASOS [\n"
                + "\n"
                + "ID (panel_5);\n"
                + "alineadO := derecha;\n"
                + "FondoElemento = \"cian\"; \n"
                + "borde := [5, \"blue\", false]; \n"
                + "\n"
                + "//fin panel 5 interno \n"
                + "\n"
                + "//inicio grupo salida texto 1 \n"
                + "grupo (salida_texto_1); \n"
                + "LetrA := \"Arial\"; \n"
                + "TamTex := 14; \n"
                + "forMAto := negrilla; \n"
                + "ColorTEx := \"blue\"; \n"
                + "//fin grupo salida texto 1 \n"
                + "\n"
                + "//inicio grupo salida texto 2\n"
                + "grupo (salida_texto_2); \n"
                + "LetrA := \"Courier New\"; \n"
                + "TamTex := 12;\n"
                + " ColorTEx := \"purple\"; \n"
                + " //fin grupo salida texto 2\n"
                + "\n"
                + " ]";
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
