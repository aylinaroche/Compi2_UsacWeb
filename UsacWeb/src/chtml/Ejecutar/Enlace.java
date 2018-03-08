package chtml.Ejecutar;

import ccss.Ejecutar.BloqueCCSS;
import ccss.Ejecutar.Estilo;
import static chtml.Ejecutar.Elementos.convertirColor;
import cjs.Ejecutar.Documento;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import usacweb.Datos;
import usacweb.Metodos;

/**
 *
 * @author Aroche
 */
public class Enlace {

    public static Componente crearEnlace(ArrayList elementos, String texto, int f, int c) {
        int alto = 50, ancho = 400;
        String letra = "Comic Sans MS";
        int tamanio = 12;
        int estilo = 0;
        int estilo2 = 0;
        JLabel enlace = new JLabel();
        enlace.setBackground(Color.WHITE);
        enlace.setForeground(Color.BLUE);
        enlace.setText(texto);
        enlace.setName("");
        for (int i = 0; i < elementos.size(); i++) {
            try {
                Estilo e = (Estilo) elementos.get(i);
                if (e.nombre.equalsIgnoreCase("id") || e.nombre.equalsIgnoreCase("grupo")) {
                    ArrayList listaCCSS = BloqueCCSS.obtenerBloque(e.nombre, (String) e.valor);
                    if (e.nombre.equalsIgnoreCase("id")) {
                        enlace.setName(e.valor.toString());
                    }
                    for (int j = 0; j < listaCCSS.size(); j++) {
                        Estilo e2 = (Estilo) listaCCSS.get(j);

                        switch (e2.nombre.toLowerCase()) {
                            case "fondo":
                                Color color = convertirColor(e2.valor.toString());
                                enlace.setBackground(color);
                                break;
                            case "opaque":
                                if (e2.valor.toString().equalsIgnoreCase("true")) {
                                    enlace.setOpaque(true);
                                } else {
                                    enlace.setOpaque(false);
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
                                        enlace.setBorder(border);
                                    } catch (NumberFormatException ex) {
                                        Datos.agregarError("Error Semantico", "Error al asignar borde al enlace " + e2.valor.toString(), f, c);
                                    }
                                }
                                break;
                            case "texto":
                                String text = (String) e2.valor;
                                switch (estilo2) {
                                    case 3:
                                        enlace.setText(text.toUpperCase());
                                        break;
                                    case 4:
                                        enlace.setText(text.toLowerCase());
                                        break;
                                    case 5:
                                        enlace.setText(Elementos.primeraMayuscula(text));
                                        break;
                                    default:
                                        enlace.setText(text);
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
                                                enlace.setText(enlace.getText().toUpperCase());
                                                break;
                                            case "minuscula":
                                                estilo2 = 4;
                                                enlace.setText(enlace.getText().toLowerCase());
                                                break;
                                            case "capital-t":
                                                estilo2 = 5;
                                                enlace.setText(Elementos.primeraMayuscula(enlace.getText()));
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
                                enlace.setVisible(false);
                                if ("verdadero".equals((String) e2.valor)) {
                                    enlace.setVisible(true);
                                }
                                break;
                            case "autoredimension":
                                break;
                            case "alineado":
                                switch (e2.valor.toString()) {
                                    case "izquierda":
                                        enlace.setHorizontalAlignment(SwingConstants.LEFT);
                                        break;
                                    case "derecha":
                                        enlace.setHorizontalAlignment(SwingConstants.RIGHT);
                                        break;
                                    case "centrado":
                                        enlace.setHorizontalAlignment(SwingConstants.CENTER);
                                        break;
                                    case "justificado":
                                        enlace.setHorizontalAlignment(SwingConstants.LEADING);
                                        break;
                                }
                                break;
                            default:
                                Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en enlace", f, c);
                                break;
                        }
                    }
                } else if (e.nombre.equalsIgnoreCase("fondo")) {
                    Color color = convertirColor((String) e.valor);
                    enlace.setBackground(color);
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
                            enlace.setHorizontalAlignment(SwingConstants.LEFT);
                            break;
                        case "derecha":
                            enlace.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                        case "centrado":
                            enlace.setHorizontalAlignment(SwingConstants.CENTER);
                            break;
                        case "justificado":
                            enlace.setHorizontalAlignment(SwingConstants.LEADING);
                            break;
                    }
                } else if (e.nombre.equalsIgnoreCase("ruta")) {
                    if (e.valor.toString() != null) {
                        try {
                            enlace.addMouseListener(new java.awt.event.MouseAdapter() {
                                @Override
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    MouseClicked(evt);
                                }

                                private void MouseClicked(MouseEvent evt) {
                                    Metodos.crearPestania(e.valor.toString());
                                }
                            });
                        } catch (Exception ex) {

                        }
                    }
                } else {
                    Datos.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en enlace", f, c);
                }

            } catch (NumberFormatException e) {
                Datos.agregarError("Error Semantico", "Error al crear el enlace", f, c);
            }
        }
        //SETEAR VALORES 
        //FONT
        Font font = enlace.getFont();
        Map atr = font.getAttributes();
        atr.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        enlace.setFont(font.deriveFont(atr));
        try {
            enlace.setFont(new Font(letra, estilo, tamanio));
        } catch (Exception e) {
            Datos.agregarError("Error Semantico", "Error al agregar font al enlace", f, c);
        }
        if (alto > 0 && ancho > 0) {
            enlace.setSize(ancho, alto);
            enlace.setPreferredSize(new Dimension(ancho, alto));
            enlace.setMaximumSize(enlace.getPreferredSize());
        }
        Componente resultado = new Componente("Enlace", enlace.getName(), enlace, new ArrayList());
        return resultado;
    }

    public static Componente modificarEnlace(JLabel enlace, String nombre, Object valor, int f, int c) {
        String letra = enlace.getFont().getFontName();
        int tamanio = enlace.getFont().getSize();
        int estilo = enlace.getFont().getStyle();
        int alto = enlace.getHeight();
        int ancho = enlace.getWidth();
        int estilo2 = 0;
        enlace.setBackground(Color.WHITE);
        enlace.setForeground(Color.BLUE);
        if (nombre.equalsIgnoreCase("id") || nombre.equalsIgnoreCase("grupo")) {
            ArrayList listaCCSS = BloqueCCSS.obtenerBloque(nombre, (String) valor);
            if (nombre.equalsIgnoreCase("id")) {
                enlace.setName(valor.toString());
            }
            for (int j = 0; j < listaCCSS.size(); j++) {
                Estilo e2 = (Estilo) listaCCSS.get(j);

                switch (e2.nombre.toLowerCase()) {
                    case "fondo":
                        Color color = convertirColor(e2.valor.toString());
                        enlace.setBackground(color);
                        break;
                    case "opaque":
                        if (e2.valor.toString().equalsIgnoreCase("true")) {
                            enlace.setOpaque(true);
                        } else {
                            enlace.setOpaque(false);
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
                                enlace.setBorder(border);
                            } catch (NumberFormatException ex) {
                                Datos.agregarError("Error Semantico", "Error al asignar borde al enlace " + e2.valor.toString(), f, c);
                            }
                        }
                        break;
                    case "texto":
                        String text = (String) e2.valor;
                        switch (estilo2) {
                            case 3:
                                enlace.setText(text.toUpperCase());
                                break;
                            case 4:
                                enlace.setText(text.toLowerCase());
                                break;
                            case 5:
                                enlace.setText(Elementos.primeraMayuscula(text));
                                break;
                            default:
                                enlace.setText(text);
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
                                        enlace.setText(enlace.getText().toUpperCase());
                                        break;
                                    case "minuscula":
                                        estilo2 = 4;
                                        enlace.setText(enlace.getText().toLowerCase());
                                        break;
                                    case "capital-t":
                                        estilo2 = 5;
                                        enlace.setText(Elementos.primeraMayuscula(enlace.getText()));
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
                        enlace.setVisible(false);
                        if ("verdadero".equals((String) e2.valor)) {
                            enlace.setVisible(true);
                        }
                        break;
                    case "autoredimension":
                        break;
                    case "alineado":
                        switch (e2.valor.toString()) {
                            case "izquierda":
                                enlace.setHorizontalAlignment(SwingConstants.LEFT);
                                break;
                            case "derecha":
                                enlace.setHorizontalAlignment(SwingConstants.RIGHT);
                                break;
                            case "centrado":
                                enlace.setHorizontalAlignment(SwingConstants.CENTER);
                                break;
                            case "justificado":
                                enlace.setHorizontalAlignment(SwingConstants.LEADING);
                                break;
                        }
                        break;
                    default:
                        Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en enlace", f, c);
                        break;
                }
            }
        } else if (nombre.equalsIgnoreCase("fondo")) {
            Color color = convertirColor((String) valor);
            enlace.setBackground(color);
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
                    enlace.setHorizontalAlignment(SwingConstants.LEFT);
                    break;
                case "derecha":
                    enlace.setHorizontalAlignment(SwingConstants.RIGHT);
                    break;
                case "centrado":
                    enlace.setHorizontalAlignment(SwingConstants.CENTER);
                    break;
                case "justificado":
                    enlace.setHorizontalAlignment(SwingConstants.LEADING);
                    break;
            }
        } else if (nombre.equalsIgnoreCase("ruta")) {
            if (valor.toString() != null) {
                try {
                    enlace.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            MouseClicked(evt);
                        }

                        private void MouseClicked(MouseEvent evt) {
                            Metodos.crearPestania(valor.toString());
                            Documento.verificarEvento(enlace.getName(),"Cliqueado");
                        }
                    });
                } catch (Exception ex) {

                }
            }
        } else {
            Datos.agregarError("Error Semantico", "Atributo " + nombre + " incorrecto en enlace", f, c);
        }
//SETEAR VALORES 
//FONT
        Font font = enlace.getFont();
        Map atr = font.getAttributes();
        atr.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        enlace.setFont(font.deriveFont(atr));
        try {
            enlace.setFont(new Font(letra, estilo, tamanio));
        } catch (Exception e) {
            Datos.agregarError("Error Semantico", "Error al agregar font al enlace", f, c);
        }
        if (alto > 0 && ancho > 0) {
            enlace.setSize(ancho, alto);
            enlace.setPreferredSize(new Dimension(ancho, alto));
            enlace.setMaximumSize(enlace.getPreferredSize());
        }
        Componente resultado = new Componente("Enlace", enlace.getName(), enlace, new ArrayList());
        return resultado;
    }
}
