package chtml;
import java_cup.runtime.Symbol;
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
Letra = [a-zA-Z_]|á|é|í|ó|ú
Digito = 0 | [1-9][0-9]* 

SinSaltos = [^\"|\r|\n]
SinSalto = [^'|\r|\n]
Saltos = \r|\n|\r\n\
Sin= [^\r|\n]

 
Comentario = "<//" [^/] ~"//>" | "<//" "/"+ "//>"

CadenaComillas = "\"" {SinSaltos}* ~"\"" |  “ {SinSaltos}* ~” |” {SinSaltos}* ~”
CadenaSinSaltos  = "saber" 




WHITE=[ \t\r\n]
%{
public String lexeme;

%}
%%
{WHITE} {/*Ignore*/}


<YYINITIAL>{
/* PALABRAS RESERVADAS */
"<chtml>" {return new Symbol(sym.chtml, yycolumn, yyline, new String(yytext()));}
"<fin-chtml>" {return new Symbol(sym.finchtml, yycolumn, yyline, new String(yytext()));}
"<encabezado>" {return new Symbol(sym.encabezado, yycolumn, yyline, new String(yytext()));}
"<fin-encabezado>" {return new Symbol(sym.finencabezado, yycolumn, yyline, new String(yytext()));}
"<cjs" {return new Symbol(sym.cjs, yycolumn, yyline, new String(yytext()));}
"><fin-cjs>" {return new Symbol(sym.fincjs, yycolumn, yyline, new String(yytext()));}
"<ccss" {return new Symbol(sym.ccss, yycolumn, yyline, new String(yytext()));}
"><fin-ccss>" {return new Symbol(sym.finccss, yycolumn, yyline, new String(yytext()));}
"ruta" {return new Symbol(sym.ruta, yycolumn, yyline, new String(yytext()));}
"fondo" {return new Symbol(sym.fondo, yycolumn, yyline, new String(yytext()));}
"<cuerpo" {return new Symbol(sym.cuerpo, yycolumn, yyline, new String(yytext()));}
"<fin-cuerpo>" {return new Symbol(sym.fincuerpo, yycolumn, yyline, new String(yytext()));}
"titulo" {return new Symbol(sym.titulo, yycolumn, yyline,new String(yytext()));}
"fin-titulo" {return new Symbol(sym.fintitulo, yycolumn, yyline,new String(yytext()));}
"panel" {return new Symbol(sym.panel, yycolumn, yyline,new String(yytext()));}
"fin-panel" {return new Symbol(sym.finpanel, yycolumn, yyline,new String(yytext()));}
"caja_texto" {return new Symbol(sym.cajatexto, yycolumn, yyline,new String(yytext()));}
"fin-caja_texto" {return new Symbol(sym.fincajatexto, yycolumn, yyline,new String(yytext()));}
"texto" {return new Symbol(sym.texto, yycolumn, yyline,new String(yytext()));}
"fin-texto" {return new Symbol(sym.fintexto, yycolumn, yyline,new String(yytext()));}
"click" {return new Symbol(sym.click, yycolumn, yyline, new String(yytext()));}
"imagen" {return new Symbol(sym.imagen, yycolumn, yyline, new String(yytext()));}
"fin-imagen" {return new Symbol(sym.finimagen, yycolumn, yyline, new String(yytext()));}
"boton" {return new Symbol(sym.boton, yycolumn, yyline, new String(yytext()));}
"fin-boton" {return new Symbol(sym.finboton, yycolumn, yyline, new String(yytext()));}
"enlace" {return new Symbol(sym.enlace, yycolumn, yyline,new String(yytext()));}
"fin-enlace" {return new Symbol(sym.finenlace, yycolumn, yyline,new String(yytext()));}
"tabla" {return new Symbol(sym.tabla, yycolumn, yyline,new String(yytext()));}
"fin-tabla" {return new Symbol(sym.fintabla, yycolumn, yyline,new String(yytext()));}
"fil_t" {return new Symbol(sym.filt, yycolumn, yyline,new String(yytext()));}
"fin-fil_t" {return new Symbol(sym.finfilt, yycolumn, yyline,new String(yytext()));}
"cb" {return new Symbol(sym.cb, yycolumn, yyline,new String(yytext()));}
"fin-cb" {return new Symbol(sym.fincb, yycolumn, yyline,new String(yytext()));}
"ct" {return new Symbol(sym.ct, yycolumn, yyline,new String(yytext()));}
"fin-ct" {return new Symbol(sym.finct, yycolumn, yyline,new String(yytext()));}
"texto_a" {return new Symbol(sym.textoa, yycolumn, yyline,new String(yytext()));}
"fin-texto_a" {return new Symbol(sym.fintextoa, yycolumn, yyline,new String(yytext()));}
"caja" {return new Symbol(sym.caja, yycolumn, yyline,new String(yytext()));}
"fin-caja" {return new Symbol(sym.fincaja, yycolumn, yyline,new String(yytext()));}
"opcion" {return new Symbol(sym.opcion, yycolumn, yyline,new String(yytext()));}
"fin-opcion" {return new Symbol(sym.finopcion, yycolumn, yyline,new String(yytext()));}
"valor" {return new Symbol(sym.valor, yycolumn, yyline,new String(yytext()));}
"spinner" {return new Symbol(sym.spinner, yycolumn, yyline,new String(yytext()));}
"fin-spinner" {return new Symbol(sym.finspinner, yycolumn, yyline,new String(yytext()));}
"salto-fin" {return new Symbol(sym.saltofin, yycolumn, yyline,new String(yytext()));}
"id" {return new Symbol(sym.idd, yycolumn, yyline,new String(yytext()));}
"grupo" {return new Symbol(sym.grupo, yycolumn, yyline,new String(yytext()));}
"alto" {return new Symbol(sym.alto, yycolumn, yyline,new String(yytext()));}
"ancho" {return new Symbol(sym.ancho, yycolumn, yyline,new String(yytext()));}
"alineado" {return new Symbol(sym.alineado, yycolumn, yyline,new String(yytext()));}
"(" {return new Symbol(sym.parentesisA, yycolumn, yyline,new String(yytext()));}
")" {return new Symbol(sym.parentesisC, yycolumn, yyline,new String(yytext()));}
";" {return new Symbol(sym.puntoComa, yycolumn, yyline,new String(yytext()));}
"=" {return new Symbol(sym.igual, yycolumn, yyline,new String(yytext()));}

}

{Letra}({Letra}|{Digito}|"_")* {lexeme=yytext(); return new Symbol(sym.id, yycolumn, yyline,new String(yytext()));}
{Digito}+ {lexeme=yytext(); return new Symbol(sym.entero, yycolumn, yyline,new String(yytext()));}
{Digito}+("."{Digito}+) {lexeme=yytext(); return new Symbol(sym.decimal, yycolumn, yyline,new String(yytext()));}
{Comentario} { }


{CadenaComillas} {lexeme=yytext(); return new Symbol(sym.cadena ,yycolumn, yyline,new String(yytext()));}
{CadenaSinSaltos} {lexeme=yytext(); return new Symbol(sym.cadenasin ,yycolumn, yyline,new String(yytext()));}
">" [^<] ~"<"  {lexeme=yytext(); return new Symbol(sym.cadenacon ,yycolumn, yyline,new String(yytext()));}


. {//return new Symbol(sym.ERROR, yycolumn, yyline,new String(yytext()));
//ejecutar.Errores.agregarError(yytext(), "Error Lexico", "No pertenece al lenguaje",0,0);
}

