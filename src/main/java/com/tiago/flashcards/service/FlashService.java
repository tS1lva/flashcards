package com.tiago.flashcards.service;

import com.tiago.flashcards.entity.FlashcardEntity;
import com.tiago.flashcards.repository.FlashRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

        //Setting initial card config
        flashcard.setRepetition(0);
        flashcard.setDifficult(2.5);
        flashcard.setInterval(0);
        flashcard.setCreatedAt(LocalDate.now());
        flashcard.setNextTime(LocalDate.now().plusDays(1));

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

    public List<FlashcardEntity> getCardsToReview() {
        List<FlashcardEntity> allFlashcards = this.list();
        List<FlashcardEntity> toBeReviewd = new ArrayList<>();

        LocalDate now = LocalDate.now();

        for (FlashcardEntity flashcard : allFlashcards) {
            LocalDate nextTimeReviewDate = flashcard.getNextTime();

            if (now.isAfter(nextTimeReviewDate)) {
                toBeReviewd.add(flashcard);
            }
        }

        if (toBeReviewd.isEmpty()) {
            System.out.println("Tudo em dia!");
            return new  ArrayList<>();

        } else {
            System.out.println("Temos " + toBeReviewd.size() + " flashcards para revisar!");
            return toBeReviewd;
        }
    }




}
