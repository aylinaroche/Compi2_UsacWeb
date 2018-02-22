package usacweb;

import java.util.ArrayList;

/**
 *
 * @author Aroche
 */
public class UsacWeb {

    public static ArrayList<Historial> listaHistorial = new ArrayList(); 
    public static ArrayList<String> listaImprimir = new ArrayList();

    public static void main(String[] args) {
        Interfaz i = new Interfaz();
        i.setSize(800, 800);
        i.show(true);

    }

    public static void agregarHistorial(String ruta) {
        Historial h = new Historial(ruta, ruta);
        listaHistorial.add(h);
    }
    
  

}
