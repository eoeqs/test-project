package org.eoeqs.testproject.controllers;

import org.eoeqs.testproject.models.Meal;
import org.eoeqs.testproject.services.MealService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meals")
public class MealController {
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping
    public Meal createMeal(@RequestBody Meal meal) {
        return mealService.createMeal(meal);
    }

    @GetMapping("/{id}")
    public Meal getMeal(@PathVariable Long id) {
        return mealService.getMeal(id);
    }
}