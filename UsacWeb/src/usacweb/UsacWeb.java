package usacweb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

/**
 *
 * @author Aroche
 */
public class UsacWeb {

    public static ArrayList<Historial> listaHistorial = new ArrayList();
    public static ArrayList<String> listaImprimir = new ArrayList();
    public static Stack<String> pilaArchivo = new Stack();
    public static ArrayList listaFavoritos = new ArrayList();
    public static Interfaz i = new Interfaz();

    public static void main(String[] args) {

        i.setSize(800, 800);
        i.show(true);

    }

    public static void agregarHistorial(String ruta) {
        Date date = new Date();
        DateFormat formato = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Historial h = new Historial(ruta, formato.format(date));
        listaHistorial.add(h);
    }

}
