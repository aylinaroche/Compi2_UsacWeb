package chtml.Ejecutar;

import ccss.Ejecutar.BloqueCCSS;
import ccss.Ejecutar.Estilo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import usacweb.Errores;
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
                            Errores.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en panel", f, c);
                            break;
                    }

                }
            } else if (e.nombre.equalsIgnoreCase("fondo")) {
                Color color = convertirColor(valor);
                panel.setBackground(color);
            } else {
                Errores.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en panel", f, c);
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
            Errores.agregarError("Error Semantico", "Al convertir " + color + " en color", 0, 0);
        }
        return nuevo;
    }

    public static void dibujar(Object cuerpo) {

        
    }
}
