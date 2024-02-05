package com.example.NoteTaking.DTO;

public record NoteDto(Long id, String text) {
    public NoteDto(String text) {
        this(null, text);
    }
}
