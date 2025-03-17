package org.eoeqs.testproject.services;

import org.eoeqs.testproject.models.Dish;
import org.eoeqs.testproject.models.Meal;
import org.eoeqs.testproject.models.Users;
import org.eoeqs.testproject.repositories.DishRepository;
import org.eoeqs.testproject.repositories.MealRepository;
import org.eoeqs.testproject.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MealService {
    private final MealRepository mealRepository;
    private final UsersRepository usersRepository;
    private final DishRepository dishRepository;


    public MealService(MealRepository mealRepository, UsersRepository usersRepository, DishRepository dishRepository) {
        this.mealRepository = mealRepository;
        this.usersRepository = usersRepository;
        this.dishRepository = dishRepository;
    }

    public Meal createMeal(Meal meal) {
        Users user = usersRepository.findById(meal.getUser().getId())
                .orElseThrow(() -> new NoSuchElementException("Пользователь с ID " + meal.getUser().getId() + " не найден"));
        meal.setUser(user);

        List<Long> dishIds = meal.getDishes().stream().map(Dish::getId).collect(Collectors.toList());
        List<Dish> existingDishes = dishRepository.findAllById(dishIds);
        if (existingDishes.size() != dishIds.size()) {
            List<Long> foundIds = existingDishes.stream().map(Dish::getId).toList();
            List<Long> missingIds = dishIds.stream().filter(id -> !foundIds.contains(id)).toList();
            throw new NoSuchElementException("Блюда с ID " + missingIds + " не найдены");
        }
        meal.setDishes(existingDishes);

        return mealRepository.save(meal);
    }

    public Meal getMeal(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Прием пищи с ID " + id + " не найден"));
    }
}