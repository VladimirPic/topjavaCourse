package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealRepository {
    List<Meal> getAllMeals();

    List<MealTo> getAllMealsWithExceedStatus();

    Meal getById(int id);

    void createMeal(Meal meal);

    void updateMeal(int id, Meal meal);

    void deleteMeal(int id);
}
