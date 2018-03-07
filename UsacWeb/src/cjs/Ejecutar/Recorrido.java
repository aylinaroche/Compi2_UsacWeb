package cjs.Ejecutar;

import static chtml.chtml.html;
import java.util.ArrayList;
import cjs.NodoCJS;
import usacweb.Datos;
import usacweb.Mensaje;

public class Recorrido {

    public static String valorSwitch = "";
    public static Boolean retornar = false, salir = false;

    public Object Recorrido(NodoCJS raiz) {
        Object result = null;
        // Nodo nodo = null;
        if (raiz != null) {
            switch (raiz.texto) {
                case "INICIO":
                    result = Recorrido(raiz.hijos[0]).toString();
                    break;
                case "INSTRUCCION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            result = Recorrido(raiz.hijos[0]);
                            if (salir == false && retornar == false) {
                                result = Recorrido(raiz.hijos[1]);
                            }
                            break;
                    }
                    break;
                case "OPCION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            if (salir == false && retornar == false) {
                                result = Recorrido(raiz.hijos[0]);
                            }
                            break;
                        case 2:
                            switch (raiz.hijos[0].texto) {
                                case "detener":
                                    salir = true;
                                    break;
                                case "retornar":
                                    retornar = true;
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 3:
                            retornar = true;
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "VARIABLE":
                    ArrayList variables;
                    switch (raiz.cantidadHijos) {
                        case 2:
                            variables = (ArrayList) Recorrido(raiz.hijos[1]);
                            for (int i = 0; i < variables.size(); i++) {
                                VariableCJS.crearVariable(variables.get(i).toString(), null, raiz.hijos[0].fila, raiz.hijos[0].col);
                            }
                            break;
                        case 3:
                            variables = (ArrayList) Recorrido(raiz.hijos[1]);
                            Object valor = Recorrido(raiz.hijos[2]);
                            for (int i = 0; i < variables.size(); i++) {
                                VariableCJS.crearVariable(variables.get(i).toString(), valor, raiz.hijos[0].fila, raiz.hijos[0].col);
                            }
                            break;
                    }
                    break;

                case "MasVARIABLE":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            ArrayList l1 = new ArrayList();
                            l1.add(raiz.hijos[0].texto);
                            result = l1;
                            break;
                        case 3:
                            ArrayList l2 = (ArrayList) Recorrido(raiz.hijos[1]);
                            l2.add(raiz.hijos[2].texto);
                            result = l2;
                            break;
                    }
                    break;
                case "ASIGNACION":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            String id = raiz.hijos[0].texto;
                            result = Recorrido(raiz.hijos[1]);
                            VariableCJS.asignarVariable(id, result, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                        case 4:
                            String id2 = raiz.hijos[0].texto;
                            Object tam = Recorrido(raiz.hijos[1]);
                            result = Recorrido(raiz.hijos[2]);
                            VariableCJS.asignarEnPosicion(id2, tam, result, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                    }
                    break;
                case "VECTOR":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            String id = raiz.hijos[1].texto;
                            result = Recorrido(raiz.hijos[2]);
                            VariableCJS.crearVector(id, result, new ArrayList(), raiz.hijos[1].fila, raiz.hijos[1].col);
                            break;
                        case 5:
                            String id2 = raiz.hijos[1].texto;
                            Object tam = Recorrido(raiz.hijos[2]);
                            result = Recorrido(raiz.hijos[3]);
                            VariableCJS.crearVector(id2, tam, result, raiz.hijos[1].fila, raiz.hijos[1].col);
                            break;
                    }
                    break;
                case "ASIGNAR":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case 4:
                            result = Recorrido(raiz.hijos[2]);
                            break;
                    }
                    break;
                case "IF":
                    String condicion;
                    html.pilaAmbito.push("if");
                    html.nivelAmbito += 1;
                    switch (raiz.cantidadHijos) {
                        case 7:
                            condicion = Recorrido(raiz.hijos[2]).toString();
                            if (condicion.equals("true")) { //Si es true, hacer istruccion
                                Recorrido(raiz.hijos[5]);
                            }
                            break;
                        case 8:
                            condicion = Recorrido(raiz.hijos[2]).toString();
                            if (condicion.equals("true")) { //Si es true, hacer istruccion
                                Recorrido(raiz.hijos[5]);
                            } else {
                                Recorrido(raiz.hijos[7]);
                            }
                            break;
                    }
                    VariableCJS.eliminarVariables();
                    html.pilaAmbito.pop();
                    html.nivelAmbito -= 1;

