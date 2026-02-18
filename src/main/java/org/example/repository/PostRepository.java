package org.example.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.example.model.Comentario;
import org.example.model.Post;
import org.bson.types.ObjectId;
import util.MongoUtil;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class PostRepository {

    private MongoCollection<Post> collection;

    public PostRepository() {
        MongoDatabase db = MongoUtil.getDatabase();
        // Mapeamos la colección directamente a la clase Post
        this.collection = db.getCollection("posts", Post.class);
    }

    // CREATE: Insertar un post
    public void guardar(Post post) {
        collection.insertOne(post);
        System.out.println("Post guardado con ID: " + post.getId());
    }

    // READ: Listar todos
    public List<Post> listarTodos() {
        List<Post> posts = new ArrayList<>();
        collection.find().into(posts);
        return posts;
    }

    // READ: Listar por etiqueta (MongoDB busca automáticamente dentro del array)
    public List<Post> listarPorEtiqueta(String etiqueta) {
        List<Post> posts = new ArrayList<>();
        // eq("etiquetas", etiqueta) funciona aunque el campo sea un array
        collection.find(eq("etiquetas", etiqueta)).into(posts);
        return posts;
    }

    // UPDATE: Modificar un campo simple (Título)
    public void actualizarTitulo(ObjectId id, String nuevoTitulo) {
        collection.updateOne(
                eq("_id", id),
                Updates.set("titulo", nuevoTitulo)
        );
        System.out.println("Título actualizado.");
    }

    // UPDATE: Añadir un comentario (Documento embebido)
    // Usamos $push para añadir a la lista existente
    public void anadirComentario(ObjectId id, Comentario comentario) {
        collection.updateOne(
                eq("_id", id),
                Updates.push("comentarios", comentario)
        );
        System.out.println("Comentario añadido.");
    }

    // DELETE: Eliminar por ID
    public void eliminar(ObjectId id) {
        collection.deleteOne(eq("_id", id));
        System.out.println("Post eliminado.");
    }
}