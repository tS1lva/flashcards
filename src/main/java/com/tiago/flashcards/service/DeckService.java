package com.tiago.flashcards.service;

import com.tiago.flashcards.dto.DeckCreateRequest;
import com.tiago.flashcards.dto.DeckDto;
import com.tiago.flashcards.entity.DeckEntity;
import com.tiago.flashcards.repository.DeckRepository;
import org.springframework.stereotype.Service;

@Service
public class DeckService {
    private final DeckRepository deckRepository;

    public DeckService ( DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public DeckDto create (DeckDto deckDto) {
        DeckEntity deckEntity = new DeckEntity();
        deckEntity.setName(deckDto.getName());
        deckEntity.setNumberOfCards(deckDto.getNumberOfCards());
        deckRepository.save(deckEntity);

        return this.toDto(deckEntity);
    }

    public DeckDto findById(Long id) {
        DeckEntity deckEntity = deckRepository.findById(id).orElse(null);

        return toDto(deckEntity);
    }

    public void deleteAll() {
        deckRepository.deleteAll();
    }

    public DeckDto toDto(DeckEntity deck) {
        DeckDto deckDto = new DeckDto();
        deckDto.setId(deck.getId());
        deckDto.setName(deck.getName());
        deckDto.setNumberOfCards(deck.getNumberOfCards());
        return deckDto;
    }

    public DeckEntity toEntity(DeckDto deckDto) {
        DeckEntity deckEntity = new DeckEntity();
        deckEntity.setId(deckDto.getId());
        deckEntity.setName(deckDto.getName());
        deckEntity.setNumberOfCards(deckDto.getNumberOfCards());
        return deckEntity;
    }

}
