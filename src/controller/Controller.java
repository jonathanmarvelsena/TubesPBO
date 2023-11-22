package controller;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.DLC;
import model.Game;
import model.ItemStatus;
import model.Review;

public class Controller {

    static DatabaseHandler conn = new DatabaseHandler();

    public Controller() {

    }

    public ArrayList<Game> getGames() {
        conn.connect();
        String query = "SELECT * FROM game";
        ArrayList<Game> games = new ArrayList<>();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Game game = new Game();
                game.setItemID(rs.getInt("id"));
                game.setName(rs.getString("name"));
                game.setDescription(rs.getString("description"));
                game.setPrice(rs.getInt("price"));
                game.setDiscountID(rs.getInt("discountid"));
                game.setCover(rs.getString("cover"));
                // Handling the ItemStatus enum
                String statusString = rs.getString("status");
                ItemStatus status = ItemStatus.valueOf(statusString); // Assuming statusString is a valid enum name
                game.setStatus(status);

                // Handling reviews
                ArrayList<Review> reviews = getReviewsForGame(game.getItemID()); // Implement getReviewsForGame method
                game.setReviews(reviews);

                // Handling DLC
                ArrayList<DLC> dlcList = getDLCs(game); // Implement getDLCForGame method
                game.setDLC(dlcList);

                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the connection when done
        }

        return games;
    }

    public ArrayList<DLC> getDLCs(Game game) {
        conn.connect();
        String query = "SELECT * FROM dlc d JOIN game g ON g.name = '" +game.getName() + "'";
        ArrayList<DLC> dlcs = new ArrayList<>();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                DLC dlc = new DLC();
                dlc.setItemID(rs.getInt("id"));
                dlc.setName(rs.getString("name"));
                dlc.setDescription(rs.getString("description"));
                dlc.setPrice(rs.getInt("price"));
                dlc.setDiscountID(rs.getInt("discountid"));
                dlc.setCover(rs.getString("cover"));
                // Handling the ItemStatus enum
                String statusString = rs.getString("status");
                ItemStatus status = ItemStatus.valueOf(statusString); // Assuming statusString is a valid enum name
                dlc.setStatus(status);

                // Handling reviews
                ArrayList<Review> reviews = getReviewsForGame(game.getItemID()); // Implement getReviewsForGame method
                dlc.setReviews(reviews);

                dlcs.add(dlc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the connection when done
        }

        return dlcs;
    }
    }

}
