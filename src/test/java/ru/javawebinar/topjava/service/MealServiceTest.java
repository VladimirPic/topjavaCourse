package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    MealService service;

    @Test
    public void get() {
        Meal meal = service.get(100003, USER_ID);
        assertMatch(meal, userMeal_1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(100003, NOT_FOUND));
    }

    @Test
    public void delete() {
        service.delete(100003, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(100003, USER_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(100003, NOT_FOUND));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> filtredMeal = service.getBetweenInclusive(LocalDate.of(2020, Month.JANUARY, 30), LocalDate.of(2020, Month.JANUARY, 30), USER_ID);
        List<Meal> testList = Arrays.asList(userMeal_1, userMeal_2, userMeal_3, userMeal_4);
        assertMatch(filtredMeal, testList);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, userMeal_7, userMeal_6, userMeal_5, userMeal_4, userMeal_3, userMeal_2, userMeal_1);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(100003, USER_ID), getUpdated());
    }

    @Test
    public void updateNotFound() {
        assertThrows(NotFoundException.class, () -> service.update(userMeal_1, ADMIN_ID));
    }

    @Test
    public void create() {
        Meal createdMeal = service.create(getNew(), USER_ID);
        Integer newId = createdMeal.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(createdMeal, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "duplicate", 200), USER_ID));
    }
}