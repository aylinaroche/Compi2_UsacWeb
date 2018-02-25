package ccss;
import java_cup.runtime.Symbol;
import usacweb.Errores;
%%
%class LexicoCCSS
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
Sin = [^\r|\n]
 
TiposComentarios = {MultilineaComentario} | {LineaComentario} 
MultilineaComentario   = "/*" [^/] ~"*/" | "/*" "/"+ "*/"
LineaComentario     = "//" {Sin}* {Saltos}? 

TiposCadenas = {CadenaSimple} | {CadenaDoble}
CadenaSimple  = "'"{SinSalto}* | '{SinSalto}* |‘{SinSalto}* ~’
CadenaDoble = "\"" {SinSaltos}* ~"\"" |  “ {SinSaltos}* ~” |” {SinSaltos}* ~”


WHITE=[ \t\r\n]
%{
public String lexeme;

%}
%%
{WHITE} {/*Ignore*/}


<YYINITIAL>{
/* PALABRAS RESERVADAS */
"texto" {return new Symbol(sym.texto, yycolumn, yyline, new String(yytext()));}
"alineado" {return new Symbol(sym.alineado, yycolumn, yyline, new String(yytext()));}
"izquierda" {return new Symbol(sym.izquierda, yycolumn, yyline, new String(yytext()));}
"derecha" {return new Symbol(sym.derecha, yycolumn, yyline, new String(yytext()));}
"centrado" {return new Symbol(sym.centrado, yycolumn, yyline, new String(yytext()));}
"justificado" {return new Symbol(sym.justificado, yycolumn, yyline, new String(yytext()));}
"formato" {return new Symbol(sym.formato, yycolumn, yyline, new String(yytext()));}
"negrilla" {return new Symbol(sym.negrilla, yycolumn, yyline, new String(yytext()));}
"cursiva" {return new Symbol(sym.cursiva, yycolumn, yyline,new String(yytext()));}
"mayuscula" {return new Symbol(sym.mayuscula, yycolumn, yyline,new String(yytext()));}
"minuscula" {return new Symbol(sym.minuscula, yycolumn, yyline,new String(yytext()));}
"capital-t" {return new Symbol(sym.capital, yycolumn, yyline,new String(yytext()));}
"letra" {return new Symbol(sym.letra, yycolumn, yyline,new String(yytext()));}
"tamtex" {return new Symbol(sym.tamtex, yycolumn, yyline,new String(yytext()));}
"fondoelemento" {return new Symbol(sym.fondo, yycolumn, yyline,new String(yytext()));}
"autoredimension" {return new Symbol(sym.auto, yycolumn, yyline,new String(yytext()));}
"visible" {return new Symbol(sym.visible, yycolumn, yyline,new String(yytext()));}
"horizontal" {return new Symbol(sym.horizontal, yycolumn, yyline,new String(yytext()));}
"vertical" {return new Symbol(sym.vertical, yycolumn, yyline,new String(yytext()));}
"false" {return new Symbol(sym.verdadero, yycolumn, yyline,new String(yytext()));}
"true" {return new Symbol(sym.falso, yycolumn, yyline,new String(yytext()));}
"borde" {return new Symbol(sym.borde, yycolumn, yyline,new String(yytext()));}
"opaque" {return new Symbol(sym.opaque, yycolumn, yyline,new String(yytext()));}
"colortext" {return new Symbol(sym.colortext, yycolumn, yyline,new String(yytext()));}
"grupo" {return new Symbol(sym.grupo, yycolumn, yyline,new String(yytext()));}
"id" {return new Symbol(sym.idd, yycolumn, yyline,new String(yytext()));}
"+" {return new Symbol(sym.mas, yycolumn, yyline, new String(yytext()));}
"*" {return new Symbol(sym.por, yycolumn, yyline,new String(yytext()));}
"-" {return new Symbol(sym.menos, yycolumn, yyline,new String(yytext()));}
"/" {return new Symbol(sym.dividido, yycolumn, yyline,new String(yytext()));}
":=" {return new Symbol(sym.igual, yycolumn, yyline,new String(yytext()));}
"(" {return new Symbol(sym.parentesisA, yycolumn, yyline,new String(yytext()));}
")" {return new Symbol(sym.parentesisC, yycolumn, yyline,new String(yytext()));}
":" {return new Symbol(sym.dosPuntos, yycolumn, yyline,new String(yytext()));}
"," {return new Symbol(sym.coma, yycolumn, yyline,new String(yytext()));}
"[" {return new Symbol(sym.corcheteA, yycolumn, yyline,new String(yytext()));}
"]" {return new Symbol(sym.corcheteC, yycolumn, yyline,new String(yytext()));}
"{" {return new Symbol(sym.llaveA, yycolumn, yyline,new String(yytext()));}
"}" {return new Symbol(sym.llaveC, yycolumn, yyline,new String(yytext()));}
"." {return new Symbol(sym.punto, yycolumn, yyline,new String(yytext()));}
";" {return new Symbol(sym.puntoComa, yycolumn, yyline, new String(yytext()));}
}
{Letra}({Letra}|{Digito}|"_")* {lexeme=yytext(); return new Symbol(sym.id, yycolumn, yyline,new String(yytext()));}
{Digito}+ {lexeme=yytext(); return new Symbol(sym.entero, yycolumn, yyline,new String(yytext()));}
{Digito}+("."{Digito}+) {lexeme=yytext(); return new Symbol(sym.decimal, yycolumn, yyline,new String(yytext()));}

{CadenaDoble} {lexeme=yytext(); return new Symbol(sym.cadena ,yycolumn, yyline,new String(yytext()));}
{CadenaSimple} {lexeme=yytext(); return new Symbol(sym.caracter ,yycolumn, yyline,new String(yytext()));}
{TiposComentarios} { }

. {
    Errores.agregarError("Error Lexico",yytext(), yyline,yycolumn);
}

