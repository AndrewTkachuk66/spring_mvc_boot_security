package app.service;

import app.model.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();

    void addUser(User user);

    void deleteUser(String id);

    User getUserById(String id);

    User getUserByLogin(String email);

    boolean validateDataFromUser(User user);
}
