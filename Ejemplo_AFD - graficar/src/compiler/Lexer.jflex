package compiler;
import java_cup.runtime.*;

%%

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


ID    = [A-Za-z_][A-Za-z0-9_]*
STRCH = [^"\\\n\r] | \\["\\/bfnrt] | \\u[0-9A-Fa-f]{4}
STRING= \"({STRCH})*\"

%%



"("                                 { return new Symbol(sym.PA); }
")"                                 { return new Symbol(sym.PC); }
";"                                 { return new Symbol(sym.PTCOMA); }

{STRING}                            { String s=yytext(); return new Symbol(sym.CADENA, s.substring(1,s.length()-1)); }
{ID}                                { return new Symbol(sym.ID, yytext()); }



//------> Ingorados
[ \t\r\n\f ]     {/* Espacios en blanco se ignoran */}

//------> Errores Léxicos
.           	{System.out.println("Error Lexico: " + yytext() + " | Fila:" + yyline + " | Columna: " + yycolumn);
}

