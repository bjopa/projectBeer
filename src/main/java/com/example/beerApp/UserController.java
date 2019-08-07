package com.example.beerApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(HttpSession session, @RequestParam String username, @RequestParam String password) {
        User user = userRepository.checkUser(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/welcome";
        }
        return "login";
    }

    @GetMapping("/welcome")
    public String welcome(HttpSession session) {
        User user = (User)session.getAttribute("user");
        List<Beer> beers = new ArrayList<>();
        beers = userRepository.getBeerByUser(user);
        if (beers!= null) {
            session.setAttribute("beers", beers);
        }
        return "welcome";
    }
}
