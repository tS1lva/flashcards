package com.tiago.flashcards.controller;


import com.tiago.flashcards.dto.FlashCreateRequest;
import com.tiago.flashcards.dto.FlashDto;
import com.tiago.flashcards.entity.FlashcardEntity;
import com.tiago.flashcards.service.FlashService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<List<FlashDto>> list() {
        return ResponseEntity.ok(flashService.list());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<FlashDto> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(flashService.getById(id));
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/create")
    public ResponseEntity<List<FlashDto>> create(@RequestBody FlashCreateRequest flashCreateRequest) {
        try {
            return ResponseEntity.ok(flashService.create(flashCreateRequest));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<List<FlashDto>> update(@PathVariable Long id, @RequestBody FlashCreateRequest flashcard) {
        try {
            return ResponseEntity.ok(flashService.update(id, flashcard));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<FlashDto>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(flashService.delete(id));
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        flashService.deleteAll();
    }

    @GetMapping("/getCardsToReview")
    public ResponseEntity<List<FlashDto>> getCardsToReview() {
        return ResponseEntity.ok(flashService.getCardsToReview());
    }

    @PutMapping("/reviewFlashcardById/{id}/{score}")
    public void reviewFlashcardById(@PathVariable Long id, @PathVariable int score) {
        flashService.reviewFlashcardById(id, score);
    }
}
