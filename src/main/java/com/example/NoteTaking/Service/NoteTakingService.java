package com.example.NoteTaking.Service;

import com.example.NoteTaking.DTO.NoteDto;
import com.example.NoteTaking.Model.Note;
import com.example.NoteTaking.Repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteTakingService {

    private final NoteRepository repository;

    public NoteDto view(Long id) {
        return repository.findById(id).map(note -> new NoteDto(note.getId(), note.getText())).orElse(null);
    }

    public List<NoteDto> viewAll() {
        return repository.findAll().stream().map(note -> new NoteDto(note.getId(), note.getText())).toList();
    }

    @Transactional
    public Long save(NoteDto noteDto) {
        return repository.save(new Note(noteDto.text())).getId();
    }

    @Transactional
    public List<Long> bulkSave(List<NoteDto> notes) {
        return repository.saveAll(notes.stream()
                        .map(note -> new Note(note.text()))
                        .toList())
                .stream()
                .map(Note::getId)
                .toList();
    }
}
