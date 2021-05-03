package com.hersystems.jeoparty.repo;

import com.hersystems.jeoparty.domain.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IQuestionRepository extends MongoRepository<Question, String> {
}
