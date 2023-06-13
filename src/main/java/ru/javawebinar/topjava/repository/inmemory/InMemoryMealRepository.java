package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
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
        Map<Integer, Meal> mealMap = repository.computeIfAbsent(meal.getId(), k -> new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            mealMap.put(meal.getId(), meal);
            return meal;
        }
        return mealMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(Integer userId, int id) {
        return repository.get(userId).remove(id) != null;
    }

    @Override
    public Meal get(Integer userId, int id) {
        Map<Integer, Meal> map = repository.get(userId) != null ? repository.get(userId) : new ConcurrentHashMap<>();
        if (!map.isEmpty()) {
            return map.get(id) != null ? map.get(id) : null;
        }
        return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        if (!repository.get(userId).isEmpty()) {
            return repository.get(userId).values()
                    .stream()
                    .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                    .collect(Collectors.toList());
        }
        return repository.get(userId).values();
    }
}

