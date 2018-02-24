package cjs;

import cjs.Ejecutar.Recoleccion;
import cjs.Ejecutar.Recorrido;
import cjs.Ejecutar.VariableCJS;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;

public class cjs {

    public static NodoCJS NODO;

    public static void main(String[] args) throws Exception {
        String path_Lexico = System.getProperty("user.dir").replace("\\", "/") + "/src/cjs/LexicoCJS.jflex";
//Genera el LEXICO
        File f = new File(path_Lexico);
        String[] Flex = new String[1];
        Flex[0] = System.getProperty("user.dir").replace("\\", "/") + "/src/cjs/LexicoCJS.jflex";//Sustituí por la direccion de tu .flex 
        jflex.Main.main(Flex);
//Genera el SINTACTICO
        String Params[] = new String[5];

        Params[0] = "-destdir";//habilita destino
        Params[1] = System.getProperty("user.dir").replace("\\", "/") + "/src/cjs";//destino
        Params[2] = "-parser";//habilita nombre
        Params[3] = "SintacticoCJS";//nombre
        Params[4] = System.getProperty("user.dir").replace("\\", "/") + "/src/cjs/SintacticoCJS.cup";//loc de .cup//loc de .cup

        try {
            java_cup.Main.main(Params);
        } catch (Exception e) {
            System.out.println(e);
        }
        archivo1();
        VariableCJS.imprimir();
    }

    public static void analizar(String texto) throws Exception {
        StringReader miReader = new StringReader(texto);
        LexicoCJS miAnalizador = new LexicoCJS(miReader);
        // VariableG.pilaAmbito.push(paradigmas.Atributos.nombreArchivo);

        SintacticoCJS parser = new SintacticoCJS(miAnalizador);
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
        usacweb.UsacWeb.pilaArchivo.push("ArchivoPrueba");
        VariableCJS.pilaAmbito.push("Global");
        VariableCJS.nivelAmbito++;
        Recoleccion r = new Recoleccion();
        r.Recorrido(NODO);
        //Recorrido c = new Recorrido();
       // c.Recorrido(NODO);
        VariableCJS.nivelAmbito--;
        VariableCJS.pilaAmbito.pop();
    }

