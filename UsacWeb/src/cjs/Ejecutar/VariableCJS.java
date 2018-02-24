package cjs.Ejecutar;

import java.util.ArrayList;
import java.util.Stack;
import usacweb.Errores;
import static usacweb.UsacWeb.pilaArchivo;

public class VariableCJS {

    public static Stack<String> pilaAmbito = new Stack<>();
    public static int nivelAmbito = 0;
    public static ArrayList<Variable> listaVariables = new ArrayList();

    public static void crearVariable(String nombre, Object valor, int f, int c) {
        if (valor instanceof ArrayList) {
            ArrayList a = (ArrayList) valor;
            crearVector(nombre, a.size(), valor, f, c);
            return;
        }
        for (int i = 0; i < listaVariables.size(); i++) {
            Variable s = (Variable) listaVariables.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.ambito.equalsIgnoreCase(pilaAmbito.peek()) && s.nivel == nivelAmbito && s.archivo.equalsIgnoreCase(pilaArchivo.peek())) {
                Errores.agregarError("Error Semantico", "La variable " + nombre + " ya existe", f, c);
                return;
            }
        }
        Variable v = new Variable("variable", nombre, valor, pilaAmbito.peek(), nivelAmbito, 0);
        listaVariables.add(v);
    }

    public static void crearVector(String nombre, Object tamanio, Object valor, int f, int c) {
        int tam = 0;
        if (tamanio instanceof Integer) {
            tam = (Integer) tamanio;
        } else {
            Errores.agregarError("Error Semantico", "El tamanio es invalido " + tamanio, f, c);
            return;
        }
        ArrayList a = (ArrayList) valor;
        if (a.size() > tam) {
            Errores.agregarError("Error Semantico", "El tamanio del vector " + nombre + " es incorrecto", f, c);
            return;
        }
        for (int i = 0; i < listaVariables.size(); i++) {
            Variable s = (Variable) listaVariables.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.ambito.equals(pilaAmbito.peek()) && s.nivel == nivelAmbito && s.archivo.equalsIgnoreCase(pilaArchivo.peek())) {
                Errores.agregarError("Error Semantico", "El vector " + nombre + " ya existe", f, c);
                return;
            }
        }
        Variable v = new Variable("vector", nombre, valor, pilaAmbito.peek(), nivelAmbito, tam);
        listaVariables.add(v);
    }

    public static void asignarVariable(String nombre, Object valor, int f, int c) {
        for (int i = 0; i < listaVariables.size(); i++) {
            Variable s = (Variable) listaVariables.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.archivo.equalsIgnoreCase(pilaArchivo.peek())) {
                if (valor instanceof ArrayList) {
                    ArrayList a = (ArrayList) valor;
                    if (a.size() != s.tamanio) {
                        Errores.agregarError("Error Semantico", "El tamanio del vector " + nombre + " es incorrecto", f, c);
                        return;
                    }
                }
                s.valor = valor;
                return;
            }
        }
        Errores.agregarError("Error Semantico", "No existe la variable a asignar " + nombre, f, c);
    }

    public static void asignarEnPosicion(String nombre, Object posicion, Object valor, int f, int c) {
        int pos;
        if (posicion instanceof Integer) {
            pos = (Integer) posicion;
        } else {
            Errores.agregarError("Error Semantico", "El tamanio es invalido " + posicion, f, c);
            return;
        }
        for (int i = 0; i < listaVariables.size(); i++) {
            Variable s = (Variable) listaVariables.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.ambito.equals(pilaAmbito.peek()) && s.nivel == nivelAmbito && s.archivo.equalsIgnoreCase(pilaArchivo.peek())) {
                if (pos > s.tamanio) {
                    Errores.agregarError("Error Semantico", "El tamanio del vector " + nombre + " es incorrecto", f, c);
                    return;
                }
                if (s.valor instanceof ArrayList) {
                    ArrayList v = (ArrayList) s.valor;
                    v.set(pos, valor);
                    return;
                }
            }
        }
        Errores.agregarError("Error Semantico", "No existe la variable a asignar " + nombre, f, c);
    }

    public static void eliminarVariables() {
        for (int i = listaVariables.size() - 1; i >= 0; i--) {
            Variable s = (Variable) listaVariables.get(i);
            if (s.nivel == nivelAmbito && !"Global".equals(s.ambito)) {
                listaVariables.remove(i);
            }
        }
    }

    public static Object obtenerVariable(String nombre, int f, int c) {
        for (int i = listaVariables.size() - 1; i >= 0; i--) {
            Variable sim = (Variable) listaVariables.get(i);
            if (sim.nombre.equalsIgnoreCase(nombre) && sim.archivo.equalsIgnoreCase(pilaArchivo.peek())) {
                Object valor = sim.valor;
                return valor;
            }
        }
        Errores.agregarError("Error Semantico", "No existe la variable " + nombre, f, c);
        return null;
    }

    public static Object obtenerVector(String nombre, Object posicion, int f, int c) {
        int pos = 0;
        if (posicion instanceof Integer) {
            pos = (Integer) posicion;
        } else {
            Errores.agregarError("Error Semantico", "El tamanio es invalido " + posicion, f, c);
            return null;
        }
        for (int i = listaVariables.size() - 1; i >= 0; i--) {
            Variable sim = (Variable) listaVariables.get(i);
            if (sim.nombre.equalsIgnoreCase(nombre) && sim.archivo.equalsIgnoreCase(pilaArchivo.peek())) {
                if (sim.valor instanceof ArrayList) {
                    ArrayList v = (ArrayList) sim.valor;
                    if (v.size() > pos) {
                        Object valor = v.get(pos);
                        return valor;
                    }
                }
            }
        }
        Errores.agregarError("Error Semantico", "Error al obtener valor del vector " + nombre, f, c);
        return "";
    }

    public static int obtenerTamanio(String nombre, int f, int c) {
        for (int i = listaVariables.size() - 1; i >= 0; i--) {
            Variable s = (Variable) listaVariables.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.archivo.equalsIgnoreCase(pilaArchivo.peek())) {
                int tam = s.tamanio;
                return tam;
            }
        }
        Errores.agregarError("Error Semantico", "No existe el vector " + nombre, f, c);
        return 0;
    }

    public static String obtenerCadena(String nombre, int f, int c) {
        for (int i = listaVariables.size() - 1; i >= 0; i--) {
            Variable s = (Variable) listaVariables.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.archivo.equalsIgnoreCase(pilaArchivo.peek())) {
                if (s.valor instanceof ArrayList) {
                    ArrayList lista = (ArrayList) s.valor;
                    String cadena = "";
                    for (int j = 0; j < lista.size(); j++) {
                        cadena += lista.get(j);
                    }
                    return cadena;
                }
            }
        }
        Errores.agregarError("Error Semantico", "No existe el vector " + nombre, f, c);
        return null;
    }

    public static void incrementar(String nombre, int n) {
        for (int i = 0; i < listaVariables.size(); i++) {
            Variable s = (Variable) listaVariables.get(i);
            if (s.nombre.equals(nombre) && pilaAmbito.contains(s.ambito) && s.archivo.equalsIgnoreCase(pilaArchivo.peek())) {
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
        for (int i = 0; i < listaVariables.size(); i++) {
            Variable v = (Variable) listaVariables.get(i);
            System.out.println(v.nombre + "  -  " + v.valor + " - " + v.tamanio + "  -  " + v.ambito + " - " + v.archivo);
        }
        System.out.println("*******************************");
    }
}
