package cjs.Ejecutar;

import chtml.Ejecutar.Componente;

/**
 *
 * @author Aroche
 */
public class Elemento {

    public String nombreElemento;
    public String nombre;
    public Componente componente;

    public Elemento(String n, String e, Componente c) {
        this.nombre = n;
        this.nombreElemento = e;
        this.componente = c;
    }

    public Elemento(String n, String e) {
        this.nombre = n;
        this.nombreElemento = e;
    }
}
