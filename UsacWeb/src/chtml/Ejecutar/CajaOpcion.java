package chtml.Ejecutar;

import ccss.Ejecutar.BloqueCCSS;
import ccss.Ejecutar.Estilo;
import static chtml.Ejecutar.Elementos.convertirColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import usacweb.Datos;

/**
 *
 * @author Aroche
 */
public class CajaOpcion {

    public static Componente crearCajaOpcion(ArrayList elementos, ArrayList opciones, int f, int c) {

        String letra = "Comic Sans MS";
        int estilo2 = 0;
        int tamanio = 12;
        int estilo = 0;
        int alto = 40, ancho = 200;
        JComboBox combo = new JComboBox();
        combo.setFont(new Font(letra, estilo, tamanio));
        combo.setName("");

        for (int i = 0; i < elementos.size(); i++) {
            try {
                Estilo e = (Estilo) elementos.get(i);
                if (e.nombre.equalsIgnoreCase("id") || e.nombre.equalsIgnoreCase("grupo")) {
                    ArrayList listaCCSS = BloqueCCSS.obtenerBloque(e.nombre, (String) e.valor);
                    if (e.nombre.equalsIgnoreCase("id")) {
                        combo.setName(e.valor.toString());
                    }
                    for (int j = 0; j < listaCCSS.size(); j++) {
                        Estilo e2 = (Estilo) listaCCSS.get(j);

                        switch (e2.nombre.toLowerCase()) {
                            case "fondo":
                                Color color = convertirColor(e2.valor.toString());
                                combo.setBackground(color);
                                break;
                            case "opaque":
                                if (e2.valor.toString().equalsIgnoreCase("true")) {
                                    combo.setOpaque(true);
                                } else {
                                    combo.setOpaque(false);
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
                                        combo.setBorder(border);
                                    } catch (NumberFormatException ex) {
                                        Datos.agregarError("Error Semantico", "Error al asignar borde al panel " + e2.valor.toString(), f, c);
                                    }
                                }
                                break;
                            case "texto":
                                String text = (String) e2.valor;
                                switch (estilo2) {
                                    case 3:
                                        combo.addItem(text.toUpperCase());
                                        break;
                                    case 4:
                                        combo.addItem(text.toLowerCase());
                                        break;
                                    case 5:
                                        combo.addItem(Elementos.primeraMayuscula(text));
                                        break;
                                    default:
                                        combo.addItem(text);
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
                                                //combo.setText(combo.getText().toUpperCase());
                                                break;
                                            case "minuscula":
                                                estilo2 = 4;
                                                // combo.setText(combo.getText().toLowerCase());
                                                break;
                                            case "capital-t":
                                                estilo2 = 5;
                                                // combo.setText(Elementos.primeraMayuscula(combo.getText()));
                                                break;
                                        }

                                    }
                                }
                                break;
                            case "letra":
                                letra = (String) e2.valor;
                                break;
                            case "tamtex":
                                tamanio = Integer.parseInt(e2.valor.toString());
                                break;
                            case "visible":
                                combo.setVisible(false);
                                if ("verdadero".equals((String) e2.valor)) {
                                    combo.setVisible(true);
                                }
                                break;
                            case "colortext":
                                Color colorT = convertirColor(e2.valor.toString());
                                combo.setForeground(colorT);
                                break;
                            case "autoredimension":

                                break;
                            case "alineado":
                                switch (e2.valor.toString()) {
                                    case "izquierda":
                                        combo.setAlignmentX(SwingConstants.LEFT);
                                        break;
                                    case "derecha":
                                        combo.setAlignmentX(SwingConstants.RIGHT);
                                        break;
                                    case "centrado":
                                        combo.setAlignmentX(SwingConstants.CENTER);
                                        break;
                                    case "justificado":
                                        combo.setAlignmentX(SwingConstants.LEADING);
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
                    combo.setBackground(color);
                } else if (e.nombre.equalsIgnoreCase("alto")) {
                    alto = Integer.parseInt((String) e.valor);
                } else if (e.nombre.equalsIgnoreCase("ancho")) {
                    ancho = Integer.parseInt((String) e.valor);
                } else if (e.nombre.equalsIgnoreCase("alineado")) {
                    switch (e.valor.toString()) {
                        case "izquierda":
                            combo.setAlignmentX(SwingConstants.LEFT);
                            break;
                        case "derecha":
                            combo.setAlignmentX(SwingConstants.RIGHT);
                            break;
                        case "centrado":
                            combo.setAlignmentX(SwingConstants.CENTER);
                            break;
                        case "justificado":
                            combo.setAlignmentX(SwingConstants.LEADING);
                            break;
                    }
                    break;
                } else if (e.nombre.equalsIgnoreCase("click")) {
                    combo.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            ActionPerformed(evt);
                        }

                        private void ActionPerformed(ActionEvent evt) {

                        }

                    });
                } else {
                    Datos.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en caja opcion", f, c);
                }

            } catch (NumberFormatException e) {
                Datos.agregarError("Error Semantico", "Error al crear caja opcion", f, c);
            }
        }
        //SETEAR VALORES 
        //FONT
        try {
            combo.setFont(new Font(letra, estilo, tamanio));
        } catch (Exception e) {
            Datos.agregarError("Error Semantico", "Error al agregar font a caja opcion", f, c);
        }
        if (alto > 0 && ancho > 0) {
            combo.setSize(ancho, alto);
            combo.setPreferredSize(new Dimension(ancho, alto));
            combo.setMaximumSize(combo.getPreferredSize());
        }

        for (int i = 0; i < opciones.size(); i++) {
            Componente comp = (Componente) opciones.get(i);
            if (comp.valor != null) {
                combo.addItem(comp.valor.toString());

            }
        }
        Componente resultado = new Componente("CajaOpcion", combo.getName(), combo, new ArrayList());
        return resultado;
    }

    public static Componente modificarCajaOpcion(JComboBox area, String nombre, Object valor, int f, int c) {

        String letra = area.getFont().getFontName();
        int tamanio = area.getFont().getSize();
        int estilo = area.getFont().getStyle();
        int alto = area.getHeight();
        int ancho = area.getWidth();
        int estilo2 = 0;
        JComboBox combo = new JComboBox();
        combo.setFont(new Font(letra, estilo, tamanio));

        if (nombre.equalsIgnoreCase("id") || nombre.equalsIgnoreCase("grupo")) {
            ArrayList listaCCSS = BloqueCCSS.obtenerBloque(nombre, (String) valor);
            if (nombre.equalsIgnoreCase("id")) {
                combo.setName(valor.toString());
            }
            for (int j = 0; j < listaCCSS.size(); j++) {
                Estilo e2 = (Estilo) listaCCSS.get(j);

                switch (e2.nombre.toLowerCase()) {
                    case "fondo":
                        Color color = convertirColor(e2.valor.toString());
                        combo.setBackground(color);
                        break;
                    case "opaque":
                        if (e2.valor.toString().equalsIgnoreCase("true")) {
                            combo.setOpaque(true);
                        } else {
                            combo.setOpaque(false);
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
                                combo.setBorder(border);
                            } catch (NumberFormatException ex) {
                                Datos.agregarError("Error Semantico", "Error al asignar borde al panel " + e2.valor.toString(), f, c);
                            }
                        }
                        break;
                    case "texto":
                        String text = (String) e2.valor;
                        switch (estilo2) {
                            case 3:
                                combo.addItem(text.toUpperCase());
                                break;
                            case 4:
                                combo.addItem(text.toLowerCase());
                                break;
                            case 5:
                                combo.addItem(Elementos.primeraMayuscula(text));
                                break;
                            default:
                                combo.addItem(text);
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
                                        //combo.setText(combo.getText().toUpperCase());
                                        break;
                                    case "minuscula":
                                        estilo2 = 4;
                                        // combo.setText(combo.getText().toLowerCase());
                                        break;
                                    case "capital-t":
                                        estilo2 = 5;
                                        // combo.setText(Elementos.primeraMayuscula(combo.getText()));
                                        break;
                                }

                            }
                        }
                        break;
                    case "letra":
                        letra = (String) e2.valor;
                        break;
                    case "tamtex":
                        tamanio = Integer.parseInt(e2.valor.toString());
                        break;
                    case "visible":
                        combo.setVisible(false);
                        if ("verdadero".equals((String) e2.valor)) {
                            combo.setVisible(true);
                        }
                        break;
                    case "colortext":
                        Color colorT = convertirColor(e2.valor.toString());
                        combo.setForeground(colorT);
                        break;
                    case "autoredimension":

                        break;
                    case "alineado":
                        switch (e2.valor.toString()) {
                            case "izquierda":
                                combo.setAlignmentX(SwingConstants.LEFT);
                                break;
                            case "derecha":
                                combo.setAlignmentX(SwingConstants.RIGHT);
                                break;
                            case "centrado":
                                combo.setAlignmentX(SwingConstants.CENTER);
                                break;
                            case "justificado":
                                combo.setAlignmentX(SwingConstants.LEADING);
                                break;
                        }
                        break;
                    default:
                        Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en panel", f, c);
                        break;
                }

            }
        } else if (nombre.equalsIgnoreCase("fondo")) {
            Color color = convertirColor((String) valor);
            combo.setBackground(color);
        } else if (nombre.equalsIgnoreCase("alto")) {
            alto = Integer.parseInt((String) valor);
        } else if (nombre.equalsIgnoreCase("ancho")) {
            ancho = Integer.parseInt((String) valor);
        } else if (nombre.equalsIgnoreCase("alineado")) {
            switch (valor.toString()) {
                case "izquierda":
                    combo.setAlignmentX(SwingConstants.LEFT);
                    break;
                case "derecha":
                    combo.setAlignmentX(SwingConstants.RIGHT);
                    break;
                case "centrado":
                    combo.setAlignmentX(SwingConstants.CENTER);
                    break;
                case "justificado":
                    combo.setAlignmentX(SwingConstants.LEADING);
                    break;
            }
        } else if (nombre.equalsIgnoreCase("click")) {
            combo.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ActionPerformed(evt);
                }

                private void ActionPerformed(ActionEvent evt) {

                }

            });
        } else {
            Datos.agregarError("Error Semantico", "Atributo " + nombre + " incorrecto en caja opcion", f, c);
        }

//SETEAR VALORES 
//FONT
        try {
            combo.setFont(new Font(letra, estilo, tamanio));
        } catch (Exception e) {
            Datos.agregarError("Error Semantico", "Error al agregar font a caja opcion", f, c);
        }
        if (alto > 0 && ancho > 0) {
            combo.setSize(ancho, alto);
            combo.setPreferredSize(new Dimension(ancho, alto));
            combo.setMaximumSize(combo.getPreferredSize());
        }

//        for (int i = 0; i < opciones.size(); i++) {
//            Componente comp = (Componente) opciones.get(i);
//            if (comp.valor != null) {
//                combo.addItem(comp.valor.toString());
//
//            }
//        }
        Componente resultado = new Componente("CajaOpcion", combo.getName(), combo, new ArrayList());
        return resultado;
    }

}
