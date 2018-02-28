package chtml.Ejecutar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import usacweb.Datos;
import static usacweb.Interfaz.panelPestanias;

/**
 *
 * @author Aroche
 */
public class Elementos {

    public static int aux = 0;

    public static void setTitulo(String titulo) {
        panelPestanias.setTitleAt(panelPestanias.getSelectedIndex(), titulo);
    }

    public static Color convertirColor(String color) {
        Color nuevo = null;
        switch (color.toLowerCase()) {
            case "red":
                return Color.RED;
            case "black":
                return Color.BLACK;
            case "blue":
                return Color.BLUE;
            case "cyan":
                return Color.CYAN;
            case "magenta":
                return Color.MAGENTA;
            case "pink":
                return Color.PINK;
            case "white":
                return Color.WHITE;
            case "yellow":
                return Color.YELLOW;
            case "green":
                return Color.GREEN;
            case "purple":
                return Color.MAGENTA;
        }
        try {
            nuevo = Color.decode(color);
        } catch (NumberFormatException e) {
            Datos.agregarError("Error Semantico", "Al convertir " + color + " en color", 0, 0);
        }
        return nuevo;
    }

    public static Object dibujar(Componente comp) {
        int auxD = aux;
        aux++;
        if (comp.tipo.equalsIgnoreCase("panel")) {
            JPanel panel = (JPanel) comp.valor;
            //panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
            Box boxV1 = Box.createVerticalBox();
            Box boxH1 = Box.createHorizontalBox();
            for (int i = 0; i < comp.listaComponentes.size(); i++) {
                if (comp.listaComponentes.get(i) != null) {
                    Componente c = comp.listaComponentes.get(i);
                    Object d = dibujar(c);
                    if (d instanceof JButton) {
                        JButton b = (JButton) d;
                        boxH1.add(b);
//                        if ("izquierda".equalsIgnoreCase(c.alineado)) { //I
//                            boxH1.add(b);
//                            boxH1.add(Box.createHorizontalGlue());
//                        } else if ("derecha".equalsIgnoreCase(c.alineado)) {
//                            boxH1.add(Box.createHorizontalGlue());
//                            boxH1.add(b);
//                            boxH1.add(Box.createHorizontalGlue());
//                        } else if ("centrado".equalsIgnoreCase(c.alineado)) {
//                            boxH1.add(Box.createHorizontalGlue());
//                            boxH1.add(b);
//                        } else {
//                            boxH1.add(b);
//                            //System.out.println("ninguno");
//                        }
                    } else if (d instanceof JPanel) {
                        JPanel b = (JPanel) d;
                        boxH1.add(b);
                    } else if (d instanceof JTextArea) {
                        JTextArea b = (JTextArea) d;
                        boxH1.add(b);
                    } else if (d instanceof String) {
                        boxV1.add(boxH1);
                        boxH1 = Box.createHorizontalBox();
                    }
                }
            }
            boxV1.add(boxH1);
            if (auxD == 0) {
                boxV1.add(Box.createVerticalGlue());
            }
            panel.add(boxV1);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            return panel;
        } 
        return comp.valor;
    }

