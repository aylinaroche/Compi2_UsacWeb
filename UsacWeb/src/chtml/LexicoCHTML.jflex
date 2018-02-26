package chtml;
import java_cup.runtime.Symbol;
import usacweb.Datos;
%%
%class LexicoCHTML
%type Symbol
%public
%char
%line
%column
%ignorecase
%function next_token
%cup

chtml    = "<"[\ \t\n\r\f]*"CHTML"[\ \t\n\r\f]*">"
finchtml    = "<"[\ \t\n\r\f]*"FIN-CHTML"[\ \t\n\r\f]*">"
encabezado   = "<"[\ \t\n\r\f]*"ENCABEZADO"[\ \t\n\r\f]*">"
finencabezado   = "<"[\ \t\n\r\f]*"FIN-ENCABEZADO"[\ \t\n\r\f]*">"
cuerpo = "<"[\ \t\n\r\f]*"CUERPO"
fincuerpo = "<"[\ \t\n\r\f]*"FIN-CUERPO"[\ \t\n\r\f]*">"
cjs  = "<"[\ \t\n\r\f]*"CJS"
fincjs  = "<"[\ \t\n\r\f]*"FIN-CJS"[\ \t\n\r\f]*">" 
ccss = "<"[\ \t\n\r\f]*"CCSS"
finccss = "<"[\ \t\n\r\f]*"FIN-CCSS"[\ \t\n\r\f]*">"
titulo   = "<"[\ \t\n\r\f]*"TITULO"
fintitulo   = "FIN-TITULO"[\ \t\n\r\f]*">"
panel    = "<"[\ \t\n\r\f]*"PANEL"
finpanel    = "<"[\ \t\n\r\f]*"FIN-PANEL"[\ \t\n\r\f]*">"
texto    = "<"[\ \t\n\r\f]*"TEXTO"
fintexto    = "FIN-TEXTO"[\ \t\n\r\f]*">"
imagen      = "<"[\ \t\n\r\f]*"IMAGEN"
finimagen   = "FIN-IMAGEN"[\ \t\n\r\f]*">"
boton       = "<"[\ \t\n\r\f]*"BOTON"
finboton    = "FIN-BOTON"[\ \t\n\r\f]*">"
enlace      = "<"[\ \t\n\r\f]*"ENLACE"
finenlace   = "FIN-ENLACE"[\ \t\n\r\f]*">"
tabla       = "<"[\ \t\n\r\f]*"TABLA"
fintabla    = "<"[\ \t\n\r\f]*"FIN-TABLA"[\ \t\n\r\f]*">"
filt        = "<"[\ \t\n\r\f]*"FIL_T"[\ \t\n\r\f]*">"
finfilt     = "<"[\ \t\n\r\f]*"FIN-FIL_T"[\ \t\n\r\f]*">"
cb          = "<"[\ \t\n\r\f]*"CB"
fincb       = "FIN-CB"[\ \t\n\r\f]*">"
ct          = "<"[\ \t\n\r\f]*"CT"
finct       = "FIN-CT"[\ \t\n\r\f]*">"
textoa      = "<"[\ \t\n\r\f]*"TEXTO_A"
fintextoa   = "FIN-TEXTO_A"[\ \t\n\r\f]*">"
cajatexto   = "<"[\ \t\n\r\f]*"CAJA_TEXTO"
fincajatexto   = "FIN-CAJA_TEXTO"[\ \t\n\r\f]*">"
caja        = "<"[\ \t\n\r\f]*"CAJA"
fincaja     = "<"[\ \t\n\r\f]*"FIN-CAJA"[\ \t\n\r\f]*">"
opcion      = "<"[\ \t\n\r\f]*"OPCION"
finopcion   = "FIN-OPCION"[\ \t\n\r\f]*">"
spinner     = "<"[\ \t\n\r\f]*"SPINNER"
finspinner  = "FIN-SPINNER"[\ \t\n\r\f]*">"
saltofin    = "<"[\ \t\n\r\f]*"SALTO-FIN"[\ \t\n\r\f]*">"

