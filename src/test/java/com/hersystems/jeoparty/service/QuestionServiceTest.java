package com.hersystems.jeoparty.service;

import com.hersystems.jeoparty.constants.Rating;
import com.hersystems.jeoparty.domain.*;
import com.hersystems.jeoparty.errorhandling.ResourceNotFoundException;
import com.hersystems.jeoparty.repo.ICategoryRepository;
import com.hersystems.jeoparty.repo.IQuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Verify QuestionService")
class QuestionServiceTest {

    private QuestionService underTest;
    private IQuestionRepository mockQuestionRepo;
    private ICategoryRepository mockCategoryRepo;
    private Question question;
    private Category category;
    private List<Category> categoryList = new ArrayList<>();
    private List<Question> questionList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        mockQuestionRepo = mock(IQuestionRepository.class);
        mockCategoryRepo = mock(ICategoryRepository.class);
        underTest = new QuestionService(mockQuestionRepo, mockCategoryRepo);
//        serviceSpy = spy(underTest);
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
        categoryList.add(category);
    }

    @Test
    @DisplayName("findAllQuestions success")
    public void givenQuestionsExist_whenFindAllQuestions_thenReturnQuestionlist() throws ResourceNotFoundException {
        //arrange
        when(mockCategoryRepo.findCategoryByName(anyString())).thenReturn(category);
        //act
        List actual = underTest.findAllQuestions(category.getName().toString());
        //assert
        assertEquals(questionList, actual);
    }

    @Test
    @DisplayName("findAllQuestions throws ResourceNotFoundException")
    public void givenQuestionsDoNotExist_whenFindAllQuestions_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
        //arrange
        when(mockCategoryRepo.findCategoryByName(anyString())).thenReturn(null);
        //act
        //assert
        assertThrows(ResourceNotFoundException.class, () -> underTest.findAllQuestions(category.getName().toString()));
    }
}