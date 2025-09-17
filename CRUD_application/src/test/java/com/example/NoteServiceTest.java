package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.example.Models.Note;
import com.example.Services.NoteService;

public class NoteServiceTest {

    // One edge case test that i could think of in the NoteService class

    @Test
    public void testIsNoteValid() {
        NoteService noteService = new NoteService();
        Note note = new Note();
        note.setNote_title("");
        note.setNote_description("Test");

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        noteService.isNoteValid(note);
    });

    assertEquals("Title is empty", exception.getMessage());
    } 
}
