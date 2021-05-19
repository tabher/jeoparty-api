package com.hersystems.jeoparty.repo;

import com.hersystems.jeoparty.domain.Category;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class CategoryRepositoryImpl implements ICategoryRepoCustom {

    private final MongoTemplate mongoTemplate;

    public CategoryRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Category findCategoryByName(String name) {

        Query query = new Query(Criteria.where("name").is(name));

        return mongoTemplate.findOne(query, Category.class);
    }
}
