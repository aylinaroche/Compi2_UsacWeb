package cjs.Ejecutar;

import static chtml.chtml.html;

public class Variable implements Cloneable {

    public String ambito;
    public int nivel;
    public String nombre;
    public Object valor;
    public String tipo;
    public String archivo;
    public int tamanio;

    public Variable(String t, String n, Object v, String a, int ni, int tam) {
        this.tipo = t;
        this.nombre = n;
        this.valor = v;
        this.ambito = a;
        this.nivel = ni;
        this.tamanio = tam;
        this.archivo = html.pilaArchivo.peek();
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
