package usacweb;

import static chtml.chtml.html;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.JOptionPane;
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
        PanelPrincipal panel = new PanelPrincipal(ruta);
        panel.setBackground(Color.BLACK);
        panel.setPreferredSize(new Dimension(30000, 30000));
        panel.setMaximumSize(panel.getPreferredSize());
        box.add(panel);
        panelPestanias.addTab(nombre, box);
        panelPestanias.setSelectedIndex(panelPestanias.getTabCount() - 1);

        String texto = Metodos.abrir(ruta);
        html.pilaArchivo.push(nombre);
//        try {
//            chtml.analizar(texto);
//        } catch (Exception ex) {
//            System.out.println("Error al analizar archivo: " + ruta + "\n" + ex);
//        }
        UsacWeb.agregarHistorial(ruta);
        html.pilaArchivo.pop();
    }

}
