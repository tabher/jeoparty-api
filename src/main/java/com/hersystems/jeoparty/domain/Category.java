package com.hersystems.jeoparty.domain;

import com.hersystems.jeoparty.constants.Rating;
import com.hersystems.jeoparty.errorhandling.ResourceNotFoundException;

import java.util.List;
import java.util.Objects;


public class Category {

    private CategoryId categoryId;
    private Name name;
    private Rating rating;
    private List<Question> questionList;

    public Category(CategoryId categoryId, Name name, Rating rating, List<Question> questionList) {

        this.categoryId = categoryId;
        this.name = name;
        this.rating = Objects.requireNonNullElse(rating, Rating.EVERYONE);
        this.questionList = questionList;
    }

    public CategoryId getCategoryId() {
        return categoryId;
    }
    public Name getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return super.equals(obj);
    }

    //TODO: add unit test for this method
    public List<Question> getQuestionList() throws ResourceNotFoundException{
        if(questionList.isEmpty()) {
            throw new ResourceNotFoundException("CategoryId: " + categoryId + " does not have any questions");
        }
        return questionList;
    }

    //TODO add categoryMethods
    public static class CategoryBuilder {
        private CategoryId categoryId;
        private Name name;
        private Rating rating;
        private List<Question> questionList;

        public CategoryBuilder(){}

        public CategoryBuilder(Category original) {
            categoryId = original.categoryId;
            name = original.name;
            rating = original.rating;
            questionList = original.questionList;
        }

        public CategoryBuilder withCategoryId(CategoryId id) {
            this.categoryId = id;
            return this;
        }

        public CategoryBuilder withName(Name name) {
            this.name = name;
            return this;
        }

        public CategoryBuilder withRating(Rating rating) {
            this.rating = rating;
            return this;
        }

        public CategoryBuilder withQuestionList(List<Question> questionList) {
            this.questionList = questionList;
            return this;
        }

        public Category build() {
            return new Category(categoryId, name, rating, questionList);
        }

    }
}