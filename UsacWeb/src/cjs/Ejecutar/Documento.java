package cjs.Ejecutar;

import chtml.Ejecutar.Boton;
import chtml.Ejecutar.CajaArea;
import chtml.Ejecutar.CajaOpcion;
import chtml.Ejecutar.CajaTexto;
import chtml.Ejecutar.Componente;
import chtml.Ejecutar.Contador;
import chtml.Ejecutar.Enlace;
import chtml.Ejecutar.Imagen;
import chtml.Ejecutar.Panel;
import chtml.Ejecutar.Tabla;
import cjs.NodoCJS;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import chtml.chtml;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;

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

    public static Boolean verificarEvento(String elemento, String nombre) {

        for (int i = 0; i < listaEventos.size(); i++) {
            Evento v = listaEventos.get(i);
            if (v.nombre.equalsIgnoreCase(nombre) && v.elemento.equalsIgnoreCase(elemento)) {
                Recorrido r = new Recorrido();
                r.Recorrido(v.nodo);
                return true;
            }
        }
        return false;
    }

    public static Object obtener(String nombre) {
        if (chtml.html.componentes != null) {
            Componente c = chtml.html.componentes;
            ArrayList cuerpo = new ArrayList();
            cuerpo.add(c);
            Componente ob = obtenerComponente(nombre, cuerpo);
            return ob;
        }
        return null;
    }

    public static Componente obtenerComponente(String nombre, ArrayList listaC) {
        Componente result = null;
        for (int i = 0; i < listaC.size(); i++) {
            Componente c = (Componente) listaC.get(i);
            if (c.nombre.equalsIgnoreCase(nombre)) {
                return c;
            } else if (c.tipo.equalsIgnoreCase("panel")) {
                result = obtenerComponente(nombre, c.listaComponentes);
                if (result != null) {
                    return result;
                }
            }
        }
        return result;
    }

    public static void setElemento(String nombre, String elemento, Object valor, int f, int c) {

        if (chtml.html.componentes != null) {
            Componente comp = chtml.html.componentes;
            try {
                Componente set = set(nombre, elemento, valor, comp, f, c);
                Documento.verificarEvento(nombre, "Modificado");
            } catch (Exception ex) {
                System.out.println("Error en setElemento : " + ex);
            }
        }
    }

    public static Componente set(String nombre, String elemento, Object valor, Componente compHijo, int f, int c) {
        Componente result = null;
        ArrayList lista = compHijo.listaComponentes;
        for (int i = 0; i < lista.size(); i++) {
            Componente comp = (Componente) lista.get(i);
            if (comp.nombre.equalsIgnoreCase(nombre)) {
                if (comp.valor instanceof JButton && comp.tipo.equalsIgnoreCase("boton")) {
                    comp = Boton.modificarBoton((JButton) comp.valor, elemento, valor, comp.listaClick, f, c);
                    return comp;
                } else if (comp.valor instanceof JButton && comp.tipo.equalsIgnoreCase("imagen")) {
                    comp = Imagen.modificarImagen((JButton) comp.valor, elemento, valor,comp.listaClick, f, c);
                    return comp;
                } else if (comp.valor instanceof JPanel) {
                    comp = Panel.modificarPanel((JPanel) comp.valor, elemento, comp.listaComponentes, comp.alineado, valor, f, c);
                    return comp;
                } else if (comp.valor instanceof JTextPane) {
                    comp = CajaArea.modificarArea((JTextPane) comp.valor, elemento, valor, f, c);
                    return comp;
                } else if (comp.valor instanceof JComboBox) {
                    comp = CajaOpcion.modificarCajaOpcion((JComboBox) comp.valor, elemento, valor, comp.listaClick, f, c);
                    return comp;
                } else if (comp.valor instanceof JLabel) {
                    comp = Enlace.modificarEnlace((JLabel) comp.valor, elemento, valor, f, c);
                    return comp;
                } else if (comp.valor instanceof JSpinner) {
                    comp = Contador.modificarSpinner((JSpinner) comp.valor, elemento, valor, f, c);
                    return comp;
                } else if (comp.valor instanceof JTable) {
                    comp = Tabla.modificarTabla((JTable) comp.valor, elemento, valor, f, c);
                    return comp;
                } else if (comp.valor instanceof JTextField) {
                    comp = CajaTexto.modificarCajaTexto((JTextField) comp.valor, elemento, valor, f, c);
                    return comp;
                }

            } else if (comp.tipo.equalsIgnoreCase("panel")) {
                set(nombre, elemento, valor, comp, f, c);
                if (result != null) {
                    return result;
                }
            }
        }
        return result;
    }
}
