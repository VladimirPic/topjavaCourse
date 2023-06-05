package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MealServlet extends HttpServlet {
    MealRepositoryImpl mealRepository = new MealRepositoryImpl();
//    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setAttribute("formatter", dateTimeFormatter);
        request.setAttribute("mealTo", mealRepository.getAllMealsWithExceedStatus());
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        String dateTimeString = request.getParameter("dateTime");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);
        meal.setDateTime(dateTime);
        meal.setDescription(request.getParameter("description"));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));

        String mealId = request.getParameter("id");
        if (mealId == null || mealId.isEmpty()) {
            mealRepository.createMeal(meal);
        } else {
            meal.setId(Integer.parseInt(mealId));
            mealRepository.updateMeal(meal.getId(), meal);
        }

        request.setAttribute("mealTo", mealRepository.getAllMealsWithExceedStatus());
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