    public static void archivo1() {
        try {
            analizar(""
                    //                    + "DimV NOMBRE: \"Henry\"; \n"
                    //                    + "Nombre: \"Julia\";\n"
                    //                    + "\n"
                    //                    + "Dimv miVector : { 14 ,240 , 350 };\n"
                    //                    + "Dimv nombres : {\"Hola\", \"Mundo\" , \"Otra\" , \"Vez\" };\n"
                    //                    + "DimV nuevoVector : { \"Hola\" , \"Mundo\" , 1 , 2 , 3 };\n"
                    //                    + "DimV vectorEstatico{3}; \n"
                    //                    + "vectorEstatico : { \"H ola\" , \"Otravez\" , 250}; \n"
                    //                    + "DimV miNumero: 4; \n"
                    //                    + "DimV vector_miNumero{miNumero};\n"
                    //                    + "vectorEstatico : { 51 , 200 , 40 , 56};\n"
                    //                    + "DimV valorVector; \n"
                    //                    + "Dimv miVector : { 14 ,240 , 350 }; \n"
                    //                    + "'Modificación del vector en la primera posición \n"
                    //                    + "miVector{0} : 100; \n"
                    //                    + "'Obtener valor del vector\n"
                    //                    + "valorVector : miVector{0}; \n"
                    //                    + "miVector{2} : valorVector + miVector{1};\n"
                    //                    + "\n"
                    //                    + "DimV tamanioVector;\n"
                    //                    + " 'Obtener el tamanio de la lista 'El tamanio de la lista es 3 \n"
                    //                    + " tamanioVector : miVector.Conteo;\n"
                    //                    + " \n"
                    //                    + " DimV miCadena; \n"
                    //                    + " DimV miLista : { \"hola\" , 1 , 2 , 3 , \"prueba\" }; \n"
                    //                    + " MiCadena : miLista.aTexto();\n"
                    //                    + " \n"
                    //                    + " DimV a:15; \n"
                    //                    + " DimV b; \n"
                    //                    + "Si( a <= 10 ){ \n"
                    //                    + "	b : 23; \n"
                    //                    + "}Sino{ \n"
                    //                    + "	b : 46; \n"
                    //                    + "}\n"
                    //                    + "\n"
                    //                    + "Selecciona ( a ){\n"
                    //                    + "	Caso 10 :\n"
                    //                    + "         b:0;"
                    //                     + " DimV i:5; \n"
                    //                    + "		Para( i : 0; i < 10; ++){\n"
                    //                    + "			b : b + 1; \n"
                    //                    + "		}\n"
                    //                    + "	Caso 15 : \n"
                    //                    + "		Mientras( a > 10 ){ \n"
                    //                    + "			a : a - 1; \n"
                    //                    + "			'detener;\n"
                    //                    + "		}\n"
                    //                    + "	Defecto :\n"
                    //                    + "		Imprimir(\"Hola mundo!\");\n"
                    //                    + "}\n"
                    //                    + "\n"
                    //                    + "funcion prueba(x){ \n"
                    //                    + "	'retornar x;\n"
                    //                    + "	Imprimir(\"El valor es = \" + x); \n"
                    //                    + "} \n"
                    //                    + "funcion prueba( x , y){ \n"
                    //                    + "	si(x > y){ \n"
                    //                    + "		Imprimir(\"Variable1 es mayor que Variable2\"); \n"
                    //                    + "	}sino{ \n"
                    //                    + "		Imprimir(\"Variable2 es mayor que Variable1\"); \n"
                    //                    + "	} \n"
                    //                    + "}\n"
                    //                    + " 'Llamada a funciones \n"
                    //                    + " Dimv v1;\n"
                    //                    + " Dimv v2; \n"
                    //                    + " v1: 10; \n"
                    //                    + " v2: 11; \n"
                    //                    + " prueba(v1); 'El valor es 10\n"
                    //                    + " prueba(v2); 'El valor es 11\n"
                    //                    + " prueba(v1,v2); 'Variable2 es mayor que Variable1\n"
                    + " \n"
                    //     + " Mensaje(\"Hola mundo\"); \n"
                    //                    + " Dimv X : 10;\n"
                    //                    + " Mensaje(X);\n"
                    //                    +                "\n"
                    //                    + "\n"
                    //                    + "Documento.Observador(\"listo\", mi_funcion(hola)); \n"
                    //                    + "Documento.Obtener(\"id_titulo\");\n"
                    //                    + "Dimv mi_boton : Documento.Obtener(\"mi_boton\"); \n"
                    //                    + "Mi_boton.setElemento(\"ruta\", \"C:/…\"); \n"
                    //                    + "Documento.Obtener(\"mi_imagen\").setElemento (\"ruta\",\"~/rutanueva\");\n"
                    //                    + "\n"
                    //                    + "Documento.Observador(\"listo\", funcion(){ \n"
                    //                    + "	Imprimir(\"Documento listo\"); \n"
                    //                    + "}); \n"
                    //                    + "\n"
                    //                    + "Dimv mi_boton : Documento.Obtener(\"mi_boton\");\n"
                    //                    + "\n"
                    //                    + "funcion mi_funcion(){ \n"
                    //                    + "	imprimir(\"Estas dentro de mi_funcion\"); \n"
                    //                    + "} \n"
                    //                    + "Mi_boton.Observador(\"clic\", mi_funcion());\n"
                    //                    + "\n"
                    //                    + "\n"
                    + ""
                    + "Dimv a : \"31/12/1999\";"
                    + "");
        } catch (Exception ex) {
            Logger.getLogger(cjs.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR :OOOO = " + ex);
        }
    }
}
