package com.tiago.flashcards.controller;

import com.tiago.flashcards.dto.DeckCreateRequest;
import com.tiago.flashcards.dto.DeckDto;
import com.tiago.flashcards.entity.DeckEntity;
import com.tiago.flashcards.repository.DeckRepository;
import com.tiago.flashcards.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/decks")
public class DeckController {

    @Autowired
    DeckService deckService;

    @PostMapping("/create")
    public DeckDto create(@RequestBody DeckCreateRequest deck) {
        DeckDto dto = new DeckDto();
        dto.setName(deck.getName());
        dto.setNumberOfCards(deck.getNumberOfCards());

        return deckService.create(dto);
    }

    @GetMapping("findById/{id}")
    public DeckDto findById(@PathVariable  Long id) {
        return deckService.findById(id);
    }
}
