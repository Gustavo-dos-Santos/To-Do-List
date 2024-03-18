package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> getAllUsers();
    void delete(User user);
    User getUser(int id);
    void edit (User user);
    User getUserByEmailAndPassword(String email, String password);

    User convertUser(User main, User convert);

    boolean userExist(Integer userId);

    boolean validUser(User user);
}
