package org.example.model;

public class Comentario {
    private String contenido;

    // Constructor vacío obligatorio para el POJO Codec
    public Comentario() {}

    public Comentario(String contenido) {
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Comentario{contenido='" + contenido + "'}";
    }
}