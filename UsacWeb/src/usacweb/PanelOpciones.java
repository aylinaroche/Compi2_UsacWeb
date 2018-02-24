package usacweb;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument.Content;

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
    JPanel panelGlobal = new JPanel();
    JLabel prueba = new JLabel();
    Box boxH1 = Box.createHorizontalBox();

    public PanelOpciones() {
        prueba.setText("HOLIIIIIIIIIIII");
        prueba.setName("prueba");
        crearPaneles();
        Box boxV1 = crearBotones();

        boxH1.add(prueba);
        boxH1.add("boxV1", boxV1);
        panelGlobal = panelCHTML;
        panelGlobal.setName("panelGlobal");

        boxH1.add(panelGlobal);
        add(boxH1);
        crearAcciones();
        //initComponents();
    }

    public Box crearBotones() {
        Box boxV1 = Box.createVerticalBox();
        botonCHTML.setText("Ver codigo CHTML");
        botonCHTML.setBounds(10, 10, 40, 40);
        botonCHTML.setBackground(new Color(102, 204, 0));
        botonCHTML.setForeground(new Color(255, 255, 255));
        botonCHTML.setFont(new Font("MS Reference Sans Serif", 0, 14));
        botonCHTML.setName("botonCHTML");

        botonCJS.setText("Ver codigo CJS");
        botonCJS.setBounds(10, 10, 40, 40);
        botonCJS.setBackground(new Color(102, 204, 0));
        botonCJS.setForeground(new Color(255, 255, 255));
        botonCJS.setFont(new Font("MS Reference Sans Serif", 0, 14));
        botonCJS.setName("botonCJS");

        botonCCSS.setText("Ver codigo CCSS");
        botonCCSS.setBounds(10, 10, 40, 40);
        botonCCSS.setBackground(new Color(102, 204, 0));
        botonCCSS.setForeground(new Color(255, 255, 255));
        botonCCSS.setFont(new Font("MS Reference Sans Serif", 0, 14));
        botonCCSS.setName("botonCCSS");

        botonSalida.setText("Consola Salida");
        botonSalida.setBounds(10, 10, 40, 40);
        botonSalida.setBackground(new Color(102, 204, 0));
        botonSalida.setForeground(new Color(255, 255, 255));
        botonSalida.setFont(new Font("MS Reference Sans Serif", 0, 14));
        botonSalida.setName("botonSalida");

        botonErrores.setText("Consola Errores");
        botonErrores.setBounds(10, 10, 40, 40);
        botonErrores.setBackground(new Color(102, 204, 0));
        botonErrores.setForeground(new Color(255, 255, 255));
        botonErrores.setFont(new Font("MS Reference Sans Serif", 0, 14));
        botonErrores.setName("botonErrores");

        boxV1.add(botonCHTML);
        boxV1.add(botonCJS);
//        boxV1.add(botonCCSS);
//        boxV1.add(botonSalida);
//        boxV1.add(botonErrores);

        return boxV1;
    }

    public void crearPaneles() {
        JButton prueba1 = new JButton();
        prueba1.setText("prueba1");
        panelCHTML.setName("panelCHTML");
        panelCHTML.add(prueba1);

        JButton prueba2 = new JButton();
        prueba2.setText("prueba22222");
        panelCJS.setName("panelCJS");
        panelCJS.add(prueba2);
    }

    public void crearAcciones() {
        botonCHTML.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {
                Component[] c1 = getComponents();
                JLabel l = new JLabel();
                l.setText("FUNCIONAAAAAAAA");
                l.setName("nuevo");
                modificarComponente(getComponents(), "prueba", l);
                l.setName("holaaa");
                modificarComponente(getComponents(), "nuevo", l);
//                removeAll();
//                add(boxH1);
            }
        });

        botonCJS.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {
                Component[] c1 = getComponents();
                modificarComponente(c1, "panelGlobal", panelCJS);

//                removeAll();
////                add(boxH1);
//                ///              UsacWeb.i.repaint();
//                for (int i = 0; i < c2.length; i++) {
//                    add(c2[i]);
//
//                }
            }
        });
    }

    public void modificarComponente(Component[] lista, String nombre, Component nuevo) {
        Component[] result = null;
        for (int i = 0; i < lista.length; i++) {
            Component comp = lista[i];
            if (comp instanceof Box) {
                Box box = (Box) comp;
                
                //Component[] c = 
                modificarComponente(box.getComponents(), nombre, nuevo);
                box.updateUI();

            }
            if (comp.getName() != null) {
                if (comp.getName().equalsIgnoreCase(nombre)) {
                    System.out.println("ANTES ::" + lista[i].toString());
                    System.out.println("EUREKA");
                    if (lista[i] instanceof JPanel) {
                        JPanel n = (JPanel) nuevo;
                        lista[i] = n;
                        n.updateUI();
                    } else if (lista[i] instanceof JLabel) {
                        JLabel n = (JLabel) nuevo;
                        lista[i] = n;
                        n.updateUI();
                    }

                    System.out.println("\n DESPUES ::" + lista[i].toString());
                    UsacWeb.i.repaint();
                    return;
                } else {
                    System.out.println("Saber que rayos: " + comp.getName());
                }
            } else {
                System.out.println("nulo");
            }

        }
    }

    public Component[] modificarComponente111(Component[] lista, String nombre, Component nuevo) {
        Component[] result = new Component[lista.length];
        for (int i = 0; i < lista.length; i++) {
            Component comp = lista[i];
            if (comp instanceof Box) {
                Box box = (Box) comp;
                result = modificarComponente111(box.getComponents(), nombre, nuevo);
            }
            if (comp.getName() != null) {
                if (comp.getName().equalsIgnoreCase(nombre)) {
                    System.out.println("ANTES ::" + lista[i].toString());
                    System.out.println("EUREKA");
                    lista[i] = nuevo;
                    this.updateUI();
                    System.out.println("\n\n\n DESPUES ::" + lista[i].toString());
                    UsacWeb.i.repaint();
                    return lista;
                } else {
                    System.out.println("Saber que rayos: " + comp.getName());
                }
            } else {
                System.out.println("nulo");
            }

        }
        return result;
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
