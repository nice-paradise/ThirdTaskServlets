package org.example.repository;

import org.example.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UserRepository {

    private DataSource dataSource;

    private static final String SQL_SAVE_USER_TO_BD = "insert into drivers (email, first_name, last_name, password, age, city, car_model, rating) values (?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_COUNT =  "SELECT param_value FROM settings WHERE param_key = 'users_count'";
    private static final String SQL_SELECT_USER_WHERE_CITY_IS =  "SELECT * FROM drivers WHERE city = ?";
    private static final String SQL_SELECT_USER_WHERE_RATING_IS =  "SELECT * FROM drivers WHERE rating = ?";
    private static final String SQL_SELECT_USER_WHERE_CAR_MODEL_IS =  "SELECT * FROM drivers WHERE car_model = ?";

    public UsersRepositoryJdbcImpl (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll() throws SQLException {
        return new ArrayList<>();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) throws SQLException {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_USER_TO_BD) ) {

            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getSurname());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setInt(5, entity.getAge());

            preparedStatement.setString(6, entity.getCity());
            preparedStatement.setString(7, entity.getCarModel());
            preparedStatement.setFloat(8, entity.getRating());
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void remove(User entity) throws SQLException {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void removeAll() throws SQLException {

    }

    @Override
    public void loadToDatabase(User[] entities) throws SQLException {

    }

    @Override
    public List<User> findAllByAge(Integer age) {
        return new ArrayList<>();
    }

    @Override
    public void findUsersOlderThan20() throws SQLException {

    }

    @Override
    public int getCountToGenerate() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COUNT);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            int count = 1;
            if (resultSet.next()) {
                count = resultSet.getInt("param_value");
            }
            return count;

        }
    }

    @Override
    public List<User> findAllByCity(String city) throws SQLException {
        List<User> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_WHERE_CITY_IS)) {
            preparedStatement.setString(1, city);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    User user = User.builder()
                            .id(resultSet.getLong("id"))
                            .name(resultSet.getString("first_name"))
                            .surname(resultSet.getString("last_name"))
                            .age(resultSet.getInt("age"))
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("password"))
                            .city(resultSet.getString("city"))
                            .carModel(resultSet.getString("car_model"))
                            .rating(resultSet.getFloat("rating"))
                            .build();

                    result.add(user);




                }
            }
        }
        return result;
    }

    @Override
    public List<User> findAllByRating(Float rating) throws SQLException {
        List<User> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_WHERE_RATING_IS)) {

            preparedStatement.setFloat(1, rating);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = User.builder()
                            .id(resultSet.getLong("id"))
                            .name(resultSet.getString("first_name"))
                            .surname(resultSet.getString("last_name"))
                            .age(resultSet.getInt("age"))
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("password"))
                            .city(resultSet.getString("city"))
                            .carModel(resultSet.getString("car_model"))
                            .rating(resultSet.getFloat("rating"))
                            .build();

                    result.add(user);


                }
            }
        }
        return result;
    }

    @Override
    public List<User> findAllByCarModel(String carModel) throws SQLException {
        List<User> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_WHERE_CAR_MODEL_IS)) {

            preparedStatement.setString(1, carModel);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = User.builder()
                            .id(resultSet.getLong("id"))
                            .name(resultSet.getString("first_name"))
                            .surname(resultSet.getString("last_name"))
                            .age(resultSet.getInt("age"))
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("password"))
                            .city(resultSet.getString("city"))
                            .carModel(resultSet.getString("car_model"))
                            .rating(resultSet.getFloat("rating"))
                            .build();

                    result.add(user);


                }
            }
        }
        return result;
    }



}
