/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccss.Ejecutar;

import ccss.NodoCCSS;

/**
 *
 * @author Aroche
 */
public class Bloque implements Cloneable {

    public String selector;
    public String nombre;
    public String tipo;
    public NodoCCSS nodo;

    public Bloque(String s, String n, String t, NodoCCSS no) {
        this.tipo = t;
        this.nombre = n;
        this.selector = s;
        this.nodo = no;
    }

}
