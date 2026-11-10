package org.example.repository;

import org.example.models.User;
import java.sql.SQLException;
import java.util.List;

public interface UserRepository extends CrudRepository<User> {

    List<User> findAllByAge(Integer age);
    void findUsersOlderThan20() throws SQLException;

    int getCountToGenerate() throws SQLException;

    List<User> findAllByCity(String city) throws SQLException;
    List<User> findAllByCarModel(String carModel) throws SQLException;
    List<User> findAllByRating(Float rating) throws SQLException;
}
