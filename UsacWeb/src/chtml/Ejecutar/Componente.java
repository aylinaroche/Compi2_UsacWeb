package chtml.Ejecutar;

import java.util.ArrayList;

/**
 *
 * @author Aroche
 */
public class Componente implements Cloneable {

    public String nombre;
    public Object valor;
    public String tipo;
    public ArrayList<Componente> listaComponentes = new ArrayList<>();
    public String alineado = "";
    public ArrayList<String> listaClick = new ArrayList<>();

    public Componente(String t, String n, Object v, ArrayList a) {
        this.tipo = t;
        this.nombre = n;
        this.valor = v;
        this.listaComponentes = a;
        this.alineado = "";
    }

    public Componente(String t, String n, Object v, ArrayList a, String l) {
        this.tipo = t;
        this.nombre = n;
        this.valor = v;
        this.listaComponentes = a;
        this.alineado = l;
    }

    public Componente(String t, String n, Object v, ArrayList a, ArrayList l) {
        this.tipo = t;
        this.nombre = n;
        this.valor = v;
        this.listaComponentes = a;
        this.alineado = "";
        this.listaClick = l;
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
