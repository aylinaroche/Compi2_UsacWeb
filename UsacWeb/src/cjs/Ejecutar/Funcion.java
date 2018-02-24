package cjs.Ejecutar;

import cjs.NodoCJS;
import java.util.ArrayList;

/**
 *
 * @author Aylin
 */
public class Funcion implements Cloneable {

    public String nombre;
    public String ambito;
    public NodoCJS nodo;
    public ArrayList parametro;
    public String archivo;

    public Funcion(String n, NodoCJS v, String a, ArrayList p) {
        this.nombre = n;
        this.nodo = v;
        this.ambito = a;
        this.parametro = p;
        this.archivo = usacweb.UsacWeb.pilaArchivo.peek();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
}
