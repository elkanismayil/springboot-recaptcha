package com.javacoder.springbootrecaptchasecurity.service;

import com.javacoder.springbootrecaptchasecurity.entity.User;
import com.javacoder.springbootrecaptchasecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository repo;

    @Override
    public void createUser(User user) {
        repo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = repo.findAll();
        return users;
    }

    @Override
    public Optional<User> getUserByUserId(Integer id) {
        Optional<User> optionalUser = repo.findById(id);
        return optionalUser;
    }
}
