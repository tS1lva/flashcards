package com.tiago.flashcards.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class FlashDto {
    @Getter
    @Setter
    @JsonProperty("id")
    private Long id;

    @Getter
    @Setter
    @JsonProperty("question")
    private String question;

    @Getter
    @Setter
    @JsonProperty("answer")
    private String answer;

    @Getter
    @Setter
    @JsonProperty("createdAt")
    private LocalDate createdAt;

    @Getter
    @Setter
    @JsonProperty("nextTime")
    private LocalDate nextTime;

    @Getter
    @Setter
    @JsonProperty("interval")
    private int interval;

    @Getter
    @Setter
    @JsonProperty("repetition")
    private int repetition;

    @Getter
    @Setter
    @JsonProperty("difficult")
    private double difficult;
}
