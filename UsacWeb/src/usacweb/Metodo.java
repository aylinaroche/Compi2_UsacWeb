/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usacweb;

import chtml.chtml;
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
public class Metodo {

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
            JOptionPane.showMessageDialog(null, 0, "Error al abrir el archivo", JOptionPane.WARNING_MESSAGE);
        }

        System.out.println("\n ********** ARCHIVO ********\n");
        System.out.println(lectura);
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

        String texto = Metodo.abrir(ruta);
        UsacWeb.pilaArchivo.push(nombre);
//        try {
//            chtml.analizar(texto);
//        } catch (Exception ex) {
//            System.out.println("Error al analizar archivo: " + ruta + "\n" + ex);
//        }
        UsacWeb.agregarHistorial(ruta);
        UsacWeb.pilaArchivo.pop();
    }

}
