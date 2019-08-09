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
            return "redirect:/index";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Beer> allBeers;
        allBeers = userRepository.getBeerByUser(user);
        Map<Integer, Integer> sumBeers = new LinkedHashMap<>();
        if (allBeers != null) {

            for (int i = 0; i < allBeers.size(); i++) {
                int count = 1;
                for (int j = i + 1; j < allBeers.size(); j++) {
                    if (allBeers.get(i).getId() == allBeers.get(j).getId()) {
                        count++;
                    }
                    if (!sumBeers.containsKey(allBeers.get(j).getId())) {
                        sumBeers.put(allBeers.get(j).getId(), count);
                    }
                }
            }

            Beer[] lastTen = new Beer[allBeers.size()>=10 ? 10 : allBeers.size()];
            for (int i = 0 ; i < lastTen.length ; i++) {
                lastTen[i]=allBeers.get(allBeers.size()-(i+1));
            }
            session.setAttribute("lastTen", lastTen);
            session.setAttribute("sumbeers", sumBeers);
            session.setAttribute("uniqueNum", sumBeers.size());
            session.setAttribute("totalNum", allBeers.size());
        }

        return "profile";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
