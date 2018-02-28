
package chtml.Ejecutar;

import ccss.Ejecutar.BloqueCCSS;
import ccss.Ejecutar.Estilo;
import static chtml.Ejecutar.Elementos.convertirColor;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import usacweb.Datos;

/**
 *
 * @author Aroche
 */
public class Panel {
    
    public static Componente crearPanel(ArrayList elementos, ArrayList componentes, int f, int c) {
        String alineado = "";
        String nombre = "";
        JPanel panel = new JPanel();
        panel.setSize(20, 20);
        for (int i = 0; i < elementos.size(); i++) {
            try {
                Estilo e = (Estilo) elementos.get(i);
                // String valor = (String)e.valor
                if (e.nombre.equalsIgnoreCase("id") || e.nombre.equalsIgnoreCase("grupo")) {
                    ArrayList listaCCSS = BloqueCCSS.obtenerBloque(e.nombre, (String) e.valor);
                    if (e.nombre.equalsIgnoreCase("id")) {
                        panel.setName(e.valor.toString());
                    }
                    for (int j = 0; j < listaCCSS.size(); j++) {
                        Estilo e2 = (Estilo) listaCCSS.get(j);

                        switch (e2.nombre.toLowerCase()) {
                            case "fondo":
                                Color color = convertirColor(e2.valor.toString());
                                panel.setBackground(color);
                                break;
                            case "alineado":
                                alineado = e2.valor.toString();
                                //panel.setLayout(new BorderLayout);
                                break;
                            case "opaque":
                                if (e2.valor.toString().equalsIgnoreCase("true")) {
                                    panel.setOpaque(true);
                                } else {
                                    panel.setOpaque(false);
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
                                        panel.setBorder(border);
                                    } catch (NumberFormatException ex) {
                                        Datos.agregarError("Error Semantico", "Error al asignar borde al panel " + e2.valor.toString(), f, c);
                                    }
                                }
                                break;
                            case "autoredimension":
                                break;
                            default:
                                Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en panel", f, c);
                                break;
                        }

                    }
                } else if (e.nombre.equalsIgnoreCase("fondo")) {
                    Color color = convertirColor((String) e.valor);
                    panel.setBackground(color);
                } else if (e.nombre.equalsIgnoreCase("alto")) {
                    int ancho = panel.getWidth();
                    int alto = Integer.parseInt((String) e.valor);
                    panel.setSize(ancho, alto);
                    panel.setPreferredSize(new Dimension(ancho, alto));
                    panel.setMaximumSize(panel.getPreferredSize());
                } else if (e.nombre.equalsIgnoreCase("ancho")) {
                    int alto = panel.getHeight();
                    int ancho = Integer.parseInt((String) e.valor);
                    panel.setSize(ancho, alto);
                    panel.setPreferredSize(new Dimension(ancho, alto));
                    panel.setMaximumSize(panel.getPreferredSize());
                } else if (e.nombre.equalsIgnoreCase("alineado")) {

                } else {
                    Datos.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en panel", f, c);
                }

            } catch (NumberFormatException e) {
                Datos.agregarError("Error Semantico", "Error al crear el panel", f, c);
            }
        }
        Componente resultado = new Componente("Panel", panel.getName(), panel, componentes);
        return resultado;
    }

  
}
