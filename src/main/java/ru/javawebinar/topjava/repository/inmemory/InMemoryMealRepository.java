package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(m -> save(1, m));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        Map<Integer, Meal> mealMap = repository.computeIfAbsent(userId, k -> new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            mealMap.put(meal.getId(), meal);
            return meal;
        }
        return mealMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int id) {
        Map<Integer, Meal> userMap = repository.get(userId);
        if (userMap == null) {
            return false;
        }
        return userMap.remove(id) != null;
    }

    @Override
    public Meal get(int userId, int id) {
        Map<Integer, Meal> userMap = repository.get(userId);
        if (userMap != null && !userMap.isEmpty()) {
            return userMap.get(id);
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getFiltredValues(userId, LocalDate.MIN, LocalDate.MAX, LocalTime.MIN, LocalTime.MAX);
    }

    public List<Meal> getFiltredValues(int userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        Map<Integer, Meal> userMap = repository.get(userId);

        return userMap != null ? userMap.values()
                .stream()
                .filter(mealTo -> DateTimeUtil.isBetweenHalfOpen(mealTo.getDate(), startDate, endDate))
                .filter(mealTo -> DateTimeUtil.isBetweenHalfOpen(mealTo.getTime(), startTime, endTime))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList()) : Collections.emptyList();
    }
}

