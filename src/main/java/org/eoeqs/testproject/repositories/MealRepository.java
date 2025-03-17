package org.eoeqs.testproject.repositories;

import org.eoeqs.testproject.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByUserIdAndDateTimeBetween(Long userId, LocalDateTime start, LocalDateTime end);
}