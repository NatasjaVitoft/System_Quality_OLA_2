package com.example.Controllers;

import java.util.List;

import com.example.Models.Note;
import com.example.Services.AuthenticationService;
import com.example.Services.NoteService;

import io.javalin.Javalin;

public class NoteController {

    private final NoteService noteService = new NoteService();
    private final AuthenticationService authenticationService = new AuthenticationService();

    // This method will register all endpoints on the app
    public void registerRoutes(Javalin app) {

        // Create Note
        app.post("/api/notes", ctx -> {

            Long userId = authenticationService.authenticateUser(ctx);
            if(userId == null) return;

            Note note = ctx.bodyAsClass(Note.class);
            noteService.createNote(note, userId);
            ctx.status(201).json("{\"message\":\"Note created\"}");
        });

        // Get all notes
        app.get("/api/notes", ctx -> {
            List<Note> notes = noteService.getAllNotes();
            ctx.json(notes);
        });

        // Get note by ID
        app.get("/api/notes/{id}", ctx -> {
            Long id = Long.valueOf(ctx.pathParam("id"));
            Note note = noteService.getNoteById(id);
            if (note != null) {
                ctx.json(note);
            } else {
                ctx.status(404).json("{\"error\":\"Note not found\"}");
            }
        });

        // Update note
        app.put("/api/notes/{id}", ctx -> {
            Long id = Long.valueOf(ctx.pathParam("id"));
            Note note = ctx.bodyAsClass(Note.class);
            note.setNote_id(id);
            noteService.updateNote(note);
            ctx.json("{\"message\":\"Note updated\"}");
        });

        // Delete note
        app.delete("/api/notes/{id}", ctx -> {
            Long id = Long.valueOf(ctx.pathParam("id"));
            noteService.deleteNoteById(id);
            ctx.json("{\"message\":\"Note deleted\"}");
        });
    }
}

