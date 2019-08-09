package com.example.beerApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.List;

@SpringBootApplication
public class BeerAppApplication {

	public static void main(String[] args) throws SQLException{


		BeerRepository beerRepository = new BeerRepository();
		beerRepository.getTopBeers();

		SpringApplication.run(BeerAppApplication.class, args);
	}

}




