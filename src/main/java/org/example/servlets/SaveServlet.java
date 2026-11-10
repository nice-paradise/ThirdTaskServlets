package org.example.servlets;

import org.example.models.User;
import org.example.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/save")
public class SaveServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int fieldsCount = userService.getCountForForm();

            req.setAttribute("countFields", fieldsCount);

            req.getRequestDispatcher("/jsp/save.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String[] emails = req.getParameterValues("email");
        String[] names = req.getParameterValues("name");
        String[] surnames = req.getParameterValues("surname");
        String[] passwords = req.getParameterValues("password");
        String[] ages = req.getParameterValues("age");
        String[] cities = req.getParameterValues("city");
        String[] carModels = req.getParameterValues("carModel");
        String[] ratings = req.getParameterValues("rating");

        List<User> driversList = new ArrayList<>();

        if (names != null) {
            for (int i = 0; i < names.length; i++) {
                User driver = User.builder()
                        .email(emails[i])
                        .name(names[i])
                        .surname(surnames[i])
                        .password(passwords[i])
                        .age(Integer.parseInt(ages[i]))
                        .city(cities[i])
                        .carModel(carModels[i])
                        .rating(Float.parseFloat(ratings[i]))
                        .build();

                driversList.add(driver);
            }
        }

        try {
            userService.saveUsers(driversList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect(req.getContextPath() + "/html/success.html");
    }
}
