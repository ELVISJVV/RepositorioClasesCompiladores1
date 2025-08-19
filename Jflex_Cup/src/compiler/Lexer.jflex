
// ------------  Paquete e importaciones ------------
package compiler;

import java_cup.runtime.*;

%%


//-------> Directivas (No tocar)

%public
%class Lexer
%cup
%char
%column
%line
%unicode
%ignorecase

%{
%}

// ------> Expresiones Regulares
entero = [0-9]+
letra = [a-zA-Z]+
id ={letra}({letra}|{entero})*


%%

// Palabras reservadas
"PROGRAM"   { main.Main.listaTokens.add( new utils.Token( yytext(), "Palabra Reservada", yyline, yycolumn ));
                return new Symbol(sym.PROGRAM,   yyline, yycolumn, yytext());
             }
"FIN"       { main.Main.listaTokens.add( new utils.Token( yytext(), "Palabra Reservada", yyline, yycolumn ));
return new Symbol(sym.FIN,       yyline, yycolumn, yytext()); }
"OPERACION" { main.Main.listaTokens.add( new utils.Token( yytext(), "Palabra Reservada", yyline, yycolumn ));
return new Symbol(sym.OPERACION, yyline, yycolumn, yytext()); }
"LET" { main.Main.listaTokens.add( new utils.Token( yytext(), "Palabra Reservada", yyline, yycolumn ));
return new Symbol(sym.LET, yyline, yycolumn, yytext()); }
"INT" { main.Main.listaTokens.add( new utils.Token( yytext(), "Palabra Reservada", yyline, yycolumn ));
return new Symbol(sym.INT, yyline, yycolumn, yytext()); }

// Símbolos
";" {
 main.Main.listaTokens.add( new utils.Token( yytext(), "Símbolos", yyline, yycolumn ));
 return new Symbol(sym.PTCOMA, yyline, yycolumn, yytext()); }

"[" {
main.Main.listaTokens.add( new utils.Token( yytext(), "Símbolos", yyline, yycolumn ));
return new Symbol(sym.CA,     yyline, yycolumn, yytext()); }
"]" {main.Main.listaTokens.add( new utils.Token( yytext(), "Símbolos", yyline, yycolumn ));
 return new Symbol(sym.CC,     yyline, yycolumn, yytext()); }

"{" {
 main.Main.listaTokens.add( new utils.Token( yytext(), "Símbolos", yyline, yycolumn ));
 return new Symbol(sym.LLA,    yyline, yycolumn, yytext()); }

"}" {
 main.Main.listaTokens.add( new utils.Token( yytext(), "Símbolos", yyline, yycolumn ));
 return new Symbol(sym.LLC,    yyline, yycolumn, yytext()); }


// Operadores
"+" {
  main.Main.listaTokens.add( new utils.Token( yytext(), "Símbolos", yyline, yycolumn ));
 return new Symbol(sym.MAS,    yyline, yycolumn, yytext()); }

"*" { return new Symbol(sym.POR,    yyline, yycolumn, yytext()); }
"-" { return new Symbol(sym.MENOS,  yyline, yycolumn, yytext()); }
"/" { return new Symbol(sym.DIVIDO, yyline, yycolumn, yytext()); }
"=" { return new Symbol(sym.IGUAL, yyline, yycolumn, yytext()); }

// Tokens léxicos
{entero} {
 main.Main.listaTokens.add( new utils.Token( yytext(), "Entero", yyline, yycolumn ));
return new Symbol(sym.ENTERO, yyline, yycolumn, yytext()); }
{id} {
 main.Main.listaTokens.add( new utils.Token( yytext(), "Entero", yyline, yycolumn ));
return new Symbol(sym.ID, yyline, yycolumn, yytext()); }

// Ignorados
[ \t\r\n\f ] { /* espacios en blanco */ }


//------> Errores Léxicos
.           	{ main.Main.listaErrores.add(new utils.ErrorClass("Léxico", yytext(), yyline, yycolumn));
//                System.out.println("Error Lexico: " + yytext() + " | Fila:" + yyline + " | Columna: " + yycolumn);
}