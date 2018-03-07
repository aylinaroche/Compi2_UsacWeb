package chtml.Ejecutar;

import ccss.Ejecutar.BloqueCCSS;
import ccss.Ejecutar.Estilo;
import static chtml.Ejecutar.Elementos.convertirColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.StyleConstants;
import usacweb.Datos;

/**
 *
 * @author Aroche
 */
public class CajaTexto {

    public static Componente crearCajaTexto(ArrayList elementos, String texto, int f, int c) {

        String letra = "Comic Sans MS";
        int tamanio = 12;
        int estilo = 0;
        int estilo2 = 0;
        int alto = 50, ancho = 100;

        JTextField caja = new JTextField();
        texto = texto.replace("\n", "");
        caja.setText(texto);
        caja.setFont(new Font(letra, estilo, tamanio));
        caja.setBackground(new Color(255, 255, 255));
        caja.setName("");

        for (int i = 0; i < elementos.size(); i++) {
            try {
                Estilo e = (Estilo) elementos.get(i);
                if (e.nombre.equalsIgnoreCase("id") || e.nombre.equalsIgnoreCase("grupo")) {
                    ArrayList listaCCSS = BloqueCCSS.obtenerBloque(e.nombre, (String) e.valor);
                    if (e.nombre.equalsIgnoreCase("id")) {
                        caja.setName(e.valor.toString());
                    }
                    for (int j = 0; j < listaCCSS.size(); j++) {
                        Estilo e2 = (Estilo) listaCCSS.get(j);

                        switch (e2.nombre.toLowerCase()) {
                            case "fondo":
                                Color color = convertirColor(e2.valor.toString());
                                caja.setBackground(color);
                                break;
                            case "opaque":
                                if (e2.valor.toString().equalsIgnoreCase("true")) {
                                    caja.setOpaque(true);
                                } else {
                                    caja.setOpaque(false);
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
                                        caja.setBorder(border);

                                    } catch (NumberFormatException ex) {
                                        Datos.agregarError("Error Semantico", "Error al asignar borde al caja texto " + e2.valor.toString(), f, c);
                                    }
                                }
                                break;
                            case "texto":
                                String text = (String) e2.valor;
                                switch (estilo2) {
                                    case 3:
                                        caja.setText(text.toUpperCase());
                                        break;
                                    case 4:
                                        caja.setText(text.toLowerCase());
                                        break;
                                    case 5:
                                        caja.setText(Elementos.primeraMayuscula(text));
                                        break;
                                    default:
                                        caja.setText(text);
                                        break;
                                }
                                break;
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
                                                caja.setText(caja.getText().toUpperCase());
                                                break;
                                            case "minuscula":
                                                estilo2 = 4;
                                                caja.setText(caja.getText().toLowerCase());
                                                break;
                                            case "capital-t":
                                            case "capital":
                                                estilo2 = 5;
                                                caja.setText(Elementos.primeraMayuscula(caja.getText()));
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
                                caja.setVisible(false);
                                if ("verdadero".equals((String) e2.valor)) {
                                    caja.setVisible(true);
                                }
                                break;
                            case "colortext":
                                Color colorT = convertirColor(e2.valor.toString());
                                caja.setForeground(colorT);
                                break;
                            case "autoredimension":
                                break;

                            case "alineado":
                                switch (e2.valor.toString()) {
                                    case "izquierda":
                                        caja.setAlignmentX(StyleConstants.ALIGN_LEFT);
                                        break;
                                    case "derecha":
                                        caja.setAlignmentX(StyleConstants.ALIGN_RIGHT);
                                        break;
                                    case "centrado":
                                        caja.setAlignmentX(StyleConstants.ALIGN_CENTER);
                                        break;
                                    case "justificado":
                                        caja.setAlignmentX(StyleConstants.ALIGN_JUSTIFIED);
                                        break;
                                }
                                break;
                            default:
                                Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en caja texto", f, c);
                                break;
                        }

                    }
                } else if (e.nombre.equalsIgnoreCase("fondo")) {
                    Color color = convertirColor((String) e.valor);
                    caja.setBackground(color);
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
                            caja.setAlignmentX(StyleConstants.ALIGN_LEFT);
                            break;
                        case "derecha":
                            caja.setAlignmentX(StyleConstants.ALIGN_RIGHT);
                            break;
                        case "centrado":
                            caja.setAlignmentX(StyleConstants.ALIGN_CENTER);
                            break;
                        case "justificado":
                            caja.setAlignmentX(StyleConstants.ALIGN_JUSTIFIED);
                            break;
                    }
                    break;
                } else {
                    Datos.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en caja de texto", f, c);
                }
            } catch (NumberFormatException e) {
                Datos.agregarError("Error Semantico", "Error al crear caja de texto", f, c);
            }
        }
        try {
            caja.setFont(new Font(letra, estilo, tamanio));
            //caja.setText(texto);
        } catch (Exception e) {
            Datos.agregarError("Error Semantico", "Error al agregar font al caja de texto", f, c);
        }
        if (alto > 0 && ancho > 0) {
            caja.setSize(ancho, alto);
            caja.setPreferredSize(new Dimension(ancho, alto));
            caja.setMaximumSize(caja.getPreferredSize());
        }
        Componente resultado = new Componente("Texto", caja.getName(), caja, new ArrayList());
        return resultado;
    }

    public static Componente modificarCajaTexto(JTextField caja, String nombre, Object valor, int f, int c) {
        String letra = caja.getFont().getFontName();
        int tamanio = caja.getFont().getSize();
        int estilo = caja.getFont().getStyle();
        int alto = caja.getHeight();
        int ancho = caja.getWidth();
        int estilo2 = 0;
        caja.setFont(new Font(letra, estilo, tamanio));
        caja.setBackground(new Color(255, 255, 255));

        if (nombre.equalsIgnoreCase("id") || nombre.equalsIgnoreCase("grupo")) {
            ArrayList listaCCSS = BloqueCCSS.obtenerBloque(nombre, (String) valor);
            if (nombre.equalsIgnoreCase("id")) {
                caja.setName(valor.toString());
            }
            for (int j = 0; j < listaCCSS.size(); j++) {
                Estilo e2 = (Estilo) listaCCSS.get(j);

                switch (e2.nombre.toLowerCase()) {
                    case "fondo":
                        Color color = convertirColor(e2.valor.toString());
                        caja.setBackground(color);
                        break;
                    case "opaque":
                        if (e2.valor.toString().equalsIgnoreCase("true")) {
                            caja.setOpaque(true);
                        } else {
                            caja.setOpaque(false);
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
                                caja.setBorder(border);

                            } catch (NumberFormatException ex) {
                                Datos.agregarError("Error Semantico", "Error al asignar borde al caja texto " + e2.valor.toString(), f, c);
                            }
                        }
                        break;
                    case "texto":
                        String text = (String) e2.valor;
                        switch (estilo2) {
                            case 3:
                                caja.setText(text.toUpperCase());
                                break;
                            case 4:
                                caja.setText(text.toLowerCase());
                                break;
                            case 5:
                                caja.setText(Elementos.primeraMayuscula(text));
                                break;
                            default:
                                caja.setText(text);
                                break;
                        }
                        break;
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
                                        caja.setText(caja.getText().toUpperCase());
                                        break;
                                    case "minuscula":
                                        estilo2 = 4;
                                        caja.setText(caja.getText().toLowerCase());
                                        break;
                                    case "capital-t":
                                    case "capital":
                                        estilo2 = 5;
                                        caja.setText(Elementos.primeraMayuscula(caja.getText()));
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
                        caja.setVisible(false);
                        if ("verdadero".equals((String) e2.valor)) {
                            caja.setVisible(true);
                        }
                        break;
                    case "colortext":
                        Color colorT = convertirColor(e2.valor.toString());
                        caja.setForeground(colorT);
                        break;
                    case "autoredimension":
                        break;

                    case "alineado":
                        switch (e2.valor.toString()) {
                            case "izquierda":
                                caja.setAlignmentX(StyleConstants.ALIGN_LEFT);
                                break;
                            case "derecha":
                                caja.setAlignmentX(StyleConstants.ALIGN_RIGHT);
                                break;
                            case "centrado":
                                caja.setAlignmentX(StyleConstants.ALIGN_CENTER);
                                break;
                            case "justificado":
                                caja.setAlignmentX(StyleConstants.ALIGN_JUSTIFIED);
                                break;
                        }
                        break;
                    default:
                        Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en caja texto", f, c);
                        break;
                }

            }
        } else if (nombre.equalsIgnoreCase("fondo")) {
            Color color = convertirColor((String) valor);
            caja.setBackground(color);
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
                    caja.setAlignmentX(StyleConstants.ALIGN_LEFT);
                    break;
                case "derecha":
                    caja.setAlignmentX(StyleConstants.ALIGN_RIGHT);
                    break;
                case "centrado":
                    caja.setAlignmentX(StyleConstants.ALIGN_CENTER);
                    break;
                case "justificado":
                    caja.setAlignmentX(StyleConstants.ALIGN_JUSTIFIED);
                    break;
            }
        } else {
            Datos.agregarError("Error Semantico", "Atributo " + nombre + " incorrecto en caja de texto", f, c);
        }

        try {
            caja.setFont(new Font(letra, estilo, tamanio));
            //caja.setText(texto);
        } catch (Exception e) {
            Datos.agregarError("Error Semantico", "Error al agregar font al caja de texto", f, c);
        }
        if (alto > 0 && ancho > 0) {
            caja.setSize(ancho, alto);
            caja.setPreferredSize(new Dimension(ancho, alto));
            caja.setMaximumSize(caja.getPreferredSize());
        }
        Componente resultado = new Componente("Texto", caja.getName(), caja, new ArrayList());
        return resultado;
    }
}
