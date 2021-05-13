package com.hersystems.jeoparty.domain;

import java.io.Serializable;
import java.util.Objects;

public final class Name implements Serializable, Comparable<Name> {
    private final String name;

    public Name(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Name other) {
        return this.name.compareTo(other.name);
    }
}
