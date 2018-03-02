package chtml.Ejecutar;

import ccss.Ejecutar.BloqueCCSS;
import ccss.Ejecutar.Estilo;
import static chtml.Ejecutar.Elementos.convertirColor;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import usacweb.Datos;

/**
 *
 * @author Aroche
 */
public class Imagen {

    public static Componente crearImagen(ArrayList elementos, String texto, int f, int c) {
        String nombre = "";
        int alto = 20, ancho = 20;
        //Iniciar boton
        JButton boton = new JButton();

        for (int i = 0; i < elementos.size(); i++) {
            try {
                Estilo e = (Estilo) elementos.get(i);
                if (e.nombre.equalsIgnoreCase("id") || e.nombre.equalsIgnoreCase("grupo")) {
                    ArrayList listaCCSS = BloqueCCSS.obtenerBloque(e.nombre, (String) e.valor);
                    if (e.nombre.equalsIgnoreCase("id")) {
                        boton.setName(e.valor.toString());
                    }
                    for (int j = 0; j < listaCCSS.size(); j++) {
                        Estilo e2 = (Estilo) listaCCSS.get(j);

                        switch (e2.nombre.toLowerCase()) {
                            case "fondo":
                                Color color = convertirColor(e2.valor.toString());
                                boton.setBackground(color);
                                break;
                            case "visible":
                                boton.setVisible(false);
                                if ("verdadero".equals((String) e2.valor)) {
                                    boton.setVisible(true);
                                }
                                break;
                            case "autoredimension":
                                break;
                            case "alineado":
                                switch (e2.valor.toString()) {
                                    case "izquierda":
                                        boton.setHorizontalAlignment(SwingConstants.LEFT);
                                        break;
                                    case "derecha":
                                        boton.setHorizontalAlignment(SwingConstants.RIGHT);
                                        break;
                                    case "centrado":
                                        boton.setHorizontalAlignment(SwingConstants.CENTER);
                                        break;
                                    case "justificado":
                                        boton.setHorizontalAlignment(SwingConstants.LEADING);
                                        break;
                                }
                                break;
                            default:
                                Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en panel", f, c);
                                break;
                        }

                    }
                } else if (e.nombre.equalsIgnoreCase("fondo")) {
                    Color color = convertirColor((String) e.valor);
                    boton.setBackground(color);
                } else if (e.nombre.equalsIgnoreCase("alto")) {
                    alto = Integer.parseInt((String) e.valor);
                } else if (e.nombre.equalsIgnoreCase("ancho")) {
                    ancho = Integer.parseInt((String) e.valor);
                } else if (e.nombre.equalsIgnoreCase("alineado")) {
                    switch (e.valor.toString()) {
                        case "izquierda":
                            boton.setHorizontalAlignment(SwingConstants.LEFT);
                            break;
                        case "derecha":
                            boton.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                        case "centrado":
                            boton.setHorizontalAlignment(SwingConstants.CENTER);
                            break;
                        case "justificado":
                            boton.setHorizontalAlignment(SwingConstants.LEADING);
                            break;
                    }
                } else if (e.nombre.equalsIgnoreCase("ruta")) {
                    if (e.valor.toString() != null) {
                        try {

                        } catch (Exception ex) {

                        }
                    }

                } else {
                    Datos.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en panel", f, c);
                }

            } catch (NumberFormatException e) {
                Datos.agregarError("Error Semantico", "Error al crear el boton", f, c);
            }
        }
        //SETEAR VALORES 
        //FONT
        if (texto != null) {
            try {

               // boton.setIcon(new ImageIcon());
            } catch (Exception e) {
                Datos.agregarError("Error Semantico", "Error al agregar imagen", f, c);
            }
        }
        if (alto > 0 && ancho > 0) {
            boton.setSize(ancho, alto);
            boton.setPreferredSize(new Dimension(ancho, alto));
            boton.setMaximumSize(boton.getPreferredSize());
        }
        Componente resultado = new Componente("Boton", boton.getName(), boton, new ArrayList());
        return resultado;
    }
}
