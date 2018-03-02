package chtml.Ejecutar;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import static java.awt.Component.LEFT_ALIGNMENT;
import static java.awt.Component.RIGHT_ALIGNMENT;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import usacweb.Datos;
import static usacweb.Interfaz.panelPestanias;

/**
 *
 * @author Aroche
 */
public class Elementos {

    public static int auxE = 0;

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

    public static Object dibujar(Componente comp, int aux) {

        if (comp.tipo.equalsIgnoreCase("panel")) {
            JPanel panel = (JPanel) comp.valor;
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            Box boxV1 = Box.createVerticalBox();
            Box boxH1 = Box.createHorizontalBox();
            if (comp.alineado.equalsIgnoreCase("derecha")) {
                boxH1.add(Box.createHorizontalGlue());
            }
            for (int i = 0; i < comp.listaComponentes.size(); i++) {
                if (comp.listaComponentes.get(i) != null) {
                    Componente c = comp.listaComponentes.get(i);
                    Object d = dibujar(c, aux + 1);
                    if (d instanceof JButton) {
                        JButton b = (JButton) d;
                        boxH1.add(b);
                    } else if (d instanceof JPanel) {
                        JPanel b = (JPanel) d;
                        boxH1.add(b);
                    } else if (d instanceof JTextArea) {
                        JTextArea b = (JTextArea) d;
                        boxH1.add(b);
                    } else if (d instanceof JTextPane) {
                        JTextPane b = (JTextPane) d;
                        boxH1.add(b);
                    } else if (d instanceof String) {
                        if (comp.alineado.equalsIgnoreCase("izquierda")) {
                            boxH1.add(Box.createHorizontalGlue());
                        }
                        boxV1.add(boxH1);
                        boxH1 = Box.createHorizontalBox();
                    }
                }
            }
            if (comp.alineado.equalsIgnoreCase("izquierda")) {
                boxH1.add(Box.createHorizontalGlue());
            }
            boxV1.add(boxH1);
            if (aux == 0) {
                boxV1.add(Box.createVerticalGlue());
            }
            panel.add(boxV1);
            return panel;
        }
        return comp.valor;
    }

    public static Object dibujar2(Componente comp) {
        System.out.println(comp.nombre);
        if (comp.tipo.equalsIgnoreCase("panel")) {
            JPanel panel = (JPanel) comp.valor;
            // panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            panel.setLayout(new BorderLayout());
            Box boxV1 = Box.createVerticalBox();
            Box boxH1 = Box.createHorizontalBox();
            System.out.println("\nFOR : " + auxE);
            auxE++;

            for (int i = 0; i < comp.listaComponentes.size(); i++) {
                if (comp.listaComponentes.get(i) != null) {
                    System.out.println("\nNUM : " + i);
                    Componente c = comp.listaComponentes.get(i);
                    Object d = dibujar2(c);
                    if (d instanceof JButton) {
                        JButton b = (JButton) d;
                        boxH1.add(b);
//                        if ("1".equals(c.alineado)) { //I
//                            panel.setAlignmentX(LEFT_ALIGNMENT);
//                            boxH1.add(b);
//                        } else if ("2".equals(c.alineado)) {
//                            panel.setAlignmentX(RIGHT_ALIGNMENT);
//                            boxH1.add(b);
//                        } else if ("3".equals(c.alineado)) {
//                            boxH1.add(b);
//                        } else {
//                            boxH1.add(b);
//                            System.out.println("ninguno");
                        //  }
                    } else if (d instanceof JPanel) {
                        JPanel b = (JPanel) d;
                        boxH1.add(b);
//                           if ("1".equals(c.alineado)) { //I
//                            panel.setAlignmentX(LEFT_ALIGNMENT);
//                            boxH1.add(b);
//                        } else if ("2".equals(c.alineado)) {
//                            panel.setAlignmentX(RIGHT_ALIGNMENT);
//                            boxH1.add(b);
//                        } else if ("3".equals(c.alineado)) {
//                            boxH1.add(b);
//                        } else {
//                            boxH1.add(b);
//                            System.out.println("ninguno");
//                        }
                    } else if (d instanceof String) {
                        // System.out.println("V1 = " + boxV1.getComponentCount());
                        boxV1.add(boxH1);
                        System.out.println("H1 = " + boxH1.getComponentCount());
                        boxH1 = Box.createHorizontalBox();
                        //   System.out.println("H2 = " + boxH1.getComponentCount());
                        System.out.println("V2 = " + boxV1.getComponentCount());
                    }
                }
            }
            System.out.println("\nFinal1 = " + boxV1.getComponentCount());
            boxV1.add(boxH1);
            System.out.println("Final2 = " + boxV1.getComponentCount());
            panel.add(boxV1, BorderLayout.LINE_END);
            //panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
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
        Componente boton = new Componente("boton", "boton1", jb, l1);
        JButton jb2 = new JButton();
        jb2.setText("Prueba2");
        Componente boton2 = new Componente("boton", "boton2", jb2, l1);
        JButton jb3 = new JButton();
        jb3.setText("Prueba3");
        Componente boton3 = new Componente("boton", "boton3", jb3, l1);
        Componente salto = new Componente("salto", "salto", jb3, l1);

        ArrayList<Componente> l2 = new ArrayList();
        l2.add(boton);
        l2.add(salto);
        l2.add(boton2);
        //    l2.add(boton3);
        JPanel jp = new JPanel();
        jp.setBackground(Color.blue);
        jp.setAlignmentX(CENTER_ALIGNMENT);
        //jp.setAlignmentX(RIGHT_ALIGNMENT);
        // jp.setAlignmentX(LEFT_ALIGNMENT);
        jp.setSize(800, 100);
        jp.setPreferredSize(new Dimension(800, 100));
        jp.setMaximumSize(jp.getPreferredSize());
        jp.setBackground(Color.blue);
        jp.setLayout(new BorderLayout());
        Componente panel = new Componente("panel", "panel1", jp, l2, "2");
        return panel;
    }

    public static String primeraMayuscula(String palabra) {
        char[] caracteres = palabra.toCharArray();
        caracteres[0] = Character.toUpperCase(caracteres[0]);
        for (int i = 0; i < palabra.length() - 2; i++) {// Es 'palabra'
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',') {// Reemplazamos
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
            }
        }
        String resultado = String.valueOf(caracteres);
        return resultado;
    }
}