Letra = [a-zA-Z_]|á|é|í|ó|ú
Digito = 0 | [1-9][0-9]* 
SinSaltos = [^\"|\r|\n]
SinSalto = [^'|\r|\n]
Saltos = \r|\n|\r\n\
Sin= [^\r|\n]
 
Comentario = "<//" [^/] ~"//>" | "<//" "/"+ "//>"

CadenaComillas = "\"" {SinSaltos}* ~"\"" |  “ {SinSaltos}* ~” |” {SinSaltos}* ~”
CadenaSinSaltos   = ">"[\ \t\n\r\f]*([A-Za-z]|[0-9]|[@]|[-]|[+]|[/]|[\\]|[*]|[=]|[.]|[,]|[{]|[}]|[']|[;]|[:])[^"\n"]*"<"
CadenaConSaltos  = ">"[\ \t\n\r\f]*([A-Za-z0-9]|[@]|[-]|[+]|[/]|[\\]|[*]|[=]|[.]|[,]|[{]|[}]|[']|[;]|[:])([^[<]]+([\n]))([^[<]]+)"<"


WHITE=[ \t\r\n]
%{
public String lexeme;

%}
%%
{WHITE} {/*Ignore*/}


<YYINITIAL>{
/* PALABRAS RESERVADAS */
{chtml} { return new Symbol(sym.chtml, yychar, yyline, yytext());}
{finchtml} { return new Symbol(sym.finchtml, yychar, yyline, yytext());}
{encabezado} { return new Symbol(sym.encabezado, yychar, yyline, yytext());}
{finencabezado} { return new Symbol(sym.finencabezado, yychar, yyline, yytext());}
{cuerpo} { return new Symbol(sym.cuerpo, yychar, yyline, yytext());}
{fincuerpo} { return new Symbol(sym.fincuerpo, yychar, yyline, yytext());}
{cjs} { return new Symbol(sym.cjs, yychar, yyline, yytext());}
{fincjs} { return new Symbol(sym.fincjs, yychar, yyline, yytext());}
{ccss} { return new Symbol(sym.ccss, yychar, yyline, yytext());}
{finccss} { return new Symbol(sym.finccss, yychar, yyline, yytext());}
{titulo} { return new Symbol(sym.titulo, yychar, yyline, yytext());}
{fintitulo} { return new Symbol(sym.fintitulo, yychar, yyline, yytext());}
{panel} { return new Symbol(sym.panel, yychar, yyline, yytext());}
{finpanel} { return new Symbol(sym.finpanel, yychar, yyline, yytext());}
{texto} { return new Symbol(sym.texto, yychar, yyline, yytext());}
{fintexto} { return new Symbol(sym.fintexto, yychar, yyline, yytext());}
{imagen} { return new Symbol(sym.imagen, yychar, yyline, yytext());}
{finimagen} { return new Symbol(sym.finimagen, yychar, yyline, yytext());}
{boton} { return new Symbol(sym.boton, yychar, yyline, yytext());}
{finboton} { return new Symbol(sym.finboton, yychar, yyline, yytext());}
{enlace} { return new Symbol(sym.enlace, yychar, yyline, yytext());}
{finenlace} { return new Symbol(sym.finenlace, yychar, yyline, yytext());}
{tabla} { return new Symbol(sym.tabla, yychar, yyline, yytext());}
{fintabla} { return new Symbol(sym.fintabla, yychar, yyline, yytext());}
{filt} { return new Symbol(sym.filt, yychar, yyline, yytext());}
{finfilt} { return new Symbol(sym.finfilt, yychar, yyline, yytext());}
{cb} { return new Symbol(sym.cb, yychar, yyline, yytext());}
{fincb} { return new Symbol(sym.fincb, yychar, yyline, yytext());}
{ct} { return new Symbol(sym.ct, yychar, yyline, yytext());}
{finct} { return new Symbol(sym.finct, yychar, yyline, yytext());}
{textoa} { return new Symbol(sym.textoa, yychar, yyline, yytext());}
{fintextoa} { return new Symbol(sym.fintextoa, yychar, yyline, yytext());}
{cajatexto} { return new Symbol(sym.cajatexto, yychar, yyline, yytext());}
{fincajatexto} { return new Symbol(sym.fincajatexto, yychar, yyline, yytext());}
{caja} { return new Symbol(sym.caja, yychar, yyline, yytext());}
{fincaja} { return new Symbol(sym.fincaja, yychar, yyline, yytext());}
{opcion} { return new Symbol(sym.opcion, yychar, yyline, yytext());}
{finopcion} { return new Symbol(sym.finopcion, yychar, yyline, yytext());}
{spinner} { return new Symbol(sym.spinner, yychar, yyline, yytext());}
{finspinner} { return new Symbol(sym.finspinner, yychar, yyline, yytext());}
{saltofin} { return new Symbol(sym.saltofin, yychar, yyline, yytext());}
"ruta" {return new Symbol(sym.ruta, yycolumn, yyline, new String(yytext()));}
"fondo" {return new Symbol(sym.fondo, yycolumn, yyline, new String(yytext()));}
"click" {return new Symbol(sym.click, yycolumn, yyline, new String(yytext()));}
"valor" {return new Symbol(sym.valor, yycolumn, yyline,new String(yytext()));}
"id" {return new Symbol(sym.idd, yycolumn, yyline,new String(yytext()));}
"grupo" {return new Symbol(sym.grupo, yycolumn, yyline,new String(yytext()));}
"alto" {return new Symbol(sym.alto, yycolumn, yyline,new String(yytext()));}
"ancho" {return new Symbol(sym.ancho, yycolumn, yyline,new String(yytext()));}
"alineado" {return new Symbol(sym.alineado, yycolumn, yyline,new String(yytext()));}
"(" {return new Symbol(sym.parentesisA, yycolumn, yyline,new String(yytext()));}
")" {return new Symbol(sym.parentesisC, yycolumn, yyline,new String(yytext()));}
";" {return new Symbol(sym.puntoComa, yycolumn, yyline,new String(yytext()));}
"=" {return new Symbol(sym.igual, yycolumn, yyline,new String(yytext()));}
">" {return new Symbol(sym.mayor, yycolumn, yyline,new String(yytext()));}
"<" {return new Symbol(sym.menor, yycolumn, yyline,new String(yytext()));}

}

{CadenaComillas} {lexeme=yytext(); return new Symbol(sym.cadena ,yycolumn, yyline,new String(yytext()));}
{CadenaSinSaltos} {lexeme=yytext(); return new Symbol(sym.cadenasin ,yycolumn, yyline,new String(yytext()));}
{CadenaConSaltos} {lexeme=yytext(); return new Symbol(sym.cadenacon, yychar, yyline, yytext());}

{Letra}({Letra}|{Digito}|"_")* {lexeme=yytext(); return new Symbol(sym.id, yycolumn, yyline,new String(yytext()));}
{Digito}+ {lexeme=yytext(); return new Symbol(sym.entero, yycolumn, yyline,new String(yytext()));}
{Digito}+("."{Digito}+) {lexeme=yytext(); return new Symbol(sym.decimal, yycolumn, yyline,new String(yytext()));}
{Comentario} { }



. {
    Datos.agregarError("Error Lexico",yytext(), yyline,yycolumn);
}

