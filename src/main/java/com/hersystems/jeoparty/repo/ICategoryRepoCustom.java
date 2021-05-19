package com.hersystems.jeoparty.repo;

import com.hersystems.jeoparty.domain.Category;

public interface ICategoryRepoCustom {

    Category findCategoryByName(String name);
}
