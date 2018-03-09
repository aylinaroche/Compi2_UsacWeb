package chtml.Ejecutar;

import ccss.Ejecutar.BloqueCCSS;
import ccss.Ejecutar.Estilo;
import static chtml.Ejecutar.Elementos.convertirColor;
import cjs.Ejecutar.Documento;
import cjs.Ejecutar.FuncionCJS;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import usacweb.Datos;

/**
 *
 * @author Aroche
 */
public class Imagen {

    public static Componente crearImagen(ArrayList elementos, String texto, int f, int c) {
        int alto = 50, ancho = 100;
        //Iniciar boton
        JButton boton = new JButton();
        boton.setBackground(Color.WHITE);
        Boolean correcta = false;
        boton.setName("");

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
                                Color color = convertirColor(e2.valor.toString());
                                boton.setBackground(color);
                                break;
                            case "visible":
                                boton.setVisible(false);
                                if ("verdadero".equals((String) e2.valor)) {
                                    boton.setVisible(true);
                                }
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
                                Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en imagen", f, c);
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
                    if (e.valor.toString() != null) {
                        String sFichero = e.valor.toString();
                        File fichero = new File(sFichero);
                        if (fichero.exists()) {
                            try {
                                boton.setIcon(new ImageIcon(e.valor.toString()));
                                correcta = true;
                            } catch (Exception ex) {
                                boton.setIcon(new ImageIcon("C:\\Users\\Aroche\\Documents\\NetBeansProjects\\UsacWeb\\src\\Imagenes\\found.png"));
                                correcta = false;
                            }
                        } else {
                            boton.setIcon(new ImageIcon("C:\\Users\\Aroche\\Documents\\NetBeansProjects\\UsacWeb\\src\\Imagenes\\found.png"));
                            correcta = false;
                            Datos.agregarError("Error Semantico", "No existe la imagen descrita en la ruta", f, c);
                        }
                    }
                } else if (e.nombre.equalsIgnoreCase("click")) {
                    boton.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            ActionPerformed(evt);
                        }

                        private void ActionPerformed(ActionEvent evt) {
                            String nF = e.valor.toString().replace("(", "").replace(")", "");
                            FuncionCJS.buscarFuncion(nF, new ArrayList(), f, c);
                            listaClick.add(e.valor.toString());
                            Documento.verificarEvento(boton.getName(), "Cliqueado");
                        }
                    });
                } else {
                    Datos.agregarError("Error Semantico", "Atributo " + e.nombre + " incorrecto en imagen", f, c);
                }

            } catch (NumberFormatException e) {
                Datos.agregarError("Error Semantico", "Error al crear imagen", f, c);
            }
        }
        if (!"".equals(texto) && correcta == false) {
            File fichero = new File(texto);
            if (fichero.exists()) {
                try {
                    boton.setIcon(new ImageIcon(texto));
                } catch (Exception ex) {
                    boton.setIcon(new ImageIcon("C:\\Users\\Aroche\\Documents\\NetBeansProjects\\UsacWeb\\src\\Imagenes\\found.png"));
                }
            } else {
                Datos.agregarError("Error Semantico", "No existe la imagen", f, c);
            }
        }
        if (alto > 0 && ancho > 0) {
            boton.setSize(ancho, alto);
            boton.setPreferredSize(new Dimension(ancho, alto));
            boton.setMaximumSize(boton.getPreferredSize());
        }
        boton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }

            private void ActionPerformed(ActionEvent evt) {
                Documento.verificarEvento(boton.getName(), "Cliqueado");
            }

        });
        Componente resultado = new Componente("Imagen", boton.getName(), boton, new ArrayList(), listaClick);
        return resultado;
    }

    public static Componente modificarImagen(JButton boton, String nombre, Object valor, ArrayList listaClick, int f, int c) {
        int alto = boton.getHeight();
        int ancho = boton.getWidth();
        boton.setBackground(Color.WHITE);
        Boolean correcta = false;
        if (nombre.equalsIgnoreCase("id") || nombre.equalsIgnoreCase("grupo")) {
            ArrayList listaCCSS = BloqueCCSS.obtenerBloque(nombre, (String) valor);
            if (nombre.equalsIgnoreCase("id")) {
                boton.setName(valor.toString());
            }
            for (int j = 0; j < listaCCSS.size(); j++) {
                Estilo e2 = (Estilo) listaCCSS.get(j);

                switch (e2.nombre.toLowerCase()) {
                    case "fondo":
                        Color color = convertirColor(e2.valor.toString());
                        boton.setBackground(color);
                        break;
                    case "visible":
                        boton.setVisible(false);
                        if ("verdadero".equals((String) e2.valor)) {
                            boton.setVisible(true);
                        }
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
                        Datos.agregarError("Error Semantico", "Atributo " + e2.nombre + " incorrecto en imagen", f, c);
                        break;
                }

            }
        } else if (nombre.equalsIgnoreCase("fondo")) {
            Color color = convertirColor((String) valor);
            boton.setBackground(color);
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
        } else if (nombre.equalsIgnoreCase("ruta")) {
            if (valor.toString() != null) {
                String sFichero = valor.toString();
                File fichero = new File(sFichero);
                if (fichero.exists()) {
                    try {
                        boton.setIcon(new ImageIcon(valor.toString()));
                        correcta = true;
                    } catch (Exception ex) {
                        boton.setIcon(new ImageIcon("C:\\Users\\Aroche\\Documents\\NetBeansProjects\\UsacWeb\\src\\Imagenes\\found.png"));
                        correcta = false;
                    }
                } else {
                    boton.setIcon(new ImageIcon("C:\\Users\\Aroche\\Documents\\NetBeansProjects\\UsacWeb\\src\\Imagenes\\found.png"));
                    correcta = false;
                    Datos.agregarError("Error Semantico", "No existe la imagen descrita en la ruta", f, c);
                }
            }
        } else if (nombre.equalsIgnoreCase("click")) {
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
            Datos.agregarError("Error Semantico", "Atributo " + nombre + " incorrecto en imagen", f, c);
        }

        if (alto > 0 && ancho > 0) {
            boton.setSize(ancho, alto);
            boton.setPreferredSize(new Dimension(ancho, alto));
            boton.setMaximumSize(boton.getPreferredSize());
        }
        Componente resultado = new Componente("Imagen", boton.getName(), boton, new ArrayList(), listaClick);
        return resultado;
    }
}
