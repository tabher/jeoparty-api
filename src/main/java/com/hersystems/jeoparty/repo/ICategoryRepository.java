package com.hersystems.jeoparty.repo;

import com.hersystems.jeoparty.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICategoryRepository extends MongoRepository<Category, String> {

    Category findCategoryByName(String name);
}
