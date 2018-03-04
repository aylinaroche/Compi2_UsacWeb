package chtml;

import chtml.Ejecutar.Recorrido;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import usacweb.HTML;

public class chtml {

    public static NodoCHTML NODO;
    public static HTML html = new HTML("Prueba");

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
        html.pilaArchivo.push("ArchivoPrueba.Html");
        archivo1();
    }

    public static Object analizar(String texto, HTML h) throws Exception {
        html = h;
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
            return null;
        }

        return iniciar();
    }

    public static Object analizar(String texto) throws Exception {
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
            return null;
        }

        return iniciar();
    }

    public static Object iniciar() {
        html.pilaArchivo.push("ArchivoPrueba");
        Recorrido r = new Recorrido();
        Object result = r.Recorrido(NODO);
        return result;
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
//                    + "		<PANEL fondo = \"red\";> "
//                    + "			<TEXTO>sdASD<FIN-TEXTO> \n"
//                    + "			<CAJA_TEXTO> Contenido en la caja de texto <FIN-CAJA_TEXTO> \n"
//                    + "			<PANEL>"
//                    + "				<TEXTO> Entrada: Esta es una etiqueta que permite saltos <FIN-TEXTO>\n"
//                    + "			<FIN-PANEL> \n"
//                    + "		<FIN-PANEL>\n"
                    + "		\n"
                    + "		<PANEL>\n"
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
                    + "			<TABLA> "
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
                    + "				<FIL_T> \n"
                    + "				<CT>Stark<FIN-CT> \n"
                    + "				<CT>Iron<FIN-CT> \n"
                    + "				<CT>50<FIN-CT> \n"
                    + "				<FIN-FIL_T> \n"
                    + "				<FIL_T> \n"
                    + "				<CT><BOTON>Enviar<FIN-BOTON><FIN-CT> \n"
//                    + "				<CT><BOTON> Enviar <FIN-BOTON><FIN-CT> \n"
//                    + "				<CT><BOTON> Enviar <FIN-BOTON><FIN-CT> \n"
                    + "				<FIN-FIL_T> \n"
               
                    + "			<FIN-TABLA>"
//                    + "			<TEXTO_A> Nombre<FIN-TEXTO_A>\n"
//                    + "			\n"
//                    + "			<CAJA_TEXTO> Contenido en la caja de texto <FIN-CAJA_TEXTO> \n"
//                    + "			<CAJA_TEXTO> <FIN-CAJA_TEXTO>"
//                    + "			<CAJA click=\"metodo()\";> "
//                    + "			<OPCION valor=\"1\";>Uno<FIN-OPCION> \n"
//                    + "			<OPCION valor=\"2\";>Dos<FIN-OPCION> \n"
//                    + "			<OPCION valor=\"3\";>Tres<FIN-OPCION> \n"
//                    + "			<OPCION valor=\"4\";>4<FIN-OPCION>\n" //verificar que acepte el 4
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
                    + "		<FIN-PANEL>\n"
                    + "	<FIN-CUERPO>\n"
                    + "<FIN-cHTML>\n"
                    + "");
        } catch (Exception ex) {
            Logger.getLogger(chtml.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR :OOOO = " + ex);
        }
    }

    public static void archivo2() {
        try {
            analizar(""
                    + "<//- Inicio CHTML \n"
                    + "	VERIFICAR COMILLAS\n"
                    + "-//> \n"
                    + "\n"
                    + "<CHTML> \n"
                    + "<//- Contenido encabezado -//> \n"
                    + "	<ENCABEZADO> \n"
                    + "		<CCSS ruta=\"C:\\Users\\Aroche\\Documents\\Archivos\\ArchivoCCSS1.ccss\";><FIN-CCSS> \n"
                    + "		<CJS ruta=\"C:\\Users\\Aroche\\Documents\\Archivos\\ArchivoCJS1.cjs\";><FIN-CJS> \n"
                    + "		<TITULO>PAGINA 1<FIN-TITULO> \n"
                    + "	<FIN-ENCABEZADO>\n"
                    + "\n"
                    + "<CUERPO> \n"
                    + "	<PANEL id = \"panel_1\"; grupo= \"panel_redireccion\"; ancho = \"716\"; alto = \"50\";> \n"
                    + "		<BOTON grupo = \"boton_redireccion\"; alto = \"35\"; ancho = \"120\"; ruta= \"C:\\proyecto1\\informacion.chtml\";>Informacion<FIN-BOTON>\n"
                    + "		<BOTON grupo = \"boton_redireccion\"; alto = \"35\"; ancho = \"160\"; ruta= \"C:\\proyecto1\\tabla.chtml\";>Tabla Estudiante<FIN-BOTON> \n"
                    + "		<BOTON grupo = \"boton_redireccion\"; alto = \"35\"; ancho = \"100\"; ruta= \"C:\\proyecto1\\index.chtml\";> Principal <FIN-BOTON> \n"
                    + "	<FIN-PANEL> \n"
                    + "	 <SALTO-FIN>\n"
                    + "\n"
                    + "	 <PANEL id = \"panel_2\" ; grupo=\"titulo\"; ancho = \"716\"; alto = \"50\";> \n"
                    + "		<//- esta linea contiene salto de linea en el texto -//> \n"
                    + "		<TEXTO id=\"titulo_doc\"; grupo= \"titulo\";>Calificacion Compiladores 2 \n"
                    + "		Proyecto 2<FIN-TEXTO> \n"
                    + "	 <FIN-PANEL> \n"
                    + "	 \n"
                    + "	 <//- Se agregaron tres saltos de linea -//> \n"
                    + "	 <SALTO-FIN> <SALTO-FIN> <SALTO-FIN>\n"
                    + "\n"
                    + "	<PANEL id = \"panel_3\"; grupo=\"titulo\"; ancho = \"716\"; alto = \"320\";> \n"
                    + "		<PANEL id = \"panel_4\"; alto =\"320\"; ancho=\"400\"; grupo=\"formulario_1\";> \n"
                    + "			<TEXTO alto= \"30\"; ancho =\"210\"; id=\"titulo_fomulario\"; grupo=\"formulario_1\";>Formulario de Notas:<FIN-TEXTO> \n"
                    + "			<SALTO-FIN>\n"
                    + "			<SALTO-FIN>\n"
                    + "			 <SALTO-FIN> \n"
                    + "			<TEXTO alto= \"16\"; ancho =\"90\"; grupo=\"formulario_1\";>Nota Proyecto 1 <FIN-TEXTO> \n"
                    + "			<SPINNER ancho = \"50\"; alto =\"25\"; id=\"nota_1\"; id=\"nota_1\"; grupo=\"formulario_2\";> <FIN-SPINNER> \n"
                    + "\n"
                    + "		<//- ingreso de espacios en blanco para las separaciones entre etiquetas -//>\n"
                    + "		 <TEXTO> <FIN-TEXTO> \n"
                    //                    + "		 <TEXTO alto= \"16\"; ancho =\"90\"; grupo=\"formulario_1\";>Nota Proyecto 2: <FIN-TEXTO> \n"
                    //                    + "		 <SPINNER ancho = \"50\"; alto =\"25\"; id=\"nota_2\"; id=\"nota_2\"; grupo=\"formulario_2\";> <FIN-SPINNER> \n"
                    //                    + "		 \n"
                    //                    + "		 <//- se agregó dos salto de linea entre elementos -//> \n"
                    //                    + "		 <SALTO-FIN> <SALTO-FIN> \n"
                    //                    + "		 <TEXTO alto= \"16\"; ancho =\"105\"; grupo=\"formulario_1\";>Nombre Estudiante:<FIN-TEXTO> \n"
                    //                    + "		 <CAJA_TEXTO alto= \"20\"; ancho =\"200\"; id=\"nombre\"; grupo=\"formulario_2\";>Nombre<FIN-CAJA_TEXTO> \n"
                    //                    + "		 <SALTO-FIN> \n"
                    //                    + "		 <TEXTO alto= \"16\"; ancho =\"65\"; grupo=\"formulario_1\";>Comentario:<FIN-TEXTO> \n"
                    //                    + "		 <TEXTO_A alto=\"95\"; ancho=\"200\" id=\"comentario\"; grupo=\"formulario_2\";><FIN-TEXTO_A> \n"
                    //                    + "		 <SALTO-FIN> \n"
                    //                    + "		 <CAJA id=\"caja_1\"; grupo=\"formulario_2\";> \n"
                    //                    + "			<OPCION valor= \"promedio\"; >Promedio<FIN-OPCION> \n"
                    //                    + "			 <OPCION valor= \"suma\"; >Suma<FIN-OPCION> \n"
                    //                    + "			 <OPCION valor= \"resta\"; >Resta<FIN-OPCION> \n"
                    //                    + "			 <OPCION valor= \"multiplicar\"; >Multiplicacion<FIN-OPCION> \n"
                    //                    + "			 <OPCION valor= \"7\"; >Modulo P1 % 7<FIN-OPCION> \n"
                    //                    + "			 <OPCION valor= \"11\"; >Modulo P2 % 11<FIN-OPCION> <FIN-CAJA> \n"
                    //                    + "		 <BOTON click=\"operacion_datos()\"; alto= \"30\"; ancho =\"210\"; id=\"boto_cal\"; grupo=\"formulario_2\";> Calcular <FIN-BOTON>\n"
                    + "		<FIN-PANEL> \n"
                    + "	 <TEXTO> <FIN-TEXTO>\n"
                    + "	 <FIN-PANEL> \n"
                    + "<FIN-CUERPO>\n"
                    + "<FIN-CHTML>"
                    + "");
        } catch (Exception e) {
        }
    }
}
