package com.example.beerApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String startPage(HttpSession session) {
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(HttpSession session, @RequestParam String username, @RequestParam String password) {
        User user = userRepository.checkUser(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/profile";
        }
        return "login";
    }

    @GetMapping("/profile")
    public String welcome(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Beer> allBeers;
        allBeers = userRepository.getBeerByUser(user);
        Map<Beer, Integer> sumBeers = new HashMap<>();
        if (allBeers != null) {
            for (int i = 0; i < allBeers.size() - 1; i++) {
                int count = 1;
                for (int j = i + 1; j < allBeers.size(); j++) {
                    if (allBeers.get(i).getId() == allBeers.get(j).getId()) {
                        count++;
                    }
                }
                if (!sumBeers.containsKey(allBeers.get(i))) {
                    sumBeers.put(allBeers.get(i), count);
                }
            }
            session.setAttribute("sumbeers", sumBeers);
        }
        return "profile";
    }
}
