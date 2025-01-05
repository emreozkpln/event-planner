package dev.buddly.event_planner.repo;

import dev.buddly.event_planner.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Optional<Category> findByCategoryName(String categoryName);
}
