package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class MealRepositoryImpl implements MealRepository{
    Collection<Meal> synchronizedMeals = Collections.synchronizedCollection(Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)));
    List<Meal> meals =new ArrayList<>(synchronizedMeals);

    public final int caloriesPerDay = 2000;

    @Override
    public List<Meal> getAllMeals() {
        return meals;
    }

    @Override
    public List<MealTo> getAllMealsWithExceedStatus() {
        return MealsUtil.filteredByCalories(getAllMeals(), caloriesPerDay);
    }

    @Override
    public Meal getById(int id) {
        return getAllMeals().get(id);
    }

    @Override
    public void createMeal(Meal meal) {
        meals.add(meal);
    }

    @Override
    public void updateMeal(int id, Meal updateMeal) {
        Meal meal = getById(updateMeal.getId());
        meal.setDateTime(updateMeal.getDateTime());
        meal.setDescription(updateMeal.getDescription());
        meal.setCalories(updateMeal.getCalories());
    }

    @Override
    public void deleteMeal(int id) {
        getAllMeals().removeIf(mealTo -> getById(id) == mealTo);
    }
}
