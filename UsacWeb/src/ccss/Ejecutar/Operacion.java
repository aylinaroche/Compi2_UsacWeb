package ccss.Ejecutar;

import java.util.Objects;
import ccss.NodoCCSS;
import usacweb.Datos;

public class Operacion {

    public static Object resolverOperacion(NodoCCSS nodo) {
        Object resultado = expresion(nodo);
        //   System.out.println(">>>>> " + resultado);
        return resultado;
    }

    private static Object expresion(NodoCCSS nodo) {

        switch (nodo.cantidadHijos) {
            case 1:
                String dato = nodo.hijos[0].texto;

                switch (dato) {
                    case "E":
                        Recorrido r = new Recorrido();
                        Object result = r.Recorrido(nodo.hijos[0]);
                        return result;
                    default:
                        String tipoDato = nodo.hijos[0].tipo;
                        switch (tipoDato) {
                            case "cadena":
                                return dato;
                            case "entero":
                                int ent = Integer.parseInt(dato);
                                return ent;
                            case "decimal":
                                Double num = Double.parseDouble(dato);
                                return num;
                            case "bool":
                                if (dato.equalsIgnoreCase("verdadero")) {
                                    return true;
                                }
                                return false;
                            default:
                                return "";
                        }
                }
            case 2:
                Object F1 = "";
                if (nodo.hijos[0].texto.equals("-")) {
                    F1 = expresion(nodo.hijos[1]);
                    try {
                        if (F1 instanceof Double) {
                            Double n = (Double) F1 * (-1);
                            return n;
                        } else if (F1 instanceof Integer) {
                            int n = (Integer) F1 * (-1);
                            return n;
                        } else {
                            Datos.agregarError("Error Semantico", "No se puede negar el valor", nodo.hijos[0].fila, nodo.hijos[0].col);
                        }
                    } catch (Exception e) {
                        System.out.println("E-(): " + e);
                    }
                } else if (nodo.hijos[0].texto.equals("!")) {
                    F1 = expresion(nodo.hijos[1]);
                    if (F1 instanceof Boolean) {
                        return !((Boolean) F1);
                    }
                    Datos.agregarError("Error Semantico", "Error al operar !", nodo.hijos[0].fila, nodo.hijos[0].col);
                    return false;
                } else {
                    return "";
                }
            case 3: //Nodo binario
                Object E1 = "";
                Object E2 = "";
                switch (nodo.hijos[1].texto) {

                    case "+": //E+E
                        E1 = expresion(nodo.hijos[0]);
                        E2 = expresion(nodo.hijos[2]);
                        try {
                            //DECIMAL
                            //CADENA
                            if (E1 instanceof String || E2 instanceof String) {
                                String r = String.valueOf(E1) + String.valueOf(E2);
                                return r;
                            } else if ((E1 instanceof Double) || (E2 instanceof Double)) {
                                if ((E1 instanceof Integer) || (E2 instanceof Integer)) { //Entero
                                    if (E1 instanceof Integer) {
                                        int v = (int) E1;
                                        Double v1 = Double.parseDouble(String.valueOf(v)) + (Double) E2;
                                        return v1;
                                    } else {
                                        int v = (int) E2;
                                        Double v1 = Double.parseDouble(String.valueOf(v)) + (Double) E1;
                                        return v1;
                                    }
                                } else if ((E1 instanceof Boolean) || (E2 instanceof Boolean)) {//Boolean
                                    if (E1 instanceof Boolean) {
                                        if ((Boolean) E1 == true) {
                                            Double v1 = (Double) E2 + 1;
                                            return v1;
                                        } else {
                                            return E2;
                                        }
                                    } else if ((Boolean) E2 == true) {
                                        Double v1 = (Double) E1 + 1;
                                        return v1;
                                    } else {
                                        return E1;
                                    }
                                } else if ((E1 instanceof Double) && (E2 instanceof Double)) {//Boolean
                                    return (Double) E1 + (Double) E2;
                                }
                                //ENTERO
                            } else if ((E1 instanceof Integer) || (E2 instanceof Integer)) {
                                if ((E1 instanceof Boolean) || (E2 instanceof Boolean)) {//Boolean
                                    if (E1 instanceof Boolean) {
                                        if ((Boolean) E1 == true) {
                                            int v1 = (Integer) E2 + 1;
                                            return v1;
                                        } else {
                                            return E2;
                                        }
                                    } else if ((Boolean) E2 == true) {
                                        int v1 = (Integer) E1 + 1;
                                        return v1;
                                    } else {
                                        return E1;
                                    }
                                } else {
                                    return (Integer) E1 + (Integer) E2;
                                }
                                //BOOL
                            } else if ((E1 instanceof Boolean) && (E2 instanceof Boolean)) {
                                return (Boolean) E1 == true || (Boolean) E2 == true;
                                //CARACTER
                            }
                            Datos.agregarError("Error Semantico", "Error al castear suma ", nodo.hijos[1].fila, nodo.hijos[1].col);
                            return (Double) 0.0;
                        } catch (NumberFormatException e) {
                            System.out.println("E+ :" + e);
                            return "";
                        }
                    //  break;                    //  break;

                    case "-": //E-E
                        E1 = expresion(nodo.hijos[0]);
                        E2 = expresion(nodo.hijos[2]);

                        try {
                            if ((E1 instanceof Double) || (E2 instanceof Double)) {
                                if ((E1 instanceof Integer) || (E2 instanceof Integer)) { //Entero
                                    if (E1 instanceof Integer) {
                                        int v = (int) E1;
                                        Double v1 = Double.parseDouble(String.valueOf(v)) - (Double) E2;
                                        return v1;
                                    } else {
                                        int v = (int) E2;
                                        Double v1 = (Double) E1 - Double.parseDouble(String.valueOf(v));
                                        return v1;
                                    }
                                } else if ((E1 instanceof Boolean) || (E2 instanceof Boolean)) {//Boolean
                                    if (E1 instanceof Boolean) {
                                        if ((Boolean) E1 == true) {
                                            Double v1 = 1 - (Double) E2;
                                            return v1;
                                        } else {
                                            return E2;
                                        }
                                    } else if ((Boolean) E2 == true) {
                                        Double v1 = (Double) E1 - 1;
                                        return v1;
                                    } else {
                                        return E1;
                                    }
                                } else if ((E1 instanceof Double) && (E2 instanceof Double)) {//Dobule
                                    return (Double) E1 - (Double) E2;
                                }
                                //ENTERO
                            } else if ((E1 instanceof Integer) || (E2 instanceof Integer)) {
                                if ((E1 instanceof Boolean) || (E2 instanceof Boolean)) {//Boolean
                                    if (E1 instanceof Boolean) {
                                        if ((Boolean) E1 == true) {
                                            int v1 = 1 - (Integer) E2;
                                            return v1;
                                        } else {
                                            return E2;
                                        }
                                    } else if ((Boolean) E2 == true) {
                                        int v1 = (Integer) E1 - 1;
                                        return v1;
                                    } else {
                                        return E1;
                                    }
                                } else if ((E1 instanceof Integer) && (E2 instanceof Integer)) {//Boolean{
                                    return (Integer) E1 - (Integer) E2;
                                }
                            }
                            Datos.agregarError("Error Semantico", "Error al castear resta ", nodo.hijos[1].fila, nodo.hijos[1].col);
                            return (Double) 0.0;
                        } catch (NumberFormatException e) {
                            System.out.println("E- :" + e);
                            return "";
                        }

                    //  break;
                    case "*": //E*E
                        E1 = expresion(nodo.hijos[0]);
                        E2 = expresion(nodo.hijos[2]);

                        try {
                            if ((E1 instanceof Double) || (E2 instanceof Double)) {
                                if ((E1 instanceof Integer) || (E2 instanceof Integer)) { //Entero
                                    if (E1 instanceof Integer) {
                                        int v = (int) E1;
                                        Double v1 = Double.parseDouble(String.valueOf(v)) * (Double) E2;
                                        return v1;
                                    } else {
                                        int v = (int) E2;
                                        Double v1 = (Double) E1 * Double.parseDouble(String.valueOf(v));
                                        return v1;
                                    }
                                } else if ((E1 instanceof Boolean) || (E2 instanceof Boolean)) {//Boolean
                                    if (E1 instanceof Boolean) {
                                        if ((Boolean) E1 == true) {
                                            Double v1 = 1 * (Double) E2;
                                            return v1;
                                        } else {
                                            return (Double) 0.0;
                                        }
                                    } else if ((Boolean) E2 == true) {
                                        Double v1 = (Double) E1 * 1;
                                        return v1;
                                    } else {
                                        return (Double) 0.0;
                                    }
                                } else if ((E1 instanceof Double) && (E2 instanceof Double)) {//Double
                                    return (Double) E1 * (Double) E2;
                                }
                                //ENTERO
                            } else if ((E1 instanceof Integer) || (E2 instanceof Integer)) {
                                if ((E1 instanceof Boolean) || (E2 instanceof Boolean)) {//Boolean
                                    if (E1 instanceof Boolean) {
                                        if ((Boolean) E1 == true) {
                                            int v1 = 1 * (Integer) E2;
                                            return v1;
                                        } else {
                                            return (Double) 0.0;
                                        }
                                    } else if ((Boolean) E2 == true) {
                                        int v1 = (Integer) E1 * 1;
                                        return v1;
                                    } else {
                                        return (Double) 0.0;
                                    }
                                } else if ((E1 instanceof Integer) && (E2 instanceof Integer)) {//Boolean{
                                    return (Integer) E1 * (Integer) E2;
                                }
                                //BOOL
                            } else if ((E1 instanceof Boolean) && (E2 instanceof Boolean)) {
                                return (Boolean) E1 == true && (Boolean) E2 == true;
                            }
                            Datos.agregarError("Error Semantico", "Error al castear multiplicación ", nodo.hijos[1].fila, nodo.hijos[1].col);
                            return (Double) 0.0;

                        } catch (NumberFormatException e) {
                            System.out.println("E");
                            return "";
                        }
                    //   break;
                    case "/": //E/E
                        E1 = expresion(nodo.hijos[0]);
                        E2 = expresion(nodo.hijos[2]);
                        try {
                            if ((E1 instanceof Double) || (E2 instanceof Double)) {
                                if ((E1 instanceof Integer) || (E2 instanceof Integer)) { //Entero
                                    if (E1 instanceof Integer) {
                                        int v = (int) E1;
                                        Double v1 = Double.parseDouble(String.valueOf(v)) / (Double) E2;
                                        return v1;
                                    } else {
                                        int v = (int) E2;
                                        Double v1 = (Double) E1 / Double.parseDouble(String.valueOf(v));
                                        return v1;
                                    }
                                } else if ((E1 instanceof Boolean) || (E2 instanceof Boolean)) {//Boolean
                                    if (E1 instanceof Boolean) {
                                        if ((Boolean) E1 == true) {
                                            Double v1 = 1 / (Double) E2;
                                            return v1;
                                        } else {
                                            return (Double) 0.0;
                                        }
                                    } else if ((Boolean) E2 == true) {
                                        Double v1 = (Double) E1 / 1;
                                        return v1;
                                    } else {
                                        Datos.agregarError("Error Semantico", "No se puede dividir dentro de 0", nodo.hijos[1].fila, nodo.hijos[1].col);
                                        return (Double) 0.0;
                                    }
                                } else if ((E1 instanceof Double) && (E2 instanceof Double)) {//Double
                                    return (Double) E1 * (Double) E2;
                                }
                                //ENTERO
                            } else if ((E1 instanceof Integer) || (E2 instanceof Integer)) {
                               if ((E1 instanceof Boolean) || (E2 instanceof Boolean)) {//Boolean
                                    if (E1 instanceof Boolean) {
                                        if ((Boolean) E1 == true) {
                                            int v1 = 1 / (Integer) E2;
                                            return v1;
                                        } else {
                                            return (Double) 0.0;
                                        }
                                    } else if ((Boolean) E2 == true) {
                                        int v1 = (Integer) E1 / 1;
                                        return v1;
                                    } else {
                                        Datos.agregarError("Error Semantico", "No se puede dividir dentro de 0", nodo.hijos[1].fila, nodo.hijos[1].col);
                                        return (Double) 0.0;
                                    }
                                } else if ((E1 instanceof Integer) && (E2 instanceof Integer)) {//Enteross
                                    int v1 = (Integer) E1;
                                    int v2 = (Integer) E2;
                                    Double v3 = Double.parseDouble(String.valueOf(v1)) / Double.parseDouble(String.valueOf(v2));
                                    return v3;
                                }
                                //BOOL
                            }
                            Datos.agregarError("Error Semantico", "Error al castear dividar ", nodo.hijos[1].fila, nodo.hijos[1].col);
                            return (Double) 0.0;

                        } catch (NumberFormatException e) {
                            System.out.println("E");
                            return "";
                        }
                    //break;
// RELACIONALES                        
                    case ">":
                        try {
                            E1 = expresion(nodo.hijos[0]);
                            E2 = expresion(nodo.hijos[2]);

                            if ((E1 instanceof Double || E2 instanceof Double) && (E1 instanceof Integer || E2 instanceof Integer)) {
                                if (E1 instanceof Double) {
                                    Double var1 = (Double) (E1);
                                    int var2 = (Integer) (E2);
                                    return var1 > var2;
                                } else {
                                    int var1 = (Integer) (E1);
                                    Double var2 = (Double) (E2);
                                    return var1 > var2;
                                }
                            } else if ((E1 instanceof Double || E2 instanceof Double) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Double) {
                                    Double var1 = (Double) (E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 > var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    Double var2 = (Double) (E2);
                                    return var1 > var2;
                                }
                            } else if ((E1 instanceof Integer || E2 instanceof Integer) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Integer) {
                                    int var1 = (Integer) (E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 > var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    int var2 = (Integer) (E2);
                                    return var1 > var2;
                                }
                            } else if ((E1 instanceof String || E2 instanceof String) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof String) {
                                    return compararMayor((String) E1, String.valueOf((char) E2));
                                } else {
                                    return compararMayor(String.valueOf((char) E1), (String) E1);
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Double || E2 instanceof Double)) {
                                if (E1 instanceof Boolean) {
                                    int var1 = obtenerBoolean(E1);
                                    Double var2 = (Double) (E2);
                                    return var1 > var2;
                                } else {
                                    Double var1 = (Double) (E1);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 > var2;
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Integer || E2 instanceof Integer)) {
                                if (E1 instanceof Boolean) {
                                    int var1 = obtenerBoolean(E1);
                                    int var2 = (Integer) (E2);
                                    return var1 > var2;
                                } else {
                                    int var1 = (Integer) (E1);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 > var2;
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Integer) {
                                    int var1 = obtenerBoolean(E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 > var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 > var2;
                                }
                            } else if ((E1 instanceof Double && E2 instanceof Double)) {
                                return (Double) E1 > (Double) E2;
                            } else if ((E1 instanceof Integer && E2 instanceof Integer)) {
                                return (Integer) E1 > (Integer) E2;
                            } else if ((E1 instanceof Character && E2 instanceof Character)) {
                                return ((String) E1).codePointAt(0) > ((String) E2).codePointAt(0);
                            } else if ((E1 instanceof Boolean && E2 instanceof Boolean)) {
                                return obtenerBoolean(E1) > obtenerBoolean(E2);
                            } else if ((E1 instanceof String && E2 instanceof String)) {
                                return compararMayor((String) E1, (String) E2);
                            } else {
                                Datos.agregarError("Error Semantico", "Error al usar operador relacional >", nodo.hijos[1].fila, nodo.hijos[1].col);
                            }
                        } catch (Exception e) {
                            System.out.println("E>: " + e);
                            return "";
                        }
//  break;
                    case "<":
                        try {
                            E1 = expresion(nodo.hijos[0]);
                            E2 = expresion(nodo.hijos[2]);

                            if ((E1 instanceof Double || E2 instanceof Double) && (E1 instanceof Integer || E2 instanceof Integer)) {
                                if (E1 instanceof Double) {
                                    Double var1 = (Double) (E1);
                                    int var2 = (Integer) (E2);
                                    return var1 < var2;
                                } else {
                                    int var1 = (Integer) (E1);
                                    Double var2 = (Double) (E2);
                                    return var1 < var2;
                                }
                            } else if ((E1 instanceof Double || E2 instanceof Double) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Double) {
                                    Double var1 = (Double) (E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 < var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    Double var2 = (Double) (E2);
                                    return var1 < var2;
                                }
                            } else if ((E1 instanceof Integer || E2 instanceof Integer) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Integer) {
                                    int var1 = (Integer) (E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 < var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    int var2 = (Integer) (E2);
                                    return var1 < var2;
                                }
                            } else if ((E1 instanceof String || E2 instanceof String) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof String) {
                                    return compararMenor((String) E1, String.valueOf((char) E2));
                                } else {
                                    return compararMenor(String.valueOf((char) E1), (String) E1);
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Double || E2 instanceof Double)) {
                                if (E1 instanceof Boolean) {
                                    int var1 = obtenerBoolean(E1);
                                    Double var2 = (Double) (E2);
                                    return var1 < var2;
                                } else {
                                    Double var1 = (Double) (E1);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 < var2;
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Integer || E2 instanceof Integer)) {
                                if (E1 instanceof Boolean) {
                                    int var1 = obtenerBoolean(E1);
                                    int var2 = (Integer) (E2);
                                    return var1 < var2;
                                } else {
                                    int var1 = (Integer) (E1);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 < var2;
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Integer) {
                                    int var1 = obtenerBoolean(E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 < var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 < var2;
                                }
                            } else if ((E1 instanceof Double && E2 instanceof Double)) {
                                return (Double) E1 < (Double) E2;
                            } else if ((E1 instanceof Integer && E2 instanceof Integer)) {
                                return (Integer) E1 < (Integer) E2;
                            } else if ((E1 instanceof Character && E2 instanceof Character)) {
                                return ((String) E1).codePointAt(0) < ((String) E2).codePointAt(0);
                            } else if ((E1 instanceof Boolean && E2 instanceof Boolean)) {
                                return obtenerBoolean(E1) < obtenerBoolean(E2);
                            } else if ((E1 instanceof String && E2 instanceof String)) {
                                return compararMenor((String) E1, (String) E2);

                            } else {
                                Datos.agregarError("Error Semantico", "Error al usar operador relacional <", nodo.hijos[1].fila, nodo.hijos[1].col);
                            }
                        } catch (Exception e) {
                            System.out.println("E<: " + e);
                            return "";
                        }
                    case ">=":
                        try {
                            E1 = expresion(nodo.hijos[0]);
                            E2 = expresion(nodo.hijos[2]);

                            if ((E1 instanceof Double || E2 instanceof Double) && (E1 instanceof Integer || E2 instanceof Integer)) {
                                if (E1 instanceof Double) {
                                    Double var1 = (Double) (E1);
                                    int var2 = (Integer) (E2);
                                    return var1 >= var2;
                                } else {
                                    int var1 = (Integer) (E1);
                                    Double var2 = (Double) (E2);
                                    return var1 >= var2;
                                }
                            } else if ((E1 instanceof Double || E2 instanceof Double) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Double) {
                                    Double var1 = (Double) (E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 >= var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    Double var2 = (Double) (E2);
                                    return var1 >= var2;
                                }
                            } else if ((E1 instanceof Integer || E2 instanceof Integer) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Integer) {
                                    int var1 = (Integer) (E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 >= var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    int var2 = (Integer) (E2);
                                    return var1 >= var2;
                                }
                            } else if ((E1 instanceof String || E2 instanceof String) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof String) {
                                    Boolean comp = compararMayor((String) E1, String.valueOf((char) E2));
                                    if (comp == false) {
                                        return compararIgual((String) E1, String.valueOf((char) E2));
                                    }
                                    return comp;
                                } else {
                                    Boolean comp = compararMayor(String.valueOf((char) E1), (String) E1);
                                    if (comp == false) {
                                        return compararIgual(String.valueOf((char) E1), (String) E1);
                                    }
                                    return comp;
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Double || E2 instanceof Double)) {
                                if (E1 instanceof Boolean) {
                                    int var1 = obtenerBoolean(E1);
                                    Double var2 = (Double) (E2);
                                    return var1 >= var2;
                                } else {
                                    Double var1 = (Double) (E1);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 >= var2;
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Integer || E2 instanceof Integer)) {
                                if (E1 instanceof Boolean) {
                                    int var1 = obtenerBoolean(E1);
                                    int var2 = (Integer) (E2);
                                    return var1 >= var2;
                                } else {
                                    int var1 = (Integer) (E1);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 >= var2;
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Integer) {
                                    int var1 = obtenerBoolean(E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 >= var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 >= var2;
                                }
                            } else if ((E1 instanceof Double && E2 instanceof Double)) {
                                return (Double) E1 >= (Double) E2;
                            } else if ((E1 instanceof Integer && E2 instanceof Integer)) {
                                return (Integer) E1 >= (Integer) E2;
                            } else if ((E1 instanceof Character && E2 instanceof Character)) {
                                return ((String) E1).codePointAt(0) >= ((String) E2).codePointAt(0);
                            } else if ((E1 instanceof Boolean && E2 instanceof Boolean)) {
                                return obtenerBoolean(E1) >= obtenerBoolean(E2);
                            } else if ((E1 instanceof String && E2 instanceof String)) {
                                Boolean comp = compararMayor((String) E1, (String) E2);
                                if (comp == false) {
                                    return compararIgual((String) E1, (String) E2);
                                }
                                return comp;
                            } else {
                                Datos.agregarError("Error Semantico", "Error al usar operador relacional >=", nodo.hijos[1].fila, nodo.hijos[1].col);
                            }
                        } catch (Exception e) {
                            System.out.println("E>=: " + e);
                            return "";
                        }
//  break;
                    case "<=":
                        try {
                            E1 = expresion(nodo.hijos[0]);
                            E2 = expresion(nodo.hijos[2]);

                            if ((E1 instanceof Double || E2 instanceof Double) && (E1 instanceof Integer || E2 instanceof Integer)) {
                                if (E1 instanceof Double) {
                                    Double var1 = (Double) (E1);
                                    int var2 = (Integer) (E2);
                                    return var1 <= var2;
                                } else {
                                    int var1 = (Integer) (E1);
                                    Double var2 = (Double) (E2);
                                    return var1 <= var2;
                                }
                            } else if ((E1 instanceof Double || E2 instanceof Double) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Double) {
                                    Double var1 = (Double) (E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 <= var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    Double var2 = (Double) (E2);
                                    return var1 <= var2;
                                }
                            } else if ((E1 instanceof Integer || E2 instanceof Integer) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Integer) {
                                    int var1 = (Integer) (E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 <= var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    int var2 = (Integer) (E2);
                                    return var1 <= var2;
                                }
                            } else if ((E1 instanceof String || E2 instanceof String) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof String) {
                                    Boolean comp = compararMenor((String) E1, String.valueOf((char) E2));
                                    if (comp == false) {
                                        return compararIgual((String) E1, String.valueOf((char) E2));
                                    }
                                    return comp;
                                } else {
                                    Boolean comp = compararMenor(String.valueOf((char) E1), (String) E1);
                                    if (comp == false) {
                                        return compararIgual(String.valueOf((char) E1), (String) E1);
                                    }
                                    return comp;
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Double || E2 instanceof Double)) {
                                if (E1 instanceof Boolean) {
                                    int var1 = obtenerBoolean(E1);
                                    Double var2 = (Double) (E2);
                                    return var1 <= var2;
                                } else {
                                    Double var1 = (Double) (E1);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 <= var2;
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Integer || E2 instanceof Integer)) {
                                if (E1 instanceof Boolean) {
                                    int var1 = obtenerBoolean(E1);
                                    int var2 = (Integer) (E2);
                                    return var1 <= var2;
                                } else {
                                    int var1 = (Integer) (E1);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 <= var2;
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Integer) {
                                    int var1 = obtenerBoolean(E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 <= var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 <= var2;
                                }
                            } else if ((E1 instanceof Double && E2 instanceof Double)) {
                                return (Double) E1 <= (Double) E2;
                            } else if ((E1 instanceof Integer && E2 instanceof Integer)) {
                                return (Integer) E1 <= (Integer) E2;
                            } else if ((E1 instanceof Character && E2 instanceof Character)) {
                                return ((String) E1).codePointAt(0) <= ((String) E2).codePointAt(0);
                            } else if ((E1 instanceof Boolean && E2 instanceof Boolean)) {
                                return obtenerBoolean(E1) <= obtenerBoolean(E2);
                            } else if ((E1 instanceof String && E2 instanceof String)) {
                                Boolean comp = compararMenor((String) E1, (String) E2);
                                if (comp == false) {
                                    return compararIgual((String) E1, (String) E2);
                                }
                                return comp;
                            } else {
                                Datos.agregarError("Error Semantico", "Error al usar operador relacional <=", nodo.hijos[1].fila, nodo.hijos[1].col);
                            }
                        } catch (Exception e) {
                            System.out.println("E<=: " + e);
                            return "";
                        }
                    case "==":
                        try {
                            E1 = expresion(nodo.hijos[0]);
                            E2 = expresion(nodo.hijos[2]);

                            if ((E1 instanceof Double || E2 instanceof Double) && (E1 instanceof Integer || E2 instanceof Integer)) {
                                if (E1 instanceof Double) {
                                    Double var1 = (Double) (E1);
                                    int var2 = (Integer) (E2);
                                    return var1 == var2;
                                } else {
                                    int var1 = (Integer) (E1);
                                    Double var2 = (Double) (E2);
                                    return var1 == var2;
                                }
                            } else if ((E1 instanceof Double || E2 instanceof Double) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Double) {
                                    Double var1 = (Double) (E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 == var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    Double var2 = (Double) (E2);
                                    return var1 == var2;
                                }
                            } else if ((E1 instanceof Integer || E2 instanceof Integer) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Integer) {
                                    int var1 = (Integer) (E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 == var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    int var2 = (Integer) (E2);
                                    return var1 == var2;
                                }
                            } else if ((E1 instanceof String || E2 instanceof String) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof String) {
                                    return compararIgual((String) E1, String.valueOf((char) E2));
                                } else {
                                    return compararIgual(String.valueOf((char) E1), (String) E1);
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Double || E2 instanceof Double)) {
                                if (E1 instanceof Boolean) {
                                    int var1 = obtenerBoolean(E1);
                                    Double var2 = (Double) (E2);
                                    return var1 == var2;
                                } else {
                                    Double var1 = (Double) (E1);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 == var2;
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Integer || E2 instanceof Integer)) {
                                if (E1 instanceof Boolean) {
                                    int var1 = obtenerBoolean(E1);
                                    int var2 = (Integer) (E2);
                                    return var1 == var2;
                                } else {
                                    int var1 = (Integer) (E1);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 == var2;
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Integer) {
                                    int var1 = obtenerBoolean(E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 == var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 == var2;
                                }
                            } else if ((E1 instanceof Double && E2 instanceof Double)) {
                                return Objects.equals((Double) E1, (Double) E2);
                            } else if ((E1 instanceof Integer && E2 instanceof Integer)) {
                                return Objects.equals((Integer) E1, (Integer) E2);
                            } else if ((E1 instanceof Character && E2 instanceof Character)) {
                                return ((String) E1).codePointAt(0) == ((String) E2).codePointAt(0);
                            } else if ((E1 instanceof Boolean && E2 instanceof Boolean)) {
                                return obtenerBoolean(E1) == obtenerBoolean(E2);
                            } else if ((E1 instanceof String && E2 instanceof String)) {
                                return compararIgual((String) E1, (String) E2);
                            } else {
                                Datos.agregarError("Error Semantico", "Error al usar operador relacional ==", nodo.hijos[1].fila, nodo.hijos[1].col);
                            }
                        } catch (Exception e) {
                            System.out.println("E==: " + e);
                            return "";
                        }
                    //  break;
                    case "!=":
                        try {
                            E1 = expresion(nodo.hijos[0]);
                            E2 = expresion(nodo.hijos[2]);

                            if ((E1 instanceof Double || E2 instanceof Double) && (E1 instanceof Integer || E2 instanceof Integer)) {
                                if (E1 instanceof Double) {
                                    Double var1 = (Double) (E1);
                                    int var2 = (Integer) (E2);
                                    return var1 != var2;
                                } else {
                                    int var1 = (Integer) (E1);
                                    Double var2 = (Double) (E2);
                                    return var1 != var2;
                                }
                            } else if ((E1 instanceof Double || E2 instanceof Double) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Double) {
                                    Double var1 = (Double) (E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 != var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    Double var2 = (Double) (E2);
                                    return var1 != var2;
                                }
                            } else if ((E1 instanceof Integer || E2 instanceof Integer) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof Integer) {
                                    int var1 = (Integer) (E1);
                                    int var2 = ((String) E2).codePointAt(0);
                                    return var1 != var2;
                                } else {
                                    int var1 = ((String) E1).codePointAt(0);
                                    int var2 = (Integer) (E2);
                                    return var1 != var2;
                                }
                            } else if ((E1 instanceof String || E2 instanceof String) && (E1 instanceof Character || E2 instanceof Character)) {
                                if (E1 instanceof String) {
                                    return !compararIgual((String) E1, String.valueOf((char) E2));
                                } else {
                                    return !compararIgual(String.valueOf((char) E1), (String) E1);
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Double || E2 instanceof Double)) {
                                if (E1 instanceof Boolean) {
                                    int var1 = obtenerBoolean(E1);
                                    Double var2 = (Double) (E2);
                                    return var1 != var2;
                                } else {
                                    Double var1 = (Double) (E1);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 != var2;
                                }
                            } else if ((E1 instanceof Boolean || E2 instanceof Boolean) && (E1 instanceof Integer || E2 instanceof Integer)) {
                                if (E1 instanceof Boolean) {
                                    int var1 = obtenerBoolean(E1);
                                    int var2 = (Integer) (E2);
                                    return var1 != var2;
                                } else {
                                    int var1 = (Integer) (E1);
                                    int var2 = obtenerBoolean(E2);
                                    return var1 != var2;
                                }
                            } else if ((E1 instanceof Double && E2 instanceof Double)) {
                                return !Objects.equals((Double) E1, (Double) E2);
                            } else if ((E1 instanceof Integer && E2 instanceof Integer)) {
                                return !Objects.equals((Integer) E1, (Integer) E2);
                           } else if ((E1 instanceof Boolean && E2 instanceof Boolean)) {
                                return obtenerBoolean(E1) != obtenerBoolean(E2);
                            } else if ((E1 instanceof String && E2 instanceof String)) {
                                return !compararIgual((String) E1, (String) E2);
                            } else {
                                Datos.agregarError("Error Semantico", "Error al usar operador relacional !=", nodo.hijos[1].fila, nodo.hijos[1].col);
                            }
                        } catch (Exception e) {
                            System.out.println("E!=: " + e);
                            return "";
                        }
                    case "&&":
                        E1 = expresion(nodo.hijos[0]);
                        E2 = expresion(nodo.hijos[2]);

                        if (E1 instanceof Boolean && E2 instanceof Boolean) {
                            return (Boolean) E1 == true && (Boolean) E2 == true;
                        }
                        Datos.agregarError("Error Semantico", "Error al operar &&", nodo.hijos[1].fila, nodo.hijos[1].col);
                        return false;

                    case "||":
                        E1 = expresion(nodo.hijos[0]);
                        E2 = expresion(nodo.hijos[2]);

                        if (E1 instanceof Boolean && E2 instanceof Boolean) {
                            return (Boolean) E1 == true || (Boolean) E2 == true;
                        }
                        Datos.agregarError("Error Semantico", "Error al operar ||", nodo.hijos[1].fila, nodo.hijos[1].col);
                        return false;
                    default: //(E)
                        return expresion(nodo.hijos[1]);
                }
        }

