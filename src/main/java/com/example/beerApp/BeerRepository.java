package com.example.beerApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BeerRepository {

    private List<Beer> beerList;

    @Autowired
    private DataSource dataSource;

    public List<Beer> getBeer(String search) throws SQLException {

        beerList = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM BEER WHERE BREWERY LIKE ? OR NAME LIKE ? OR STYLE LIKE ?")) {

            ps.setString(1, "%" + search + "%");
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                beerList.add(rsBeer(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return beerList;
    }

    Beer rsBeer(ResultSet rs) throws SQLException {
        return new Beer(rs.getInt("ID"),
                rs.getString("Name"),
                rs.getString("Brewery"),
                rs.getString("Style"),
                rs.getDouble("Alcohol"),
                rs.getString("Description"),
                rs.getString("Picture"));

    }

    public List<Beer> getPage(int page, int pageSize, List<Beer> beerList) {
        int from = Math.max(0,page*pageSize);
        int to = Math.min(beerList.size(),(page+1)*pageSize);

        return beerList.subList(from, to);
    }

    public int numberOfPages() {
        return (int)Math.ceil(new Double(beerList.size()) / 4);
    }
     
}
