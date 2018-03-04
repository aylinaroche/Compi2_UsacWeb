package cjs.Ejecutar;

import static chtml.chtml.html;
import cjs.NodoCJS;
import java.util.ArrayList;
import usacweb.Datos;

public class FuncionCJS {

    //public static ArrayList<Funcion> listaFunciones = new ArrayList();
    public static void agregarFuncion(String nombre, NodoCJS nodo, ArrayList parametro, int f, int c) {
        ArrayList<Funcion> listaFunciones = html.listaFunciones;
        for (int i = 0; i < listaFunciones.size(); i++) {
            Funcion s = listaFunciones.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.parametro.size() == parametro.size() && s.archivo.equalsIgnoreCase(html.pilaArchivo.peek())) {
                Datos.agregarError("Error Semantico", "La funcion " + nombre + " ya existe", f, c);
                return;
            }
        }
        Funcion mf = new Funcion(nombre, nodo, html.pilaAmbito.peek(), (ArrayList) parametro.clone());
        listaFunciones.add(mf);
    }

    public static Object buscarFuncion(String id, ArrayList parametro, int f, int c) {
        ArrayList<Funcion> listaFunciones = html.listaFunciones;
        Object retorno;
        for (int i = 0; i < listaFunciones.size(); i++) {
            Funcion mf = (Funcion) listaFunciones.get(i);

            if (mf.nombre.equalsIgnoreCase(id)) {
                html.pilaAmbito.push(id);
                html.nivelAmbito++;
                if (parametro.isEmpty() && mf.parametro.isEmpty()) {
                    parametro.clear();
                    Recorrido r = new Recorrido();
                    retorno = r.Recorrido(mf.nodo);
                    html.nivelAmbito--;
                    html.pilaAmbito.pop();
                    return retorno;
                }
                if (parametro.size() == mf.parametro.size()) {
                    for (int j = 0; j < parametro.size(); j++) {
                        String p = (String) mf.parametro.get(j);
                        Object p2 = (Object) parametro.get(j);
                        VariableCJS.crearVariable(p, p2, f, c);
                    }
                    parametro.clear();
                    Recorrido r = new Recorrido();
                    retorno = r.Recorrido(mf.nodo);
                    VariableCJS.eliminarVariables();
                    html.nivelAmbito--;
                    html.pilaAmbito.pop();
                    return retorno;
                }
            }
        }
        Datos.agregarError("Error Semantico", "No se ha encontrado el metodo", f, c);

        return "";
    }
}
