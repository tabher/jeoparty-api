package com.hersystems.jeoparty.domain;

import java.io.Serializable;
import java.util.Objects;

public final class Score implements Serializable, Comparable<Score> {
    private final Long score;

    public Score(Long score) {
        this.score = Objects.requireNonNull(score);
    }

    @Override
    public int compareTo(Score o) {
        return this.score.compareTo(o.score);
    }
}
