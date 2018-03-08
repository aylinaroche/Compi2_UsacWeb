package cjs;

import static chtml.chtml.html;
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
        html.pilaArchivo.push("ArchivoPrueba.cjs");
        archivo4();
        // VariableCJS.imprimir();
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
        VariableCJS.imprimir();
    }

    public static void iniciar() {
        html.pilaArchivo.push("ArchivoPrueba");
        html.pilaAmbito.push("Global");
        html.nivelAmbito++;
        Recoleccion r = new Recoleccion();
        r.Recorrido(NODO);
        Recorrido c = new Recorrido();
        c.Recorrido(NODO);
        html.nivelAmbito--;
        html.pilaAmbito.pop();
    }

    public static void archivo1() {
        try {
            analizar(""
                    + "DimV NOMBRE: \"Henry\"; \n"
                    + "Nombre: \"Julia\";\n"
                    + "\n"
                    + "Dimv miVector : { 14 ,240 , 350 };\n"
                    + "Dimv nombres : {\"Hola\", \"Mundo\" , \"Otra\" , \"Vez\" };\n"
                    + "DimV nuevoVector : { \"Hola\" , \"Mundo\" , 1 , 2 , 3 };\n"
                    + "DimV vectorEstatico{3}; \n"
                    + "vectorEstatico : { \"H ola\" , \"Otravez\" , 250}; \n"
                    + "DimV miNumero: 4; \n"
                    + "DimV vector_miNumero{miNumero};\n"
                    + "vectorEstatico : { 51 , 200 , 40 , 56};\n"
                    + "DimV valorVector; \n"
                    + "Dimv miVector : { 14 ,240 , 350 }; \n"
                    + "'Modificación del vector en la primera posición \n"
                    + "miVector{0} : 100; \n"
                    + "'Obtener valor del vector\n"
                    + "valorVector : miVector{0}; \n"
                    + "miVector{2} : valorVector + miVector{1};\n"
                    + "\n"
                    + "DimV tamanioVector;\n"
                    + " 'Obtener el tamanio de la lista 'El tamanio de la lista es 3 \n"
                    + " tamanioVector : miVector.Conteo;\n"
                    + " \n"
                    + " DimV miCadena; \n"
                    + " DimV miLista : { \"hola\" , 1 , 2 , 3 , \"prueba\" }; \n"
                    + " MiCadena : miLista.aTexto();\n"
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
                    //                    + "Dimv a : \"31/10/1999\";"
                    //                    + "Dimv b : \"31/12/1999 23:59:50\";"
                    //                    + "Dimv c : \"Hola\";"
                    //                    + "$"
                    //                    + "Dimv d : 8;"
                    //                    + "Si( a << b){ \n"
                    //                    + "	imprimir(\"true1\"); \n"
                    //                    + "}"
                    //                    + "Si( c != 8 ){ \n"
                    //                    + "	imprimir(\"true2\"); \n"
                    //                    + "}"
                    //                    + "Si( c == c ){ \n"
                    //                    + "	imprimir(\"true3\"); \n"
                    //                    + "}"
                    + " "//falso
                    + ""//falso
                    + "" //falso
                    + "");
        } catch (Exception ex) {
            Logger.getLogger(cjs.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR :OOOO = " + ex);
        }
    }

    public static void archivo2() {
        try {
            analizar("Dimv miVector : { 14 ,240 , 350 }; \n"
                    + "Dimv nombres : {\"Hola\", \"Mundo\" , \"Otra\" , \"Vez\" }; \n"
                    + "DimV nuevoVector : { \"Hola\" , \"Mundo\" , 1 , 2 , 3 }; \n"
                    + "DimV vectorStatico{3}; \n"
                    + "DimV miNumero: 4;\n"
                    + "DimV tamanioVector;\n"
                    + "\n"
                    + "tamanioVector: miVector.conteo;\n"
                    + "\n"
                    + "miNumero : nuevoVector.conteo;\n"
                    + "\n"
                    + "imprimir(\"probando metodos vectores \\n\");\n"
                    + "\n"
                    + "imprimir(\"miVector: \" + tamanioVector + \"\\n\");\n"
                    + "imprimir(\"nuevoVector: \" + miNumero + \"\\n\");\n"
                    + "\n"
                    + "\n"
                    + "imprimir(\"PROBANDO ACCESO A VECTORES \\n\");\n"
                    + "\n"
                    + "imprimir(miVector{1} + \"\\n\");\n"
                    + "\n"
                    + "\n"
                    + "imprimir(\"resultado de miVector : \" + miVector.aTexto());\n"
                    + "\n"
                    + "si(tamanioVector < (miNumero * 5)){\n"
                    + "\n"
                    + "	imprimir(\"Estoy en el if 1\");\n"
                    + "	DimV nada:0;\n"
                    + "	imprimir(\"el while se debe de imprimir 1 vez\");\n"
                    + "	mientras(nada<tamanioVector){\n"
                    + "		imprimir(\"vuelta \" + nada);\n"
                    + "		imprimir(\"dentro del while probaremos el selecciona\");\n"
                    + "		selecciona(miNumero-1) {\n"
                    + "			caso 20:\n"
                    + "				imprimir(\"este caso 20 no es\");\n"
                    + "				detener;\n"
                    + "			caso 6:\n"
                    + "				imprimir(\"este caso 6  tampoco es\");\n"
                    + "				detener;\n"
                    + "			caso 4:\n"
                    + "				imprimir(\"este caso 4  si es, imprimo la posicion 4 de nuevoVector \" + nuevoVector{3});\n"
                    + "				imprimir(\"no le puse detener\");\n"
                    + "				para(dimv a:0 ; a<4;++){\n"
                    + "					imprimir(\"estoy en el para:\" + a);\n"
                    + "					si(a == 1){\n"
                    + "						imprimir(\"Quise imprimir la vuelta 1 otra vez\");\n"
                    + "\n"
                    + "					}sino{\n"
                    + "\n"
                    + "						imprimir(\"pues aca en el sino no pasa nada \");\n"
                    + "					}\n"
                    + "				}\n"
                    + "			defecto:\n"
                    + "				imprimir(\"Si funciono el seleccionar\");\n"
                    + "\n"
                    + "		}\n"
                    + "		imprimir(\"me sali del seleccionar\");\n"
                    + "		detener;\n"
                    + "		nada:nada+1;\n"
                    + "	}\n"
                    + "}"
                    + "");
        } catch (Exception e) {
        }
    }

    public static void archivo3() {
        try {
            analizar(""
                    + "Documento.Observador(\"listo\", mi_funcion()); \n"
                    + "\n"
                    + "Documento.Obtener(\"id_titulo\");\n"
                    + "\n"
                    + "\n"
                    + "Dimv mi_boton : Documento.Obtener(\"mi_boton\"); \n"
                    + "\n"
                    + "Mi_boton.setElemento(\"ruta\", \"C:/…\");\n"
                    + "\n"
                    + "Documento.Obtener(\"mi_imagen\").setElemento (\"ruta\",\"~/rutanueva\");\n"
                    + "\n"
                    + "Documento.Observador(\"listo\", funcion(){ \n"
                    + "	Imprimir(\"Documento listo\"); \n"
                    + "	});\n"
                    + "\n"
                    + "Dimv mi_boton : Documento.Obtener(\"mi_boton\"); \n"
                    + "funcion mi_funcion(){ \n"
                    + "	imprimir(\"Estas dentro de mi_funcion\"); \n"
                    + "} \n"
                    + "\n"
                    + "Mi_boton.Observador(\"clic\", mi_funcion());"
                    + "");
        } catch (Exception ex) {
            Logger.getLogger(cjs.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR :OOOO = " + ex);
        }
    }

    public static void archivo4() {
        try {
            analizar(""
                    //                    + "funcion valorMayor(x,y){\n"
                    //                    + "Dimv miVector : { 14 ,240 , 350 };\n"
                    //                    + "\n"
                    //                    + "	si(x>y){\n"
                    //                    + "		imprimir(x);\n"
                    //                    + "	}sino{\n"
                    //                    + "		imprimir(y);\n"
                    //                    + "	}\n"
                    //                    + "	retornar {5,6,7};\n"
                    //                    + "\n"
                    //                    + "}\n"
                    //                    + "\n"
                    //                    + "imprimir(\"La funcion es:\" +valorMayor(5,10).atexto());\n"
                    //                    + "\n"
                    //                    + "imprimir(valorMayor(10,5){1});\n"
                    //                    + "		"
                    //                    + ""
                    //                    + "funcion metodo8(){\n"
                    //                    + "     metodo9().setElemento(\"alto\",50); \n"
                    //                    + "}\n"
                    //                    + "\n"
                    //                    + "funcion metodo9(){\n"
                    //                    + "	DimV boton : Documento.Obtener(\"boton1\"); \n"
                    //                    + "	retornar boton;\n"
                    //                    + "}\n"
                    //                    + "\n"
                    //                    + "funcion metodo10(){\n"
                    //                    + "	retornar  Documento.Obtener(\"boton1\");\n"
                    //                    + "}"
                    + "dimv fecha1: \"31/12/1999\";\n"
                    + "dimv fecha2: \"02/02/2002\";\n"
                    + "dimv time1: \"31/12/1999 23:59:59\";\n"
                    + "dimv time2: \"30/11/1998 22:58:52\";\n"
                    + "dimv texto1: \"texto1\";\n"
                    + "dimv texto2: \"texto1\";\n"
                    + "dimv buleano1: true;\n"
                    + "dimv buleano2: false;\n"
                    + "dimv numerico1: 10;\n"
                    + "dimv numerico2: 20;\n"
//                    + "imprimir(\"suma\");\n"
//                    + "imprimir ( numerico1 + numerico1 );\n"
//                    + "imprimir ( numerico1 + buleano1 );\n"
//                    + "imprimir ( numerico1 + texto1 );\n"
//                    + "imprimir ( buleano1 + numerico1 );\n"
//                    + "imprimir ( buleano1 + buleano1 );\n"
//                    + "imprimir ( buleano1 + texto1 );\n"
//                    + "imprimir ( texto1 + numerico1 );\n"
//                    + "imprimir ( texto1 + buleano1 );\n"
//                    + "imprimir ( texto1 + texto1 );\n"
//                    + "imprimir ( texto1 + fecha1 );\n"
//                    + "imprimir ( numerico1 - numerico1 );\n"
//                    + "imprimir ( numerico1 - buleano1 );\n"
//                    + "imprimir ( buleano1 - numerico1 );\n"
//                    + "imprimir ( numerico1 * numerico1 );\n"
//                    + "imprimir ( numerico1 * buleano1 );\n"
//                    + "imprimir ( buleano1 * numerico1 );\n"
//                    + "imprimir ( buleano1 * buleano1 );\n"
//                    + "imprimir ( numerico1 / numerico1 );\n"
//                    + "imprimir ( numerico1 / buleano1 );\n"
//                    + "imprimir ( buleano1 / numerico1 );\n"
//                    + "imprimir ( numerico1 ^ numerico1 );\n"
//                    + "imprimir ( numerico1 ^ buleano1 );\n"
//                    + "imprimir ( buleano1 ^ numerico1 );\n"
//                    + "imprimir ( numerico1 % numerico1 );\n"
//                    + "imprimir ( numerico1 % buleano1 );\n"
//                    + "imprimir ( buleano1 % numerico1 );\n"
                      + "imprimir ( \"*******1\");\n"
//                    + "\n"
                    + "imprimir(\"todo esto es error\");\n"
                    + "imprimir ( numerico1 + fecha1 );\n"
                    + "imprimir ( buleano1 + fecha1 );\n"
                    + "imprimir ( fecha1 + numerico1 );\n"
                    + "imprimir ( fecha1 + buleano1 );\n"
                    + "imprimir ( fecha1 + texto1 );\n"
                    + "imprimir ( fecha1 + fecha1 );\n"
                    + "imprimir ( numerico1 - texto1 );\n"
                    + "imprimir ( numerico1 - fecha1 );\n"
                    + "imprimir ( buleano1 - buleano1 );\n"
                    + "imprimir ( buleano1 - texto1 );\n"
                    + "imprimir ( buleano1 - fecha1 );\n"
                    + "imprimir ( texto1 - numerico1 );\n"
                    + "imprimir ( texto1 - buleano1 );\n"
                    + "imprimir ( texto1 - texto1 );\n"
                    + "imprimir ( texto1 - fecha1 );\n"
                    + "imprimir ( fecha1 - numerico1 );\n"
                    + "imprimir ( fecha1 - buleano1 );\n"
                    + "imprimir ( fecha1 - texto1 );\n"
                    + "imprimir ( fecha1 - fecha1 );\n"
                    + "imprimir ( numerico1 * texto1 );\n"
                    + "imprimir ( numerico1 * fecha1 );\n"
                    + "imprimir ( buleano1 * texto1 );\n"
                    + "imprimir ( buleano1 * fecha1 );\n"
                    + "imprimir ( \"******2\");\n"
                    + "imprimir ( texto1 * numerico1 );\n"
                    + "imprimir ( texto1 * buleano1 );\n"
                    + "imprimir ( texto1 * texto1 );\n"
                    + "imprimir ( texto1 * fecha1 );\n"
                    + "imprimir ( fecha1 * numerico1 );\n"
                    + "imprimir ( fecha1 * buleano1 );\n"
                    + "imprimir ( fecha1 * texto1 );\n"
                    + "imprimir ( fecha1 * fecha1 );\n"
                    + "imprimir ( numerico1 / texto1 );\n"
                    + "imprimir ( numerico1 / fecha1 );\n"
                    + "imprimir ( buleano1 / buleano1 );\n"
                    + "imprimir ( buleano1 / texto1 );\n"
                    + "imprimir ( buleano1 / fecha1 );\n"
                    + "imprimir ( texto1 / numerico1 );\n"
                    + "imprimir ( texto1 / buleano1 );\n"
                    + "imprimir ( texto1 / texto1 );\n"
                    + "imprimir ( texto1 / fecha1 );\n"
                    + "imprimir ( fecha1 / numerico1 );\n"
                    + "imprimir ( fecha1 / buleano1 );\n"
                    + "imprimir ( fecha1 / texto1 );\n"
                    + "imprimir ( fecha1 / fecha1 );\n"
                    + "imprimir ( numerico1 ^ texto1 );\n"
                    + "imprimir ( numerico1 ^ fecha1 );\n"
                    + "imprimir ( buleano1 ^ buleano1 );\n"
                    + "imprimir ( buleano1 ^ texto1 );\n"
                    + "imprimir ( buleano1 ^ fecha1 );\n"
                    + "imprimir ( texto1 ^ numerico1 );\n"
                    + "imprimir ( texto1 ^ buleano1 );\n"
                    + "imprimir ( texto1 ^ texto1 );\n"
                    + "imprimir ( texto1 ^ fecha1 );\n"
                    + "imprimir ( fecha1 ^ numerico1 );\n"
                    + "imprimir ( fecha1 ^ buleano1 );\n"
                    + "imprimir ( fecha1 ^ texto1 );\n"
                    + "imprimir ( fecha1 ^ fecha1 );\n"
                    + "imprimir ( \"*******3\");\n"
                    + "imprimir ( numerico1 % texto1 );\n"
                    + "imprimir ( numerico1 % fecha1 );\n"
                    + "imprimir ( buleano1 % buleano1 );\n"
                    + "imprimir ( buleano1 % texto1 );\n"
                    + "imprimir ( buleano1 % fecha1 );\n"
                    + "imprimir ( texto1 % numerico1 );\n"
                    + "imprimir ( texto1 % buleano1 );\n"
                    + "imprimir ( texto1 % texto1 );\n"
                    + "imprimir ( texto1 % fecha1 );\n"
                    + "imprimir ( fecha1 % numerico1 );\n"
                    + "imprimir ( fecha1 % buleano1 );\n"
                    + "imprimir ( fecha1 % texto1 );\n"
                    + "imprimir ( fecha1 % fecha1 );\n"
                    + "imprimir(\"se recuper'o de errores\");"
                    +"");
        } catch (Exception ex) {
            Logger.getLogger(cjs.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR :OOOO = " + ex);
        }
    }
}
