package com.hersystems.jeoparty.service;

import com.hersystems.jeoparty.domain.Category;
import com.hersystems.jeoparty.errorhandling.ResourceAlreadyExistsException;
import com.hersystems.jeoparty.errorhandling.ResourceNotFoundException;
import com.hersystems.jeoparty.repo.ICategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final ICategoryRepository categoryRepository;

    public CategoryService(final ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() throws ResourceNotFoundException {
        List resultSet = categoryRepository.findAll();
        if(ObjectUtils.isEmpty(resultSet)) {
            throw new ResourceNotFoundException("Unable to find categories");
        }
        return resultSet;
    }

    public Category findCategoryById(final String id) throws ResourceNotFoundException {
        Optional<Category> result = categoryRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Unable to find category");
        }
        return result.get();
    }

    public Category saveNewCategory(final Category newCategory) throws ResourceAlreadyExistsException {
        Optional<Category> result;

        if(ObjectUtils.isEmpty(newCategory.getId())) {
            result = Optional.ofNullable(categoryRepository.findCategoryByName(newCategory.getName()));
        }
        else {
            result = categoryRepository.findById(newCategory.getId());
        }

        if (!ObjectUtils.isEmpty(result)) {
            throw new ResourceAlreadyExistsException("Category already exists");
        }
        return categoryRepository.save(newCategory);
    }
}
