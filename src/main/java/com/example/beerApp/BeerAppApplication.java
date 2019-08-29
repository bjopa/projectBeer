package com.example.beerApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class BeerAppApplication {

	public static void main(String[] args) throws SQLException{

//		BeerRepository beerRepository = new BeerRepository();
//		beerRepository.getBeer("dugges");

		SpringApplication.run(BeerAppApplication.class, args);
	}

}




