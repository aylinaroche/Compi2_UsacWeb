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
        JSpinner spinner = new JSpinner();
        spinner.setFont(new Font(letra, estilo, tamanio));
        spinner.setValue(0);
        spinner.setName("");
        try {
            int cont = Integer.parseInt(texto);
            spinner.setValue(cont);
        } catch (NumberFormatException e) {
            Datos.agregarError("Error Semantico", "Al agregar " + texto + " al contador", f, c);
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
                                        Datos.agregarError("Error Semantico", "Error al asignar borde al contador " + e2.valor.toString(), f, c);
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
                                Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en contador", f, c);
                                break;
                        }

                    }
                } else if (e.nombre.equalsIgnoreCase("fondo")) {
                    Color color = convertirColor((String) e.valor);
                    spinner.setBackground(color);
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
                            System.out.println(ex);
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
                            System.out.println(ex);
                        }
                    }
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
                    Datos.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en contador", f, c);
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

    public static Componente modificarSpinner(JSpinner spinner, String nombre, Object valor, int f, int c) {

        String letra = spinner.getFont().getFontName();
        int tamanio = spinner.getFont().getSize();
        int estilo = spinner.getFont().getStyle();
        int alto = spinner.getHeight();
        int ancho = spinner.getWidth();

        if (nombre.equalsIgnoreCase("id") || nombre.equalsIgnoreCase("grupo")) {
            ArrayList listaCCSS = BloqueCCSS.obtenerBloque(nombre, (String) valor);
            if (nombre.equalsIgnoreCase("id")) {
                spinner.setName(valor.toString());
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
                                Datos.agregarError("Error Semantico", "Error al asignar borde al contador " + e2.valor.toString(), f, c);
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
                        Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en contador", f, c);
                        break;
                }

            }
        } else if (nombre.equalsIgnoreCase("fondo")) {
            Color color = convertirColor((String) valor);
            spinner.setBackground(color);
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
                    System.out.println(ex);
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
                    System.out.println(ex);
                }
            }
        } else if (nombre.equalsIgnoreCase("alineado")) {
            switch (valor.toString()) {
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
        } else {
            Datos.agregarError("Error Semantico", "Atributo " + nombre + " incorrecto en contador", f, c);
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
