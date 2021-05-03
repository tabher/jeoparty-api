package com.hersystems.jeoparty.controller;

import com.hersystems.jeoparty.domain.Category;
import com.hersystems.jeoparty.errorhandling.ResourceNotFoundException;
import com.hersystems.jeoparty.service.CategoryService;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;
    private final Logger logger;
    private final String getCategoriesUrl = "/categories";
    private final String getCategoryUrl = "/categories/{id}";

    public CategoryController(final CategoryService categoryService, final Logger logger) {
        this.categoryService = categoryService;
        this.logger = logger;
    }

    @GetMapping(value = getCategoriesUrl, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getCategories() throws ResourceNotFoundException {
        logger.info("GET " + getCategoriesUrl + " initiated");
        return ResponseEntity.ok(categoryService.findAllCategories());
    }
}
