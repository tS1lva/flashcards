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
    private Long id;

    private String question;
    private String answer;

    private LocalDate createdAt;


    private LocalDate nextTime;

    @Getter
    @Setter
    private int interval;

    @Getter
    @Setter
    private int repetition;

    @Getter
    @Setter
    private int difficult;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getNextTime() {
        return nextTime;
    }

    public void setNextTime(LocalDate nextTime) {
        this.nextTime = nextTime;
    }

}
