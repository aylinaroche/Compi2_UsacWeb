package usacweb;

import ccss.Ejecutar.Bloque;
import chtml.Ejecutar.Componente;
import cjs.Ejecutar.Funcion;
import cjs.Ejecutar.Variable;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JButton;

/**
 *
 * @author Aroche
 */
public class HTML {

    public String nombre;
    public Componente componentes = null;
    public Componente componentesAux = null;
    public String ruta;
    public PanelPrincipal panel = null;
    //GLOBAL
    public Stack<String> pilaArchivo = new Stack();
    public String codigoCHTML;
    public String codigoCCSS;
    public String codigoCJS;
    public int numPagina = 0;
    public ArrayList<String> listaPaginas = new ArrayList();
    //CJS
    public Stack<String> pilaAmbito = new Stack<>();
    public int nivelAmbito;
    public ArrayList<Variable> listaVariables = new ArrayList<>();
    public ArrayList<Funcion> listaFunciones = new ArrayList();
    public ArrayList<JButton> listaBotones = new ArrayList();
    //CCSS
    public ArrayList<Bloque> listaBloques = new ArrayList<>();

    public HTML(String n) {
        this.nombre = n;
        //  System.out.println("Nuevo html = " + n);
    }

    public HTML(String nombre, int noPagina) {
        this.nombre = nombre;
        //  System.out.println("Nuevo html = " + n);
    }

}
