package com.tiago.flashcards.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class FlashCreateRequest {
    @Getter
    @Setter
    @JsonProperty("question")
    @NonNull
    private String question;

    @Getter
    @Setter
    @JsonProperty("answer")
    @NonNull
    private String answer;
}
