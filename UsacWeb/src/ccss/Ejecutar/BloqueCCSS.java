/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccss.Ejecutar;

import ccss.NodoCCSS;
import java.util.ArrayList;
import usacweb.Errores;

/**
 *
 * @author Aroche
 */
public class BloqueCCSS {

    public static ArrayList<Bloque> listaBloques = new ArrayList();

    public static void crearBloque(String selector, String tipo, String nombre, NodoCCSS nodo, int f, int c) {
        for (int i = 0; i < listaBloques.size(); i++) {
            Bloque s = listaBloques.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.tipo.equalsIgnoreCase(tipo) && s.selector.equalsIgnoreCase(selector)) {
                Errores.agregarError("Error Semantico", "El bloque " + nombre + " ya existe", f, c);
                return;
            }
        }
        Bloque v = new Bloque(selector, nombre, tipo, nodo);
        listaBloques.add(v);
    }
}
