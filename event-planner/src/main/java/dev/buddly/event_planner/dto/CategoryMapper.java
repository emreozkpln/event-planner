package dev.buddly.event_planner.dto;

import dev.buddly.event_planner.dto.response.CategoryResponse;
import dev.buddly.event_planner.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public CategoryResponse toCategoryResponse(Category category){
        return new CategoryResponse(
                category.getId(),
                category.getCategoryName()
        );
    }
}
