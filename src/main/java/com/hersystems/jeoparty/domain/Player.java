package com.hersystems.jeoparty.domain;

public class Player {

    private String id;
    private String name;
    private long highScore;

    public Player(String id, String name, long highScore) {
        this.id = id;
        this.name = name;
        this.highScore = highScore;
    }

    public Player() {
    }
}
