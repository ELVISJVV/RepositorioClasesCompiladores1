package main;




import java.io.StringReader;



public class Main {


    public static void main(String[] args) {

        String entrada = "AFD_1(\"100\");";
        // 0 10 110  00 100
        // 1 11 101 1001
        //
        //       | E | 0 | 1|
        //       | S | B  | A  |
        //       | A | B  | A  |
        //       | B | C  | A  |
        //       | C |  -  | A  |



        analizar(entrada);
                // Si quieres regenerar analizadores en runtime, descomenta:
                //analizadores("src/compiler/", "Lexer.jflex", "Parser.cup");

            }


            public static void analizar(String entrada) {

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
