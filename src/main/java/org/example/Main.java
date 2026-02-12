package org.example;

import org.example.model.Post;
import org.example.repository.PostRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PostRepository pRepo = new PostRepository();

        Post p1 = new Post();
        p1.setTitulo("Hello world");
        p1.setMensaje("Mi primer post");

        pRepo.addPost(p1);
    }
}