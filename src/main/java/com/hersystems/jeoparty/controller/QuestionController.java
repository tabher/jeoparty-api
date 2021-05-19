package com.hersystems.jeoparty.controller;

import com.hersystems.jeoparty.domain.Question;
import com.hersystems.jeoparty.errorhandling.ResourceNotFoundException;
import com.hersystems.jeoparty.service.QuestionService;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {

    private final QuestionService questionService;
    private final Logger logger;
    private final String getQuestionsUrl = "/categories/{categoryName}/questions";

    public QuestionController(final QuestionService questionService, final Logger logger) {
        this.questionService = questionService;
        this.logger = logger;
    }

    @GetMapping(value = getQuestionsUrl, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Question>> getQuestions(@PathVariable("categoryName") String categoryName) throws ResourceNotFoundException {
        logger.info("GET " + getQuestionsUrl + " initiated");
        return ResponseEntity.ok(questionService.findAllQuestions(categoryName));
    }
}
