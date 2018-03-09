
package cjs.Ejecutar;

import cjs.NodoCJS;

/**
 *
 * @author Aroche
 */
public class Evento {

    public String nombreElemento;
    public String tipoEvento;
    public NodoCJS nodo;

    public Evento(String n, String e, NodoCJS v) {
        this.tipoEvento = e;
        this.nodo = v;
        this.nombreElemento = n;
    }

}
