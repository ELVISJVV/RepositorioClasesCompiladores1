package utils;

public class ErrorClass {

    private String tipo;
    private String lexema;
    private int fila;
    private int columna;

    public ErrorClass(String tipo, String lexema, int fila, int columna) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.fila = fila;
        this.columna = columna;

    }

    public String getTipo() {
        return tipo;
    }

    public String getLexma() {
        return lexema;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    @Override
    public String toString() {
        return "Error: " + tipo + " en la línea " + fila + " y columna " + columna + " con el lexema " + lexema;
    }
}
