package com.example.beerApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String searchStart(){

        return "search";
    }

    @PostMapping("/result/{page}")
    public String searchResult(@RequestParam String search, @PathVariable int page, Model model, HttpSession session) throws SQLException {

        session.setAttribute("search", search);

        List<Beer> beerList = beerRepository.getBeer(search);
        beerList = beerRepository.getPage(page-1, 5, beerList);

        int numberOfPages = beerRepository.numberOfPages();

        model.addAttribute("beerList", beerList);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("currentPage", page);

        return"searchResult";
    }

    @GetMapping("/result/{page}")
    public String searchResult2(@PathVariable int page, Model model, HttpSession session, HttpServletRequest request) throws SQLException {

        String search = (String)session.getAttribute("search");

        BeerRepository repository = new BeerRepository();

        List<Beer> beerList = repository.getBeer(search);
        beerList = repository.getPage(page-1, 5, beerList);

        int numberOfPages = repository.numberOfPages();

        model.addAttribute("beerList", beerList);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("currentPage", page);

        return"searchResult";
    }

    @PostMapping ("/result/{page}/{beerId}")
    String rateBeer (@PathVariable int page, @PathVariable int beerId, HttpSession session, Model model, HttpServletRequest request) throws SQLException {

        String search = (String)session.getAttribute("search");

        List<Beer> beerList = beerRepository.getBeer(search);
        beerList = beerRepository.getPage(page-1, 5, beerList);

        int numberOfPages = beerRepository.numberOfPages();

        model.addAttribute("beerList", beerList);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("currentPage", page);

        int beerRating = Integer.parseInt(request.getParameter("beerRating"));

        userRepository.setBeerByUser(beerId, 2, beerRating);

        return"searchResult";
    }

}
