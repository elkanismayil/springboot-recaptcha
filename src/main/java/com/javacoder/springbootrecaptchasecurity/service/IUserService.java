package com.javacoder.springbootrecaptchasecurity.service;

import com.javacoder.springbootrecaptchasecurity.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    void createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserByUserId(Integer id);
}
