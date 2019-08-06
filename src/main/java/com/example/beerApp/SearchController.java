package com.example.beerApp;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

        return"search";
    }

    @PostMapping("/")
    public String searchResult(@RequestParam String search, Model model) throws SQLException {

        BeerRepository repository = new BeerRepository();
        List<Beer> resultList = repository.getBeer(search);
        model.addAttribute("Result", resultList);

        return"searchResult";
    }

}
