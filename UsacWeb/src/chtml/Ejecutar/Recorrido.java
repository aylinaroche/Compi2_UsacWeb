package chtml.Ejecutar;

import ccss.Ejecutar.Estilo;
import chtml.NodoCHTML;
import java.util.ArrayList;
import usacweb.Errores;
import usacweb.Metodo;
import usacweb.UsacWeb;

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
                            Elementos.dibujar(result);
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
                            String texto = Metodo.abrir(ruta);
                            UsacWeb.pilaArchivo.push(Metodo.obtenerNombre(ruta));
                            if (texto.equals("")) {
                                Errores.agregarError("Error Semantico", "El archivo no se ha encontrado", raiz.hijos[0].fila, raiz.hijos[0].col);
                                break;
                            }
                            if (raiz.hijos[0].texto.equalsIgnoreCase("ccss")) {
                                try {
                                    ccss.ccss.analizar(texto);
                                } catch (Exception ex) {
                                    Errores.agregarError("Error Semantico", "No se pudo analizar el archivo ccss", raiz.hijos[0].fila, raiz.hijos[0].col);
                                }
                            } else {
                                try {
                                    cjs.cjs.analizar(texto);
                                } catch (Exception ex) {
                                    Errores.agregarError("Error Semantico", "No se pudo analizar el archivo ccss", raiz.hijos[0].fila, raiz.hijos[0].col);
                                }
                            }
                            UsacWeb.pilaArchivo.pop();
                            break;
                    }
                    break;
                case "CUERPO":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = Elementos.crearPanel(new ArrayList(), null,raiz.hijos[0].fila,raiz.hijos[0].col);
                            break;
                        case 4:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {
                                result = Recorrido(raiz.hijos[1]);
                                result = Elementos.crearPanel((ArrayList) result, null,raiz.hijos[0].fila,raiz.hijos[0].col);
                            } else {
                                result = Recorrido(raiz.hijos[1]);
                                result = Elementos.crearPanel(new ArrayList(), result,raiz.hijos[0].fila,raiz.hijos[0].col);
                            }
                            break;
                        case 5:
                            ArrayList elem = (ArrayList) Recorrido(raiz.hijos[1]);
                            result = Recorrido(raiz.hijos[3]);
                            result = Elementos.crearPanel(elem, result,raiz.hijos[0].fila,raiz.hijos[0].col);
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
                            result = Recorrido(raiz.hijos[0]);
                            break;
                    }
                    break;
                case "PANEL":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = Elementos.crearPanel(new ArrayList(), null,raiz.hijos[0].fila,raiz.hijos[0].col);
                            break;
                        case 4:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {
                                result = Recorrido(raiz.hijos[1]);
                                result = Elementos.crearPanel((ArrayList) result, null,raiz.hijos[0].fila,raiz.hijos[0].col);
                            } else {
                                result = Recorrido(raiz.hijos[1]);
                                result = Elementos.crearPanel(new ArrayList(), result,raiz.hijos[0].fila,raiz.hijos[0].col);
                            }
                            break;
                        case 5:
                            ArrayList elem = (ArrayList) Recorrido(raiz.hijos[1]);
                            result = Recorrido(raiz.hijos[3]);
                            result = Elementos.crearPanel(elem, result,raiz.hijos[0].fila,raiz.hijos[0].col);
                            break;
                    }
                    break;
                case "TEXTO":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            //Crearlo vacio
                            break;
                        case 4:
                            break;
                    }
                    break;
                case "IMAGEN":
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
                            result = new Estilo(raiz.hijos[0].texto, raiz.hijos[2].texto);
                            break;
                    }
                    break;
                case "BOTON":
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
                            break;
                        case 3:
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
                case "SPINNER":
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
                //////
                case "ERROR":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            Errores.agregarError("Error Sintactico", raiz.hijos[0].texto, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                    }
                    break;

            }
        }
        return result;
    }

}
