package com.hersystems.jeoparty.domain;

import java.io.Serializable;
import java.util.Objects;

public final class Answer implements Serializable, Comparable<Answer> {
    private final String value;

    public Answer(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public String toString() {
        return this.value;
    }

    @Override
    public int compareTo(Answer o) {
        return this.value.compareTo(o.value);
    }
}
