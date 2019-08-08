package com.example.beerApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeerRepository {
    private List<Beer> beerList;

    public List<Beer> getBeer(String search) throws SQLException {

        String connstr = "jdbc:sqlserver://localhost;databaseName=SkumMasters;user=skumadmin;password=123;";

        String sql = "SELECT * FROM BEER WHERE BREWERY LIKE ? OR NAME LIKE ? OR STYLE LIKE ?";

        beerList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(connstr);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + search + "%");
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                beerList.add(rsBeer(rs));
            }

            for (int i = 0; i < beerList.size(); i++) {
                System.out.println(beerList.get(i).getBrewery());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return beerList;
    }

    Beer rsBeer(ResultSet rs) throws SQLException {
        return new Beer(rs.getInt("ID"),
                rs.getString("Name"),
                rs.getString("brewery"));
    }

    public List<Beer> getPage(int page, int pageSize, List<Beer> beerList) {
        int from = Math.max(0,page*pageSize);
        int to = Math.min(beerList.size(),(page+1)*pageSize);

        return beerList.subList(from, to);
    }

    public int numberOfPages() {
        return (int)Math.ceil(new Double(beerList.size()) / 1);
    }

}
