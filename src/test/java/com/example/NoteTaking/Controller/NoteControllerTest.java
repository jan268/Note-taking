package com.example.NoteTaking.Controller;

import com.example.NoteTaking.DTO.NoteDto;
import com.example.NoteTaking.Model.Note;
import com.example.NoteTaking.Repository.NoteRepository;
import com.example.NoteTaking.Service.NoteTakingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class NoteControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private NoteTakingService noteTakingService;
    @Autowired
    private NoteController noteController;
    private TestRestTemplate testRestTemplate = new TestRestTemplate();
    private String CONTROLLER_PREFIX = "http://localhost:8080/note";
    private Long id;

    @BeforeEach
    void setUp() {
        Note msg = noteRepository.save(new Note("msg"));
        id = msg.getId() + 1;
        noteRepository.deleteAll();
    }


    @Test
    void view() {
        noteRepository.save(new Note("msg"));
        assertThat(this.testRestTemplate.getForObject(CONTROLLER_PREFIX + "/" + id, NoteDto.class))
                .isEqualTo(new NoteDto(id, "msg"));
    }

    @Test
    void viewAll() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(List.of(new NoteDto(id, "msg1"), new NoteDto(id+1, "msg2")));
        this.testRestTemplate.postForLocation(CONTROLLER_PREFIX + "/bulkSave",
                List.of(new NoteDto("msg1"), new NoteDto("msg2")));
        assertThat(this.testRestTemplate.getForObject(CONTROLLER_PREFIX + "/all", String.class))
                .isEqualTo(s);
    }

    @Test
    void save() {
        this.testRestTemplate.postForLocation(CONTROLLER_PREFIX + "/save", new NoteDto("msg1"));
        assertThat(new Note(id, "msg1")).isEqualTo(noteRepository.findById(id).get());
    }
    @Test
    void bulkSave() {
        this.testRestTemplate.postForLocation(CONTROLLER_PREFIX + "/bulkSave",
                List.of(new NoteDto("msg1"), new NoteDto("msg2")));
        assertThat(List.of(new Note(id, "msg1"), new Note(id+1, "msg2"))).isEqualTo(noteRepository.findAll());

    }
}