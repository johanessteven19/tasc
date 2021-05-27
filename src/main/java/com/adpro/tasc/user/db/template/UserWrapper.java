package com.adpro.tasc.user.db.template;

import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.Role;
import com.adpro.tasc.user.db.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserWrapper implements UserDAO {

    List<User> userList = new ArrayList<User>();

    @Override
    public User getUser(String username) {
        return null;
    }

    public void addUser(String userName, String fullName, String password, Role role) {
        User user = new User(userName, fullName, password, role);
        userList.add(user);
    }

    public void setUserStudent(String userName, String fullName, String password) {
        User user = new User(userName, fullName, password, Role.ROLE_STUDENT);
        userList.add(user);
    }

    @Override
    public List<User> getAllUser() {
        return userList;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void updatePassword(String username, String password) {

    }

    @Override
    public void updateUser(String username, User user) {

    }

    @Override
    public void deleteUser(String username) {

    }
}
