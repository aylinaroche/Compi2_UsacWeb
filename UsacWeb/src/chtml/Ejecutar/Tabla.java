package chtml.Ejecutar;

import ccss.Ejecutar.BloqueCCSS;
import ccss.Ejecutar.Estilo;
import static chtml.Ejecutar.Elementos.convertirColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
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
        String letra = "Comic Sans MS";
        int tamanio = 12;
        int estilo = 0;
        int estilo2 = 0;
        JTable tabla = new JTable();
        tabla.setName("");
        //tabla.setLayout(new BorderLayout());
        tabla.setFont(new Font(letra, estilo, tamanio));

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
                                        Datos.agregarError("Error Semantico", "Error al asignar borde al tabla " + e2.valor.toString(), f, c);
                                    }
                                }
                                break;
//                            case "texto":
//                                String text = (String) e2.valor;
//                                switch (estilo2) {
//                                    case 3:
//                                        tabla.setText(text.toUpperCase());
//                                        break;
//                                    case 4:
//                                        tabla.setText(text.toLowerCase());
//                                        break;
//                                    case 5:
//                                        tabla.setText(Elementos.primeraMayuscula(text));
//                                        break;
//                                    default:
//                                        tabla.setText(text);
//                                        break;
//                                }
//                                break;
                            case "formato":
                                if (e2.valor instanceof ArrayList) {
                                    ArrayList formato = (ArrayList) e2.valor;
                                    for (int k = 0; k < formato.size(); k++) {
                                        String form = (String) formato.get(k);
                                        switch (form.toLowerCase()) {
                                            case "negrilla":
                                                estilo = estilo + 1;
                                                break;
                                            case "cursiva":
                                                estilo = estilo + 2;
                                                break;
                                            case "mayuscula":
                                                estilo2 = 3;
                                                // tabla.setText(tabla.getText().toUpperCase());
                                                break;
                                            case "minuscula":
                                                estilo2 = 4;
                                                //tabla.setText(tabla.getText().toLowerCase());
                                                break;
                                            case "capital-t":
                                                estilo2 = 5;
                                                // tabla.setText(Elementos.primeraMayuscula(tabla.getText()));
                                                break;
                                        }

                                    }
                                }
                                break;
                            case "letra":
                                letra = (String) e2.valor;
                                break;
                            case "tamtex":
                                if (e2.valor instanceof Double) {
                                    Double decimal = (Double) e2.valor;
                                    tamanio = decimal.intValue();
                                } else if (e2.valor instanceof Integer) {
                                    tamanio = Integer.parseInt(e2.valor.toString());
                                } else if (e2.valor instanceof String) {
                                    try {
                                        tamanio = Integer.parseInt((String) e2.valor);
                                    } catch (NumberFormatException ex) {
                                        System.out.println("Problema al castear: " + ex);
                                    }
                                }
                                break;
                            case "visible":
                                tabla.setVisible(false);
                                if ("verdadero".equals((String) e2.valor)) {
                                    tabla.setVisible(true);
                                }
                                break;
                            case "colortext":
                                Color colorT = convertirColor(e2.valor.toString());
                                tabla.setForeground(colorT);
                                break;
                            case "autoredimension":

                                break;
                            case "alineado":
                                switch (e2.valor.toString()) {
                                    case "izquierda":
                                        tabla.setAlignmentX(SwingConstants.LEFT);
                                        break;
                                    case "derecha":
                                        tabla.setAlignmentX(SwingConstants.RIGHT);
                                        break;
                                    case "centrado":
                                        tabla.setAlignmentX(SwingConstants.CENTER);
                                        break;
                                    case "justificado":
                                        tabla.setAlignmentX(SwingConstants.LEADING);
                                        break;
                                }
                                break;
                            default:
                                Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en tabla", f, c);
                                break;
                        }

                    }
                } else if (e.nombre.equalsIgnoreCase("fondo")) {
                    Color color = convertirColor((String) e.valor);
                    tabla.setBackground(color);
                } else if (e.nombre.equalsIgnoreCase("alto")) {
                    if (e.valor instanceof Double) {
                        Double decimal = (Double) e.valor;
                        alto = decimal.intValue();
                    } else if (e.valor instanceof Integer) {
                        alto = Integer.parseInt(e.valor.toString());
                    } else if (e.valor instanceof String) {
                        try {
                            alto = Integer.parseInt((String) e.valor);
                        } catch (NumberFormatException ex) {
                            System.out.println("Problema al castear: " + ex);
                        }
                    }
                } else if (e.nombre.equalsIgnoreCase("ancho")) {
                    if (e.valor instanceof Double) {
                        Double decimal = (Double) e.valor;
                        ancho = decimal.intValue();
                    } else if (e.valor instanceof Integer) {
                        ancho = Integer.parseInt(e.valor.toString());
                    } else if (e.valor instanceof String) {
                        try {
                            ancho = Integer.parseInt((String) e.valor);
                        } catch (NumberFormatException ex) {
                            System.out.println("Problema al castear: " + ex);
                        }
                    }
                } else if (e.nombre.equalsIgnoreCase("alineado")) {
                    switch (e.valor.toString()) {
                        case "izquierda":
                            tabla.setAlignmentX(SwingConstants.LEFT);
                            break;
                        case "derecha":
                            tabla.setAlignmentX(SwingConstants.RIGHT);
                            break;
                        case "centrado":
                            tabla.setAlignmentX(SwingConstants.CENTER);
                            break;
                        case "justificado":
                            tabla.setAlignmentX(SwingConstants.LEADING);
                            break;
                    }
                } else {
                    Datos.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en tabla", f, c);
                }

            } catch (NumberFormatException e) {
                Datos.agregarError("Error Semantico", "Error al crear tabla", f, c);
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
                        //Componente valor = (Componente) celda.valor;
                        if (celda.nombre.equalsIgnoreCase("texto")) {
                            titulo[k] = celda.valor.toString();
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
        Object[][] objetos = new Object[tamF2 + 1][tamC];

        for (int i = 0; i < tamC; i++) {
            objetos[0][i] = titulo[i];
        }

        int j = 1;
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

        if (objetos == null) {
            objetos = new Object[1][titulo.length];
            for (int i = 0; i < titulo.length; i++) {
                objetos[0][i] = " - ";
            }
        }
        if (titulo != null) {
            tabla.setDefaultRenderer(Object.class, new Render());

            DefaultTableModel d = new DefaultTableModel(objetos, titulo) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tabla.setModel(d);
            tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
        }
        // JTable prueba = new JTable(objetos, titulo);
        Componente resultado = new Componente("Tabla", tabla.getName(), tabla, new ArrayList(), alineado);
        return resultado;
    }

    public static Componente modificarTabla(JTable tabla, String nombre, Object valor, int f, int c) {
        String alineado = "centrado";
        String letra = tabla.getFont().getFontName();
        int tamanio = tabla.getFont().getSize();
        int estilo = tabla.getFont().getStyle();
        int alto = tabla.getHeight();
        int ancho = tabla.getWidth();
        int estilo2 = 0;

        if (nombre.equalsIgnoreCase("id") || nombre.equalsIgnoreCase("grupo")) {
            ArrayList listaCCSS = BloqueCCSS.obtenerBloque(nombre, (String) valor);
            if (nombre.equalsIgnoreCase("id")) {
                tabla.setName(valor.toString());
            }
            for (int j = 0; j < listaCCSS.size(); j++) {
                Estilo e2 = (Estilo) listaCCSS.get(j);

                switch (e2.nombre.toLowerCase()) {
                    case "fondo":
                        Color color = convertirColor(e2.valor.toString());
                        tabla.setBackground(color);
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
                                Datos.agregarError("Error Semantico", "Error al asignar borde al tabla " + e2.valor.toString(), f, c);
                            }
                        }
                        break;
//                            case "texto":
//                                String text = (String) e2.valor;
//                                switch (estilo2) {
//                                    case 3:
//                                        tabla.setText(text.toUpperCase());
//                                        break;
//                                    case 4:
//                                        tabla.setText(text.toLowerCase());
//                                        break;
//                                    case 5:
//                                        tabla.setText(Elementos.primeraMayuscula(text));
//                                        break;
//                                    default:
//                                        tabla.setText(text);
//                                        break;
//                                }
//                                break;
                    case "formato":
                        if (e2.valor instanceof ArrayList) {
                            ArrayList formato = (ArrayList) e2.valor;
                            for (int k = 0; k < formato.size(); k++) {
                                String form = (String) formato.get(k);
                                switch (form.toLowerCase()) {
                                    case "negrilla":
                                        estilo = estilo + 1;
                                        break;
                                    case "cursiva":
                                        estilo = estilo + 2;
                                        break;
                                    case "mayuscula":
                                        estilo2 = 3;
                                        // tabla.setText(tabla.getText().toUpperCase());
                                        break;
                                    case "minuscula":
                                        estilo2 = 4;
                                        //tabla.setText(tabla.getText().toLowerCase());
                                        break;
                                    case "capital-t":
                                        estilo2 = 5;
                                        // tabla.setText(Elementos.primeraMayuscula(tabla.getText()));
                                        break;
                                }

                            }
                        }
                        break;
                    case "letra":
                        letra = (String) e2.valor;
                        break;
                    case "tamtex":
                        if (e2.valor instanceof Double) {
                            Double decimal = (Double) e2.valor;
                            tamanio = decimal.intValue();
                        } else if (e2.valor instanceof Integer) {
                            tamanio = Integer.parseInt(e2.valor.toString());
                        } else if (e2.valor instanceof String) {
                            try {
                                tamanio = Integer.parseInt((String) e2.valor);
                            } catch (NumberFormatException ex) {
                                System.out.println("Problema al castear: " + ex);
                            }
                        }
                        break;
                    case "visible":
                        tabla.setVisible(false);
                        if ("verdadero".equals((String) e2.valor)) {
                            tabla.setVisible(true);
                        }
                        break;
                    case "colortext":
                        Color colorT = convertirColor(e2.valor.toString());
                        tabla.setForeground(colorT);
                        break;
                    case "autoredimension":

                        break;
                    case "alineado":
                        switch (e2.valor.toString()) {
                            case "izquierda":
                                tabla.setAlignmentX(SwingConstants.LEFT);
                                break;
                            case "derecha":
                                tabla.setAlignmentX(SwingConstants.RIGHT);
                                break;
                            case "centrado":
                                tabla.setAlignmentX(SwingConstants.CENTER);
                                break;
                            case "justificado":
                                tabla.setAlignmentX(SwingConstants.LEADING);
                                break;
                        }
                        break;
                    default:
                        Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en tabla", f, c);
                        break;
                }

            }
        } else if (nombre.equalsIgnoreCase("fondo")) {
            Color color = convertirColor((String) valor);
            tabla.setBackground(color);
        } else if (nombre.equalsIgnoreCase("alto")) {
            if (valor instanceof Double) {
                Double decimal = (Double) valor;
                alto = decimal.intValue();
            } else if (valor instanceof Integer) {
                alto = Integer.parseInt(valor.toString());
            } else if (valor instanceof String) {
                try {
                    alto = Integer.parseInt((String) valor);
                } catch (NumberFormatException ex) {
                    System.out.println("Problema al castear: " + ex);
                }
            }
        } else if (nombre.equalsIgnoreCase("ancho")) {
            if (valor instanceof Double) {
                Double decimal = (Double) valor;
                ancho = decimal.intValue();
            } else if (valor instanceof Integer) {
                ancho = Integer.parseInt(valor.toString());
            } else if (valor instanceof String) {
                try {
                    ancho = Integer.parseInt((String) valor);
                } catch (NumberFormatException ex) {
                    System.out.println("Problema al castear: " + ex);
                }
            }
        } else if (nombre.equalsIgnoreCase("alineado")) {
            switch (valor.toString()) {
                case "izquierda":
                    tabla.setAlignmentX(SwingConstants.LEFT);
                    break;
                case "derecha":
                    tabla.setAlignmentX(SwingConstants.RIGHT);
                    break;
                case "centrado":
                    tabla.setAlignmentX(SwingConstants.CENTER);
                    break;
                case "justificado":
                    tabla.setAlignmentX(SwingConstants.LEADING);
                    break;
            }
        } else {
            Datos.agregarError("Error Semantico", "Atributo " + nombre + " incorrecto en tabla", f, c);
        }

        if (alto > 0 && ancho > 0) {
            tabla.setSize(ancho, alto);
            tabla.setPreferredSize(new Dimension(ancho, alto));
            tabla.setMaximumSize(tabla.getPreferredSize());
        }
