package chtml.Ejecutar;

import ccss.Ejecutar.BloqueCCSS;
import ccss.Ejecutar.Estilo;
import static chtml.Ejecutar.Elementos.convertirColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import usacweb.Datos;
import usacweb.Metodos;

/**
 *
 * @author Aroche
 */
public class Enlace {

    public static Componente crearEnlace(ArrayList elementos, String texto, int f, int c) {
        int alto = 50, ancho = 400;
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
                    alto = Integer.parseInt((String) e.valor);
                } else if (e.nombre.equalsIgnoreCase("ancho")) {
                    ancho = Integer.parseInt((String) e.valor);
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
                            //C:\Users\Aroche\Documents\NetBeansProjects\UsacWeb\src\Imagenes\not.jpg
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

        if (alto > 0 && ancho > 0) {
            enlace.setSize(ancho, alto);
            enlace.setPreferredSize(new Dimension(ancho, alto));
            enlace.setMaximumSize(enlace.getPreferredSize());
        }
        Componente resultado = new Componente("Enlace", enlace.getName(), enlace, new ArrayList());
        return resultado;
    }
}
