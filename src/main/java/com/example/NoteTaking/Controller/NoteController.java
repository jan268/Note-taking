package com.example.NoteTaking.Controller;

import com.example.NoteTaking.DTO.NoteDto;
import com.example.NoteTaking.Service.NoteTakingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(("/note"))
public class NoteController {

    @Autowired
    private final NoteTakingService service;

    @GetMapping("/{id}")
    public NoteDto view(@PathVariable Long id) {
        NoteDto view = service.view(id);
        System.out.println(view);
        return view;
    }

    @GetMapping("/all")
    public List<NoteDto> viewAll() {
        List<NoteDto> notes = service.viewAll();
        System.out.println(notes);
        return notes;
    }

    @PostMapping("/save")
    public Long save(@RequestBody NoteDto noteDto) {
        Long save = service.save(noteDto);
        System.out.println("I saved with id: " + save);
        return save;
    }

    @PostMapping("/bulkSave")
    public List<Long> bulkSave(@RequestBody List<NoteDto> notes) {
        List<Long> saved = service.bulkSave(notes);
        System.out.println("I saved with ids: " + saved);
        return saved;
    }
}
