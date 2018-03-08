package chtml.Ejecutar;

import ccss.Ejecutar.BloqueCCSS;
import ccss.Ejecutar.Estilo;
import static chtml.Ejecutar.Elementos.convertirColor;
import cjs.Ejecutar.Documento;
import cjs.Ejecutar.FuncionCJS;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import usacweb.Datos;
import usacweb.Metodos;

/**
 *
 * @author Aroche
 */
public class Boton {

    public static Componente crearBoton(ArrayList elementos, String texto, int f, int c) {

        String letra = "Comic Sans MS";
        int tamanio = 12;
        int estilo = 0;
        int estilo2 = 0;
        int alto = 40, ancho = 100;
        //Iniciar boton
        JButton boton = new JButton();
        boton.setText(texto);
        boton.setName("");
        boton.setFont(new Font(letra, estilo, tamanio));

        ArrayList listaClick = new ArrayList();

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
                            //case "fondoelemento":
                                Color color = convertirColor(e2.valor.toString());
                                boton.setBackground(color);
                                break;
                            case "opaque":
                                if (e2.valor.toString().equalsIgnoreCase("true")) {
                                    boton.setOpaque(true);
                                } else {
                                    boton.setOpaque(false);
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
                                        boton.setBorder(border);
                                    } catch (NumberFormatException ex) {
                                        Datos.agregarError("Error Semantico", "Error al asignar borde al boton " + e2.valor.toString(), f, c);
                                    }
                                }
                                break;
                            case "texto":
                                String text = (String) e2.valor;
                                switch (estilo2) {
                                    case 3:
                                        boton.setText(text.toUpperCase());
                                        break;
                                    case 4:
                                        boton.setText(text.toLowerCase());
                                        break;
                                    case 5:
                                        boton.setText(Elementos.primeraMayuscula(text));
                                        break;
                                    default:
                                        boton.setText(text);
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
                                                boton.setText(boton.getText().toUpperCase());
                                                break;
                                            case "minuscula":
                                                estilo2 = 4;
                                                boton.setText(boton.getText().toLowerCase());
                                                break;
                                            case "capital-t":
                                                estilo2 = 5;
                                                boton.setText(Elementos.primeraMayuscula(boton.getText()));
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
                                boton.setVisible(false);
                                if ("verdadero".equals((String) e2.valor)) {
                                    boton.setVisible(true);
                                }
                                break;
                            case "colortext":
                                Color colorT = convertirColor(e2.valor.toString());
                                boton.setForeground(colorT);
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
                                Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en el boton", f, c);
                                break;
                        }
                    }
                } else if (e.nombre.equalsIgnoreCase("fondo")) {
                    Color color = convertirColor((String) e.valor);
                    boton.setBackground(color);
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
                    boton.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            ActionPerformed(evt);
                        }

                        private void ActionPerformed(ActionEvent evt) {
                            Metodos.crearPestania(e.valor.toString());
                        }

                    });
                } else if (e.nombre.equalsIgnoreCase("click")) {
                    boton.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            ActionPerformed(evt);
                        }

                        private void ActionPerformed(ActionEvent evt) {
                            String r = e.valor.toString().replace("(", "").replace(")", "");
                            FuncionCJS.buscarFuncion(r, new ArrayList(), f, c);
                            //listaClick.add(r);
                            Documento.verificarEvento(boton.getName(), "Cliqueado");
                        }

                    });
                } else {
                    Datos.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en boton", f, c);
                }

            } catch (NumberFormatException e) {
                Datos.agregarError("Error Semantico", "Error al crear el boton", f, c);
            }
        }
        //SETEAR VALORES 
        //FONT
        try {
            boton.setFont(new Font(letra, estilo, tamanio));
        } catch (Exception e) {
            Datos.agregarError("Error Semantico", "Error al agregar font al boton", f, c);
        }
        if (alto > 0 && ancho > 0) {
            boton.setSize(ancho, alto);
            boton.setPreferredSize(new Dimension(ancho, alto));
            boton.setMaximumSize(boton.getPreferredSize());
        }
        Componente resultado = new Componente("Boton", boton.getName(), boton, new ArrayList(), listaClick);
        return resultado;
    }

    public static Componente modificarBoton(JButton boton, String atributo, Object valor, ArrayList listaClick, int f, int c) {
        String letra = boton.getFont().getFontName();
        int tamanio = boton.getFont().getSize();
        int estilo = boton.getFont().getStyle();
        int alto = boton.getHeight();
        int ancho = boton.getWidth();
        int estilo2 = 0;
        if (atributo.equalsIgnoreCase("id") || atributo.equalsIgnoreCase("grupo")) {
            ArrayList listaCCSS = BloqueCCSS.obtenerBloque(atributo, (String) valor);
            if (atributo.equalsIgnoreCase("id")) {
                boton.setName(valor.toString());
            }
            for (int j = 0; j < listaCCSS.size(); j++) {
                Estilo e2 = (Estilo) listaCCSS.get(j);

                switch (e2.nombre.toLowerCase()) {
                    case "fondo":
                        Color color = convertirColor(e2.valor.toString());
                        boton.setBackground(color);
                        break;
                    case "opaque":
                        if (e2.valor.toString().equalsIgnoreCase("true")) {
                            boton.setOpaque(true);
                        } else {
                            boton.setOpaque(false);
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
                                boton.setBorder(border);
                            } catch (NumberFormatException ex) {
                                Datos.agregarError("Error Semantico", "Error al asignar borde al boton " + e2.valor.toString(), f, c);
                            }
                        }
                        break;
                    case "texto":
                        String text = (String) e2.valor;
                        switch (estilo2) {
                            case 3:
                                boton.setText(text.toUpperCase());
                                break;
                            case 4:
                                boton.setText(text.toLowerCase());
                                break;
                            case 5:
                                boton.setText(Elementos.primeraMayuscula(text));
                                break;
                            default:
                                boton.setText(text);
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
                                        boton.setText(boton.getText().toUpperCase());
                                        break;
                                    case "minuscula":
                                        estilo2 = 4;
                                        boton.setText(boton.getText().toLowerCase());
                                        break;
                                    case "capital-t":
                                        estilo2 = 5;
                                        boton.setText(Elementos.primeraMayuscula(boton.getText()));
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
                                alto = Integer.parseInt((String) e2.valor);
                            } catch (NumberFormatException ex) {
                                System.out.println(ex);
                            }
                        }
                        break;
                    case "visible":
                        boton.setVisible(false);
                        if ("verdadero".equals((String) e2.valor)) {
                            boton.setVisible(true);
                        }
                        break;
                    case "colortext":
                        Color colorT = convertirColor(e2.valor.toString());
                        boton.setForeground(colorT);
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
                        Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en el boton", f, c);
                        break;
                }

            }
        } else if (atributo.equalsIgnoreCase("fondo")) {
            Color color = convertirColor((String) valor);
            boton.setBackground(color);
        } else if (atributo.equalsIgnoreCase("alto")) {
            if (valor instanceof Double) {
                Double decimal = (Double) valor;
                alto = decimal.intValue();
            } else if (valor instanceof Integer) {
                alto = Integer.parseInt(valor.toString());
            } else if (valor instanceof String) {
                try {
                    ancho = Integer.parseInt((String) valor);
                } catch (NumberFormatException ex) {
                    System.out.println(ex);
                }
            }
        } else if (atributo.equalsIgnoreCase("ancho")) {
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
        } else if (atributo.equalsIgnoreCase("alineado")) {
            switch (valor.toString()) {
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
        } else if (atributo.equalsIgnoreCase("ruta")) {
            boton.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ActionPerformed(evt);
                }

                private void ActionPerformed(ActionEvent evt) {
                    Metodos.crearPestania(valor.toString());
                }

            });
        } else if (atributo.equalsIgnoreCase("click")) {
            boton.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ActionPerformed(evt);
                }

                private void ActionPerformed(ActionEvent evt) {
                    String nF = valor.toString().replace("(", "").replace(")", "");
                    listaClick.add(nF);
//                    for (int i = 0; i < listaClick.size(); i++) {
//                        String nFuncion = (String) listaClick.get(i);
                        FuncionCJS.buscarFuncion(nF, new ArrayList(), f, c);
//                    }
                    Documento.verificarEvento(boton.getName(), "Cliqueado");

                }

            });
        } else {
            Datos.agregarError("Error Semantico", "Atributo " + atributo + " incorrecto en boton", f, c);
        }
//FONT
        try {
            boton.setFont(new Font(letra, estilo, tamanio));
        } catch (Exception e) {
            Datos.agregarError("Error Semantico", "Error al agregar font al boton", f, c);
        }
        if (alto > 0 && ancho > 0) {
            boton.setSize(ancho, alto);
            boton.setPreferredSize(new Dimension(ancho, alto));
            boton.setMaximumSize(boton.getPreferredSize());
        }
        Componente resultado = new Componente("Boton", boton.getName(), boton, new ArrayList(), listaClick);
        return resultado;
    }
}
