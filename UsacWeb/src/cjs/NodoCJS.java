package cjs;

public class NodoCJS {

    public String texto;
    public String valor;
    public int fila;
    public int col;
    public int cantidadHijos;
    public NodoCJS padre;
    public NodoCJS hijos[];
    public String tipo;
    public String ambito;

    public NodoCJS(String texto) {
        this.texto = texto;
        this.valor = texto;
        this.tipo = texto;
        this.cantidadHijos = 0;
    }

    public NodoCJS(String texto, String tipo) {
        this.texto = texto;
        this.valor = texto;
        this.tipo = tipo;
        this.cantidadHijos = 0;
    }

    public NodoCJS(String texto, int fila, int col) {
        this.texto = texto;
        this.valor = texto;
        this.fila = fila;
        this.col = col;
        this.cantidadHijos = 0;
    }
    
     public NodoCJS(String texto, String tipo,int fila, int col) {
        this.texto = texto;
        this.valor = texto;
        this.tipo = tipo;
        this.fila = fila;
        this.col = col;
        this.cantidadHijos = 0;
    }

    public void insertarHijo(NodoCJS nuevo, NodoCJS nueva2) {

        if (nuevo == null) {
            return;
        }
        NodoCJS aux[] = new NodoCJS[this.cantidadHijos + 1];

        for (int i = 0; i < this.cantidadHijos; i++) {
            aux[i] = this.hijos[i];
            aux[i].padre = this;
        }
        nuevo.padre = this;
        aux[this.cantidadHijos] = nuevo;
        this.hijos = aux;
        this.cantidadHijos++;

    }

    public void insertar(NodoCJS nuevo) {
        if (nuevo == null) {
            return; //si el nodo nuevo es nulo, no se creo en la gramatica y no debe ser agregado por lo tanto
        }
        NodoCJS aux[] = new NodoCJS[this.cantidadHijos + 1];
        //se copian los hijos del nodo al auxiliar
        for (int i = 0; i < this.cantidadHijos; i++) {
            aux[i] = this.hijos[i];
            aux[i].padre = this;
        }
        nuevo.padre = this;
        aux[this.cantidadHijos] = nuevo;
        this.hijos = aux;
        this.cantidadHijos++;

    }
}
