package com.hersystems.jeoparty.domain;

import com.hersystems.jeoparty.constants.Rating;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "category")
public class Category {
    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private Rating rating;
    private List<Question> questionList = new ArrayList();

    public Category(String id, String name, Rating rating, List<Question> questionList) {
        this.id = id;
        this.name = name;
        this.rating = Objects.requireNonNullElse(rating, Rating.EVERYONE);
        this.questionList = questionList;
    }

    public Category() {
    }
}