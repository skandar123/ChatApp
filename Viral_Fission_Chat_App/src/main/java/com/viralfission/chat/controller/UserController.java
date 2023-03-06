package com.viralfission.chat.controller;

import com.viralfission.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String listContacts(Model model){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUserName=authentication.getName();
            model.addAttribute("currentUser",currentUserName);
        }
        model.addAttribute("contacts", userService.getAllContacts());
        return "user/home";
    }

    @GetMapping("/chat/{id}")
    public String chat(@PathVariable int id, Model model){
        model.addAttribute("chatting_with", userService.getMemberById(id));
        return "user/chat";
    }
}
