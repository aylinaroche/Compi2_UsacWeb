package cjs.Ejecutar;

import chtml.Ejecutar.Boton;
import chtml.Ejecutar.Componente;
import chtml.Ejecutar.Elementos;
import cjs.NodoCJS;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import usacweb.Interfaz;

/**
 *
 * @author Aroche
 */
public class Documento {

    public static ArrayList<Evento> listaEventos = new ArrayList();

    public static void crearEvento(String elemento, String nombre, NodoCJS nodo) {
        Evento v = new Evento(elemento, nombre, nodo);
        listaEventos.add(v);
    }

    public static void verificarEvento(String elemento, String nombre) {
        for (int i = 0; i < listaEventos.size(); i++) {
            Evento v = listaEventos.get(i);
            if (v.nombre.equalsIgnoreCase(nombre) && v.elemento.equalsIgnoreCase(elemento)) {
                Recorrido r = new Recorrido();
                r.Recorrido(v.nodo);
                return;
            }
        }
    }

    public static Object obtener(String nombre) {
        if (chtml.chtml.html.componentes != null) {
            Componente c = chtml.chtml.html.componentes;
            Componente ob = obtenerComponente(nombre, c.listaComponentes);
            //chtml.chtml.html.listaElementos.add(new Elemento(nombre, nombre));
            return ob;
        }
        return null;
    }

    public static Componente obtenerComponente(String nombre, ArrayList listaC) {
        for (int i = 0; i < listaC.size(); i++) {
            Componente c = (Componente) listaC.get(i);
            if (c.nombre.equalsIgnoreCase(nombre)) {
                return c;
            }
            return obtenerComponente(nombre, c.listaComponentes);
        }
        return null;
    }

    public static void setElemento(String nombre, String elemento, Object valor, int f, int c) {
        if (chtml.chtml.html.componentes != null) {
            Componente comp = chtml.chtml.html.componentes;
            set(nombre, elemento, valor, comp.listaComponentes, f, c);
            Object result = Elementos.dibujar(comp, 0);
            Interfaz.panelPestanias.updateUI();
            
//            if (result instanceof JPanel) {
//                chtml.chtml.html.crearHTML((JPanel) result);
//            } else if (result instanceof JScrollPane) {
//                chtml.chtml.html.crearHTML((JScrollPane) result);
//            }
//            Documento.verificarEvento("Documento", "Listo");
        }

    }

    public static Componente set(String nombre, String elemento, Object valor, ArrayList lista, int f, int c) {

        for (int i = 0; i < lista.size(); i++) {
            Componente comp = (Componente) lista.get(i);
            if (comp.nombre.equalsIgnoreCase(nombre)) {
                if (comp.tipo.equalsIgnoreCase("boton")) {
                    comp.valor = Boton.cambiarBoton((JButton) comp.valor, elemento, valor, f, c);
                    return null;
                }
            }
            return set(nombre, elemento, valor, comp.listaComponentes, f, c);
        }
        return null;
    }

    public static void cambiarBoton() {

    }

}
