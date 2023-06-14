package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll() {
        log.info("getAll");
        return MealsUtil.getTos(service.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay());
    }

    public List<MealTo> getAllByDateTime(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        log.info("getAllByDateTime");
        return MealsUtil.getTos(service.getAllByDateTime(SecurityUtil.authUserId(), startDate, endDate, startTime, endTime), SecurityUtil.authUserCaloriesPerDay());
    }

    public Meal getById(int mealId) {
        log.info("getById");
        return service.get(SecurityUtil.authUserId(), mealId);
    }

    public void delete(int mealId) {
        log.info("delete");
        service.delete(SecurityUtil.authUserId(), mealId);
    }

    public Meal create(Meal meal) {
        log.info("save");
        return service.create(SecurityUtil.authUserId(), meal);
    }

    public void update(int userId, Meal meal) {
        log.info("update");
        service.update(userId, meal);
    }
}