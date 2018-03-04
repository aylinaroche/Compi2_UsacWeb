package usacweb;

import ccss.Ejecutar.Bloque;
import cjs.Ejecutar.Funcion;
import cjs.Ejecutar.Variable;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Aroche
 */
public class HTML {

    public String nombre;
    //GLOBAL
    public Stack<String> pilaArchivo = new Stack();
    public String codigoCHTML;
    public String codigoCCSS;
    public String codigoCJS;
    public int numPagina =0;
    public ArrayList<String> listaPaginas  = new ArrayList();
    //CJS
    public Stack<String> pilaAmbito = new Stack<>();
    public int nivelAmbito;
    public ArrayList<Variable> listaVariables = new ArrayList<>();
    public ArrayList<Funcion> listaFunciones = new ArrayList();
    //CCSS
    public ArrayList<Bloque> listaBloques = new ArrayList<>();

    public HTML(String n) {
        this.nombre = n;
      //  System.out.println("Nuevo html = " + n);
    }
  
    
}