        return "";
    }

    public static Boolean compararMayor(String c1, String c2) {
        if (c1.length() > c2.length()) {
            return true;
        } else if (c1.length() < c2.length()) {
            return false;
        }
        for (int i = 0; i < c1.length(); i++) {
            int v1 = c1.codePointAt(i);
            int v2 = c1.codePointAt(i);
            return v1 > v2;
        }
        return false;
    }

    public static Boolean compararMenor(String c1, String c2) {
        if (c1.length() > c2.length()) {
            return false;
        } else if (c1.length() < c2.length()) {
            return true;
        }
        for (int i = 0; i < c1.length(); i++) {
            int v1 = c1.codePointAt(i);
            int v2 = c1.codePointAt(i);
            return v1 < v2;
        }
        return false;
    }

    public static Boolean compararIgual(String c1, String c2) {
        if (c1.length() > c2.length()) {
            return false;
        } else if (c1.length() < c2.length()) {
            return false;
        }
        for (int i = 0; i < c1.length(); i++) {
            int v1 = c1.codePointAt(i);
            int v2 = c1.codePointAt(i);
            if (v1 != v2) {
                return false;
            }
        }
        return true;
    }

    public static int obtenerBoolean(Object E1) {
        Boolean b = (Boolean) E1;
        if (b == true) {
            return 1;
        } else {
            return 0;
        }
    }
}
