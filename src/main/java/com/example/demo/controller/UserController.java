package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping()
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/register")
    public String showUserForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "form-user";
    }
    @PostMapping("/register")
    public String saveRegister(@ModelAttribute("user") User user, BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            return "form-user";
        }
        service.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login-user";
    }
    @PostMapping("/login")
    public String accessLogin(@ModelAttribute User userLogin){
        try{
            User user = service.getUserByEmailAndPassword(userLogin.getEmail(), userLogin.getPassword());
            return "redirect:/record/"+user.getId();
        }
        catch (Exception e){
            return "redirect:/login";
        }
    }

    @GetMapping("/user/{userId}")
    public String userPage(@PathVariable("userId") Integer userId, Model model){
        User user = service.getUser(userId);
        model.addAttribute("user", user);
        return "user-config";
    }

    @PostMapping("/user/{userId}")
    public String saveEdit(@ModelAttribute("user") User user, @PathVariable("userId") Integer userId){
        User oldUser = service.getUser(userId);
        User editUser = service.convertUser(oldUser, user);
        editUser.setId(userId);
        service.save(editUser);
        return "redirect:/user/"+userId;
    }

    @GetMapping("/user/{userId}/delete")
    public String deleteUser( @PathVariable("userId") Integer userId){
        if (service.userExist(userId)){
            User delete = service.getUser(userId);
            service.delete(delete);
        }
        return "redirect:/login";
    }


    @GetMapping("/home")
    public String userHome(){
        return "user-page";
    }
}
