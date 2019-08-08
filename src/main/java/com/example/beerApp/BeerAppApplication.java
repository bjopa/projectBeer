package com.example.beerApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class BeerAppApplication {

	public static void main(String[] args) throws SQLException{

		BeerRepository beerRepository = new BeerRepository();
		beerRepository.getBeer("dugges");

		SpringApplication.run(BeerAppApplication.class, args);
	}



//		String connstr = "jdbc:sqlserver://localhost;databaseName=SkumMasters;user=skumadmin;password=123;";

//		String connstr = "jdbc:sqlserver://skummasters.database.windows.net:1433;database=SkumMaster;user=skumadmin@skummasters;password=Hej12345;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
//
//		String sql = "SELECT * FROM BEER";
//
//		try (Connection conn = DriverManager.getConnection(connstr)) {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//
//			if (rs.next()) {
//				System.out.println(rs.findColumn("Style"));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

}




