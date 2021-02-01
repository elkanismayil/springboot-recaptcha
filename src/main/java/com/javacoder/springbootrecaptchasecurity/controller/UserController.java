package com.javacoder.springbootrecaptchasecurity.controller;

import com.javacoder.springbootrecaptchasecurity.entity.User;
import com.javacoder.springbootrecaptchasecurity.service.IUserService;
import com.javacoder.springbootrecaptchasecurity.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping("/register")
    public String registerUser(Model model) {
        User user = new User();
        CaptchaUtil.getCaptcha(user);
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user, Model model) {
        if (user.getCaptcha().equals(user.getHiddenCaptcha())) {
            service.createUser(user);
            model.addAttribute("message", "User registered successfully");
            return "redirect:all-users";
        } else {
            model.addAttribute("message", "Invalid captcha");
            CaptchaUtil.getCaptcha(user);
            model.addAttribute("user", user);
        }
        return "registration";
    }

    @GetMapping("/all-users")
    public String getAllUsers(Model model){
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
