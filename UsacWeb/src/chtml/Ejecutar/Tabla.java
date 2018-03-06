package chtml.Ejecutar;

import ccss.Ejecutar.BloqueCCSS;
import ccss.Ejecutar.Estilo;
import static chtml.Ejecutar.Elementos.convertirColor;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.RIGHT_ALIGNMENT;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import usacweb.Datos;

/**
 *
 * @author Aroche
 */
public class Tabla {

    public static Componente crearTabla(ArrayList elementos, ArrayList filas, int f, int c) {
        String alineado = "centrado";
        int alto = 500, ancho = 1000;

        JTable tabla = new JTable();
        tabla.setName("");
        tabla.setLayout(new BorderLayout());

        for (int i = 0; i < elementos.size(); i++) {
            try {
                Estilo e = (Estilo) elementos.get(i);

                if (e.nombre.equalsIgnoreCase("id") || e.nombre.equalsIgnoreCase("grupo")) {
                    ArrayList listaCCSS = BloqueCCSS.obtenerBloque(e.nombre, (String) e.valor);
                    if (e.nombre.equalsIgnoreCase("id")) {
                        tabla.setName(e.valor.toString());
                    }
                    for (int j = 0; j < listaCCSS.size(); j++) {
                        Estilo e2 = (Estilo) listaCCSS.get(j);

                        switch (e2.nombre.toLowerCase()) {
                            case "fondo":
                                Color color = convertirColor(e2.valor.toString());
                                tabla.setBackground(color);
                                break;
                            case "alineado":
                                alineado = e2.valor.toString();
                                if (alineado.equalsIgnoreCase("derecha")) {
                                    tabla.setAlignmentX(RIGHT_ALIGNMENT);
                                }
                                break;
                            case "opaque":
                                if (e2.valor.toString().equalsIgnoreCase("true")) {
                                    tabla.setOpaque(true);
                                } else {
                                    tabla.setOpaque(false);
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
                                        
                                        tabla.setBorder(border);
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
                    tabla.setBackground(color);
                } else if (e.nombre.equalsIgnoreCase("alto")) {
                    try {
                        alto = Integer.parseInt((String) e.valor);
                    } catch (NumberFormatException en) {

                    }
                } else if (e.nombre.equalsIgnoreCase("ancho")) {
                    try {
                        ancho = Integer.parseInt((String) e.valor);
                    } catch (NumberFormatException en) {

                    }
                } else if (e.nombre.equalsIgnoreCase("alineado")) {
                    alineado = e.valor.toString();
                    if (alineado.equalsIgnoreCase("derecha")) {
                        tabla.setAlignmentX(RIGHT_ALIGNMENT);
                    }
                } else {
                    Datos.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en panel", f, c);
                }

            } catch (NumberFormatException e) {
                Datos.agregarError("Error Semantico", "Error al crear el panel", f, c);
            }
        }

        if (alto > 0 && ancho > 0) {
            tabla.setSize(ancho, alto);
            tabla.setPreferredSize(new Dimension(ancho, alto));
            tabla.setMaximumSize(tabla.getPreferredSize());
        }

        Object[] titulo = null;
        int tamF = 0;
        int tamC = 0;
        for (int i = 0; i < filas.size(); i++) {
            //Filas
            ArrayList celdas = (ArrayList) filas.get(i);
            if (celdas.size() > 0) {
                Componente t = (Componente) celdas.get(0);
                if (t.tipo.equalsIgnoreCase("cb")) {
                    titulo = new Object[celdas.size()];
                    for (int k = 0; k < celdas.size(); k++) {
                        Componente celda = (Componente) celdas.get(k);
                        Componente valor = (Componente) celda.valor;
                        if (valor.tipo.equalsIgnoreCase("texto")) {
                            titulo[k] = valor.valor.toString();
                        }
                    }
                    tamF++;
                }
                if (tamC < celdas.size()) {
                    tamC = celdas.size();
                }
            }
        }
        int tamF2 = filas.size() - tamF;
        System.out.println("Filas = " + tamF2 + ", Col = " + tamC);
        Object[][] objetos = new Object[tamF2][tamC];
        int j = 0;
        for (int i = 0; i < filas.size(); i++) {
            //Filas
            ArrayList celdas = (ArrayList) filas.get(i);
            if (celdas.size() > 0) {
                Componente t = (Componente) celdas.get(0);
                if (t.tipo.equalsIgnoreCase("ct")) {
//                    titulo = new Object[celdas.size()];
                    for (int k = 0; k < celdas.size(); k++) {
                        Componente celda = (Componente) celdas.get(k);
                        Componente valor = (Componente) celda.valor;
                        //if (valor.tipo.equalsIgnoreCase("texto")) {
                            objetos[j][k] = valor.valor;
                        //}
                    }
                    j++;
                }
                 
            }
           
        }

        DefaultTableModel d = new DefaultTableModel(objetos, titulo);
        tabla.setModel(d);
        if (objetos == null) {
            objetos = new Object[1][titulo.length];
            for (int i = 0; i < titulo.length; i++) {
                objetos[0][i] = " - ";
            }
        }

        JTable prueba = new JTable(objetos, titulo);

        Componente resultado = new Componente("Tabla", tabla.getName(), prueba, new ArrayList(), alineado);
        return resultado;
    }

}
