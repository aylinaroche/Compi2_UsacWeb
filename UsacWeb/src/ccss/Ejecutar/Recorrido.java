package ccss.Ejecutar;

import java.util.ArrayList;
import ccss.NodoCCSS;

public class Recorrido {

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
                case "OPCION":
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
                case "TIPO":
                    String tipo = raiz.hijos[0].texto;
                    String nombre = raiz.hijos[2].texto;
                    switch (raiz.cantidadHijos) {
                        case 4:
                            ArrayList atr1 = new ArrayList();
                            BloqueCCSS.crearBloque(selector, tipo, nombre, atr1, raiz.hijos[2].fila, raiz.hijos[2].col);
                            break;
                        case 5:
                            ArrayList atr2 = (ArrayList) Recorrido(raiz.hijos[4]);
                            BloqueCCSS.crearBloque(selector, tipo, nombre, atr2, raiz.hijos[2].fila, raiz.hijos[2].col);
                            break;
                    }
                    break;
                case "ESTILOS":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            ArrayList l1 = new ArrayList();
                            result = Recorrido(raiz.hijos[0]);
                            l1.add(result);
                            result = l1;
                            break;
                        case 2:
                            ArrayList l2 = (ArrayList) Recorrido(raiz.hijos[0]);
                            l2.add(Recorrido(raiz.hijos[1]));
                            result = l2;
                            break;
                    }
                    break;
                case "ESTILO":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            result = Recorrido(raiz.hijos[2]);
                            Estilo e1 = new Estilo(raiz.hijos[0].texto, result);
                            result = e1;
                            break;
                        case 8:
                            switch (raiz.hijos[0].texto) {
                                case "auto":
                                    ArrayList auto = new ArrayList();
                                    auto.add(Recorrido(raiz.hijos[3])); //valor
                                    auto.add(Recorrido(raiz.hijos[5]));//area
                                    Estilo e2 = new Estilo(raiz.hijos[0].texto, auto);
                                    result = e2;
                                    break;
                            }
                            break;
                        case 10:
                            switch (raiz.hijos[0].texto) {
                                case "borde":
                                    ArrayList borde = new ArrayList();
                                    borde.add(Recorrido(raiz.hijos[3])); //tamanio
                                    borde.add(Recorrido(raiz.hijos[5])); //color
                                    borde.add(Recorrido(raiz.hijos[7])); //curva
                                    Estilo e3 = new Estilo(raiz.hijos[0].texto, borde);
                                    result = e3;
                                    break;
                            }
                            break;
                    }
                    break;
                case "ALINEADO":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = raiz.hijos[0].texto;
                            break;
                    }
                    break;
                case "FORMATOS":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            ArrayList l1 = new ArrayList();
                            result = Recorrido(raiz.hijos[0]);
                            l1.add(result);
                            result = l1;
                            break;
                        case 3:
                            ArrayList l2 = (ArrayList) Recorrido(raiz.hijos[0]);
                            l2.add(Recorrido(raiz.hijos[2]));
                            result = l2;
                            break;
                    }
                    break;
                case "FORMATO":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = raiz.hijos[0].texto;
                            break;
                    }
                    break;
                case "VALOR":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = raiz.hijos[0].texto;
                            break;
                    }
                    break;
                case "AREA":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = raiz.hijos[0].texto;
                            break;
                    }
                    break;
                case "OP":
                    result = Recorrido(raiz.hijos[0]);
                    break;
                case "E":
                    result = Operacion.resolverOperacion(raiz);
                    break;
            }

        }
        return result;
    }

}
