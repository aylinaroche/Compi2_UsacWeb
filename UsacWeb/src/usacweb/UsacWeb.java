package usacweb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Aroche
 */
public class UsacWeb {

    public static ArrayList<Historial> listaHistorial = new ArrayList();
    public static ArrayList<String> listaFavoritos = new ArrayList();
    public static ArrayList<HTML> listaHTML = new ArrayList<HTML>();
    public static Interfaz interfaz = new Interfaz();
//    public static String codigoCHTML;
//    public static String codigoCCSS;
//    public static String codigoCJS; 
// public static Stack<String> pilaArchivo = new Stack();

    public static void main(String[] args) {

        interfaz.setSize(800, 800);
        interfaz.show(true);
        interfaz.setLocationRelativeTo(null);

    }

    public static void agregarHistorial(String ruta) {
        Date date = new Date();
        DateFormat formato = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Historial h = new Historial(ruta, formato.format(date));
        listaHistorial.add(h);
    }

}
