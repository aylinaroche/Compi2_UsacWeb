package usacweb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static usacweb.Interfaz.panelPestanias;

/**
 *
 * @author Aroche
 */
public class UsacWeb {

    public static ArrayList<Historial> listaHistorial = new ArrayList();
    public static ArrayList<String> listaFavoritos = new ArrayList();
    public static ArrayList<HTML> listaHTML = new ArrayList<HTML>();
    public static Interfaz interfaz = new Interfaz();

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

    public static void verificarPestania() {
//        int num = chtml.chtml.html.numPagina;
//        ArrayList lista = chtml.chtml.html.listaPaginas;
//        System.out.println(" nAnterior = " + chtml.chtml.html.nombre + ", " + chtml.chtml.html.numPagina);
//        System.out.println("-- " + panelPestanias.getSelectedIndex() + ", " + UsacWeb.listaHTML.size());
//        chtml.chtml.html = UsacWeb.listaHTML.get(panelPestanias.getSelectedIndex());
//        chtml.chtml.html.numPagina = num;
//        chtml.chtml.html.listaPaginas = lista;
        System.out.println(" nDespues = " + chtml.chtml.html.nombre + ", " + chtml.chtml.html.numPagina);
        System.out.println("");
    }
}
