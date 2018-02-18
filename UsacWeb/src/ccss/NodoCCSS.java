package ccss;

public class NodoCCSS {

    public String texto;
    public String valor;
    public int fila;
    public int col;
    public int cantidadHijos;
    public NodoCCSS padre;
    public NodoCCSS hijos[];
    public String tipo;
    public String ambito;

    public NodoCCSS(String texto) {
        this.texto = texto;
        this.valor = texto;
        this.tipo = texto;
        this.cantidadHijos = 0;
    }

    public NodoCCSS(String texto, String tipo) {
        this.texto = texto;
        this.valor = texto;
        this.tipo = tipo;
        this.cantidadHijos = 0;
    }

    public NodoCCSS(String texto, int fila, int col) {
        this.texto = texto;
        this.valor = texto;
        this.fila = fila;
        this.col = col;
        this.cantidadHijos = 0;
    }

    public NodoCCSS(String texto, String tipo, int fila, int col) {
        this.texto = texto;
        this.valor = texto;
        this.tipo = tipo;
        this.fila = fila;
        this.col = col;
        this.cantidadHijos = 0;
    }

    public void insertarHijo(NodoCCSS nuevo, NodoCCSS nueva2) {

        if (nuevo == null) {
            return;
        }
        NodoCCSS aux[] = new NodoCCSS[this.cantidadHijos + 1];

        for (int i = 0; i < this.cantidadHijos; i++) {
            aux[i] = this.hijos[i];
            aux[i].padre = this;
        }
        nuevo.padre = this;
        aux[this.cantidadHijos] = nuevo;
        this.hijos = aux;
        this.cantidadHijos++;

    }

    public void insertar(NodoCCSS nuevo) {
        if (nuevo == null) {
            return; //si el nodo nuevo es nulo, no se creo en la gramatica y no debe ser agregado por lo tanto
        }
        NodoCCSS aux[] = new NodoCCSS[this.cantidadHijos + 1];
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
