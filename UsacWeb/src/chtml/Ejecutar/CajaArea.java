package chtml.Ejecutar;

import ccss.Ejecutar.BloqueCCSS;
import ccss.Ejecutar.Estilo;
import static chtml.Ejecutar.Elementos.convertirColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import usacweb.Datos;

/**
 *
 * @author Aroche
 */
public class CajaArea {

    public static Componente crearTexto(ArrayList elementos, String texto, Boolean editable, int f, int c) {

        String letra = "Comic Sans MS";
        int tamanio = 12;
        int estilo = 0;
        int estilo2 = 0;
        int alto = 400, ancho = 800;

        JScrollPane scroll = new JScrollPane();
        JTextPane area = new JTextPane();

        area.setFont(new Font(letra, estilo, tamanio));
        area.setEditable(editable);
        if (editable == true) {
            area.setBackground(Color.WHITE);
            area.setText(texto);
        } else {
            area.setBackground(new Color(0, 0, 0, 0));
            area.setOpaque(false);
            //  texto = texto.replace("\n", "");
            area.setText(texto);
        }
        if (texto.equals(" ") | texto.equals("  ") | texto.equals("  ")) {
            alto = 10;
            ancho = 10;
        }

        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        area.setParagraphAttributes(attribs, true);

        for (int i = 0; i < elementos.size(); i++) {
            try {
                Estilo e = (Estilo) elementos.get(i);
                if (e.nombre.equalsIgnoreCase("id") || e.nombre.equalsIgnoreCase("grupo")) {
                    ArrayList listaCCSS = BloqueCCSS.obtenerBloque(e.nombre, (String) e.valor);
                    if (e.nombre.equalsIgnoreCase("id")) {
                        area.setName(e.valor.toString());
                    }
                    for (int j = 0; j < listaCCSS.size(); j++) {
                        Estilo e2 = (Estilo) listaCCSS.get(j);

                        switch (e2.nombre.toLowerCase()) {
                            case "fondo":
                                Color color = convertirColor(e2.valor.toString());
                                area.setBackground(color);
                                StyleConstants.setBackground(attribs, color);
                                area.setParagraphAttributes(attribs, true);
                                break;
                            case "opaque":
                                if (e2.valor.toString().equalsIgnoreCase("true")) {
                                    area.setOpaque(true);
                                } else {
                                    area.setOpaque(false);
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
                                        area.setBorder(border);

                                    } catch (NumberFormatException ex) {
                                        Datos.agregarError("Error Semantico", "Error al asignar borde al panel " + e2.valor.toString(), f, c);
                                    }
                                }
                                break;
                            case "texto":
                                String text = (String) e2.valor;
                                switch (estilo2) {
                                    case 3:
                                        area.setText(text.toUpperCase());
                                        break;
                                    case 4:
                                        area.setText(text.toLowerCase());
                                        break;
                                    case 5:
                                        area.setText(Elementos.primeraMayuscula(text));
                                        break;
                                    default:
                                        area.setText(text);
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
                                                StyleConstants.setBold(attribs, true);
                                                StyleConstants.setFontSize(attribs, 16);
                                                area.setParagraphAttributes(attribs, true);
                                                break;
                                            case "cursiva":
                                                estilo = estilo + 2;
                                                StyleConstants.setItalic(attribs, true);
                                                area.setParagraphAttributes(attribs, true);
                                                break;
                                            case "mayuscula":
                                                estilo2 = 3;
                                                area.setText(area.getText().toUpperCase());
                                                break;
                                            case "minuscula":
                                                estilo2 = 4;
                                                area.setText(area.getText().toLowerCase());
                                                break;
                                            case "capital-t":
                                            case "capital":
                                                estilo2 = 5;
                                                area.setText(Elementos.primeraMayuscula(area.getText()));
                                                break;
                                        }
                                    }
                                }
                                break;
                            case "letra":
                                letra = (String) e2.valor;
                                StyleConstants.setFontFamily(attribs, letra);
                                area.setParagraphAttributes(attribs, true);
                                break;
                            case "tamtex":
                                if (e2.valor instanceof Double) {
                                    Double decimal = (Double) e2.valor;
                                    tamanio = decimal.intValue();
                                    StyleConstants.setFontSize(attribs, tamanio);
                                    area.setParagraphAttributes(attribs, true);
                                } else if (e2.valor instanceof Integer) {
                                    tamanio = Integer.parseInt(e2.valor.toString());
                                    StyleConstants.setFontSize(attribs, tamanio);
                                    area.setParagraphAttributes(attribs, true);
                                }
                                break;
                            case "visible":
                                area.setVisible(false);
                                if ("verdadero".equals((String) e2.valor)) {
                                    area.setVisible(true);
                                }
                                break;
                            case "colortext":
                                Color colorT = convertirColor(e2.valor.toString());
                                //area.setForeground(colorT);
                                StyleConstants.setForeground(attribs, colorT);
                                area.setParagraphAttributes(attribs, true);
                                break;
                            case "autoredimension":

                                break;
                            case "alineado":
                                switch (e2.valor.toString()) {
                                    case "izquierda":
                                        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_LEFT);
                                        area.setParagraphAttributes(attribs, true);
                                        break;
                                    case "derecha":
                                        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);
                                        area.setParagraphAttributes(attribs, true);
                                        break;
                                    case "centrado":
                                        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
                                        area.setParagraphAttributes(attribs, true);
                                        break;
                                    case "justificado":
                                        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_JUSTIFIED);
                                        area.setParagraphAttributes(attribs, true);
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
                    area.setBackground(color);
                } else if (e.nombre.equalsIgnoreCase("alto")) {
                    alto = Integer.parseInt((String) e.valor);
                } else if (e.nombre.equalsIgnoreCase("ancho")) {
                    ancho = Integer.parseInt((String) e.valor);
                } else if (e.nombre.equalsIgnoreCase("alineado")) {
                    switch (e.valor.toString()) {
                        case "izquierda":
                            StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_LEFT);
                            area.setParagraphAttributes(attribs, true);
                            break;
                        case "derecha":
                            StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);
                            area.setParagraphAttributes(attribs, true);
                            break;
                        case "centrado":
                            attribs = new SimpleAttributeSet();
                            StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
                            area.setParagraphAttributes(attribs, true);
                            break;
                        case "justificado":
                            attribs = new SimpleAttributeSet();
                            StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_JUSTIFIED);
                            area.setParagraphAttributes(attribs, true);
                            break;
                    }
                } else {
                    Datos.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en area de texto", f, c);
                }
            } catch (NumberFormatException e) {
                Datos.agregarError("Error Semantico", "Error al crear area de texto", f, c);
            }
        }
        //SETEAR VALORES 
        //FONT
        try {
            area.setFont(new Font(letra, estilo, tamanio));
            //area.setText(texto);
        } catch (Exception e) {
            Datos.agregarError("Error Semantico", "Error al agregar font al area de texto", f, c);
        }
        if (alto > 0 && ancho > 0) {
            area.setSize(ancho, alto);
            area.setPreferredSize(new Dimension(ancho, alto));
            area.setMaximumSize(area.getPreferredSize());
        }
        if (editable == true) {
            scroll.setViewportView(area);
            Componente resultado = new Componente("CajaArea", area.getName(), scroll, new ArrayList());
            return resultado;
        }
        Componente resultado = new Componente("CajaArea", area.getName(), area, new ArrayList());
        return resultado;
    }

}
