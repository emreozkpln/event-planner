package dev.buddly.event_planner.controller;

import dev.buddly.event_planner.dto.request.CategoryRequest;
import dev.buddly.event_planner.dto.response.CategoryResponse;
import dev.buddly.event_planner.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/create")
    public void createCategory(
            @RequestBody CategoryRequest request
            ) {
        categoryService.createCategory(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(
            @PathVariable Integer id
    ) {
        categoryService.deleteCategory(id);
    }
}
