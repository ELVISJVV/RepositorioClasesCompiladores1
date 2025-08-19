package utils;

public class Variable {
    private String cadena;
    private String nombre;
    private String tipo;
    private int Fila;
    private int Columna;

    public Variable( String nombre, String tipo,String cadena, int fila, int columna) {
        this.cadena = cadena;
        this.nombre = nombre;
        this.tipo = tipo;
        Fila = fila;
        Columna = columna;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFila() {
        return Fila;
    }

    public void setFila(int fila) {
        Fila = fila;
    }

    public int getColumna() {
        return Columna;
    }

    public void setColumna(int columna) {
        Columna = columna;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



}
