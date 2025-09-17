package com.example;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.javalin.Javalin;

public class APITest {

    private static Javalin app;
    private static HttpClient client;
    private static String baseUri;

    // Setup and teardown methods 
    
    @BeforeAll   
    public static void setup() {
        app = Javalin.create().start(0); // A random free port
        new com.example.Controllers.NoteController().registerRoutes(app);
        client = HttpClient.newHttpClient();
        baseUri = "http://localhost:" + app.port();
    }

    @AfterAll  
    public static void teardown() {
        app.stop();
    }

    // Integrationtests for API endpoints

    @Test
    public void testGetAllNotes() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new java.net.URI(baseUri + "/api/notes/"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
    }

    @Test
    public void testGetNoteById() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new java.net.URI(baseUri + "/api/notes/3"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode()); 
    }

    /* 
    // Test API delete note (that still dosent have security)
    @Test
    public void testDeleteNote() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new java.net.URI(baseUri + "/api/notes/2"))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
    }
    */
}
