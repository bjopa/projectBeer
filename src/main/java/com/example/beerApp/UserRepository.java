package com.example.beerApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRepository {

    @Autowired
    private DataSource dataSource;

    @Autowired
    BeerRepository beerRepository;


    private User rsUser(ResultSet rs) throws SQLException {
        // Helper method to create a User object instantiated with data from the ResultSet
        return new User(rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"));
    }

    public User checkUser(String username, String password) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM UserEtt WHERE username=?")) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User foundUser = rsUser(rs);
                if (foundUser.getPassword().equals(password)) {
                    return foundUser;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setBeerByUser(int beerId, int userId, int beerRating) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO UserHistory(BeerID, UserID, Rating)\n" +
                     "VALUES (?, ?, ?)")) {
            ps.setInt(1, beerId);
            ps.setInt(2, userId);
            ps.setInt(3, beerRating);
            ps.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Beer> getBeerByUser(User user) {

        List<Beer> beers = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Beer " +
                     "INNER JOIN UserHistory ON UserHistory.BeerID = Beer.ID " +
                     "INNER JOIN UserEtt ON UserEtt.ID = UserHistory.UserID " +
                     "WHERE UserEtt.Username LIKE ?")) {
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                beers.add(beerRepository.rsBeer(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beers;
    }

    public void addUser(String username, String password) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO UserEtt(Username, Password) VALUES(?,?)")) {
            ps.setString(1,username);
            ps.setString(2,password);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
