package com.hersystems.jeoparty.domain;

import java.util.List;

public class Team {

    private String id;
    private String name;
    private long score;
    private List<Question> answeredCorrect;
    private List<Player> memberList;

    public Team(String id, String name, long score, List<Question> answeredCorrect, List<Player> memberList) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.answeredCorrect = answeredCorrect;
        this.memberList = memberList;
    }

    public Team() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getScore() {
        return score;
    }

    public List<Question> getAnsweredCorrect() {
        return answeredCorrect;
    }

    public List<Player> getMemberList() {
        return memberList;
    }
}
