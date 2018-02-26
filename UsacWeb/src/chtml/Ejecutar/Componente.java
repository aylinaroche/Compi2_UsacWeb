package chtml.Ejecutar;

import java.util.ArrayList;

/**
 *
 * @author Aroche
 */
public class Componente {

    public String nombre;
    public Object valor;
    public String tipo;
    public ArrayList<Componente> listaComponentes;

    public Componente(String t, String n, Object v, ArrayList a) {
        this.tipo = t;
        this.nombre = n;
        this.valor = v;
        this.listaComponentes = a;
    }
}
