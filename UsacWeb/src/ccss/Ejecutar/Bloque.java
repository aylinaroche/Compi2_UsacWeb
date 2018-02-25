package ccss.Ejecutar;

import java.util.ArrayList;

/**
 *
 * @author Aroche
 */
public class Bloque implements Cloneable {

    public String selector;
    public String nombre;
    public String tipo;
    public ArrayList elementos;

    public Bloque(String s, String n, String t, ArrayList e) {
        this.tipo = t;
        this.nombre = n;
        this.selector = s;
        this.elementos = e;
    }

}
