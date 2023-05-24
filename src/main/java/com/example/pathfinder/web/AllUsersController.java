package com.example.pathfinder.web;

import com.example.pathfinder.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllUsersController {

    private final UserService userService;

    public AllUsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/all")
    public String allUsersInSystem(Model model){
        var allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "allUsers";
    }
}
