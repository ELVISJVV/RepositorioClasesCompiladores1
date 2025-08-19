package main;



import utils.ErrorClass;
import utils.Variable;
import utils.Token;

import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;



public class Main {

    public static LinkedList<ErrorClass> listaErrores = new LinkedList<>();
    public static LinkedList<Token> listaTokens = new LinkedList<>();
    public static HashMap<String, Variable> tablaSimbolos = new HashMap<>();

    public static void main(String[] args) {

        String entrada = " PROGRAM { LET INT hola = 3; OPERACION[hola+3*3]; OPERACION[hola-3]; }FIN";

        analizar(entrada);
        // Si quieres regenerar analizadores en runtime, descomenta:
        //analizadores("src/compiler/", "Lexer.jflex", "Parser.cup");

        for (ErrorClass error : listaErrores) {
            System.out.println(error);
        }
        for (Token token : listaTokens) {
            System.out.println(token);
        }

    }


    public static void analizar(String entrada) {
    listaErrores.clear();
    listaTokens.clear();


        try {
           compiler.Lexer lexer = new compiler.Lexer(new StringReader(entrada));
           compiler.Parser parser = new compiler.Parser(lexer);
            parser.parse();
        } catch (Exception e) {
            System.out.println("Error fatal en compilación de entrada.");
            e.printStackTrace();
        }
    }

    public static void analizadores(String ruta, String jflexFile, String cupFile) {
        try {
            // Orden correcto de argumentos para JFlex:
            String[] opcionesJflex = {"-d", ruta, ruta + jflexFile};
            jflex.Main.generate(opcionesJflex);

            String[] opcionesCup = {"-destdir", ruta, "-parser", "Parser", ruta + cupFile};
            java_cup.Main.main(opcionesCup);
        } catch (Exception e) {
            System.out.println("No se ha podido generar los analizadores");
            e.printStackTrace();
        }
    }
}