//
//        Object[] titulo = null;
//        int tamF = 0;
//        int tamC = 0;
//        for (int i = 0; i < filas.size(); i++) {
//            //Filas
//            ArrayList celdas = (ArrayList) filas.get(i);
//            if (celdas.size() > 0) {
//                Componente t = (Componente) celdas.get(0);
//                if (t.tipo.equalsIgnoreCase("cb")) {
//                    titulo = new Object[celdas.size()];
//                    for (int k = 0; k < celdas.size(); k++) {
//                        Componente celda = (Componente) celdas.get(k);
//                        //Componente valor = (Componente) celda.valor;
//                        if (celda.nombre.equalsIgnoreCase("texto")) {
//                            titulo[k] = celda.valor.toString();
//                        }
//                    }
//                    tamF++;
//                }
//                if (tamC < celdas.size()) {
//                    tamC = celdas.size();
//                }
//            }
//        }
//        int tamF2 = filas.size() - tamF;
//        System.out.println("Filas = " + tamF2 + ", Col = " + tamC);
//        Object[][] objetos = new Object[tamF2 + 1][tamC];
//
//        for (int i = 0; i < tamC; i++) {
//            objetos[0][i] = titulo[i];
//        }
//
//        int j = 1;
//        for (int i = 0; i < filas.size(); i++) {
//            //Filas
//            ArrayList celdas = (ArrayList) filas.get(i);
//            if (celdas.size() > 0) {
//                Componente t = (Componente) celdas.get(0);
//                if (t.tipo.equalsIgnoreCase("ct")) {
////                    titulo = new Object[celdas.size()];
//                    for (int k = 0; k < celdas.size(); k++) {
//                        Componente celda = (Componente) celdas.get(k);
//                        Componente valor = (Componente) celda.valor;
//                        //if (valor.tipo.equalsIgnoreCase("texto")) {
//                        objetos[j][k] = valor.valor;
//                        //}
//                    }
//                    j++;
//                }
//            }
//        }
//
//        if (objetos == null) {
//            objetos = new Object[1][titulo.length];
//            for (int i = 0; i < titulo.length; i++) {
//                objetos[0][i] = " - ";
//
//            }
//        }
//        if (titulo != null) {
//            tabla.setDefaultRenderer(Object.class,
//                     new Render());
//
//            DefaultTableModel d = new DefaultTableModel(objetos, titulo) {
//                @Override
//                public boolean isCellEditable(int row, int column) {
//                    return false;
//                }
//            };
//            tabla.setModel(d);
//            tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
//        }
        // JTable prueba = new JTable(objetos, titulo);
        Componente resultado = new Componente("Tabla", tabla.getName(), tabla, new ArrayList(), alineado);
        return resultado;
    }

}
