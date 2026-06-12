package com.tiago.flashcards.repository;

import com.tiago.flashcards.entity.DeckEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<DeckEntity,Long> {
}
