package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(int userId, Meal newMeal) {
        return repository.save(userId, newMeal);
    }

    public void update(int userId, Meal meal) {
        repository.save(userId, meal);
    }

    public void delete(Integer userId, int id) {
        checkNotFoundWithId(repository.delete(userId, id), id);
    }

    public Meal get(Integer userId, int id) {
        return checkNotFoundWithId(repository.get(userId, id), id);
    }

    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    public List<Meal> getAllByDateTime(int userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        return repository.getFiltredValues(userId,
                startDate == null ? LocalDate.MIN : startDate, endDate == null ? LocalDate.MAX : endDate,
                startTime == null ? LocalTime.MIN : startTime, endTime == null ? LocalTime.MAX : endTime);
    }
}