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
import javax.swing.JPanel;
import javax.swing.border.Border;
import usacweb.Datos;

/**
 *
 * @author Aroche
 */
public class Panel {

    public static Componente crearPanel(ArrayList elementos, ArrayList componentes, int f, int c) {
        String alineado = "centrado";
        int alto = 500, ancho = 1000;
        JPanel panel = new JPanel();
        panel.setName("");
        panel.setLayout(new BorderLayout());

        for (int i = 0; i < elementos.size(); i++) {
            try {
                Estilo e = (Estilo) elementos.get(i);

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
                                if (alineado.equalsIgnoreCase("derecha")) {
                                    panel.setAlignmentX(RIGHT_ALIGNMENT);
                                }
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
                                Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en panel ", f, c);
                                break;
                        }

                    }
                } else if (e.nombre.equalsIgnoreCase("fondo")) {
                    Color color = convertirColor((String) e.valor);
                    panel.setBackground(color);
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
                    alineado = e.valor.toString();
                    if (alineado.equalsIgnoreCase("derecha")) {
                        panel.setAlignmentX(RIGHT_ALIGNMENT);
                    }
                } else {
                    Datos.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en panel", f, c);
                }

            } catch (NumberFormatException e) {
                Datos.agregarError("Error Semantico", "Error al crear el panel", f, c);
            }
        }

        if (alto > 0 && ancho > 0) {
            panel.setSize(ancho, alto);
            panel.setPreferredSize(new Dimension(ancho, alto));
            panel.setMaximumSize(panel.getPreferredSize());
        }
        //scroll.setViewportView(panel);
        Componente resultado = new Componente("Panel", panel.getName(), panel, componentes, alineado);
        return resultado;
    }

    public static Componente crearCuerpo(ArrayList elementos, ArrayList componentes, int f, int c, int fondo) {
        String alineado = "centrado";
        int alto = 0, ancho = 0;
        JPanel panel = new JPanel();
        //panel.setSize(100, 100);
        panel.setName("cuerpo");
        panel.setLayout(new BorderLayout());
        if(fondo== 1){
            panel.setBackground(Color.WHITE);
        }

        for (int i = 0; i < elementos.size(); i++) {
            try {
                Estilo e = (Estilo) elementos.get(i);

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
                                if (alineado.equalsIgnoreCase("derecha")) {
                                    panel.setAlignmentX(RIGHT_ALIGNMENT);
                                }
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
                    if (e.valor instanceof Double) {
                        Double decimal = (Double) e.valor;
                        alto = decimal.intValue();
                    } else if (e.valor instanceof Integer) {
                        alto = Integer.parseInt(e.valor.toString());
                    } else if (e.valor instanceof String) {
                        try {
                            ancho = Integer.parseInt((String) e.valor);
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
                    alineado = e.valor.toString();
                } else {
                    Datos.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en panel", f, c);
                }

            } catch (NumberFormatException e) {
                Datos.agregarError("Error Semantico", "Error al crear el panel", f, c);
            }
        }

        if (alto > 0 && ancho > 0) {
            panel.setSize(ancho, alto);
            panel.setPreferredSize(new Dimension(ancho, alto));
            panel.setMaximumSize(panel.getPreferredSize());
        }
        Componente resultado = new Componente("Panel", panel.getName(), panel, componentes, alineado);
        return resultado;
    }

    public static Componente modificarPanel(JPanel panel, String nombre, ArrayList componentes, String alineado, Object valor, int f, int c) {
        int alto = panel.getHeight();
        int ancho = panel.getWidth();
        panel.setLayout(new BorderLayout());

        if (nombre.equalsIgnoreCase("id") || nombre.equalsIgnoreCase("grupo")) {
            ArrayList listaCCSS = BloqueCCSS.obtenerBloque(nombre, (String) valor);
            if (nombre.equalsIgnoreCase("id")) {
                panel.setName(valor.toString());
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
                        if (alineado.equalsIgnoreCase("derecha")) {
                            panel.setAlignmentX(RIGHT_ALIGNMENT);
                        }
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
        } else if (nombre.equalsIgnoreCase("fondo") || nombre.equalsIgnoreCase("fondoelemento")) {
            Color color = convertirColor((String) valor);
            panel.setBackground(color);
        } else if (nombre.equalsIgnoreCase("alto")) {
            if (valor instanceof Double) {
                Double decimal = (Double) valor;
                alto = decimal.intValue();
            } else if (valor instanceof Integer) {
                alto = Integer.parseInt(valor.toString());
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
            alineado = valor.toString();
            if (alineado.equalsIgnoreCase("derecha")) {
                panel.setAlignmentX(RIGHT_ALIGNMENT);
            } else if (valor instanceof String) {
                try {
                    ancho = Integer.parseInt((String) valor);
                } catch (NumberFormatException ex) {
                    System.out.println(ex);
                }
            }
        } else {
            Datos.agregarError("Error Semantico", "Atributo " + nombre + " incorrecto en panel", f, c);
        }

        if (alto > 0 && ancho > 0) {
            panel.setSize(ancho, alto);
            panel.setPreferredSize(new Dimension(ancho, alto));
            panel.setMaximumSize(panel.getPreferredSize());
        }
        //scroll.setViewportView(panel);
        Componente resultado = new Componente("Panel", panel.getName(), panel, componentes, alineado);
        return resultado;
    }

}
