package com.example.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Models.Note;

public class NoteService {

    // Connection

    private Connection conn;

    public NoteService() {
        // For testing purposes
    }

    public NoteService(Connection conn) {
        this.conn = conn;
    }

    // Crud operations for Note entity would go here

    // CREATE
    public void createNote(Note note, Long userId) {
        
        isNoteValid(note); 

        String SQL = "INSERT INTO note (user_id, title, content) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setLong(1, userId); 
            pstmt.setString(2, note.getNote_title());
            pstmt.setString(3, note.getNote_description());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // READ
    public Note getNoteById(Long id) {
        String SQL = "SELECT * FROM note WHERE id = ?";
        Note note = null;

        try(PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                note = new Note(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getTimestamp("created_at")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return note;
    }


    // Validate note is not empty

    public void isNoteValid(Note note) {
        if (note.getNote_title() == null || note.getNote_title().trim().isEmpty()) {
            throw new IllegalArgumentException("Title is empty");
        }
        if (note.getNote_description() == null || note.getNote_description().trim().isEmpty()) {
            throw new IllegalArgumentException("Description is empty");
        }
    }

    // UPDATE

    public void updateNote(Note note) {
        
        isNoteValid(note);

        String SQL = "UPDATE note SET title = ?, content = ? WHERE id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, note.getNote_title());
            pstmt.setString(2, note.getNote_description());
            pstmt.setLong(3, note.getNote_id());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // DELETE
    public void deleteNoteById(Long id) {
        String SQL = "DELETE FROM note WHERE id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // GET ALL NODES

    public List<Note> getAllNotes() {
        String SQL = "SELECT * FROM note";
        List<Note> notes = new ArrayList<>();

        try(PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Note note = new Note(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getTimestamp("created_at")
                );
                notes.add(note);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return notes;
    }
}
