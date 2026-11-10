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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/console")
public class ConsoleServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        writer.println("<h1>Console Servlet Active!</h1>");
        writer.println("<p>Click on a link to check constraints output in IntelliJ Run log:</p>");
        writer.println("<ul>");
        writer.println("  <li><a href='?city=Kazan'>Filter by City (Kazan)</a></li>");
        writer.println("  <li><a href='?carModel=Kia'>Filter by Car Model (Kia)</a></li>");
        writer.println("  <li><a href='?rating=4.8'>Filter by Rating (4.8)</a></li>");
        writer.println("</ul>");

        String cityParam = req.getParameter("city");
        String carModelParam = req.getParameter("carModel");
        String ratingParam = req.getParameter("rating");

        try {
            if (cityParam != null && !cityParam.isEmpty()) {
                System.out.println(" FILTER BY CITY ");

                List<User> users = userService.getByCity(cityParam);
                for (User u : users) {
                    System.out.println("City: " + u.getCity() + " | Driver: " + u.getName() + " " + u.getSurname());
                }
            }


            if (carModelParam != null && !carModelParam.isEmpty()) {
                System.out.println(" FILTER BY CAR MODEL ");
                List<User> users = userService.getByCarModel(carModelParam);
                for (User u : users) {
                    System.out.println("Car: " + u.getCarModel() + " | Driver: " + u.getName() + " " + u.getSurname());
                }
            }


            if (ratingParam != null && !ratingParam.isEmpty()) {
                System.out.println(" FILTER BY RATING ");
                Float ratingValue = Float.parseFloat(ratingParam);
                List<User> users = userService.getByRating(ratingValue);
                for (User u : users) {
                    System.out.println("Rating: " + u.getRating() + " | Driver: " + u.getName() + " " + u.getSurname());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
