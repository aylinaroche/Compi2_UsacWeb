package chtml.Ejecutar;

import ccss.Ejecutar.BloqueCCSS;
import ccss.Ejecutar.Estilo;
import static chtml.Ejecutar.Elementos.convertirColor;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
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
        int alto = 50, ancho = 100;
        //Iniciar boton
        JButton boton = new JButton();
        boton.setBackground(Color.WHITE);
        Boolean correcta = false;
        boton.setName("");

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
                                Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en imagen", f, c);
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
                        String sFichero = e.valor.toString();
                        File fichero = new File(sFichero);
                        if (fichero.exists()) {
                            try {
                                boton.setIcon(new ImageIcon(e.valor.toString()));
                                correcta = true;
                            } catch (Exception ex) {
                                boton.setIcon(new ImageIcon("C:\\Users\\Aroche\\Documents\\NetBeansProjects\\UsacWeb\\src\\Imagenes\\found.png"));
                                correcta = false;
                            }
                        } else {
                            boton.setIcon(new ImageIcon("C:\\Users\\Aroche\\Documents\\NetBeansProjects\\UsacWeb\\src\\Imagenes\\found.png"));
                            correcta = false;
                            Datos.agregarError("Error Semantico", "No existe la imagen descrita en la ruta", f, c);
                        }
                    }

                } else {
                    Datos.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en imagen", f, c);
                }

            } catch (NumberFormatException e) {
                Datos.agregarError("Error Semantico", "Error al crear imagen", f, c);
            }
        }
        if (!"".equals(texto) && correcta == false) {
            File fichero = new File(texto);
            if (fichero.exists()) {
                try {
                    boton.setIcon(new ImageIcon(texto));
                } catch (Exception ex) {
                    boton.setIcon(new ImageIcon("C:\\Users\\Aroche\\Documents\\NetBeansProjects\\UsacWeb\\src\\Imagenes\\found.png"));
                }
            } else {
                Datos.agregarError("Error Semantico", "No existe la imagen", f, c);
            }
        }
        if (alto > 0 && ancho > 0) {
            boton.setSize(ancho, alto);
            boton.setPreferredSize(new Dimension(ancho, alto));
            boton.setMaximumSize(boton.getPreferredSize());
        }
        Componente resultado = new Componente("Imagen", boton.getName(), boton, new ArrayList());
        return resultado;
    }
}
