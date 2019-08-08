package com.example.beerApp;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Controller
public class SearchController {

//    @GetMapping("/")
//    public String searchStart(){
//
//        return "search";
//    }

    @PostMapping("/result/{page}")
    public String searchResult(@RequestParam String search, @PathVariable int page, Model model, HttpSession session) throws SQLException {

        session.setAttribute("search", search);

        BeerRepository repository = new BeerRepository();

        List<Beer> beerList = repository.getBeer(search);
        beerList = repository.getPage(page-1, 5, beerList);

        int numberOfPages = repository.numberOfPages();

        model.addAttribute("beerList", beerList);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("currentPage", page);

        return"searchResult";
    }

    @GetMapping("/result/{page}")
    public String searchResult2(@PathVariable int page, Model model, HttpSession session) throws SQLException {

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

}
