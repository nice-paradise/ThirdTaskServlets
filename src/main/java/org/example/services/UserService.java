package org.example.services;

import org.example.models.User;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    int getCountForForm() throws SQLException;
    void saveUsers(List<User> users) throws SQLException;


    List<User> getByCity(String city) throws SQLException;
    List<User> getByCarModel(String carModel) throws SQLException;
    List<User> getByRating(Float rating) throws SQLException;
}
