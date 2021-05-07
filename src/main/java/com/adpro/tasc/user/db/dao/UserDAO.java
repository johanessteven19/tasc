package com.adpro.tasc.user.db.dao;

import com.adpro.tasc.user.db.model.User;

import java.util.List;

public interface UserDAO {
    User getUser(String username);
    List<User> getAllUser();

    void createUser(User user);
    void updatePassword(String username, String password);

    void updateUser(String username, User user);
    void deleteUser(String username);
}
