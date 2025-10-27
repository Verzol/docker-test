package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepo.findAll());
        
        model.addAttribute("newUser", new User());
        
        return "index"; 
    }

    @PostMapping("/add")
    public String createUser(@ModelAttribute User newUser) {
        userRepo.save(newUser); 
        
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
        
        return "redirect:/";
    }
}