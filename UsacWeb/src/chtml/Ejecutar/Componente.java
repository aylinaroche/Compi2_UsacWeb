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
    public String alineado;

    public Componente(String t, String n, Object v, ArrayList a) {
        this.tipo = t;
        this.nombre = n;
        this.valor = v;
        this.listaComponentes = a;
    }

    public Componente(String t, String n, Object v, ArrayList a, String l) {
        this.tipo = t;
        this.nombre = n;
        this.valor = v;
        this.listaComponentes = a;
        this.alineado = l;
    }
}
