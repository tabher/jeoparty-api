package com.hersystems.jeoparty.domain;

import java.util.Objects;

public class Question {

    private String id;
    private String text;
    private String answer;
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