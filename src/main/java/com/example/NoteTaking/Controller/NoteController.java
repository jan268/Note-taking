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
        return service.view(id);
    }

    @GetMapping("/all")
    public List<NoteDto> viewAll() {
        return service.viewAll();
    }

    @PostMapping("/save")
    public Long save(@RequestBody NoteDto noteDto) {
        return service.save(noteDto);
    }
}
