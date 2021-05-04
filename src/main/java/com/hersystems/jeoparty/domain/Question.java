package com.hersystems.jeoparty.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Document(collection = "question")
public class Question {

    @Id
    private String id;
    @NotNull
    private String text;
    @NotNull
    private String answer;
    @NotNull
    private long points;
    private boolean isDouble;

    public Question(String id, String text, String answer, long points, boolean isDouble) {
        this.id = id;
        this.text = text;
        this.answer = answer;
        this.points = points;
        this.isDouble = Objects.requireNonNullElse(isDouble, false);
    }

    public Question() {
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }

    public long getPoints() {
        return points;
    }

    public boolean isDouble() {
        return isDouble;
    }
}