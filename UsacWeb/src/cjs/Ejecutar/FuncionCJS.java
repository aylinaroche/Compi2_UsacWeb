package cjs.Ejecutar;

import cjs.NodoCJS;
import java.util.ArrayList;
import usacweb.Errores;
import static usacweb.UsacWeb.pilaArchivo;

public class FuncionCJS {

    public static ArrayList<Funcion> listaFunciones = new ArrayList();

    public static void agregarFuncion(String nombre, NodoCJS nodo, ArrayList parametro, int f, int c) {
        for (int i = 0; i < listaFunciones.size(); i++) {
            Funcion s = listaFunciones.get(i);
            if (s.nombre.equalsIgnoreCase(nombre) && s.parametro.size() == parametro.size() && s.archivo.equalsIgnoreCase(pilaArchivo.peek())) {
                Errores.agregarError("Error Semantico", "La funcion " + nombre + " ya existe", f, c);
                return;
            }
        }
        Funcion mf = new Funcion(nombre, nodo, VariableCJS.pilaAmbito.peek(), (ArrayList) parametro.clone());
        listaFunciones.add(mf);
    }

    public static Object buscarFuncion(String id, ArrayList parametro, int f, int c) {
        Object retorno;
        for (int i = 0; i < listaFunciones.size(); i++) {
            Funcion mf = (Funcion) listaFunciones.get(i);

            if (mf.nombre.equalsIgnoreCase(id) && mf.archivo.equalsIgnoreCase(pilaArchivo.peek())) {
                VariableCJS.pilaAmbito.push(id);
                VariableCJS.nivelAmbito++;
                if (parametro.isEmpty() && mf.parametro.isEmpty()) {
                    parametro.clear();
                    Recorrido r = new Recorrido();
                    retorno = r.Recorrido(mf.nodo);
                    VariableCJS.nivelAmbito--;
                    VariableCJS.pilaAmbito.pop();
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
                    VariableCJS.nivelAmbito--;
                    VariableCJS.pilaAmbito.pop();
                    return retorno;
                }
            }
        }
        Errores.agregarError("Error Semantico", "No se ha encontrado el metodo", f, c);

        return "";
    }
}
