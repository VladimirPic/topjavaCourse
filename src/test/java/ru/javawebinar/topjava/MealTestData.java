package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int NOT_FOUND = 10;

    public static final Meal userMeal_1 = new Meal(100003,
            LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак USER",
            500);

    public static final Meal userMeal_2 = new Meal(100004,
            LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед USER",
            1000);

    public static final Meal userMeal_3 = new Meal(100005,
            LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин USER",
            500);

    public static final Meal userMeal_4 = new Meal(100006,
            LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение USER",
            100);

    public static final Meal userMeal_5 = new Meal(100007,
            LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак USER",
            1000);

    public static final Meal userMeal_6 = new Meal(100008,
            LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед USER",
            500);

    public static final Meal userMeal_7 = new Meal(100009,
            LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин USER",
            410);

    public static final Meal adminMeal_1 = new Meal(100010,
            LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак ADMIN",
            500);

    public static final Meal adminMeal_2 = new Meal(100011,
            LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед ADMIN",
            1000);

    public static final Meal adminMeal_3 = new Meal(100012,
            LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин ADMIN",
            500);

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2021, Month.JANUARY, 25, 20, 0), "Ужин ADMIN",
                120);
    }

    public static Meal getUpdated() {
        Meal updated = userMeal_1;
        updated.setDateTime(LocalDateTime.of(2020, Month.JANUARY, 30, 16, 0));
        updated.setDescription("Полдник USER");
        updated.setCalories(100);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
