package com.tiago.flashcards.controller;


import com.tiago.flashcards.entity.FlashcardEntity;
import com.tiago.flashcards.service.FlashService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flashcards")
public class FlashController {
    private FlashService flashService;

    public FlashController(FlashService flashService) {
        this.flashService = flashService;
    }

    @GetMapping("/list")
    public List<FlashcardEntity> list() {
        return flashService.list();
    }

    @GetMapping("/getById/{id}")
    public FlashcardEntity getById(@PathVariable Long id) {
        return flashService.getById(id);
    }

    @PostMapping("/create")
    public List<FlashcardEntity> create(@RequestBody FlashcardEntity flashcard) {
        return flashService.create(flashcard);
    }

    @PutMapping("/update")
    public List<FlashcardEntity> update(FlashcardEntity flashcard) {
        return flashService.update(flashcard);
    }

    @DeleteMapping("/delete/{id}")
    public List<FlashcardEntity> delete(@PathVariable Long id) {
        return flashService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        flashService.deleteAll();
    }

    @GetMapping("/review")
    public List<FlashcardEntity> review() {
        return flashService.review();
    }
}
