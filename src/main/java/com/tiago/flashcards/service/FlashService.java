package com.tiago.flashcards.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tiago.flashcards.entity.FlashcardEntity;
import com.tiago.flashcards.repository.FlashRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public List<FlashcardEntity> review () {
        List<FlashcardEntity> allFlashcards = this.list();
        List<FlashcardEntity> toBeReviewd = this.list();
        toBeReviewd.clear();

        LocalDate actual = LocalDate.now();
        int actualDay = actual.getDayOfMonth();
        int actualMonth = actual.getMonthValue();
        int actualYear = actual.getYear();

        LocalDate a = LocalDate.of(actualYear, actualMonth, actualDay);


        for (FlashcardEntity flashcard : allFlashcards) {

            int flashcardDay = flashcard.getNextTime().getDayOfMonth();
            int flashcardMonth = flashcard.getNextTime().getMonthValue();
            int flashcardYear = flashcard.getNextTime().getYear();

            LocalDate b = LocalDate.of(flashcardYear, flashcardMonth, flashcardDay);


            if (a.isAfter(b)) {

                toBeReviewd.add(flashcard);
                System.out.println("nao cheguei aqui");
            }
        }

        if (toBeReviewd.isEmpty()) {
            System.out.println("Lista Vazia");
            return allFlashcards;
        } else {
            System.out.println("Lista Cheia");
            return toBeReviewd;
        }

    }


}
