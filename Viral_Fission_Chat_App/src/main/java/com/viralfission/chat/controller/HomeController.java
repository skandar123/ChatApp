package com.viralfission.chat.controller;

import com.viralfission.chat.model.UserDtls;
import com.viralfission.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/signin")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute UserDtls user, HttpSession session){
        boolean f=userService.checkEmail(user.getEmail());
        if(f) {
            session.setAttribute("msg", "Email exists");
        } else {
            UserDtls userDetails = userService.createUser(user);
            if (userDetails == null) {
                session.setAttribute("msg","Error in server");
            }
        }
        return "redirect:/register";
    }


}
