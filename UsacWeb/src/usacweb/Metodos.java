package usacweb;

import chtml.chtml;
import cjs.Ejecutar.Documento;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static usacweb.Interfaz.panelPestanias;

/**
 *
 * @author Aroche
 */
public class Metodos {

    public static String abrir(String ruta) {
        String lectura = "";
        try {
            File f = new File(ruta);
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String aux;
                while ((aux = br.readLine()) != null) {
                    lectura = lectura + aux + "\n";
                }
            } catch (IOException e) {
            }
        } catch (NullPointerException e) {
            Datos.agregarError("Error Semantico", "Error al abrir el archivo", 0, 0);
        }
//        System.out.println("\n ********** ARCHIVO ********\n");
//        System.out.println(lectura);
        return lectura;
    }

    public static String obtenerNombre(String ruta) {
        String[] valores = ruta.replace("\\", "/").split("/");
        return valores[valores.length - 1];
    }

    public static void crearPestania(String ruta) {
        String nombre = obtenerNombre(ruta);
        Box box = Box.createHorizontalBox();
        panelPestanias.setName("Pestania" + UsacWeb.interfaz.contPestania);
        PanelPrincipal panel = new PanelPrincipal(ruta);
        panel.ruta.setText(ruta);
        panel.setBackground(Color.BLACK);
        panel.setPreferredSize(new Dimension(30000, 30000));
        panel.setMaximumSize(panel.getPreferredSize());
        box.add(panel);
        panelPestanias.addTab("Pestania" + UsacWeb.interfaz.contPestania, box);
        panelPestanias.setSelectedIndex(panelPestanias.getTabCount() - 1);
        UsacWeb.listaHTML.add(new HTML(panelPestanias.getName()));

        String texto = Metodos.abrir(ruta);

        if (!"".equals(texto)) {
            HTML html = UsacWeb.listaHTML.get(UsacWeb.listaHTML.size() - 1);
            html.pilaArchivo.push(nombre);
            try {
                Object result = chtml.analizar(texto, html);
                html.codigoCHTML = texto;
                if (result instanceof JPanel) {
                    panel.crearHTML((JPanel) result);
                } else if (result instanceof JScrollPane) {
                    panel.crearHTML((JScrollPane) result);
                }
                //   Documento.verificarEvento("Documento", "Listo");
            } catch (Exception ex) {
                System.out.println("Error al analizar archivo: " + ruta + "\n" + ex);
            }
            html.pilaArchivo.pop();
            UsacWeb.agregarHistorial(ruta);
            html.numPagina++;
            html.listaPaginas.add(ruta);
            return;
        }
        JOptionPane.showMessageDialog(null, " No se ha encontrado el archivo: " + ruta, "WARNING", JOptionPane.WARNING_MESSAGE);
    }

}
