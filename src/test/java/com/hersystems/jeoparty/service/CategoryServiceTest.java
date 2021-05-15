package com.hersystems.jeoparty.service;

import com.hersystems.jeoparty.constants.Rating;
import com.hersystems.jeoparty.domain.*;
import com.hersystems.jeoparty.errorhandling.ResourceAlreadyExistsException;
import com.hersystems.jeoparty.errorhandling.ResourceNotFoundException;
import com.hersystems.jeoparty.repo.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("Verify CategoryService")
class CategoryServiceTest {

    private CategoryService underTest;
    private ICategoryRepository mockRepo;
    private Category category;
    private List<Category> categoryList = new ArrayList<>();
    private List<Question> questionList = new ArrayList<>();
    private Question question;

    @BeforeEach
    void setUp() {
        mockRepo = mock(ICategoryRepository.class);
        underTest = new CategoryService(mockRepo);
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
    @DisplayName("findAllCategories success")
    public void givenCategoryExists_whenFindAllCategories_returnCategory() throws ResourceNotFoundException {
        //arrange
        when(mockRepo.findAll()).thenReturn(categoryList);
        //act
        List actual = underTest.findAllCategories();
        //assert
        assertEquals(categoryList, actual);
    }

    @Test
    @DisplayName("findAllCategories throws ResourceNotFoundException")
    public void givenCategoryDoesNotExist_whenFindAllCategories_throwResourceNotFoundException() throws ResourceNotFoundException {
        //arrange
        when(mockRepo.findAll()).thenReturn(null);
        //act
        //assert
        assertThrows(ResourceNotFoundException.class, () -> underTest.findAllCategories());
    }

    @Test
    @DisplayName("findCategoryById success")
    public void givenCategoryDoesExist_whenFindCategoryById_returnCategory() throws ResourceNotFoundException {
        //arrange
        when(mockRepo.findById(anyString())).thenReturn(Optional.ofNullable(category));
        //act
        Category actual = underTest.findCategoryById(category.getCategoryId().toString());
        //assert
        assertEquals(category, actual);
    }

    @Test
    @DisplayName("findCategoryById throws ResourceNotFoundException")
    public void givenCategoryDoesNotExist_whenFindByCategoryId_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
        //arrange
        when(mockRepo.findById(anyString())).thenReturn(Optional.empty());
        //act
        //assert
        assertThrows(ResourceNotFoundException.class, () -> underTest.findCategoryById(category.getCategoryId().toString()));
    }

    @Test
    @DisplayName("saveNewCategory success")
    public void givenCategoryDoesNotExist_whenSaveNewCategory_thenReturnSavedEntity() throws ResourceAlreadyExistsException {
        //arrange
        when(mockRepo.findCategoryByName(category.getName())).thenReturn(null);
        when(mockRepo.findById(anyString())).thenReturn(null);
        when(mockRepo.save(category)).thenReturn(category);
        //act
        Category actual = underTest.saveNewCategory(category);
        //assert
        assertEquals(category, actual);
    }

    @Test
    @DisplayName("saveNewCategory throws ResourceAlreadyExistsException")
    public void givenCategoryDoesExist_whenSaveNewCategory_thenThrowResourceAlreadyExistsException() throws ResourceAlreadyExistsException{
        //arrange
        when(mockRepo.findById(anyString())).thenReturn(Optional.ofNullable(category));
        //act
        //assert
        assertThrows(ResourceAlreadyExistsException.class, () -> underTest.saveNewCategory(category));
    }
}