package utils;

public class Token {
    private String lexema;
    private String tipo;
    private int fila;
    private int columna;


    public Token(String lexema, String tipo, int fila, int columna) {
        this.lexema = lexema;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;

    }

    public String getLexema() {
        return lexema;
    }

    public String getTipo() {
        return tipo;
    }

    public int getColumna() {
        return columna;
    }

    public int getFila() {
        return fila;
    }

    @Override
    public String toString() {
        return "Token{" +
                "lexema='" + lexema + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fila=" + fila +
                ", columna=" + columna +
                '}';
    }
}
