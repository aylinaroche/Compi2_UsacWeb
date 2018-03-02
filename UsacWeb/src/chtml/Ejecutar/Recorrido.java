package chtml.Ejecutar;

import ccss.Ejecutar.Estilo;
import chtml.NodoCHTML;
import static chtml.chtml.html;
import java.util.ArrayList;
import javax.swing.JPanel;
import usacweb.Datos;
import usacweb.Metodos;

public class Recorrido {

    public static String valorSwitch = "";
    public static Boolean retornar = false, salir = false;

    public Object Recorrido(NodoCHTML raiz) {
        Object result = null;
        // Nodo nodo = null;
        if (raiz != null) {
            switch (raiz.texto) {
                case "INICIO":
                    result = Recorrido(raiz.hijos[0]);
                    break;
                case "RAIZ":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "HTML":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            Recorrido(raiz.hijos[0]);
                            result = Recorrido(raiz.hijos[1]);
                            JPanel panel = (JPanel) Elementos.dibujar((Componente) result, 0);
                            result = panel;
                            break;
                    }
                    break;
                case "ENCABEZADO":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "INFORMACION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            result = Recorrido(raiz.hijos[0]);
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "INFO":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            Elementos.setTitulo(raiz.hijos[1].texto.replace(">", "").replace("<", ""));
                            break;
                        case 4:
                            String ruta = raiz.hijos[2].texto.replace("\"", "");
                            String texto = Metodos.abrir(ruta);
                            html.pilaArchivo.push(Metodos.obtenerNombre(ruta));
                            if (texto.equals("")) {
                                Datos.agregarError("Error Semantico", "El archivo no se ha encontrado", raiz.hijos[0].fila, raiz.hijos[0].col);
                                break;
                            }
                            if (raiz.hijos[0].texto.equalsIgnoreCase("<ccss")) {
                                try {
                                    ccss.ccss.analizar(texto);
                                } catch (Exception ex) {
                                    Datos.agregarError("Error Semantico", "No se pudo analizar el archivo ccss", raiz.hijos[0].fila, raiz.hijos[0].col);
                                }
                            } else {
                                try {
                                    cjs.cjs.analizar(texto);
                                } catch (Exception ex) {
                                    Datos.agregarError("Error Semantico", "No se pudo analizar el archivo cjs", raiz.hijos[0].fila, raiz.hijos[0].col);
                                }
                            }
                            html.pilaArchivo.pop();
                            break;
                    }
                    break;
                case "CUERPO":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = Panel.crearCuerpo(new ArrayList(), null, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                        case 4:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {
                                result = Recorrido(raiz.hijos[1]);
                                result = Panel.crearCuerpo((ArrayList) result, null, raiz.hijos[0].fila, raiz.hijos[0].col);
                            } else {
                                result = Recorrido(raiz.hijos[2]);
                                result = Panel.crearCuerpo(new ArrayList(), (ArrayList) result, raiz.hijos[0].fila, raiz.hijos[0].col);
                            }
                            break;
                        case 5:
                            ArrayList elem = (ArrayList) Recorrido(raiz.hijos[1]);
                            result = Recorrido(raiz.hijos[3]);
                            result = Panel.crearCuerpo(elem, (ArrayList) result, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                    }
                    break;
                case "ETIQUETAS":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            ArrayList l1 = new ArrayList();
                            result = Recorrido(raiz.hijos[0]);
                            l1.add(result);
                            result = l1;
                            break;
                        case 2:
                            ArrayList l2 = (ArrayList) Recorrido(raiz.hijos[0]);
                            l2.add(Recorrido(raiz.hijos[1]));
                            result = l2;
                            break;
                    }
                    break;
                case "ETIQUETA":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            if (raiz.hijos[0].texto.equals("saltofin")) {
                                Componente c = new Componente("salto", "salto", "salto", new ArrayList());
                                result = c;
                            } else {
                                result = Recorrido(raiz.hijos[0]);
                            }
                            break;
                    }
                    break;
                case "PANEL":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = Panel.crearPanel(new ArrayList(), new ArrayList(), raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                        case 4:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {
                                result = Recorrido(raiz.hijos[1]);
                                result = Panel.crearPanel((ArrayList) result, new ArrayList(), raiz.hijos[0].fila, raiz.hijos[0].col);
                            } else {
                                result = Recorrido(raiz.hijos[2]);
                                result = Panel.crearPanel(new ArrayList(), (ArrayList) result, raiz.hijos[0].fila, raiz.hijos[0].col);
                            }
                            break;
                        case 5:
                            ArrayList elem = (ArrayList) Recorrido(raiz.hijos[1]);
                            result = Recorrido(raiz.hijos[3]);
                            result = Panel.crearPanel(elem, (ArrayList) result, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                    }
                    break;
                case "TEXTO":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            result = CajaArea.crearTexto(new ArrayList(), "", false, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                        case 4:
                            ArrayList elem = (ArrayList) Recorrido(raiz.hijos[1]);
                            String texto = raiz.hijos[2].texto.replace(">", "").replace("<", "");
                            result = CajaArea.crearTexto(elem, texto, false, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                    }
                    break;
                case "IMAGEN":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = Imagen.crearImagen(new ArrayList(), "", raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                        case 3:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {
                                result = Recorrido(raiz.hijos[1]);
                                result = Imagen.crearImagen((ArrayList) result, "", raiz.hijos[0].fila, raiz.hijos[0].col);
                            } else {
                                String texto = raiz.hijos[1].texto.replace(">", "").replace("<", "");
                                result = Imagen.crearImagen(new ArrayList(), texto, raiz.hijos[0].fila, raiz.hijos[0].col);
                            }
                            break;
                        case 4:
                            ArrayList elem = (ArrayList) Recorrido(raiz.hijos[1]);
                            String texto = raiz.hijos[2].texto.replace(">", "").replace("<", "");
                            result = Imagen.crearImagen(elem, texto, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                    }
                    break;
                case "ELEMENTOS":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            ArrayList l1 = new ArrayList();
                            result = Recorrido(raiz.hijos[0]);
                            l1.add(result);
                            result = l1;
                            break;
                        case 2:
                            ArrayList l2 = (ArrayList) Recorrido(raiz.hijos[0]);
                            l2.add(Recorrido(raiz.hijos[1]));
                            result = l2;
                            break;
                    }
                    break;
                case "ELEMENTO":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            String valor = raiz.hijos[2].texto.replace("\"", "");
                            result = new Estilo(raiz.hijos[0].texto, valor);
                            break;
                    }
                    break;
                case "BOTON":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = Boton.crearBoton(new ArrayList(), "Boton", raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                        case 3:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {
                                result = Recorrido(raiz.hijos[1]);
                                result = Boton.crearBoton((ArrayList) result, "Boton", raiz.hijos[0].fila, raiz.hijos[0].col);
                            } else {
                                String texto = raiz.hijos[1].texto.replace(">", "").replace("<", "");
                                result = Boton.crearBoton(new ArrayList(), texto, raiz.hijos[0].fila, raiz.hijos[0].col);
                            }
                            break;
                        case 4:
                            ArrayList elem = (ArrayList) Recorrido(raiz.hijos[1]);
                            String texto = raiz.hijos[2].texto.replace(">", "").replace("<", "");
                            result = Boton.crearBoton(elem, texto, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                    }
                    break;

                case "ENLACE":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            //Crearlo vacio
                            break;
                        case 3:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {

                            } else {

                            }
                            break;
                        case 4:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "TABLA":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            //Crearlo vacio
                            break;
                        case 4:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {

                            } else {

                            }
                            break;
                        case 5:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "LISTAFILA":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            Recorrido(raiz.hijos[0]);
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "FILA":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "CELDAS":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            Recorrido(raiz.hijos[0]);
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "CELDA":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            if (raiz.hijos[0].texto.equals("cb")) {

                            } else {

                            }
                            break;
                    }
                    break;
                case "CONTENIDO":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "TEXTOA":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = CajaArea.crearTexto(new ArrayList(), "", false, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                        case 3:
                            ArrayList elem = (ArrayList) Recorrido(raiz.hijos[1]);
                            String texto = raiz.hijos[2].texto.replace(">", "").replace("<", "");
                            result = CajaArea.crearTexto(elem, texto, true, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                    }
                    break;
                case "CAJATEXTO":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            //Crearlo vacio
                            break;
                        case 3:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {

                            } else {

                            }
                            break;
                        case 4:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "CAJA":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            //Crearlo vacio
                            break;
                        case 4:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {

                            } else {

                            }
                            break;
                        case 5:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "OPCION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            Recorrido(raiz.hijos[0]);
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "OPCIONES":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = CajaOpcion.crearCajaOpcion(new ArrayList(), new ArrayList(), raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                        case 4:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {
                                result = Recorrido(raiz.hijos[1]);
                                result = CajaOpcion.crearCajaOpcion((ArrayList) result, new ArrayList(), raiz.hijos[0].fila, raiz.hijos[0].col);
                            } else {
                                result = Recorrido(raiz.hijos[2]);
                                result = CajaOpcion.crearCajaOpcion(new ArrayList(), (ArrayList) result, raiz.hijos[0].fila, raiz.hijos[0].col);
                            }
                            break;
                        case 5:
                            ArrayList elem = (ArrayList) Recorrido(raiz.hijos[1]);
                            result = Recorrido(raiz.hijos[3]);
                            result = CajaOpcion.crearCajaOpcion(elem, (ArrayList) result, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                    }
                    break;
                case "SPINNER":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = Contador.crearSpinner(new ArrayList(), "0", raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                        case 4:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {
                                result = Recorrido(raiz.hijos[1]);
                                result = Contador.crearSpinner((ArrayList) result, "0", raiz.hijos[0].fila, raiz.hijos[0].col);
                            } else {
                                String texto = raiz.hijos[2].texto.replace(">", "").replace("<", "");
                                result = Contador.crearSpinner(new ArrayList(), texto, raiz.hijos[0].fila, raiz.hijos[0].col);
                            }
                            break;
                        case 5:
                            ArrayList elem = (ArrayList) Recorrido(raiz.hijos[1]);
                            String texto = raiz.hijos[3].texto.replace(">", "").replace("<", "");
                            result = Contador.crearSpinner(elem, texto, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                    }
                    break;
                case "ERROR":
                    Datos.agregarError("Error Sintactico", raiz.hijos[0].texto, raiz.hijos[0].fila, raiz.hijos[0].col);

            }
        }
        return result;
    }

}
