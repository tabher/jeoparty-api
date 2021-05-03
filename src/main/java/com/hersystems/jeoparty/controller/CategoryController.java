package com.hersystems.jeoparty.controller;

import com.hersystems.jeoparty.domain.Category;
import com.hersystems.jeoparty.errorhandling.ResourceAlreadyExistsException;
import com.hersystems.jeoparty.errorhandling.ResourceNotFoundException;
import com.hersystems.jeoparty.service.CategoryService;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;
    private final Logger logger;
    private final String getCategoriesUrl = "/categories";
    private final String getCategoryUrl = "/categories/{id}";
    private final String postCategoryUrl = "/category";

    public CategoryController(final CategoryService categoryService, final Logger logger) {
        this.categoryService = categoryService;
        this.logger = logger;
    }

    @GetMapping(value = getCategoriesUrl, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getCategories() throws ResourceNotFoundException {
        logger.info("GET " + getCategoriesUrl + " initiated");
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @GetMapping(value = getCategoryUrl, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCategory(@PathVariable("id") String id) throws ResourceNotFoundException{
        logger.info("GET " + getCategoryUrl + " initiated");
        return ResponseEntity.ok(categoryService.findCategoryById(id));
    }

    @PostMapping(value = postCategoryUrl, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> createNewCategory(@RequestBody @Valid Category category) throws ResourceAlreadyExistsException {
        logger.info("POST " + postCategoryUrl + " initiated");
        return ResponseEntity.ok(categoryService.saveNewCategory(category));
    }

}
