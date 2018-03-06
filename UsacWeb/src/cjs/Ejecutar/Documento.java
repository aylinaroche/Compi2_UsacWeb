package cjs.Ejecutar;

import chtml.Ejecutar.Boton;
import chtml.Ejecutar.CajaArea;
import chtml.Ejecutar.CajaOpcion;
import chtml.Ejecutar.Componente;
import chtml.Ejecutar.Elementos;
import chtml.Ejecutar.Panel;
import cjs.NodoCJS;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import chtml.chtml;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import static usacweb.Interfaz.panelPestanias;
import static usacweb.Metodos.obtenerNombre;
import usacweb.PanelPrincipal;

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
//            if (v.nombre.equalsIgnoreCase("documento") && v.elemento.equalsIgnoreCase("Listo")) {
//                for (int j = 0; j < chtml.html.listaElementos.size(); j++) {
//                    Elemento e = chtml.html.listaElementos.get(j);
//                    if (e.nombreElemento.equalsIgnoreCase(v.nombre)) {
//                        Recorrido r = new Recorrido();
//                        r.Recorrido(v.nodo);
//                    }
//                }
//
//            } else 
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
                Componente clon = (Componente) comp.clone();
//                ArrayList cuerpo = new ArrayList();
//                cuerpo.add(set);
                Componente set = set(nombre, elemento, valor, comp, f, c);
                Object result = Elementos.dibujar(set, 0);

                String n = obtenerNombre(chtml.html.ruta);
                Box box = Box.createHorizontalBox();
                //panelPestanias.setName("Pestania" + UsacWeb.interfaz.contPestania);
                PanelPrincipal panel = new PanelPrincipal(chtml.html.ruta);
                panel.ruta.setText(chtml.html.ruta);
                panel.setBackground(Color.BLACK);
                panel.setPreferredSize(new Dimension(30000, 30000));
                panel.setMaximumSize(panel.getPreferredSize());
                box.add(panel);
                panelPestanias.addTab("Pestania" + usacweb.UsacWeb.interfaz.contPestania, box);
                panelPestanias.setSelectedIndex(panelPestanias.getTabCount() - 1);
//                //UsacWeb.listaHTML.add(new HTML(panelPestanias.getName()));
                //panelPestanias.setTabComponentAt(panelPestanias.getSelectedIndex(), box);

                try {
                    if (result instanceof JPanel) {
                        panel.crearHTML((JPanel) result);
                    } else if (result instanceof JScrollPane) {
                        panel.crearHTML((JScrollPane) result);
                    }
                } catch (Exception ex) {
                    System.out.println("Error al analizar archivo: " + chtml.html.ruta + "\n" + ex);
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static Componente set(String nombre, String elemento, Object valor, Componente compHijo, int f, int c) {

        ArrayList lista = compHijo.listaComponentes;
        ArrayList<Componente> listaNueva = new ArrayList();

        for (int i = 0; i < lista.size(); i++) {
            Componente comp = (Componente) lista.get(i);
            if (comp.nombre.equalsIgnoreCase(nombre)) {
                if (comp.valor instanceof JButton) {
                    comp = Boton.modificarBoton((JButton) comp.valor, elemento, valor, f, c);
                } else if (comp.valor instanceof JPanel) {
                    System.out.println("Antes = " + comp.alineado);
                    comp = Panel.modificarPanel((JPanel) comp.valor, elemento, comp.listaComponentes, comp.alineado, valor, f, c);
                    System.out.println("Antes = " + comp.alineado);
                    lista.set(i, comp);
                } else if (comp.valor instanceof JTextPane) {
                    comp = CajaArea.modificarArea((JTextPane) comp.valor, elemento, valor, f, c);
                } else if (comp.valor instanceof JComboBox) {
                    comp = CajaOpcion.modificarCajaOpcion((JComboBox) comp.valor, elemento, valor, f, c);
                }
            } else if (comp.tipo.equalsIgnoreCase("panel")) {
                comp = set(nombre, elemento, valor, comp, f, c);
            }
            listaNueva.add(comp);
        }
        Componente compPadre = new Componente(compHijo.tipo, compHijo.nombre, compHijo.valor, listaNueva, compHijo.alineado);
        return compPadre;
    }

    public static void set2(String nombre, String elemento, Object valor, Componente compHijo, int f, int c) {

        Componente compPadre = null;
        ArrayList lista = compHijo.listaComponentes;
        for (int i = 0; i < lista.size(); i++) {
            Componente comp = (Componente) lista.get(i);
            if (comp.nombre.equalsIgnoreCase(nombre)) {
                if (comp.valor instanceof JButton) {
                    comp = Boton.modificarBoton((JButton) comp.valor, elemento, valor, f, c);
                    // return comp;
                } else if (comp.valor instanceof JPanel) {
                    comp = Panel.modificarPanel((JPanel) comp.valor, elemento, comp.listaComponentes, comp.alineado, valor, f, c);
                    lista.set(i, comp);
                    // return comp;
                } else if (comp.valor instanceof JTextPane) {
                    comp = CajaArea.modificarArea((JTextPane) comp.valor, elemento, valor, f, c);
                    //return comp;
                } else if (comp.valor instanceof JComboBox) {
                    comp = CajaOpcion.modificarCajaOpcion((JComboBox) comp.valor, elemento, valor, f, c);
                    // return comp;
                }
            } else if (comp.tipo.equalsIgnoreCase("panel")) {
                set(nombre, elemento, valor, comp, f, c);
//                if (result != null) {
//                    return result;
//                }
            }

        }
        //  return result;
    }

}
