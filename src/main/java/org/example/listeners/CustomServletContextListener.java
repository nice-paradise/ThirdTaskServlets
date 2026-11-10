package org.example.listeners;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.example.repository.UserRepository;
import org.example.repository.UsersRepositoryJdbcImpl;
import org.example.services.UserService;
import org.example.services.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class CustomServletContextListener implements ServletContextListener {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "2938";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb_11_503";
    private static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);


        UserRepository userRepository = new UsersRepositoryJdbcImpl(dataSource);
        UserService userService = new UserServiceImpl(userRepository);

        servletContext.setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
