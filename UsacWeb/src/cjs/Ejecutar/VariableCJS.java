package cjs.Ejecutar;

import java.util.ArrayList;
import static chtml.chtml.html;
import usacweb.Datos;

public class VariableCJS {

    public static void crearVariable(String nombre, Object valor, int f, int c) {

        if (valor instanceof ArrayList) {
            ArrayList a = (ArrayList) valor;
            crearVector(nombre, a.size(), valor, f, c);
            return;
        } 
        for (int i = 0; i < html.listaVariables.size(); i++) {
            Variable s = (Variable) html.listaVariables.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.ambito.equalsIgnoreCase(html.pilaAmbito.peek()) && s.nivel == html.nivelAmbito && s.archivo.equalsIgnoreCase(html.pilaArchivo.peek())) {
                Datos.agregarError("Error Semantico", "La variable " + nombre + " ya existe", f, c);
                return;
            }
        }
        Variable v = new Variable("variable", nombre, valor, html.pilaAmbito.peek(), html.nivelAmbito, 0);
        html.listaVariables.add(v);
    }

    public static void crearVector(String nombre, Object tamanio, Object valor, int f, int c) {
        int tam = 0;
        if (tamanio instanceof Integer) {
            tam = (Integer) tamanio;
        } else {
            Datos.agregarError("Error Semantico", "El tamanio es invalido " + tamanio, f, c);
            return;
        }
        ArrayList a = (ArrayList) valor;
        if (a.size() > tam) {
            Datos.agregarError("Error Semantico", "El tamanio del vector " + nombre + " es incorrecto", f, c);
            return;
        }
        for (int i = 0; i < html.listaVariables.size(); i++) {
            Variable s = (Variable) html.listaVariables.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.ambito.equals(html.pilaAmbito.peek()) && s.nivel == html.nivelAmbito && s.archivo.equalsIgnoreCase(html.pilaArchivo.peek())) {
                Datos.agregarError("Error Semantico", "El vector " + nombre + " ya existe", f, c);
                return;
            }
        }
        Variable v = new Variable("vector", nombre, valor, html.pilaAmbito.peek(), html.nivelAmbito, tam);
        html.listaVariables.add(v);
    }

    public static void asignarVariable(String nombre, Object valor, int f, int c) {
        for (int i = 0; i < html.listaVariables.size(); i++) {
            Variable s = (Variable) html.listaVariables.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.archivo.equalsIgnoreCase(html.pilaArchivo.peek())) {
                if (valor instanceof ArrayList) {
                    ArrayList a = (ArrayList) valor;
                    if (a.size() != s.tamanio) {
                        Datos.agregarError("Error Semantico", "El tamanio del vector " + nombre + " es incorrecto", f, c);
                        return;
                    }
                }
                s.tamanio = 0;
                s.valor = valor;
                return;
            }
        }
        Datos.agregarError("Error Semantico", "No existe la variable a asignar " + nombre, f, c);
    }

    public static void asignarEnPosicion(String nombre, Object posicion, Object valor, int f, int c) {
        int pos;
        if (posicion instanceof Integer) {
            pos = (Integer) posicion;
        } else {
            Datos.agregarError("Error Semantico", "El tamanio es invalido " + posicion, f, c);
            return;
        }
        for (int i = 0; i < html.listaVariables.size(); i++) {
            Variable s = (Variable) html.listaVariables.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.ambito.equals(html.pilaAmbito.peek()) && s.nivel == html.nivelAmbito && s.archivo.equalsIgnoreCase(html.pilaArchivo.peek())) {
                if (pos > s.tamanio) {
                    Datos.agregarError("Error Semantico", "El tamanio del vector " + nombre + " es incorrecto", f, c);
                    return;
                }
                if (s.valor instanceof ArrayList) {
                    ArrayList v = (ArrayList) s.valor;
                    v.set(pos, valor);
                    return;
                }
            }
        }
        Datos.agregarError("Error Semantico", "No existe la variable a asignar " + nombre, f, c);
    }

    public static void eliminarVariables() {
        for (int i = html.listaVariables.size() - 1; i >= 0; i--) {
            Variable s = (Variable) html.listaVariables.get(i);
            if (s.nivel == html.nivelAmbito && !"Global".equals(s.ambito)) {
                html.listaVariables.remove(i);
            }
        }
    }

    public static Object obtenerVariable(String nombre, int f, int c) {
        for (int i = html.listaVariables.size() - 1; i >= 0; i--) {
            Variable sim = (Variable) html.listaVariables.get(i);
            if (sim.nombre.equalsIgnoreCase(nombre) && sim.archivo.equalsIgnoreCase(html.pilaArchivo.peek())) {
                Object valor = sim.valor;
                return valor;
            }
        }
        Datos.agregarError("Error Semantico", "No existe la variable " + nombre, f, c);
        return null;
    }

    public static Object obtenerVector(String nombre, Object posicion, int f, int c) {
        int pos = 0;
        if (posicion instanceof Integer) {
            pos = (Integer) posicion;
        } else {
            Datos.agregarError("Error Semantico", "El tamanio es invalido " + posicion, f, c);
            return null;
        }
        for (int i = html.listaVariables.size() - 1; i >= 0; i--) {
            Variable sim = (Variable) html.listaVariables.get(i);
            if (sim.nombre.equalsIgnoreCase(nombre) && sim.archivo.equalsIgnoreCase(html.pilaArchivo.peek())) {
                if (sim.valor instanceof ArrayList) {
                    ArrayList v = (ArrayList) sim.valor;
                    if (v.size() > pos) {
                        Object valor = v.get(pos);
                        return valor;
                    }
                }
            }
        }
        Datos.agregarError("Error Semantico", "Error al obtener valor del vector " + nombre, f, c);
        return "";
    }

    public static int obtenerTamanio(String nombre, int f, int c) {
        for (int i = html.listaVariables.size() - 1; i >= 0; i--) {
            Variable s = (Variable) html.listaVariables.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.archivo.equalsIgnoreCase(html.pilaArchivo.peek())) {
                int tam = s.tamanio;
                return tam;
            }
        }
        Datos.agregarError("Error Semantico", "No existe el vector " + nombre, f, c);
        return 0;
    }

    public static String obtenerCadena(String nombre, int f, int c) {
        for (int i = html.listaVariables.size() - 1; i >= 0; i--) {
            Variable s = (Variable) html.listaVariables.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.archivo.equalsIgnoreCase(html.pilaArchivo.peek())) {
                if (s.valor instanceof ArrayList) {
                    ArrayList lista = (ArrayList) s.valor;
                    String cadena = "{";
                    for (int j = 0; j < lista.size() - 1; j++) {
                        cadena += lista.get(j) + ",";
                    }
                    cadena += lista.get(lista.size() - 1) + "}";
                    return cadena;
                }
            }
        }
        Datos.agregarError("Error Semantico", "No existe el vector " + nombre, f, c);
        return null;
    }

    public static void incrementar(String nombre, int n) {
        for (int i = 0; i < html.listaVariables.size(); i++) {
            Variable s = (Variable) html.listaVariables.get(i);
            if (s.nombre.equals(nombre) && html.pilaAmbito.contains(s.ambito) && s.archivo.equalsIgnoreCase(html.pilaArchivo.peek())) {
                try {
                    s.valor = (int) s.valor + n;
                } catch (Exception e) {
                    System.out.println("Error al incrementar = " + e);
                }
                return;
            }
        }
    }

    public static void imprimir() {
        System.out.println("********** IMPRIMIR ***********");
        for (int i = 0; i < html.listaVariables.size(); i++) {
            Variable v = (Variable) html.listaVariables.get(i);
            System.out.println(v.nombre + "  -  " + v.valor + " - " + v.tamanio + "  -  " + v.ambito + " - " + v.archivo);
        }
        System.out.println("*******************************");
    }
}
