package com.hersystems.jeoparty.controller;

import com.hersystems.jeoparty.constants.Rating;
import com.hersystems.jeoparty.domain.Category;
import com.hersystems.jeoparty.domain.Question;
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
        question = new Question(UUID.randomUUID().toString(),
                "Who is queen of the north?",
                "Elsa",
                200,
                false);
        questionList.add(question);
        category = new Category(UUID.randomUUID().toString(),
                "Disney, Disney, Disney",
                Rating.EVERYONE,
                questionList);
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
        assertThrows(ResourceNotFoundException.class, () -> {
            underTest.getCategories();
        });
    }

    @Test
    @DisplayName("getCategory success")
    public void givenGetCategory_whenCategoryExists_thenReturnHttpStatus200() throws ResourceNotFoundException {
        //arrange
        //act
        //assert
    }

    @Test
    @DisplayName("getCategory throws ResourceNotFoundException")
    public void givenGetCategory_whenCategoryDontExist_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
        //arrange
        //act
        //assert
    }
}