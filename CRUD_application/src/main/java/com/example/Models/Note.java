package com.example.Models;

import java.sql.Timestamp;

public class Note {
    
    private Long note_id;
    private String note_title;
    private String note_description;
    private Timestamp created_at;

    public Note() {

    }

    public Note(Long note_id, String note_title, String note_description, Timestamp created_at) {
        this.note_id = note_id;
        this.note_title = note_title;
        this.note_description = note_description;
        this.created_at = created_at;
    }

    public Long getNote_id() {
        return note_id;
    }

    public void setNote_id(Long note_id) {
        this.note_id = note_id;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNote_description() {
        return note_description;
    }

    public void setNote_description(String note_description) {
        this.note_description = note_description;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
