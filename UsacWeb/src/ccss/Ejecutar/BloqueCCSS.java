/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccss.Ejecutar;

import ccss.NodoCCSS;
import java.util.ArrayList;
import usacweb.Datos;

/**
 *
 * @author Aroche
 */
public class BloqueCCSS {

    public static ArrayList<Bloque> listaBloques = new ArrayList();

    public static void crearBloque(String selector, String tipo, String nombre, ArrayList atributos, int f, int c) {
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
        for (int i = 0; i < listaBloques.size(); i++) {
            Bloque s = listaBloques.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.tipo.equalsIgnoreCase(tipo)) {
                if(s.elementos == null){
                    return new ArrayList();
                }
                return s.elementos;
            }
        }
        Datos.agregarError("Error Semantico", "El bloque " + nombre + " no existe", 0, 0);
        return new ArrayList();
    }

    public static void imprimirBloque() {
        for (int i = 0; i < listaBloques.size(); i++) {
            Bloque s = listaBloques.get(i);
            System.out.println(" " + s.selector + " - " + s.tipo + " - " + s.nombre);
        }
    }
}
