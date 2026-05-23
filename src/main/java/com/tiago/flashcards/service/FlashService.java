package com.tiago.flashcards.service;

import com.tiago.flashcards.entity.FlashcardEntity;
import com.tiago.flashcards.repository.FlashRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashService {

    private final FlashRepository flashRepository;

    public FlashService(FlashRepository flashRepository) {
        this.flashRepository = flashRepository;
    }

    public List<FlashcardEntity> list () {
//        Sort sort = Sort.by("prioridade").ascending().and(
//                Sort.by("descricao").ascending()
//        );
//        return flashRepository.findAll(sort);
        return flashRepository.findAll();
    }

    public FlashcardEntity getById (Long id) {
        return flashRepository.getById(id);
    }

    public List<FlashcardEntity> create (FlashcardEntity flashcard) {
        flashRepository.save(flashcard);
        return this.list();
    }

    public List<FlashcardEntity> update (FlashcardEntity flashcard) {
        flashRepository.save(flashcard);
        return this.list();
    }

    public List<FlashcardEntity> delete (Long id) {
        flashRepository.deleteById(id);
        return this.list();
    }

    public void deleteAll () {
        flashRepository.deleteAll();
    }


}
