package chtml.Ejecutar;

import ccss.Ejecutar.BloqueCCSS;
import ccss.Ejecutar.Estilo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import usacweb.Datos;
import static usacweb.Interfaz.panelPestanias;

/**
 *
 * @author Aroche
 */
public class Elementos {

    public static void setTitulo(String titulo) {
        panelPestanias.setTitleAt(panelPestanias.getSelectedIndex(), titulo);
    }

    public static JPanel crearPanel(ArrayList elementos, Object componentes, int f, int c) {
        JPanel panel = new JPanel();
        for (int i = 0; i < elementos.size(); i++) {
            Estilo e = (Estilo) elementos.get(i);
            String valor = (String) e.valor;

            if (e.nombre.equalsIgnoreCase("id") || e.nombre.equalsIgnoreCase("grupo")) {
                ArrayList listaCCSS = BloqueCCSS.obtenerBloque(e.nombre, valor);

                for (int j = 0; j < listaCCSS.size(); j++) {
                    Estilo e2 = (Estilo) listaCCSS.get(j);

                    switch (e2.nombre.toLowerCase()) {
                        case "fondo":
                            Color color = convertirColor(e2.valor.toString());
                            panel.setBackground(color);
                            break;
                        case "texto":
                            break;
                        default:
                            Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en panel", f, c);
                            break;
                    }

                }
            } else if (e.nombre.equalsIgnoreCase("fondo")) {
                Color color = convertirColor(valor);
                panel.setBackground(color);
            } else {
                Datos.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en panel", f, c);
            }

        }

        return panel;
    }

    public static Color convertirColor(String color) {
        Color nuevo = null;
        switch (color.toLowerCase()) {
            case "red":
                nuevo = Color.RED;
                break;
            case "black":
                nuevo = Color.BLACK;
                break;
            case "blue":
                nuevo = Color.BLUE;
                break;
            case "cyan":
                nuevo = Color.CYAN;
                break;
            case "magenta":
                nuevo = Color.MAGENTA;
                break;
            case "pink":
                nuevo = Color.PINK;
                break;
            case "white":
                nuevo = Color.WHITE;
                break;
            case "yellow":
                nuevo = Color.YELLOW;
                break;
            case "green":
                nuevo = Color.GREEN;
                break;
        }
        try {
            nuevo = Color.decode(color);
        } catch (NumberFormatException e) {
            Datos.agregarError("Error Semantico", "Al convertir " + color + " en color", 0, 0);
        }
        return nuevo;
    }

    public static Object dibujar(Componente comp) {
        if (comp.tipo.equalsIgnoreCase("panel")) {
            JPanel panel = (JPanel) comp.valor;

            for (int i = 0; i < comp.listaComponentes.size(); i++) {
                Componente c = comp.listaComponentes.get(i);
                Object d = dibujar(c);
                if (d instanceof JButton) {
                    JButton b = (JButton) d;
                    panel.add(b);
                }
            }
            return panel;
        } else if (comp.tipo.equalsIgnoreCase("boton")) {
            JButton boton = (JButton) comp.valor;
            return boton;
        }
        return null;
    }

    public static Componente compPrueba() {
        ArrayList<Componente> l1 = new ArrayList();
        JButton jb = new JButton();
        jb.setText("Prueba");
        Componente boton = new Componente("boton", "boton1", jb, l1);
        ArrayList<Componente> l2 = new ArrayList();
        l2.add(boton);
        JPanel jp = new JPanel();
        jp.setBackground(Color.blue);
        Componente panel = new Componente("panel", "panel1", jp, l2);
        return panel;
    }
}
