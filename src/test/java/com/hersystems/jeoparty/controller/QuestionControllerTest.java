package com.hersystems.jeoparty.controller;

import com.hersystems.jeoparty.constants.Rating;
import com.hersystems.jeoparty.domain.*;
import com.hersystems.jeoparty.errorhandling.ResourceNotFoundException;
import com.hersystems.jeoparty.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("Verify QuestionController")
class QuestionControllerTest {

    private QuestionController underTest;
    private QuestionService mockQuestionService;
    private Category category;
    private List<Question> questionList = new ArrayList<>();
    private Question question;

    @BeforeEach
    void setUp() {
        mockQuestionService = mock(QuestionService.class);
        underTest = new QuestionController(mockQuestionService, mock(Logger.class));
        question = new Question.Builder()
                .withId(new QuestionId(UUID.randomUUID().toString()))
                .withText("Who is the queen of the north?")
                .withAnswer(new Answer("Elsa"))
                .withScore(new Score((long) 200))
                .withIsDouble(false)
                .build();
        questionList.add(question);
        category = new Category.CategoryBuilder()
                .withCategoryId(new CategoryId(UUID.randomUUID().toString()))
                .withName(new Name("Disney, Disney, Disney"))
                .withRating(Rating.EVERYONE)
                .withQuestionList(questionList)
                .build();
    }

    @Test
    @DisplayName("getQuestions success")
    public void givenGetQuestions_whenQuestionsExist_thenReturnHttpStatus200() throws ResourceNotFoundException {
        //arrange
        when(mockQuestionService.findAllQuestions(anyString())).thenReturn(questionList);
        //act
        ResponseEntity actual = underTest.getQuestions(category.getName().toString());
        //assert
        assertEquals(200, actual.getStatusCodeValue());
        assertEquals(questionList, actual.getBody());
    }

    @Test
    @DisplayName("getQuestions throws ResourceNotFoundException")
    public void givenGetQuestions_whenQuestionsDontExist_thenReturnHttpStatus404() throws ResourceNotFoundException {
        //arrange
        when(mockQuestionService.findAllQuestions(anyString())).thenThrow(ResourceNotFoundException.class);
        //act
        //assert
        assertThrows(ResourceNotFoundException.class, () -> underTest.getQuestions(category.getName().toString()));
    }
}