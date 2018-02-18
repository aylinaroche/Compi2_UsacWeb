package usacweb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Errores {

    public static ArrayList errores = new ArrayList();

    public static void agregarError(String t, String d, int f, int c) {
        Errror e = new Errror();
        e.archivo = "";
        e.tipo = t;
        e.descripcion = d;
        e.fila = f;
        e.columna = c;
        errores.add(e);
        System.out.println("-> " + t + ": " + d);
    }

    public static void reporteErrores(ArrayList errores, String nombre) {
        File f;
        FileWriter escritorArchivo;
        java.util.Date fecha = new Date();
//        System.out.println(fecha);

        if (errores.isEmpty()) {
            System.out.println("Esta vacio");
        }
        try {
            f = new File("C:\\Users\\Aylin\\Desktop\\Errores" + nombre + ".html");
            escritorArchivo = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(escritorArchivo);
            try (PrintWriter salida = new PrintWriter(bw)) {
                salida.write("<html>");
                salida.write("<head><title>Reporte de Errores</title></head>");
                salida.write("<body bgcolor=\"black\">");
                salida.write("<h1><center><FONT COLOR=silver>PROYECTO 1<FONT></center></h1>\"");
                salida.write("<h1><center><FONT COLOR=81426E> REPORTE DE ERRORES <FONT></center></h1>");

                salida.write("<center>");

                salida.write(fecha.toString());
                salida.write("<br>");
                salida.write("<br>");
                salida.write("<table border= 1 width= 500>");
                salida.write("<tr>");
                salida.write("<th><font color=\"#24AAFF\" face=\"courier new\"> TIPO </font></th>");
                salida.write("<th><font color=\"#24AAFF\" face=\"courier new\"> LEXEMA </font></th>");
                salida.write("<th><font color=\"#24AAFF\" face=\"courier new\"> DESCRIPCION </font></th>");
//                salida.write("<th><font color=\"#24AAFF\" face=\"courier new\"> AMBITO </font></th>");
//                salida.write("<th><font color=\"#24AAFF\" face=\"courier new\"> ROL </font></th>");
                salida.write("</tr>");

                for (int i = 0; i < errores.size(); i++) {
                    Errror s = (Errror) errores.get(i);
                    salida.write("<tr>");
                    salida.write("<th><font color=\"white\">" + s.tipo + "</font></th>");
              //      salida.write("<th><font color=\"white\">" + s.lexema + "</font></th>");
                    salida.write("<th><font color=\"white\">" + s.descripcion + "</font></th>");
//                    salida.write("<th><font color=\"white\">" + s.columna+ "</font></th>");
//                    salida.write("<th><font color=\"white\">" + s.fila+ "</font></th>");
                }
                salida.write("</table><br>");
                salida.write("</body></html>");
            }
            System.out.println("El fichero se ha creado correctamente");
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
            System.out.println("No ha podido ser creado el fichero");
        }

    }

    public static void imprimirError() {
        for (int i = 0; i < errores.size(); i++) {
            Errror e = (Errror) errores.get(i);
            System.out.println("> " + e.descripcion);
        }
    }
}

class Errror {

    public String archivo;
    public String descripcion;
    public String tipo;
    public int columna;
    public int fila;

}
