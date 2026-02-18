package org.example.model;

import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
    private ObjectId id; // MongoDB lo gestiona automáticamente
    private String titulo;
    private String mensaje;
    private Date fechaPublicacion;

    // Arrays / Listas
    private List<String> etiquetas;

    // Documentos embebidos
    private List<Comentario> comentarios;

    // Constructor vacío obligatorio
    public Post() {
        // Inicializamos las listas para evitar NullPointerException al añadir datos en Java
        this.etiquetas = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    // Getters y Setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public Date getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(Date fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

    public List<String> getEtiquetas() { return etiquetas; }
    public void setEtiquetas(List<String> etiquetas) { this.etiquetas = etiquetas; }

    public List<Comentario> getComentarios() { return comentarios; }
    public void setComentarios(List<Comentario> comentarios) { this.comentarios = comentarios; }

    @Override
    public String toString() {
        return "\nPost {" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", etiquetas=" + etiquetas +
                ", comentarios=" + comentarios +
                '}';
    }
}