package com.example.backend.controller;

import com.example.backend.model.AppUserDTO;
import com.example.backend.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return "Hii User";
    }

    @GetMapping("/logout")
    public void logout(HttpSession session){
        session.invalidate();
    }

    @PostMapping("/register")
    public String register(@RequestBody AppUserDTO appUserDTO){
       String username = userService.register(appUserDTO);
       return username;
    }
}
