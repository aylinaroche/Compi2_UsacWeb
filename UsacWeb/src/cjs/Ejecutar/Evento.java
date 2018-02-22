
package cjs.Ejecutar;

import cjs.NodoCJS;

/**
 *
 * @author Aroche
 */
public class Evento {

    public String elemento;
    public String nombre;
    public NodoCJS nodo;

    public Evento(String a, String n, NodoCJS v) {
        this.nombre = n;
        this.nodo = v;
        this.elemento = a;
    }

}
