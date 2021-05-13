package com.hersystems.jeoparty.domain;

import java.io.Serializable;
import java.util.Objects;

public final class CategoryId implements Serializable, Comparable<CategoryId> {
    private final String id;

    public CategoryId(String id) {
        this.id = Objects.requireNonNull(id);
    }

    public String toString() {
        return this.id;
    }

    @Override
    public int compareTo(CategoryId o) {
        return this.id.compareTo(o.id);
    }
}
