package cjs;
import java_cup.runtime.Symbol;
import usacweb.Errores;
%%
%class LexicoCJS
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
 

Fecha       = "'"{Digito}+"/"{Digito}+"/"{Digito}+"'"
TiposComentarios = {MultilineaComentario} | {LineaComentario} 
MultilineaComentario   = "'/" [^/] ~"/'" | "'/" "/"+ "/'"
LineaComentario     = "'" {Sin}* {Saltos}? 

TiposCadenas = {CadenaSimple} | {CadenaDoble}
CadenaDoble = "\"" {SinSaltos}* ~"\"" |  “ {SinSaltos}* ~” |” {SinSaltos}* ~”


WHITE=[ \t\r\n]
%{
public String lexeme;

%}
%%
{WHITE} {/*Ignore*/}


<YYINITIAL>{
/* PALABRAS RESERVADAS */
"dimv" {return new Symbol(sym.dimv, yycolumn, yyline, new String(yytext()));}
"atexto()" {return new Symbol(sym.atexto, yycolumn, yyline, new String(yytext()));}
"funcion" {return new Symbol(sym.funcion, yycolumn, yyline, new String(yytext()));}
"mensaje" {return new Symbol(sym.mensaje, yycolumn, yyline, new String(yytext()));}
"documento" {return new Symbol(sym.documento, yycolumn, yyline, new String(yytext()));}
"obtener" {return new Symbol(sym.obtener, yycolumn, yyline, new String(yytext()));}
"setElemento" {return new Symbol(sym.set, yycolumn, yyline, new String(yytext()));}
"observador" {return new Symbol(sym.observador, yycolumn, yyline, new String(yytext()));}
"retornar" {return new Symbol(sym.retornar, yycolumn, yyline,new String(yytext()));}
"Si" {return new Symbol(sym.si, yycolumn, yyline,new String(yytext()));}
"Sino" {return new Symbol(sym.sino, yycolumn, yyline,new String(yytext()));}
"Selecciona" {return new Symbol(sym.selecciona, yycolumn, yyline,new String(yytext()));}
"Caso" {return new Symbol(sym.caso, yycolumn, yyline,new String(yytext()));}
"Defecto" {return new Symbol(sym.defecto, yycolumn, yyline,new String(yytext()));}
"Mientras" {return new Symbol(sym.mientras, yycolumn, yyline,new String(yytext()));}
"Para" {return new Symbol(sym.para, yycolumn, yyline,new String(yytext()));}
"Detener" {return new Symbol(sym.detener, yycolumn, yyline,new String(yytext()));}
"Imprimir" {return new Symbol(sym.imprimir, yycolumn, yyline,new String(yytext()));}
"Conteo" {return new Symbol(sym.conteo, yycolumn, yyline,new String(yytext()));}
"'false'" {return new Symbol(sym.verdadero, yycolumn, yyline,new String(yytext()));}
"'true'" {return new Symbol(sym.falso, yycolumn, yyline,new String(yytext()));}
"+" {return new Symbol(sym.mas, yycolumn, yyline, new String(yytext()));}
"*" {return new Symbol(sym.por, yycolumn, yyline,new String(yytext()));}
"-" {return new Symbol(sym.menos, yycolumn, yyline,new String(yytext()));}
"/" {return new Symbol(sym.dividido, yycolumn, yyline,new String(yytext()));}
"^" {return new Symbol(sym.potencia, yycolumn, yyline,new String(yytext()));}
"%" {return new Symbol(sym.modulo, yycolumn, yyline,new String(yytext()));}
"--" {return new Symbol(sym.disminuir, yycolumn, yyline,new String(yytext()));}
"++" {return new Symbol(sym.aumentar, yycolumn, yyline,new String(yytext()));}
">" {return new Symbol(sym.mayor, yycolumn, yyline,new String(yytext()));}
"<" {return new Symbol(sym.menor, yycolumn, yyline,new String(yytext()));}
"<=" {return new Symbol(sym.menorIgual, yycolumn, yyline,new String(yytext()));}
">=" {return new Symbol(sym.mayorIgual, yycolumn, yyline,new String(yytext()));}
"=" {return new Symbol(sym.igual, yycolumn, yyline,new String(yytext()));}
"==" {return new Symbol(sym.igualDoble, yycolumn, yyline,new String(yytext()));}
"!=" {return new Symbol(sym.diferente, yycolumn, yyline,new String(yytext()));}
"(" {return new Symbol(sym.parentesisA, yycolumn, yyline,new String(yytext()));}
")" {return new Symbol(sym.parentesisC, yycolumn, yyline,new String(yytext()));}
"||" {return new Symbol(sym.or, yycolumn, yyline,new String(yytext()));}
"&&" {return new Symbol(sym.and, yycolumn, yyline,new String(yytext()));}
"!" {return new Symbol(sym.not, yycolumn, yyline,new String(yytext()));}
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

{Fecha} {lexeme=yytext(); return new Symbol(sym.fecha, yycolumn, yyline,new String(yytext()));}

{CadenaDoble} {lexeme=yytext(); return new Symbol(sym.cadena ,yycolumn, yyline,new String(yytext()));}
{TiposComentarios} { }



. {
    Errores.agregarError("Error Lexico",yytext(), yyline,yycolumn);
}

