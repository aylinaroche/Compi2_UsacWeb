package chtml.Ejecutar;

import cjs.Ejecutar.*;
import static cjs.Ejecutar.VariableCJS.nivelAmbito;
import static cjs.Ejecutar.VariableCJS.pilaAmbito;
import java.util.ArrayList;
import chtml.NodoCHTML;

public class Recorrido {

    public static String valorSwitch = "";
    public static Boolean retornar = false, salir = false;

    public Object Recorrido(NodoCHTML raiz) {
        Object result = null;
        // Nodo nodo = null;
        if (raiz != null) {
            switch (raiz.texto) {
                case "INICIO":
                    result = Recorrido(raiz.hijos[0]);
                    break;
                case "RAIZ":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "HTML":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            Recorrido(raiz.hijos[0]);
                            Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "ENCABEZADO":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "INFORMACION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "INFO":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            break;
                        case 4:
                            break;
                    }
                    break;
                case "CUERPO":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            //Crearlo vacio
                            break;
                        case 4:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {

                            } else {

                            }
                            break;
                        case 5:
                            result = Recorrido(raiz.hijos[1]);
                            result = Recorrido(raiz.hijos[3]);
                            break;
                    }
                    break;
                case "ETIQUETAS":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            Recorrido(raiz.hijos[0]);
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "ETIQUETA":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            Recorrido(raiz.hijos[0]);
                            break;
                    }
                    break;
                case "PANEL":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            //Crearlo vacio
                            break;
                        case 4:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {

                            } else {

                            }
                            break;
                        case 5:
                            result = Recorrido(raiz.hijos[1]);
                            result = Recorrido(raiz.hijos[3]);
                            break;
                    }
                    break;
                case "TEXTO":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            //Crearlo vacio
                            break;
                        case 4:
                            break;
                    }
                    break;
                case "IMAGEN":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            //Crearlo vacio
                            break;
                        case 3:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {

                            } else {

                            }
                            break;
                        case 4:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "ELEMENTO":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            break;
                    }
                    break;
                case "BOTON":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            //Crearlo vacio
                            break;
                        case 3:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {

                            } else {

                            }
                            break;
                        case 4:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "ENLACE":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            //Crearlo vacio
                            break;
                        case 3:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {

                            } else {

                            }
                            break;
                        case 4:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "TABLA":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            //Crearlo vacio
                            break;
                        case 4:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {

                            } else {

                            }
                            break;
                        case 5:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "LISTAFILA":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            Recorrido(raiz.hijos[0]);
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "FILA":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "CELDAS":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            Recorrido(raiz.hijos[0]);
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "CELDA":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            if (raiz.hijos[0].texto.equals("cb")) {

                            } else {

                            }
                            break;
                    }
                    break;
                case "CONTENIDO":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "TEXTOA":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            break;
                        case 3:
                            break;
                    }
                    break;
                case "CAJATEXTO":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            //Crearlo vacio
                            break;
                        case 3:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {

                            } else {

                            }
                            break;
                        case 4:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "CAJA":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            //Crearlo vacio
                            break;
                        case 4:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {

                            } else {

                            }
                            break;
                        case 5:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "OPCION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            Recorrido(raiz.hijos[0]);
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "OPCIONES":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            //Crearlo vacio
                            break;
                        case 4:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {

                            } else {

                            }
                            break;
                        case 5:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "SPINNER":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            //Crearlo vacio
                            break;
                        case 4:
                            if (raiz.hijos[1].texto.equals("ELEMENTOS")) {

                            } else {

                            }
                            break;
                        case 5:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                //////
            }
        }
        return result;
    }

}
