package usacweb;

import java.util.ArrayList;

public class Datos {

    public static ArrayList<Errror> listaErrores = new ArrayList();
    public static ArrayList<Salida> listaImprimir = new ArrayList();

    public static void agregarError(String t, String d, int f, int c) {
        Errror e = new Errror(UsacWeb.pilaArchivo.peek(), d, t, f, c);
        listaErrores.add(e);
        System.out.println("-> " + t + ": " + d + ": " + f + ": " + c + ": " + e.archivo);
    }

    public static void imprimirError() {
        for (int i = 0; i < listaErrores.size(); i++) {
            Errror e = (Errror) listaErrores.get(i);
            System.out.println("> " + e.descripcion);
        }
    }

    public static Object[][] obtenerErrores() {
        Object[][] tabla = new Object[Datos.listaErrores.size()][5];
        if (Datos.listaErrores.size() > 0) {
            for (int j = 0; j < Datos.listaErrores.size(); j++) {
                Errror e = Datos.listaErrores.get(j);
                tabla[j][1] = e.archivo;
                tabla[j][2] = e.fila;
                tabla[j][3] = e.columna;
                tabla[j][4] = e.tipo;
                tabla[j][5] = e.descripcion;
            }
        } else {
            Object[][] tabla2 = new Object[][]{
                {" - ", " - ", " - ", " - ", " - "}
            };
            return tabla2;
        }
        return tabla;
    }

    public static void agregarSalida(String t, int f, int c) {
        Salida e = new Salida(UsacWeb.pilaArchivo.peek(), t, f, c);
        listaImprimir.add(e);
        System.out.println("-> " + t + ": " + f + ": " + c + ": " + e.archivo);
    }

    public static Object[][] obtenerSalida() {
        
        Object[][] tabla = new Object[Datos.listaImprimir.size()][4];
        if (Datos.listaImprimir.size() > 0) {
            for (int j = 0; j < Datos.listaImprimir.size(); j++) {
                Salida e = Datos.listaImprimir.get(j);
                tabla[j][1] = e.archivo;
                tabla[j][2] = e.fila;
                tabla[j][3] = e.columna;
                tabla[j][4] = e.texto;
            }
        } else {
            Object[][] tabla2 = new Object[][]{
                {" - ", " - ", " - ", " - "}
            };
            return tabla2;
        }
        return tabla;
    }
}

class Errror {

    public String archivo;
    public String descripcion;
    public String tipo;
    public int columna;
    public int fila;

    public Errror(String a, String d, String t, int c, int f) {
        this.archivo = a;
        this.descripcion = d;
        this.tipo = t;
        this.columna = c;
        this.fila = f;
    }
}

class Salida {

    public String archivo;
    public String texto;
    public int columna;
    public int fila;

    public Salida(String a, String t, int c, int f) {
        this.archivo = a;
        this.texto = t;
        this.columna = c;
        this.fila = f;
    }
}
