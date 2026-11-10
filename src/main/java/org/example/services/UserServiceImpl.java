package org.example.services;

import org.example.models.User;
import org.example.repository.UserRepository;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int getCountForForm() throws SQLException {
        return userRepository.getCountToGenerate();
    }

    @Override
    public void saveUsers(List<User> users) throws SQLException {
        for (User user : users) {
            userRepository.save(user);
        }
    }

    @Override
    public List<User> getByCity(String city) throws SQLException {
        return userRepository.findAllByCity(city);
    }

    @Override
    public List<User> getByCarModel(String carModel) throws SQLException {
        return userRepository.findAllByCarModel(carModel);
    }

    @Override
    public List<User> getByRating(Float rating) throws SQLException {
        return userRepository.findAllByRating(rating);
    }
}
