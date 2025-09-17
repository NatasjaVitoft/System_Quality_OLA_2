package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import org.junit.Test;

import com.example.Models.Note;
import com.example.Services.NoteService;

public class NoteServiceTest {

    @Test
    public void testNoteTitleNotEmpty() {
        Note note = new Note();
        note.setNote_title("Test");
        note.setNote_description("Test");
        assertNotNull(note.getNote_title());
    }


    // Test is note valid
    @Test
    public void testIsNoteValid() {
        NoteService noteService = new NoteService();
        Note note = new Note();
        note.setNote_title("");
        note.setNote_description("Test");

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        noteService.isNoteValid(note);
    });

    assertEquals("Title cannot be empty", exception.getMessage());
    }
}
