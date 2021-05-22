package com.hersystems.jeoparty.service;

import com.hersystems.jeoparty.domain.Category;
import com.hersystems.jeoparty.domain.Question;
import com.hersystems.jeoparty.errorhandling.ResourceNotFoundException;
import com.hersystems.jeoparty.repo.ICategoryRepository;
import com.hersystems.jeoparty.repo.IQuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class QuestionService {

    private final IQuestionRepository questionRepository;
    private final ICategoryRepository categoryRepository;

    public QuestionService(final IQuestionRepository questionRepository, final ICategoryRepository categoryRepository) {
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Question> findAllQuestions(String categoryName) throws ResourceNotFoundException {
        Category existingCategory = findExistingCategoryByName(categoryName);
        if(ObjectUtils.isEmpty(existingCategory)) {
            throw new ResourceNotFoundException("Unable to find a category: cannot return questions if category does not exist");
        }
        return existingCategory.getQuestionList();
    }

    private Category findExistingCategoryByName(String name) throws ResourceNotFoundException {
        Category result = categoryRepository.findCategoryByName(name);

        if(ObjectUtils.isEmpty(result)) {
            throw new ResourceNotFoundException("Unable to find category with name: " + name);
        }
        return result;
    }
}
