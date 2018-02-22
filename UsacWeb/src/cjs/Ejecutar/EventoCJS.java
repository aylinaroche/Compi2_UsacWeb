
package cjs.Ejecutar;

import cjs.NodoCJS;
import java.util.ArrayList;

/**
 *
 * @author Aroche
 */
public class EventoCJS {

    public static ArrayList<Evento> listaEventos = new ArrayList();

    public static void crearEvento(String elemento, String nombre, NodoCJS nodo) {
        Evento v = new Evento(elemento, nombre, nodo);
        listaEventos.add(v);
    }

    public static void verificarEvento(String elemento, String nombre) {
        for (int i = 0; i < listaEventos.size(); i++) {
            Evento v = listaEventos.get(i);
            if (v.nombre.equalsIgnoreCase(nombre) && v.elemento.equalsIgnoreCase(elemento)) {
                Recorrido r = new Recorrido();
                r.Recorrido(v.nodo);
                return;
            }
        }
    }

}
