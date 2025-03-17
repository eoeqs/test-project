package org.eoeqs.testproject;

import org.eoeqs.testproject.models.Dish;
import org.eoeqs.testproject.models.Meal;
import org.eoeqs.testproject.models.Users;
import org.eoeqs.testproject.models.enums.Gender;
import org.eoeqs.testproject.models.enums.Goal;
import org.eoeqs.testproject.repositories.DishRepository;
import org.eoeqs.testproject.repositories.MealRepository;
import org.eoeqs.testproject.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MealRepositoryTest {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DishRepository dishRepository;

    @Test
    public void testCreateMealWithDishes() {
        Users user = new Users();
        user.setName("Петр");
        user.setEmail("petr@example.com");
        user.setAge(40);
        user.setWeight(90);
        user.setHeight(185);
        user.setGender(Gender.MALE);
        user.setGoal(Goal.MAINTENANCE);
        Users savedUser = usersRepository.save(user);

        Dish dish1 = new Dish();
        dish1.setName("Каша");
        dish1.setCalories(200);
        Dish savedDish1 = dishRepository.save(dish1);

        Dish dish2 = new Dish();
        dish2.setName("Яблоко");
        dish2.setCalories(50);
        Dish savedDish2 = dishRepository.save(dish2);

        Meal meal = new Meal();
        meal.setDateTime(LocalDateTime.now());
        meal.setUser(savedUser);
        meal.setDishes(Arrays.asList(savedDish1, savedDish2));

        Meal savedMeal = mealRepository.save(meal);

        assertNotNull(savedMeal.getId());
        assertEquals(2, savedMeal.getDishes().size());
        assertEquals(savedUser.getId(), savedMeal.getUser().getId());
    }
}