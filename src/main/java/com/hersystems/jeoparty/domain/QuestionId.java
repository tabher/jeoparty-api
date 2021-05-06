package com.hersystems.jeoparty.domain;

import java.io.Serializable;
import java.util.Objects;

public final class QuestionId implements Serializable, Comparable<QuestionId> {
    private final String id;

    public String toString() {
        return this.id;
    }

    public QuestionId(String id) {
        this.id = Objects.requireNonNull(id);
    }

    @Override
    public int compareTo(QuestionId o) {
        return this.id.compareTo(o.id);
    }
}
