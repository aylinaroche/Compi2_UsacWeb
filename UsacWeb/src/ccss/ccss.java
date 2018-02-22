package ccss;

import ccss.Ejecutar.BloqueCCSS;
import ccss.Ejecutar.Recorrido;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;

public class ccss {

    public static NodoCCSS NODO;

    public static void main(String[] args) throws Exception {
        String path_Lexico = System.getProperty("user.dir").replace("\\", "/") + "/src/ccss/LexicoCCSS.jflex";
//Genera el LEXICO
        File f = new File(path_Lexico);
        String[] Flex = new String[1];
        Flex[0] = System.getProperty("user.dir").replace("\\", "/") + "/src/ccss/LexicoCCSS.jflex";//Sustituí por la direccion de tu .flex 
        jflex.Main.main(Flex);
//Genera el SINTACTICO
        String Params[] = new String[5];

        Params[0] = "-destdir";//habilita destino
        Params[1] = System.getProperty("user.dir").replace("\\", "/") + "/src/ccss";//destino
        Params[2] = "-parser";//habilita nombre
        Params[3] = "SintacticoCCSS";//nombre
        Params[4] = System.getProperty("user.dir").replace("\\", "/") + "/src/ccss/SintacticoCCSS.cup";//loc de .cup//loc de .cup

        try {
            java_cup.Main.main(Params);
        } catch (Exception e) {
            System.out.println(e);
        }
        archivo1();
    }

    public static void analizar(String texto) throws Exception {
        StringReader miReader = new StringReader(texto);
        LexicoCCSS miAnalizador = new LexicoCCSS(miReader);
        // VariableG.pilaAmbito.push(paradigmas.Atributos.nombreArchivo);

        SintacticoCCSS parser = new SintacticoCCSS(miAnalizador);
        parser.parse();
        try {
            Symbol s;
            s = (Symbol) miAnalizador.next_token();
            int cont = 0;
            while (s.sym != 0) {
                System.out.println(cont + " Lexema " + s.value + "  Token " + s.sym + " Linea " + (s.right + 1) + " Columna " + (s.left + 2));
                s = (Symbol) miAnalizador.next_token();
                cont++;
            }
        } catch (IOException e) {
            System.out.println("ERRROOOOR::: " + e);
        }

        iniciar();
    }

    public static void iniciar() {
        Recorrido r = new Recorrido();
        r.Recorrido(NODO);
        BloqueCCSS.imprimirBloque();
    }

    public static void archivo1() {
        try {
            analizar(""
                    + "Titular_ccss [ \n"
                    + "\n"
                    + "//Inicio Grupo_areas_1 \n"
                    + "GRUPO (grupo_areas_1); \n"
                    + "Formato := mayuscula, NEGRILLA, capital-t; \n"
                    + "Letra := \"Courier New\";\n"
                    + "Tamtex := 15.5; \n"
                    + "Fondoelemento := \"green\";\n"
                    + "//Fin Grupo_areas_1 \n"
                    + "\n"
                    + "//Inicio Grupo_areas_2\n"
                    + "GRUPO (grupo_areas_2); \n"
                    + "Formato := mayuscula, NEGRILLA, capital-t; \n"
                    + "Letra := \"Courier New\"; \n"
                    + "Tamtex := 13; \n"
                    + "//Fin Grupo_areas_2\n"
                    + "\n"
                   + "//Inicioidentificador_1 \n"
                    + "ID (identificador_1); \n"
                    + "Formato := mayuscula, NEGRILLA, capital-t; \n"
                    + "Letra := \"Arial\";"
                    + "Tamtex := 12; \n"
                    + "\n"
                    + "] //Fin identificador_1 \n"
                    + "\n"
                    + "caja_de_texto [ \n"
                    + "ID (identificador_2); \n"
                    + "Formato := NEGRILLA; \n"
                    + "Letra := \"Arial\"; \n"
                    + "Tamtex := 18; \n"
                    + "]"
                    + "");
        } catch (Exception ex) {
            Logger.getLogger(ccss.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR :OOOO = " + ex);
        }
    }
}
