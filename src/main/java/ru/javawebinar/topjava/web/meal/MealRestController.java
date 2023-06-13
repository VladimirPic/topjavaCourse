package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll() {
        log.info("getAll");
        return MealsUtil.getTos(service.getAll(SecurityUtil.authUserId()), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealTo> getFiltredValues(int userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        return MealsUtil.getTos(
                new ArrayList<>(service.getAll(userId).stream()
                        .filter(mealTo -> {
                            LocalDate minDate = startDate != null ? startDate : LocalDate.MIN;
                            LocalDate maxDate = endDate != null ? endDate : LocalDate.MAX;
                            return DateTimeUtil.isBetweenHalfOpen(mealTo.getDate(), minDate, maxDate);
                        })
                        .filter(mealTo -> {
                            LocalTime minTime = startTime != null ? startTime : LocalTime.MIN;
                            LocalTime maxTime = endTime != null ? endTime : LocalTime.MAX;
                            return DateTimeUtil.isBetweenHalfOpen(mealTo.getTime(), minTime, maxTime);
                        })
                        .collect(Collectors.toList())), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal getById(int mealId) {
        log.info("getById");
        return service.get(SecurityUtil.authUserId(), mealId);
    }

    public boolean delete(int mealId) {
        log.info("delete");
        return service.delete(SecurityUtil.authUserId(), mealId);
    }

    public Meal save(Meal meal) {
        log.info("save");
        return service.save(SecurityUtil.authUserId(), meal);
    }
}