package ccss.Ejecutar;

import java.util.ArrayList;
import ccss.NodoCCSS;

public class Recorrido {

    public static String valorSwitch = "";
    public static Boolean retornar = false, salir = false;

    public Object Recorrido(NodoCCSS raiz) {
        Object result = null;
        // Nodo nodo = null;
        if (raiz != null) {
            switch (raiz.texto) {
                case "INICIO":
                    result = Recorrido(raiz.hijos[0]).toString();
                    break;
                case "ESTILOS":
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
                case "ESTILO":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            switch (raiz.hijos[0].texto) {
                                case "alineado":
                                    result = Recorrido(raiz.hijos[2]);
                                    break;
                                case "texto":
                                    break;
                                case "formato":
                                    result = Recorrido(raiz.hijos[0]);
                                    break;
                                case "letra":
                                    break;
                                case "tamtex":
                                    break;
                                case "fondo":
                                    break;
                                case "visible":
                                    break;
                                case "opaque":
                                    break;
                                case "colortext":
                                    break;
                            }
                            result = Recorrido(raiz.hijos[0]);
                            break;
                        case 8:
                            break;

                    }
                    break;
//                case "VARIABLE":
//                    ArrayList variables;
//                    switch (raiz.cantidadHijos) {
//                        case 2:
//                            variables = (ArrayList) Recorrido(raiz.hijos[1]);
//                            for (int i = 0; i < variables.size(); i++) {
//                                VariableCJS.crearVariable(variables.get(i).toString(), null, raiz.hijos[0].fila, raiz.hijos[0].col);
//                            }
//                            break;
//                        case 3:
//                            variables = (ArrayList) Recorrido(raiz.hijos[1]);
//                            Object valor = Recorrido(raiz.hijos[2]);
//                            for (int i = 0; i < variables.size(); i++) {
//                                VariableCJS.crearVariable(variables.get(i).toString(), valor, raiz.hijos[0].fila, raiz.hijos[0].col);
//                            }
//                            break;
//                    }
//                    break;
//
                case "ARREGLO":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            ArrayList coord = new ArrayList();
                            coord.add(Recorrido(raiz.hijos[1]));
                            result = coord;
                            break;
                        case 4:
                            ArrayList coord2 = (ArrayList) Recorrido(raiz.hijos[0]);
                            coord2.add(Recorrido(raiz.hijos[2]));
                            result = coord2;
                            break;
                    }
                    break;

                case "IMPRIMIR":
                    result = Recorrido(raiz.hijos[2]);
                    String imp = result.toString();
                    if (imp != null) {
                        //        paradigmas.Atributos.imprimirGraphik.add(imp);
                        System.out.println(imp);
                    }
                    break;
                case "OP":
                    result = Recorrido(raiz.hijos[0]);
                    break;
                case "E":
                    //result = Operacion.resolverOperacion(raiz);
                    break;
            }
        }
        return result;
    }

}
