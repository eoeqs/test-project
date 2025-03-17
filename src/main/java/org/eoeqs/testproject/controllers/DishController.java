package org.eoeqs.testproject.controllers;

import jakarta.validation.Valid;
import org.eoeqs.testproject.models.Dish;
import org.eoeqs.testproject.services.DishService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    public Dish createDish(@Valid @RequestBody Dish dish) {
        return dishService.createDish(dish);
    }

    @GetMapping("/{id}")
    public Dish getDish(@PathVariable Long id) {
        return dishService.getDish(id);
    }

    @PutMapping("/{id}")
    public Dish updateDish(@PathVariable Long id, @Valid @RequestBody Dish dish) {
        return dishService.updateDish(id, dish);
    }
}