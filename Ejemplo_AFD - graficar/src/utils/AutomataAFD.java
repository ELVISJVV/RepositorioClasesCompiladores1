package utils;


import java.nio.charset.StandardCharsets;
import java.util.*;
import java.nio.file.*;
import java.io.*;



public class AutomataAFD {
    private final String nombre;
    private final String inicial;
    private final Map<String, Map<String,String>> transiciones;
    private final Set<String> aceptacion;

    public AutomataAFD(String nombre, String inicial,
                       Map<String, Map<String,String>> transiciones,
                       Set<String> aceptacion){
        this.nombre = nombre; this.inicial = inicial; this.transiciones = transiciones; this.aceptacion = aceptacion;
    }

    public String getNombre(){ return nombre; }

    public boolean validarCadena(String w){
        String q = inicial; //estado inicial
        for(char c: w.toCharArray()){   //sirve para recorrer la cadena simbolo por simbolo (char)
            String a = String.valueOf(c);  //guardamos el simbolo actual
            Map<String,String> fila = transiciones.get(q);   // transiciones para el simbolo
            if (fila == null || !fila.containsKey(a)) return false;   //verificar que si contenga la transicion
            q = fila.get(a);  // actualizar el estado
        }
        return aceptacion.contains(q);    //verificar que si sea un estado de aceptacion
    }

    private Set<String> getAllStates(){
        Set<String> states = new LinkedHashSet<>();
        states.add(inicial);
        states.addAll(transiciones.keySet());
        for ( Map<String,String> fila : transiciones.values()) states.addAll(fila.keySet());
        return states;
    }

    // Entrada -> Transiciones
    // Origen -> Alfabeto -> Destino
    // S -> {"0" -> "A", "1" -> "A", "0" -> "X"}
    //Salida -> Aristas
    // Origen -> Destino -> Alfabeto
    // S -> {A -> [0,1], C [0]}


    private Map<String, Map<String, List<String>>> juntarAristas() {
        Map<String, Map<String, List<String>>> aristas = new LinkedHashMap<>();

        for (var entrada : transiciones.entrySet()) {
            String origen = entrada.getKey();  // origen actual
            // Es un map que va a guardar de la forma etiqueta -> Destino
            Map<String, String> destinos = entrada.getValue();

            // clave interna  = estadp de destino
            // valor interno = lista de las etiquetas origen -> destino
            if (!aristas.containsKey(origen)) aristas.put(origen, new LinkedHashMap<>());

            Map<String, List<String>> destinosOrigen = aristas.get(origen);

            for (var entradaEtiqueta : destinos.entrySet()) {
                String etiqueta = entradaEtiqueta.getKey();
                String destino = entradaEtiqueta.getValue();

                if (!destinosOrigen.containsKey(destino)) {
                    destinosOrigen.put(destino, new ArrayList<>());
                }
//
                destinosOrigen.get(destino).add(etiqueta);

            }

        }
            return aristas;

    }


    // Convertir dot a string

    public String ConvertirADot(){
        StringBuilder sb = new StringBuilder();

        sb.append("digraph ").append(nombre).append(" {\n");
        sb.append("rankdir=LR;\n");
        sb.append("node [shape=circle];\n");


        sb.append("  _ini [shape=point,label=\"\"];\n");
        sb.append(" _ini -> \"").append(inicial).append("\";\n");

        // Tenemos que iterar todos aquellos estados que sean de aceptacion para verlos de forma distintiva (circulo doble)
        for (String q : aceptacion) {
         sb.append("  \"").append(q).append("\" [shape=doublecircle];\n");
        }


        var edges = juntarAristas();

        for (var origen : edges.keySet()) {
            for ( var destino : edges.get(origen).keySet()) {

                List<String> simbolos = edges.get(origen).get(destino);

                String label = String.join(",", simbolos);
                sb.append("  \"").append(origen).append("\" -> \"").append(destino).append("\" [label=\"")
                        .append(label).append("\"];\n");
            }

        }
        sb.append("}\n");
        return sb.toString();
    }



    public Path generarDot(Path dotPath) throws IOException{

        Files.createDirectories(dotPath.getParent() == null ? Paths.get(".") : dotPath.getParent());
        Files.writeString(dotPath,ConvertirADot(), StandardCharsets.UTF_8);
        return dotPath;
    }


    public void exportarGrafica(Path outputWithoutExt)throws IOException, InterruptedException{
        Path dot = generarDot(outputWithoutExt.resolveSibling(outputWithoutExt.getFileName() + ".dot"));
        Process p = new ProcessBuilder("dot", "-Tpng", dot.toString(), "-o",
                outputWithoutExt.resolveSibling(outputWithoutExt.getFileName() + ".png").toString())
                .redirectErrorStream(true)
                .start();
        int code = p.waitFor();
        if (code != 0) {
            try (var r = p.inputReader()) {
                System.err.println("Graphviz 'dot' fallo con código " + code + ". Log:");

            }
        } else {
            System.out.println("Graficado: " + outputWithoutExt + ".png");
        }
    }


}
