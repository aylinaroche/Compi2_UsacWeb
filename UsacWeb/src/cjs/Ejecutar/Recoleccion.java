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
                            FuncionCJS.agregarFuncion(id, raiz.hijos[5], new ArrayList(), raiz.hijos[1].fila, raiz.hijos[1].col);
                            break;
                        case 8:
                            ArrayList param = (ArrayList) Recorrido(raiz.hijos[3]);
                            FuncionCJS.agregarFuncion(id, raiz.hijos[6], param, raiz.hijos[1].fila, raiz.hijos[1].col);
                            break;
                    }
                    break;
                case "PARAMETROS":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            ArrayList l1 = new ArrayList();
                            l1.add(raiz.hijos[0].texto);
                            result = l1;
                            break;
                        case 2:
                            ArrayList l2 = (ArrayList) Recorrido(raiz.hijos[0]);
                            l2.add(raiz.hijos[1].texto);
                            result = l2;
                            break;
                    }
                    break;
            }
        }
        return result;
    }

}
