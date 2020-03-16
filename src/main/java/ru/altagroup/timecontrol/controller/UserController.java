package ru.altagroup.timecontrol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.altagroup.timecontrol.service.UserService;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }

}
