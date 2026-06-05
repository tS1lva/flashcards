package com.tiago.flashcards.service;

import com.tiago.flashcards.dto.FlashCreateRequest;
import com.tiago.flashcards.dto.FlashDto;
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

    public List<FlashDto> list () {
//        Sort sort = Sort.by("prioridade").ascending().and(
//                Sort.by("descricao").ascending()
//        );
//        return flashRepository.findAll(sort);
        List<FlashDto> flashDtos = new ArrayList<>();

        for (FlashcardEntity flashcardEntity : flashRepository.findAll()) {

            flashDtos.add(this.toDto(flashcardEntity));

        }
        return flashDtos;
    }

    public FlashcardEntity getById (Long id) {
        return flashRepository.getById(id);
    }

    public List<FlashDto> create (FlashCreateRequest flashCreateRequest) throws Exception {

        if (flashCreateRequest.getAnswer() == null || flashCreateRequest.getQuestion() == null) {
            throw new Exception("Invalid answer or question");
        }
        FlashcardEntity flashcardEntity = new FlashcardEntity();
        //Setting initial card config
        flashcardEntity.setRepetition(0);
        flashcardEntity.setDifficult(2.5);
        flashcardEntity.setInterval(0);
        flashcardEntity.setCreatedAt(LocalDate.now());
        flashcardEntity.setNextTime(LocalDate.now().plusDays(1));
        flashcardEntity.setQuestion(flashCreateRequest.getQuestion());
        flashcardEntity.setAnswer(flashCreateRequest.getAnswer());

        flashRepository.save(flashcardEntity);
        return this.list();
    }

    public List<FlashDto> update (FlashcardEntity flashcard) {
        flashRepository.save(flashcard);
        return this.list();
    }

    public List<FlashDto> delete (Long id) {
        flashRepository.deleteById(id);
        return this.list();
    }

    public void deleteAll () {
        flashRepository.deleteAll();
    }

    public List<FlashDto> getCardsToReview() {
        List<FlashDto> allFlashcards = this.list();
        List<FlashDto> toBeReviewd = new ArrayList<>();

        LocalDate now = LocalDate.now();

        for (FlashDto flashcard : allFlashcards) {
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

    public void reviewFlashcardById (Long id, int score) {
        FlashcardEntity flashcard = flashRepository.getById(id);
        FlashCard flashCardReviwer = new FlashCard(score, flashcard.getInterval(), flashcard.getRepetition(), flashcard.getDifficult());
        flashCardReviwer.calculateInterval(score, flashcard.getRepetition());
        flashcard.setNextTime(LocalDate.now().plusDays(flashCardReviwer.getInterval()));
        flashcard.setRepetition(flashCardReviwer.getRepetition());
        flashcard.setDifficult(flashCardReviwer.getDifficult());
        flashcard.setInterval(flashCardReviwer.getInterval());
        flashRepository.save(flashcard);

    }

    public FlashDto toDto (FlashcardEntity flashcardEntity) {
        FlashDto flashDto = new FlashDto();
        flashDto.setId(flashcardEntity.getId());
        flashDto.setQuestion(flashcardEntity.getQuestion());
        flashDto.setAnswer(flashcardEntity.getAnswer());
        flashDto.setCreatedAt(flashcardEntity.getCreatedAt());
        flashDto.setNextTime(flashcardEntity.getNextTime());
        flashDto.setInterval(flashcardEntity.getInterval());
        flashDto.setDifficult(flashcardEntity.getDifficult());
        flashDto.setRepetition(flashcardEntity.getRepetition());

        return flashDto;
    }


}
