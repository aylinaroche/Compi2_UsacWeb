package cjs.Ejecutar;

import cjs.NodoCJS;
import java.util.ArrayList;

public class Recoleccion {

    public static ArrayList parametros = new ArrayList();

    public Object Recorrido(NodoCJS raiz) {
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
                case "OPCION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                    }
                    break;
                case "FUNCION":
                    String id = raiz.hijos[1].texto;
                    switch (raiz.cantidadHijos) {
                        case 7:
                            FuncionCJS.agregarFuncion(id, raiz.hijos[5], FuncionCJS.parametros, raiz.hijos[1].fila, raiz.hijos[1].col);
                            break;
                        case 8:
                            Recorrido(raiz.hijos[3]);
                            FuncionCJS.agregarFuncion(id, raiz.hijos[6], FuncionCJS.parametros, raiz.hijos[1].fila, raiz.hijos[1].col);
                            break;
                    }
                    FuncionCJS.parametros.clear();
                    break;
                case "PARAMETROS":
                    Recorrido c = new Recorrido();
                    switch (raiz.cantidadHijos) {
                        case 1:
                            FuncionCJS.agregarParametro(raiz.hijos[0].texto);
                            break;
                        case 2:
                            Recorrido(raiz.hijos[0]);
                            FuncionCJS.agregarParametro(raiz.hijos[1].texto);
                            break;
                    }
                    break;
//                case "VARIABLE":
//                case "VECTOR":
//                case "ASIGNACION":
//                case "IMPRIMIR":
//                case "MENSAJE":
//                    Recorrido r = new Recorrido();
//                    r.Recorrido(raiz);
//                    break;
            }
        }
        return result;
    }

}
