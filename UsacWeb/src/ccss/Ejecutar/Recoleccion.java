package ccss.Ejecutar;

import ccss.NodoCCSS;
import java.util.ArrayList;

public class Recoleccion {

    public static ArrayList parametros = new ArrayList();
    public static String selector = "";

    public Object Recorrido(NodoCCSS raiz) {
        Object result = null;
        // Nodo nodo = null;
        if (raiz != null) {
            switch (raiz.texto) {
                case "INICIO":
                    result = Recorrido(raiz.hijos[0]);
                    break;
                case "INSTRUCCION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            Recorrido(raiz.hijos[0]);
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "BLOQUE":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            selector = raiz.hijos[0].texto;
                            result = Recorrido(raiz.hijos[2]);
                            break;
                    }
                    break;
                case "TIPO":
                    String tipo = raiz.hijos[0].texto;
                    String nombre = raiz.hijos[2].texto;
                    switch (raiz.cantidadHijos) {
                        case 4:
                            BloqueCCSS.crearBloque(selector, tipo, nombre, null, raiz.hijos[2].fila, raiz.hijos[2].col);
                            break;
                        case 5:
                            BloqueCCSS.crearBloque(selector, tipo, nombre, raiz.hijos[4], raiz.hijos[2].fila, raiz.hijos[2].col);
                            break;
                    }
                    break;
            }
        }
        return result;
    }

}
