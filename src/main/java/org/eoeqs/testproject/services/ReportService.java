package org.eoeqs.testproject.services;

import org.eoeqs.testproject.models.Dish;
import org.eoeqs.testproject.models.Meal;
import org.eoeqs.testproject.models.Users;
import org.eoeqs.testproject.repositories.MealRepository;
import org.eoeqs.testproject.repositories.UsersRepository;
import org.eoeqs.testproject.utils.DailyReport;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReportService {
    private final MealRepository mealRepository;
    private final UsersRepository usersRepository;

    public ReportService(MealRepository mealRepository, UsersRepository usersRepository) {
        this.mealRepository = mealRepository;
        this.usersRepository = usersRepository;
    }

    public DailyReport getDailyReport(Long userId, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        List<Meal> meals = mealRepository.findByUserIdAndDateTimeBetween(userId, start, end);
        double totalCalories = meals.stream()
                .flatMap(meal -> meal.getDishes().stream())
                .mapToDouble(Dish::getCalories)
                .sum();
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Пользователь с ID " + userId + " не найден"));
        double dailyNorm = user.calculateDailyCalorieNorm();
        return new DailyReport(totalCalories, dailyNorm, meals);
    }

    public boolean checkDailyNorm(Long userId, LocalDate date) {
        DailyReport report = getDailyReport(userId, date);
        return report.getTotalCalories() <= report.getDailyNorm();
    }

    public List<DailyReport> getHistory(Long userId) {
        return List.of();
    }
}