package com.example.beerApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeerRepository {


    public List<Beer> getBeer(String search) throws SQLException {

        String connstr = "jdbc:sqlserver://localhost;databaseName=SkumMasters;user=skumadmin;password=123;";

        String sql = "SELECT * FROM BEER WHERE BREWERY LIKE ? OR NAME LIKE ?";

        List<Beer> resultList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(connstr);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, search);
            ps.setString(2, search);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                resultList.add(rsBeer(rs));
            }

            for (int i = 0; i < resultList.size(); i++) {
                System.out.println(resultList.get(i).getBrewery());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    private Beer rsBeer(ResultSet rs) throws SQLException {
        return new Beer(rs.getInt("ID"),
                rs.getString("Name"),
                rs.getString("brewery"));
    }
}
