package org.eoeqs.testproject.repositories;

import org.eoeqs.testproject.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {}