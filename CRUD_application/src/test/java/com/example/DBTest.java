package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Services.NoteService;

public class DBTest {

    private static Connection conn;
    private static NoteService noteService;

    @BeforeAll
    public static void setup() throws Exception {

        conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();

        stmt.execute("CREATE TABLE user (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "username VARCHAR(100) NOT NULL UNIQUE, " +
                     "password VARCHAR(255) NOT NULL, " +
                     "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                     ");");

 
        stmt.execute("INSERT INTO user (username, password) VALUES ('test','test12345');");


        stmt.execute("CREATE TABLE note (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "user_id INTEGER NOT NULL, " +
                     "title VARCHAR(255) NOT NULL, " +
                     "content TEXT NOT NULL, " +
                     "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                     "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE" +
                     ");");

        noteService = new NoteService(conn);
    }
    
    @BeforeEach
    public void insertSampleNote() throws Exception {
        conn.createStatement().execute("DELETE FROM note");
        conn.createStatement().execute("INSERT INTO note (user_id, title, content) VALUES (1, 'TEST', 'TEST')");
    }

    @Test
    public void testGetNoteById() {
        Long noteId = 1L; 
        assert(noteService.getNoteById(noteId) != null);
    }

    @Test
    public void testGetAllNotes() {
        assert(!noteService.getAllNotes().isEmpty());
    }

    @Test
    public void deleteNote() {
        Long noteId = 1L; 
        noteService.deleteNoteById(noteId);
        assert(noteService.getNoteById(noteId) == null);
    }
}