    public static Object dibujar2(Componente comp) {
        System.out.println(comp.nombre);
        if (comp.tipo.equalsIgnoreCase("panel")) {
            JPanel panel = (JPanel) comp.valor;
            panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
            Box boxV1 = Box.createVerticalBox();
            Box boxH1 = Box.createHorizontalBox();
            System.out.println("\nFOR : " + aux);
            aux++;

            for (int i = 0; i < comp.listaComponentes.size(); i++) {
                if (comp.listaComponentes.get(i) != null) {
                    System.out.println("\nNUM : " + i);
                    Componente c = comp.listaComponentes.get(i);
                    Object d = dibujar2(c);
                    if (d instanceof JButton) {
                        JButton b = (JButton) d;
                        if ("1".equals(c.alineado)) { //I
                            boxH1.add(b);
                            boxH1.add(Box.createHorizontalGlue());
                        } else if ("2".equals(c.alineado)) {
                            boxH1.add(Box.createHorizontalGlue());
                            boxH1.add(b);
                            boxH1.add(Box.createHorizontalGlue());
                        } else if ("3".equals(c.alineado)) {
                            boxH1.add(Box.createHorizontalGlue());
                            boxH1.add(b);
                        } else {
                            boxH1.add(b);
                            System.out.println("ninguno");
                        }
                    } else if (d instanceof JPanel) {
                        JPanel b = (JPanel) d;
                        boxH1.add(b);
                    } else if (d instanceof String) {
                        // System.out.println("V1 = " + boxV1.getComponentCount());
                        boxV1.add(boxH1);
                        System.out.println("H1 = " + boxH1.getComponentCount());
                        boxH1 = Box.createHorizontalBox();
                        //   System.out.println("H2 = " + boxH1.getComponentCount());
                        System.out.println("V2 = " + boxV1.getComponentCount());
                    }

                    if ("1".equals(comp.alineado)) {
                        boxH1.add(Box.createHorizontalGlue());
                    } else if ("2".equals(comp.alineado)) {

                    } else if ("3".equals(comp.alineado)) {

                    }
                }
            }
            System.out.println("\nFinal1 = " + boxV1.getComponentCount());
            boxV1.add(boxH1);
            System.out.println("Final2 = " + boxV1.getComponentCount());
            panel.add(boxV1);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            return panel;
        } else if (comp.tipo.equalsIgnoreCase("boton")) {
            JButton boton = (JButton) comp.valor;
            return boton;
        } else if (comp.tipo.equalsIgnoreCase("salto")) {
            return "salto";
        }
        return null;
    }

    public static Object dibujar3(Componente comp) {
        //System.out.println(comp.nombre);
        if (comp.tipo.equalsIgnoreCase("panel")) {
            JPanel panel = (JPanel) comp.valor;
            panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
            for (int i = 0; i < comp.listaComponentes.size(); i++) {
                if (comp.listaComponentes.get(i) != null) {
                    //System.out.println("NUM : " + i);
                    Componente c = comp.listaComponentes.get(i);
                    Object d = dibujar(c);
                    if (d instanceof JButton) {
                        JButton b = (JButton) d;
                        panel.add(b);
                    } else if (d instanceof JPanel) {
                        JPanel b = (JPanel) d;
                        panel.add(b);
                    } else if (d instanceof String) {

                    }
                }
            }
//            panel.setLayout(new BorderLayout());
//            panel.add(panel, BorderLayout.CENTER);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            return panel;
        } else if (comp.tipo.equalsIgnoreCase("boton")) {
            JButton boton = (JButton) comp.valor;
            return boton;
        } else if (comp.tipo.equalsIgnoreCase("salto")) {
            return "salto";
        }
        return null;
    }

    public static Componente compPrueba() {
        ArrayList<Componente> l1 = new ArrayList();
        JButton jb = new JButton();
        jb.setText("Prueba1");
        Componente boton = new Componente("boton", "boton1", jb, l1, "1");
        JButton jb2 = new JButton();
        jb2.setText("Prueba2");
        Componente boton2 = new Componente("boton", "boton2", jb2, l1, "2");
        JButton jb3 = new JButton();
        jb3.setText("Prueba3");
        Componente boton3 = new Componente("boton", "boton3", jb3, l1, "3");
        Componente salto = new Componente("salto", "salto", jb3, l1);

        ArrayList<Componente> l2 = new ArrayList();
        l2.add(boton);
        l2.add(salto);
        l2.add(boton2);
        //    l2.add(boton3);
        JPanel jp = new JPanel();
        jp.setBackground(Color.blue);
        Componente panel = new Componente("panel", "panel1", jp, l2);
        return panel;
    }

    public static String primeraMayuscula(String palabra) {
        String nueva = palabra;
        return nueva;
    }
}
