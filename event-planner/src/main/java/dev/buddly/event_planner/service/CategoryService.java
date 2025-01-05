package dev.buddly.event_planner.service;

import dev.buddly.event_planner.dto.CategoryMapper;
import dev.buddly.event_planner.dto.request.CategoryRequest;
import dev.buddly.event_planner.dto.response.CategoryResponse;
import dev.buddly.event_planner.exception.OperationNotPermittedException;
import dev.buddly.event_planner.model.Category;
import dev.buddly.event_planner.repo.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public void createCategory(CategoryRequest request) {
        categoryRepository.findByCategoryName(request.categoryName()).ifPresent(category -> {
            throw new OperationNotPermittedException("Category already exists");
        });
        Category category = new Category();
        category.setCategoryName(request.categoryName());
        categoryRepository.save(category);
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }

    public void deleteCategory(Integer id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new OperationNotPermittedException("Category not found"));
        categoryRepository.delete(category);
    }
}
