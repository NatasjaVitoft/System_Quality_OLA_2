package com.example;

import com.example.Controllers.NoteController;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.plugins.enableCors(cors -> cors.add(it -> it.anyHost())); // allow CORS
        }).start(8080);

        // Register controllers
        new NoteController().registerRoutes(app);

        System.out.println("✅ Javalin running at http://localhost:8080/api/notes");
    }
}
