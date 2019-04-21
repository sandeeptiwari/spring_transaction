package co.in.sandi.dao;

import co.in.sandi.model.User;

import java.util.List;

public interface UserDAO {
    void insertUser(User user);

    User getUserById(int userId);

    User getUser(String username);

    List<User> getUsers();
}
