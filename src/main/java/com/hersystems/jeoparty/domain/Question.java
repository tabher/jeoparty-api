package com.hersystems.jeoparty.domain;

import java.util.Objects;

public class Question {

    private QuestionId id;
    private String text;
    private Answer answer;
    private Score score;
    private boolean isDouble;

    public Question(QuestionId id, String text, Answer answer, Score score, boolean isDouble) {
        this.id = id;
        this.text = text;
        this.answer = answer;
        this.score = score;
        this.isDouble = Objects.requireNonNullElse(isDouble, false);
    }

    //builder class obj
    public static class Builder {
        private QuestionId id;
        private String text;
        private Answer answer;
        private Score score;
        private boolean isDouble;

        public Builder() {
            //empty constr
        }

        //modify existing question
        public Builder(Question original) {
            id = original.id;
            text = original.text;
            answer = original.answer;
            score = original.score;
            isDouble = original.isDouble;
        }

        public Builder withId(QuestionId id) {
            this.id = id;
            return this;
        }

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder withAnswer(Answer answer) {
            this.answer = answer;
            return this;
        }

        public Builder withScore(Score score) {
            this.score = score;
            return this;
        }

        public Builder withIsDouble(boolean isDouble) {
            this.isDouble = isDouble;
            return this;
        }

        public Question build() {
            return new Question(id, text, answer, score, isDouble);
        }
    }
}