package chtml.Ejecutar;

import ccss.Ejecutar.BloqueCCSS;
import ccss.Ejecutar.Estilo;
import static chtml.Ejecutar.Elementos.convertirColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import usacweb.Datos;

/**
 *
 * @author Aroche
 */
public class Contador {
    
    public static Componente crearSpinner(ArrayList elementos, String texto, int f, int c) {
        
        String letra = "Comic Sans MS";
        int tamanio = 12;
        int estilo = 0;
        int alto = 20, ancho = 20;
        //Iniciar spinner
        // JButton spinner = new JButton();
        JSpinner spinner = new JSpinner();
        spinner.setFont(new Font(letra, estilo, tamanio));
        spinner.setValue(0);
        try {
            int cont = Integer.parseInt(texto);
            spinner.setValue(cont);
        } catch (NumberFormatException e) {
        }
        
        for (int i = 0; i < elementos.size(); i++) {
            try {
                Estilo e = (Estilo) elementos.get(i);
                if (e.nombre.equalsIgnoreCase("id") || e.nombre.equalsIgnoreCase("grupo")) {
                    ArrayList listaCCSS = BloqueCCSS.obtenerBloque(e.nombre, (String) e.valor);
                    if (e.nombre.equalsIgnoreCase("id")) {
                        spinner.setName(e.valor.toString());
                    }
                    for (int j = 0; j < listaCCSS.size(); j++) {
                        Estilo e2 = (Estilo) listaCCSS.get(j);
                        
                        switch (e2.nombre.toLowerCase()) {
                            case "fondo":
                                Color color = convertirColor(e2.valor.toString());
                                spinner.setBackground(color);
                                break;
                            case "opaque":
                                if (e2.valor.toString().equalsIgnoreCase("true")) {
                                    spinner.setOpaque(true);
                                } else {
                                    spinner.setOpaque(false);
                                }
                            case "borde":
                                if (e2.valor instanceof ArrayList) {
                                    ArrayList valores = (ArrayList) e2.valor;
                                    try {
                                        int tam = (int) valores.get(0);
                                        Color colorBorde = convertirColor((String) valores.get(1));
                                        Boolean curva = false;
                                        if ("verdadero".equals((String) valores.get(2))) {
                                            curva = true;
                                        }
                                        Border border = BorderFactory.createLineBorder(colorBorde, tam, curva);
                                        spinner.setBorder(border);
                                    } catch (NumberFormatException ex) {
                                        Datos.agregarError("Error Semantico", "Error al asignar borde al panel " + e2.valor.toString(), f, c);
                                    }
                                }
                                break;
                            case "visible":
                                spinner.setVisible(false);
                                if ("verdadero".equals((String) e2.valor)) {
                                    spinner.setVisible(true);
                                }
                                break;
                            case "autoredimension":
                                
                                break;
                            case "alineado":
                                switch (e2.valor.toString()) {
                                    case "izquierda":
                                        spinner.setAlignmentX(SwingConstants.LEFT);
                                        break;
                                    case "derecha":
                                        spinner.setAlignmentX(SwingConstants.RIGHT);
                                        break;
                                    case "centrado":
                                        spinner.setAlignmentX(SwingConstants.CENTER);
                                        break;
                                    case "justificado":
                                        spinner.setAlignmentX(SwingConstants.LEADING);
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
                    spinner.setBackground(color);
                } else if (e.nombre.equalsIgnoreCase("alto")) {
                    alto = Integer.parseInt((String) e.valor);
                } else if (e.nombre.equalsIgnoreCase("ancho")) {
                    ancho = Integer.parseInt((String) e.valor);
                } else if (e.nombre.equalsIgnoreCase("alineado")) {
                    switch (e.valor.toString()) {
                        case "izquierda":
                            spinner.setAlignmentX(SwingConstants.LEFT);
                            break;
                        case "derecha":
                            spinner.setAlignmentX(SwingConstants.RIGHT);
                            break;
                        case "centrado":
                            spinner.setAlignmentX(SwingConstants.CENTER);
                            break;
                        case "justificado":
                            spinner.setAlignmentX(SwingConstants.LEADING);
                            break;
                    }
                    break;
                } else {
                    Datos.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en panel", f, c);
                }
                
            } catch (NumberFormatException e) {
                Datos.agregarError("Error Semantico", "Error al crear el spinner", f, c);
            }
        }
        //SETEAR VALORES 
        //FONT
        try {
            spinner.setFont(new Font(letra, estilo, tamanio));
        } catch (Exception e) {
            Datos.agregarError("Error Semantico", "Error al agregar font al spinner", f, c);
        }
        if (alto > 0 && ancho > 0) {
            spinner.setSize(ancho, alto);
            spinner.setPreferredSize(new Dimension(ancho, alto));
            spinner.setMaximumSize(spinner.getPreferredSize());
        }
        Componente resultado = new Componente("Spinner", spinner.getName(), spinner, new ArrayList());
        return resultado;
    }
    
}
