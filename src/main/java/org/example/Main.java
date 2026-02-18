package org.example;

import org.example.model.Comentario;
import org.example.model.Post;
import org.bson.types.ObjectId;
import org.example.repository.PostRepository;
import util.MongoUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO BLOG (Twitter style) ---");

        PostRepository repo = new PostRepository();

        // 1. CREATE: Crear un post nuevo
        Post post1 = new Post();
        post1.setTitulo("Bienvenido a MongoDB");
        post1.setMensaje("Este es mi primer post usando Java y Mongo POJOs");
        post1.setFechaPublicacion(new Date());
        post1.setEtiquetas(Arrays.asList("java", "mongodb", "dam"));
        // Lista de comentarios vacía inicialmente

        repo.guardar(post1);
        ObjectId idPost = post1.getId(); // Guardamos el ID para usarlo luego

        // 2. READ: Listar todos
        System.out.println("\n--- LISTA DE TODOS LOS POSTS ---");
        List<Post> todos = repo.listarTodos();
        todos.forEach(System.out::println);

        // 3. UPDATE: Modificar título
        System.out.println("\n--- MODIFICANDO TÍTULO ---");
        repo.actualizarTitulo(idPost, "Bienvenido a MongoDB (Editado)");

        // 4. UPDATE: Añadir comentario (Embebido)
        System.out.println("\n--- AÑADIENDO COMENTARIO ---");
        repo.anadirComentario(idPost, new Comentario("¡Gran post! Muy útil."));
        repo.anadirComentario(idPost, new Comentario("Gracias por la info."));

        // Comprobamos los cambios mostrando el post específico
        System.out.println("Post tras actualizaciones:");
        repo.listarTodos().forEach(p -> {
            if(p.getId().equals(idPost)) System.out.println(p);
        });

        // 5. READ: Buscar por etiqueta
        System.out.println("\n--- BUSCANDO POR ETIQUETA 'java' ---");
        repo.listarPorEtiqueta("java").forEach(System.out::println);

        // 6. DELETE: Borrar el post
        System.out.println("\n--- ELIMINANDO POST ---");
        repo.eliminar(idPost);

        // Verificamos que está vacío
        System.out.println("Posts restantes: " + repo.listarTodos().size());

        // Cerrar conexión
        MongoUtil.close();
    }
}