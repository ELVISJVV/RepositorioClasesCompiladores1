package utils;

import java.util.*;
import java.nio.file.Paths;


public class RegistroAutomatas {
    private static final Map<String, AutomataAFD> automatas = new LinkedHashMap<>();

    private static void t(Map<String, Map<String,String>> transiciones, String from, String alfabeto, String to) {
        transiciones.computeIfAbsent(from, k -> new LinkedHashMap<>()).put(alfabeto, to);
    }

    static {
        //
        Map<String, Map<String,String>> transiciones = new LinkedHashMap<>();


        // S -> 1, A | 0, B ;
        t(transiciones, "S", "1", "A");
        t(transiciones, "S", "0", "B");

        // A -> 1, A | 0, B ;
        t(transiciones, "A", "1", "A");
        t(transiciones, "A", "0", "B");

        // B -> 0, C | 1, A ;
        t(transiciones, "B", "0", "C");
        t(transiciones, "B", "1", "A");

        // C -> 1, A ;
        t(transiciones, "C", "1", "A");

        // Inicial I = S; Aceptación = {B, C}
        AutomataAFD a = new AutomataAFD("AFD_1", "S", transiciones, Set.of("B", "C"));
        automatas.put(a.getNombre(), a);
    }

    public static void validar(String nombre, String cadena){
        AutomataAFD a = automatas.get(nombre);
        if (a == null){
            System.out.println("No existe el autómata: " + nombre);
            return;
        }
        boolean ok = a.validarCadena(cadena);
        System.out.println(nombre + "(\"" + cadena + "\") => " + (ok ? "Cadena Válida" : "Cadena Inválida"));
    }


    public static void graficar(String nombre, String rutaSinExt){

        AutomataAFD a = automatas.get(nombre);
        if (a == null){
            System.out.println("El automnata ingresado no existe: " + nombre);
        }

        System.out.println(a.ConvertirADot());
        try {
            a.exportarGrafica(java.nio.file.Paths.get(rutaSinExt));
        }catch (Exception e){
            System.out.println("Error al convertir ADot");
            e.printStackTrace();
        }
    }

}
