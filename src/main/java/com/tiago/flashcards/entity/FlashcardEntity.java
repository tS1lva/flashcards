package com.tiago.flashcards.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "flashcards")
public class FlashcardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String question;

    @Getter
    @Setter
    private String answer;

    @Getter
    @Setter
    private LocalDate createdAt;

    @Getter
    @Setter
    private LocalDate nextTime;

    @Getter
    @Setter
    private int interval;

    @Getter
    @Setter
    private int repetition;

    @Getter
    @Setter
    private double difficult;

}
