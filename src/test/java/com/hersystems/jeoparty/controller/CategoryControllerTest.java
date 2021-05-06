package com.hersystems.jeoparty.controller;

import com.hersystems.jeoparty.constants.Rating;
import com.hersystems.jeoparty.domain.*;
import com.hersystems.jeoparty.errorhandling.ResourceAlreadyExistsException;
import com.hersystems.jeoparty.errorhandling.ResourceNotFoundException;
import com.hersystems.jeoparty.service.CategoryService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("Verify CategoryController")
class CategoryControllerTest {

    private CategoryController underTest;
    private CategoryService mockService;
    private Category category;
    private List<Category> categoryList = new ArrayList<>();
    private List<Question> questionList = new ArrayList<>();
    private Question question;

    @BeforeEach
    void setUp() {
        mockService = mock(CategoryService.class);
        underTest = new CategoryController(mockService, mock(Logger.class));
        question = new Question.Builder()
                .withId(new QuestionId(UUID.randomUUID().toString()))
                .withText("Who is the queen of the north?")
                .withAnswer(new Answer("Elsa"))
                .withScore(new Score((long) 200))
                .withIsDouble(false)
                .build();
//        question = new Question(UUID.randomUUID().toString(),
//                "Who is queen of the north?",
//                "Elsa",
//                200,
//                false);
        questionList.add(question);
//        category = new Category(UUID.randomUUID().toString(),
//                "Disney, Disney, Disney",
//                Rating.EVERYONE,
//                questionList);
        category = new Category.CategoryBuilder()
                .withCategoryId(new CategoryId(UUID.randomUUID().toString()))
                .withName(new Name("Disney, Disney, Disney"))
                .withRating(Rating.EVERYONE)
                .withQuestionList(questionList)
                .build();
        categoryList.add(category);
    }

    @Test
    @DisplayName("getCategories success")
    public void givenGetCategories_whenCategoriesExist_thenReturnHttpStatus200() throws ResourceNotFoundException {
        //arrange
        when(mockService.findAllCategories()).thenReturn(categoryList);
        //act
        ResponseEntity actual = underTest.getCategories();
        //assert
        assertEquals(200, actual.getStatusCodeValue());
        assertEquals(categoryList, actual.getBody());
    }

    @Test
    @DisplayName("getCategories throws ResourceNotFoundException")
    public void givenGetCategories_whenCategoriesDontExist_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
        //arrange
        when(mockService.findAllCategories()).thenThrow(ResourceNotFoundException.class);
        //act
        //assert
        assertThrows(ResourceNotFoundException.class, () -> underTest.getCategories());
    }

    @Test
    @DisplayName("getCategory success")
    public void givenGetCategory_whenCategoryExists_thenReturnHttpStatus200() throws ResourceNotFoundException {
        //arrange
        when(mockService.findCategoryById(anyString())).thenReturn(category);
        //act
        ResponseEntity actual = underTest.getCategory(category.getCategoryId().toString());
        //assert
        assertEquals(200, actual.getStatusCodeValue());
        assertEquals(category, actual.getBody());
    }

    @Test
    @DisplayName("getCategory throws ResourceNotFoundException")
    public void givenGetCategory_whenCategoryDontExist_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
        //arrange
        when(mockService.findCategoryById(anyString())).thenThrow(ResourceNotFoundException.class);
        //act
        //assert
        assertThrows(ResourceNotFoundException.class, () -> underTest.getCategory(category.getCategoryId().toString()));
    }

//    @Test
//    @DisplayName("createNewCategory success")
//    public void givenCreateNewCategory_whenCategoryDoesNotExist_thenReturnHttpStatus200() throws ResourceAlreadyExistsException, ResourceNotFoundException {
//        //arrange
//        when(mockService.saveNewCategory(any())).thenReturn(category);
//        //act
//        ResponseEntity actual = underTest.createNewCategory(category);
//        //assert
//        assertEquals(200, actual.getStatusCodeValue());
//        assertEquals(category, actual.getBody());
//    }
//
//    @Test
//    @DisplayName("createNewCategory throws ResourceAlreadyExistsException")
//    public void givenCreateNewCategory_whenCategoryExists_thenThrowResourceAlreadyExistsException() throws ResourceAlreadyExistsException {
//        //arrange
//        when(mockService.saveNewCategory(any())).thenThrow(ResourceAlreadyExistsException.class);
//        //act
//        //assert
//        assertThrows(ResourceAlreadyExistsException.class, () -> {
//            underTest.createNewCategory(category);
//        });
//    }
}