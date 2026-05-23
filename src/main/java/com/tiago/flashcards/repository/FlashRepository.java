package com.tiago.flashcards.repository;

import com.tiago.flashcards.entity.FlashcardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashRepository extends JpaRepository<FlashcardEntity, Long> {
}
