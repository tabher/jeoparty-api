package com.hersystems.jeoparty.service;

import com.hersystems.jeoparty.domain.Category;
import com.hersystems.jeoparty.errorhandling.ResourceNotFoundException;
import com.hersystems.jeoparty.repo.ICategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

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

}
