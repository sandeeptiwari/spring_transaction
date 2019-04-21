package co.in.sandi.tx;

import co.in.sandi.model.User;

import java.util.List;

public interface UserManager {
    void insertUser(User user);

    User getUserById(int userId);

    User getUser(String username);

    List<User> getUsers();
}
