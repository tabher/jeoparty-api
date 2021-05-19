package com.hersystems.jeoparty.repo;

import com.hersystems.jeoparty.domain.Category;
import com.hersystems.jeoparty.domain.Name;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICategoryRepository extends MongoRepository<Category, String>, ICategoryRepoCustom {


}
