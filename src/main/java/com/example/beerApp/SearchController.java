package com.example.beerApp;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Controller
public class SearchController {

    @GetMapping("/")
    public String searchStart(){

        return "search";
    }

    @PostMapping("/{page}")
    public String searchResult(@RequestParam String search, @PathVariable int page, Model model) throws SQLException {

        BeerRepository repository = new BeerRepository();

        List<Beer> beerList = repository.getBeer(search);
        beerList = repository.getPage(page, 1, beerList);

        int numberOfPages = repository.numberOfPages();

        model.addAttribute("Beers", beerList);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("currentPage", page);

        return"searchResult";
    }

    @GetMapping("/{page}")
    public String searchResult2(@RequestParam(required = false, defaultValue = "n") String search, @PathVariable int page, Model model) throws SQLException {
        
        BeerRepository repository = new BeerRepository();

        List<Beer> beerList = repository.getBeer(search);
        beerList = repository.getPage(page, 1, beerList);

        int numberOfPages = repository.numberOfPages();

        model.addAttribute("Beers", beerList);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("currentPage", page);

        return"searchResult";
    }

}
