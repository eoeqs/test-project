package org.eoeqs.testproject;

import org.eoeqs.testproject.models.Dish;
import org.eoeqs.testproject.repositories.DishRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DishRepositoryTest {

    @Autowired
    private DishRepository dishRepository;

    @Test
    public void testCreateDishWithMinimalValues() {
        Dish dish = new Dish();
        dish.setName("Вода");
        dish.setCalories(0);
        dish.setProteins(0);
        dish.setFats(0);
        dish.setCarbs(0);

        Dish savedDish = dishRepository.save(dish);

        assertNotNull(savedDish.getId());
        assertEquals("Вода", savedDish.getName());
        assertEquals(0, savedDish.getCalories(), 0.1);
    }

    @Test
    public void testUpdateDishCalories() {
        Dish dish = new Dish();
        dish.setName("Салат");
        dish.setCalories(100);
        dish.setProteins(5);
        dish.setFats(2);
        dish.setCarbs(10);

        Dish savedDish = dishRepository.save(dish);
        savedDish.setCalories(150);
        Dish updatedDish = dishRepository.save(savedDish);

        assertEquals(150, updatedDish.getCalories(), 0.1);
    }
}