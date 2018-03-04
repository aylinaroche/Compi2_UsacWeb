package ccss.Ejecutar;

import static chtml.chtml.html;
import java.util.ArrayList;
import usacweb.Datos;

/**
 *
 * @author Aroche
 */
public class BloqueCCSS {

    public static void crearBloque(String selector, String tipo, String nombre, ArrayList atributos, int f, int c) {
        ArrayList<Bloque> listaBloques = html.listaBloques;
        for (int i = 0; i < listaBloques.size(); i++) {
            Bloque s = listaBloques.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.tipo.equalsIgnoreCase(tipo) && s.selector.equalsIgnoreCase(selector)) {
                Datos.agregarError("Error Semantico", "El bloque " + nombre + " ya existe", f, c);
                return;
            }
        }
        Bloque v = new Bloque(selector, nombre, tipo, atributos);
        listaBloques.add(v);
    }

    public static ArrayList obtenerBloque(String tipo, String nombre) {
        ArrayList<Bloque> listaBloques = html.listaBloques;
        for (int i = 0; i < listaBloques.size(); i++) {
            Bloque s = listaBloques.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.tipo.equalsIgnoreCase(tipo)) {
                if (s.elementos == null) {
                    return new ArrayList();
                }
                return s.elementos;
            }
        }
     //   Datos.agregarError("Error Semantico", "El bloque " + nombre + " no existe", 0, 0);
        return new ArrayList();
    }

    public static void imprimirBloque() {
        ArrayList<Bloque> listaBloques = html.listaBloques;
        for (int i = 0; i < listaBloques.size(); i++) {
            Bloque s = listaBloques.get(i);
            System.out.println(" " + s.selector + " - " + s.tipo + " - " + s.nombre);
        }
    }
}