                    break;
                case "ELSE":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            Recorrido(raiz.hijos[2]);
                            break;
                    }
                    break;
                case "CICLO":
                    html.pilaAmbito.push("Mientras");
                    html.nivelAmbito += 1;
                    switch (raiz.cantidadHijos) {
                        case 7:
                            boolean w;
                            result = Recorrido(raiz.hijos[2]);
                            w = result.toString().equals("true");
                            while (w) {
                                Recorrido(raiz.hijos[5]);
                                result = Recorrido(raiz.hijos[2]);
                                w = result.toString().equals("true");
                                if (w == false) {
                                    break;
                                }
                                if (salir == true || retornar == true) {
                                    w = false;
                                }
                            }
                            break;
                    }
                    salir = false;
                    html.pilaAmbito.pop();
                    VariableCJS.eliminarVariables();
                    html.nivelAmbito -= 1;

                    break;
                case "SWITCH":
                    html.pilaAmbito.push("switch");
                    html.nivelAmbito += 1;
                    switch (raiz.cantidadHijos) {
                        case 7:
                            valorSwitch = Recorrido(raiz.hijos[2]).toString();
                            Recorrido(raiz.hijos[5]);
                            break;
                        case 8:
                            valorSwitch = Recorrido(raiz.hijos[2]).toString();
                            result = Recorrido(raiz.hijos[5]).toString();
                            if (result.equals("false")) { //Si es true, hacer istruccion
                                Recorrido(raiz.hijos[6]);
                            }
                            break;
                    }
                    html.pilaAmbito.pop();
                    VariableCJS.eliminarVariables();
                    html.nivelAmbito -= 1;

                    break;
                case "CASO":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            String val = Recorrido(raiz.hijos[1]).toString();
                            result = "false";
                            if (val.equals(valorSwitch)) {
                                Recorrido(raiz.hijos[3]);
                                result = "true";
                            }

                            break;
                        case 5:
                            result = Recorrido(raiz.hijos[0]).toString();
                            if (result.equals("false")) {
                                val = Recorrido(raiz.hijos[2]).toString();
                                if (val.equals(valorSwitch)) {
                                    Recorrido(raiz.hijos[4]);
                                    result = "true";
                                } else {
                                    try {
                                        double a = Double.parseDouble(val);
                                        double b = Double.parseDouble(valorSwitch);
                                        if (a == b) {
                                            Recorrido(raiz.hijos[4]);
                                            result = "true";
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Error ss = " + e);
                                    }
                                }
                            }
                            break;
                    }
                case "DEFECTO":
                    Recorrido(raiz.hijos[2]);
                    break;
                case "PARA":
                    html.pilaAmbito.push("For");
                    Boolean f;
                    switch (raiz.cantidadHijos) {
                        case 10:
                            Recorrido(raiz.hijos[2]);
                            html.nivelAmbito += 1;
                            condicion = Recorrido(raiz.hijos[3]).toString();
                            f = "true".equals(condicion);
                            int limite = 0;
                            String var = "";
                            while (f) {
                                salir = false;
                                if (limite == 0) {
                                    var = html.listaVariables.get(html.listaVariables.size() - 1).nombre;
                                }
                                result = Recorrido(raiz.hijos[8]);//accion
                                int i = (int) Recorrido(raiz.hijos[5]); //i++
                                VariableCJS.incrementar(var, i);
                                condicion = Recorrido(raiz.hijos[3]).toString();
                                f = "true".equals(condicion);
                                limite += 1;
                                if (limite == 1000) {
                                    System.out.println("Limite!");
                                    break;
                                }
                                if (salir == true || retornar == true) {
                                    f = false;
                                }
                                VariableCJS.eliminarVariables();
                            }
                            salir = false;
                            VariableCJS.eliminarVariables();
                            html.pilaAmbito.pop();
                            html.nivelAmbito -= 1;
                            break;
                        case 12:
                            html.nivelAmbito += 1;
                            int limite2 = 0;
                            String var2 = raiz.hijos[2].texto;
                            Object valor = Recorrido(raiz.hijos[3]);
                            VariableCJS.asignarVariable(var2, valor, raiz.hijos[2].fila, raiz.hijos[2].col);
                            condicion = Recorrido(raiz.hijos[5]).toString();
                            f = "true".equals(condicion);
                            while (f) {
                                salir = false;
                                result = Recorrido(raiz.hijos[10]);//accion
                                int i = (int) Recorrido(raiz.hijos[7]); //i++
                                VariableCJS.incrementar(var2, i);
                                condicion = Recorrido(raiz.hijos[5]).toString();
                                f = "true".equals(condicion);
                                limite2 += 1;
                                if (limite2 == 1000) {
                                    System.out.println("Limite!");
                                    break;
                                }
                                if (salir == true || retornar == true) {
                                    f = false;
                                }
                                VariableCJS.eliminarVariables();
                            }
                            salir = false;
                            VariableCJS.eliminarVariables();
                            html.pilaAmbito.pop();
                            html.nivelAmbito -= 1;

                            break;
                    }
                    break;
                case "OPERADOR":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            int valor;
                            if (raiz.hijos[0].texto.equals("++")) {
                                valor = 1;
                            } else {
                                valor = -1;
                            }
                            result = valor;
                            break;
                    }
                    break;
                case "LLAMADAOPCION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            result = Recorrido(raiz.hijos[0]);
                            Object opcion = Recorrido(raiz.hijos[1]);
                            if (result instanceof ArrayList) {
                                ArrayList vector = (ArrayList) result;

                                if (opcion instanceof String) {
                                    String op = (String) opcion;
                                    if (op.equalsIgnoreCase("conteo")) {
                                        result = vector.size();
                                    } else {
                                        String cadena = "{";
                                        for (int j = 0; j < vector.size() - 1; j++) {
                                            cadena += vector.get(j) + ",";
                                        }
                                        cadena += vector.get(vector.size() - 1) + "}";
                                        result = cadena;
                                    }
                                } else {
                                    if (opcion instanceof Integer) {
                                        int pos = (Integer) opcion;
                                        result = vector.get(pos);
                                    }
                                }
                            }
                            break;
                    }
                    retornar = false;
                    salir = false;
                    break;
                case "LLAMADA":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            result = FuncionCJS.buscarFuncion(raiz.hijos[0].texto, new ArrayList(), raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                        case 4:
                            result = Recorrido(raiz.hijos[2]);
                            ArrayList parametro = (ArrayList) result;
                            result = FuncionCJS.buscarFuncion(raiz.hijos[0].texto, parametro, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                    }
                    retornar = false;
                    salir = false;
                    break;
                case "ACCESO":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            if ("conteo".equalsIgnoreCase(raiz.hijos[2].texto)) {
                                result = VariableCJS.obtenerTamanio(raiz.hijos[0].texto, raiz.hijos[0].fila, raiz.hijos[0].col);
                            } else {
                                result = VariableCJS.obtenerCadena(raiz.hijos[0].texto, raiz.hijos[0].fila, raiz.hijos[0].col);
                            }
                            break;
                        case 4:
                            result = Recorrido(raiz.hijos[2]);
                            result = VariableCJS.obtenerVector(raiz.hijos[0].texto, result, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                    }
                    break;
                case "ACCESO2":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = raiz.hijos[1].texto;
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "VALORES":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            ArrayList valor = new ArrayList();
                            valor.add(result);
                            result = valor;
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[0]);
                            ArrayList valor2 = (ArrayList) result;
                            result = Recorrido(raiz.hijos[2]);
                            valor2.add(result);
                            result = valor2;
                            break;
                    }
                    break;
                case "MENSAJE":
                    String msj = Recorrido(raiz.hijos[2]).toString();
                    if (msj != null) {
                        System.out.println(msj);
                        usacweb.UsacWeb.interfaz.setEnabled(false);
                        Mensaje m = new Mensaje(msj);
                        m.show();
                    }
                    break;

                case "IMPRIMIR":
                    result = Recorrido(raiz.hijos[2]);
                    if (result != null) {
                        String imp = result.toString();
                        Datos.agregarSalida(imp, raiz.hijos[0].fila, raiz.hijos[0].col);
                    }
                    break;
                case "DOCUMENTO":
                    switch (raiz.cantidadHijos) {
                        case 2: //Obtener
                            result = Recorrido(raiz.hijos[1]);
                            result = Documento.obtener(result.toString());
                            break;
                        case 5: //observador
                            Documento.crearEvento("documento", raiz.hijos[1].texto.replace("\"", ""), raiz.hijos[3]);
                            break;
                        case 6://id
                            Documento.crearEvento(raiz.hijos[0].texto, raiz.hijos[2].texto.replace("\"", ""), raiz.hijos[4]);
                            break;
                    }
                    break;
                case "SET":
                    switch (raiz.cantidadHijos) {
                        case 7: //set 
                            for (int i = 0; i < html.listaElementos.size(); i++) {
                                Elemento e = html.listaElementos.get(i);
                                //System.out.println("" + e.nombre + " - - - " + raiz.hijos[0].texto);
                                if (e.nombre.equals(raiz.hijos[0].texto)) {
                                    result = Recorrido(raiz.hijos[4]);
                                    Documento.setElemento(e.nombreElemento, raiz.hijos[2].texto.replace("\"", ""), result, raiz.hijos[0].fila, raiz.hijos[0].col);
                                    return null;
                                }
                            }
                            Datos.agregarError("Error Semantico", "No existe la variable " + raiz.hijos[0].texto + " para el SetElemento", 0, 0);
                            break;
                        case 8: //set con obtener
                            result = Recorrido(raiz.hijos[5]);
                            Documento.setElemento(raiz.hijos[1].texto, raiz.hijos[3].texto.replace("\"", ""), result, raiz.hijos[0].fila, raiz.hijos[0].col);
                            break;
                    }
                    break;
                case "OP":
                    result = Recorrido(raiz.hijos[0]);
                    break;
                case "E":
                    result = Operacion.resolverOperacion(raiz);
                    break;
            }
        }
        return result;
    }

}
