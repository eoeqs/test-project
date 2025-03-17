package org.eoeqs.testproject.services;

import org.eoeqs.testproject.models.Dish;
import org.eoeqs.testproject.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Dish createDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish getDish(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Блюдо с ID " + id + " не найдено"));
    }

    public Dish updateDish(Long id, Dish updatedDish) {
        Dish dish = getDish(id);
        dish.setName(updatedDish.getName());
        dish.setCalories(updatedDish.getCalories());
        dish.setProteins(updatedDish.getProteins());
        dish.setFats(updatedDish.getFats());
        dish.setCarbs(updatedDish.getCarbs());
        return dishRepository.save(dish);
    }
}