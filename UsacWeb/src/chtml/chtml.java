package chtml;

import chtml.Ejecutar.Recorrido;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;

public class chtml {

    public static NodoCHTML NODO;

    public static void main(String[] args) throws Exception {
        String path_Lexico = System.getProperty("user.dir").replace("\\", "/") + "/src/chtml/LexicoCHTML.jflex";
//Genera el LEXICO
        File f = new File(path_Lexico);
        String[] Flex = new String[1];
        Flex[0] = System.getProperty("user.dir").replace("\\", "/") + "/src/chtml/LexicoCHTML.jflex";//Sustituí por la direccion de tu .flex 
        jflex.Main.main(Flex);
//Genera el SINTACTICO
        String Params[] = new String[5];

        Params[0] = "-destdir";//habilita destino
        Params[1] = System.getProperty("user.dir").replace("\\", "/") + "/src/chtml";//destino
        Params[2] = "-parser";//habilita nombre
        Params[3] = "SintacticoCHTML";//nombre
        Params[4] = System.getProperty("user.dir").replace("\\", "/") + "/src/chtml/SintacticoCHTML.cup";//loc de .cup//loc de .cup

        try {
            java_cup.Main.main(Params);
        } catch (Exception e) {
            System.out.println(e);
        }
        usacweb.UsacWeb.pilaArchivo.push("ArchivoPrueba.Html");
        archivo1();
    }

    public static void analizar(String texto) throws Exception {
        StringReader miReader = new StringReader(texto);
        LexicoCHTML miAnalizador = new LexicoCHTML(miReader);
        // VariableG.pilaAmbito.push(paradigmas.Atributos.nombreArchivo);

        SintacticoCHTML parser = new SintacticoCHTML(miAnalizador);
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
            return;
        }

        iniciar();
    }

    public static void iniciar() {
        usacweb.UsacWeb.pilaArchivo.push("ArchivoPrueba");
        Recorrido r = new Recorrido();
        r.Recorrido(NODO);
    }

    public static void archivo1() {
        try {
            analizar(""
                    + "<CHTML>" 
                    + "	<ENCABEZADO> \n"
                    + "		<CJS ruta = \"C:/fichero/fichero_1.cjs\";><FIN-CJS> \n"
                    + "		<CCSS ruta = \"C:/fichero/estilo_2.ccss\";><FIN-CCSS> \n"
                    + "<// luis como molesta xD //>"
                    + "		<TITULO> USAC WEB <FIN-TITULO>\n"
                    + "	<FIN-ENCABEZADO>\n"
                    + "	<CUERPO fondo = \"red\"; >"
                    + "	"
                    + "		<PANEL fondo = \"red\";> "
                    + "			<TEXTO>sdASD<FIN-TEXTO> \n"
                    + "			<CAJA_TEXTO> Contenido en la caja de texto <FIN-CAJA_TEXTO> \n"
                    + "			<PANEL>"
                    + "				<TEXTO> Entrada: Esta es una etiqueta que permite saltos <FIN-TEXTO>\n"
                    + "			<FIN-PANEL> \n"
                    + "		<FIN-PANEL>\n"
                    + "		\n"
//                    + "		<PANEL>\n"
//                    + "			<IMAGEN> c://proyecto1/imagen.png <FIN-IMAGEN> \n"
//                    + "			<IMAGEN ruta=\"c://fichero/hoja.chtml\";> c://proy1/foto.jpg <FIN-IMAGEN> \n"
//                    + "			<IMAGEN click=\"metodo()\";> c://proyecto1/clase.gif <FIN-IMAGEN>\n"
//                    + "			\n"
//                    + "			<BOTON> Enviar <FIN-BOTON> \n"
//                    + "			<BOTON click=\"salida_consola()\";>Este es un botón<FIN-BOTON> \n"
//                    + "			<BOTON ruta=\"c://pro_1/index.chtml\";> Principal <FIN-BOTON>\n"
//                    + "			\n"
//                    + "			<ENLACE ruta=\"ruta/ruta\";> <//- Texto a mostrar -//> <FIN-ENLACE> \n"
//                    + "			<ENLACE ruta=\"c://ruta/ruta.chtml\";> Página \nRuta <FIN-ENLACE>\n"
//                    + "			\n"
//                    + "			<TABLA> "
//                    + "				<FIL_T>"
//                    + "				<CB>Nombre<FIN-CB> \n"
//                    + "				<CB>Apellido<FIN-CB> \n"
//                    + "				<CB>Edad<FIN-CB>\n"
//                    + "                         <FIN-FIL_T> \n"
//                    + "				<FIL_T> \n"
//                    + "				<CT>Mack<FIN-CT> \n"
//                    + "				<CT>Hill<FIN-CT> \n"
//                    + "				<CT>15<FIN-CT> \n"
//                    + "                         <FIN-FIL_T> \n"
//                    + "				<FIL_T> \n"
//                    + "				<CT>Stark<FIN-CT> \n"
//                    + "				<CT>Iron<FIN-CT> \n"
//                    + "				<CT>50<FIN-CT> \n"
//                    + "				<FIN-FIL_T> \n"
//                    + "			<FIN-TABLA>"
//                    + "			<TEXTO_A> Nombre<FIN-TEXTO_A>\n"
//                    + "			\n"
//                    + "			<CAJA_TEXTO> Contenido en la caja de texto <FIN-CAJA_TEXTO> \n"
//                    + "			<CAJA_TEXTO> <FIN-CAJA_TEXTO>"
//                    + "			<CAJA click=\"metodo()\";> "
//                    + "			<OPCION valor=\"1\";>Uno<FIN-OPCION> \n"
//                    + "			<OPCION valor=\"2\";>Dos<FIN-OPCION> \n"
//                    + "			<OPCION valor=\"3\";>Tres<FIN-OPCION> \n"
//                          + "			<OPCION valor=\"4\";>4<FIN-OPCION>\n" //verificar que acepte el 4
//                    + "			<FIN-CAJA>"
//                    + "			\n"
//                    + "			<SPINNER>15<FIN-SPINNER>\n"
//                    + "			<SPINNER>1<FIN-SPINNER>\n"
//                    + "			\n"
//                    + "			<SALTO-FIN> \n"
//                    + "			<CAJA> "
//                    + "\n"
//                    + "			<OPCION valor=\"1\";>Uno<FIN-OPCION> \n"
//                    + "			<OPCION valor=\"2\";>Dos<FIN-OPCION> \n"
//                    + "			<FIN-CAJA> \n"
//                    + "			<SALTO-FIN> \n"
//                    + "			<CAJA_TEXTO> Contenido en la caja de texto <FIN-CAJA_TEXTO>\n"
//                    + "			\n"
//                    + "			\n"
//                    + "		<FIN-PANEL>\n"
                    + "	<FIN-CUERPO>\n"
                    + "<FIN-cHTML>\n"
                    + "");
        } catch (Exception ex) {
            Logger.getLogger(chtml.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR :OOOO = " + ex);
        }
    }
}
