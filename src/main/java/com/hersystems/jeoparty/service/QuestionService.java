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

    public Question findByQuestionId(String categoryName, String questionId) throws ResourceNotFoundException {
        Category existingCategory = findExistingCategoryByName(categoryName);
        if(ObjectUtils.isEmpty(existingCategory)) {
            throw new ResourceNotFoundException("Unable to find a category: cannot return question if category does not exist");
        }

        if(containsName(existingCategory.getQuestionList(), questionId)) {
            //pop var with index
            existingCategory.getQuestionList().indexOf();
        }
        return existingCategory.getQuestionList().get();
    }

    private boolean containsName(final List<Question> list, final String name){
        return list.stream().anyMatch(o -> o.toString().equals(name));
    }

    private int indexOf(final List<Question> list, final String name) {
        return list.indexOf(o -> list.stream().anyMatch(o -> o.toString().equals(name)));
    }

    private Category findExistingCategoryByName(String name) throws ResourceNotFoundException {
        Category result = categoryRepository.findCategoryByName(name);

        if(ObjectUtils.isEmpty(result)) {
            throw new ResourceNotFoundException("Unable to find category with name: " + name);
        }
        return result;
    }
}
