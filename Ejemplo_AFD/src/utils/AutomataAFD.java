package utils;


import java.util.Map;
import java.util.Set;

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
}
