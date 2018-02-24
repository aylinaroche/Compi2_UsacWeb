/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usacweb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

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
        String[] valores = ruta.split("/");
        return valores[valores.length - 1];
    }

}
