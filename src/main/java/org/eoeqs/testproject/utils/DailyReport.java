package org.eoeqs.testproject.utils;

import org.eoeqs.testproject.models.Meal;

import java.util.List;

public class DailyReport {
    private double totalCalories;
    private double dailyNorm;
    private List<Meal> meals;

    public DailyReport(double totalCalories, double dailyNorm, List<Meal> meals) {
        this.totalCalories = totalCalories;
        this.dailyNorm = dailyNorm;
        this.meals = meals;
    }

    public double getTotalCalories() { return totalCalories; }
    public double getDailyNorm() { return dailyNorm; }
    public List<Meal> getMeals() { return meals; }
}