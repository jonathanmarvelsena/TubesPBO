package controller;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.AccountStatus;
import model.Admin;
import model.DLC;
import model.Game;
import model.ItemStatus;
import model.Publisher;
import model.Review;
import model.User;

public class Controller {

    static DatabaseHandler conn = new DatabaseHandler();

    public Controller() {

    }

    // SELECT WHERE ini usn sama pw (punya user)
    public User getUser(String username, String password) {
        conn.connect();
        String query = "SELECT * FROM Account WHERE username ='" + username + "'&&password='" + password + "'";
        User user = new User();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setStatus(AccountStatus.valueOf(rs.getString("status")));
                user.setWallet(rs.getDouble("wallet"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.disconnect();
        return (user);
    }

    // SELECT WHERE ini usn sama pw (punya admin)
    public Admin getAdmin(String username, String password) {
        conn.connect();
        String query = "SELECT * FROM Account WHERE username ='" + username + "'&&password='" + password + "'";
        Admin admin = new Admin();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                admin.setId(rs.getInt("id"));
                admin.setName(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setStatus(AccountStatus.valueOf(rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.disconnect();
        return (admin);
    }

    // SELECT WHERE ini usn sama pw (punya publisher)
    public Publisher getPublisher(String username, String password) {
        conn.connect();
        String query = "SELECT * FROM Account WHERE username ='" + username + "'&&password='" + password + "'";
        Publisher publisher = new Publisher();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                publisher.setId(rs.getInt("id"));
                publisher.setName(rs.getString("username"));
                publisher.setPassword(rs.getString("password"));
                publisher.setStatus(AccountStatus.valueOf(rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.disconnect();
        return (publisher);
    }

    // INSERT (punya user)
    public boolean insertNewUser(User user) {
        conn.connect();
        String query = "INSERT INTO users VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getPassword());
            // Mengambil nilai string dari enum GenderEnum
            String statusString = user.getStatus().toString();
            stmt.setString(4, statusString); // Menyimpan nilai string ke dalam kolom gender  
            stmt.setDouble(5, user.getWallet());
            conn.disconnect();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            conn.disconnect();
            return (false);
        }
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
                ArrayList<Review> reviews = getReviewsForGame(game); // Implement getReviewsForGame method
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
        String query = "SELECT * FROM dlc d JOIN game g ON g.game_id = '" + game.getItemID() + "'";
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
                ArrayList<Review> reviews = getReviewsForDLC(dlc); // Implement getReviewsForGame method
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

    public ArrayList<Review> getReviewsForGame(Game game) {
        conn.connect();
        String query = "SELECT * FROM review WHERE item_id = " + game.getItemID();
        ArrayList<Review> reviews = new ArrayList<>();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Review review = new Review();
                review.setReviewID(rs.getInt("review_id"));

                // Set the associated game
                review.setItem(game);

                // Fetch the associated user (you need to implement getUserById method)
                User user = getUserById(rs.getInt("user_id"));
                review.setUser(user);

                // Set the review text
                review.setReviewText(rs.getString("comment"));

                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return reviews;
    }

    public ArrayList<Review> getReviewsForDLC(DLC dlc) {
        conn.connect();
        String query = "SELECT * FROM review WHERE item_id = " + dlc.getItemID();
        ArrayList<Review> reviews = new ArrayList<>();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Review review = new Review();
                review.setReviewID(rs.getInt("review_id"));

                // Set the associated DLC
                review.setItem(dlc);

                // Fetch the associated user (you need to implement getUserById method)
                User user = getUserById(rs.getInt("user_id"));
                review.setUser(user);

                // Set the review text
                review.setReviewText(rs.getString("comment"));

                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return reviews;
    }

    public User getUserById(int userId) {
        conn.connect();
        String query = "SELECT * FROM user WHERE id = " + userId;

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                int id = rs.getInt("id");

                return new User(name, password, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        // Return null if user not found
        return null;
    }

    public Game getGameById(int gameId) {
        conn.connect();
        String query = "SELECT * FROM game WHERE id = " + gameId;

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                // Retrieve other attributes based on your database schema
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int discountId = rs.getInt("discountid");
                String statusString = rs.getString("status");
                ItemStatus status = ItemStatus.valueOf(statusString);
                // Fetch other attributes as needed

                return new Game(gameId, name, description, price, discountId, null, status, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        // Return null if the game is not found
        return null;
    }

    public DLC getDLCById(int dlcId) {
        conn.connect();
        String query = "SELECT * FROM dlc WHERE dlc_id = " + dlcId;

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                // Retrieve other attributes based on your database schema
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int discountId = rs.getInt("discountid");
                String statusString = rs.getString("status");
                ItemStatus status = ItemStatus.valueOf(statusString);
                // Fetch other attributes as needed

                return new DLC(dlcId, name, description, price, discountId, null, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        // Return null if the DLC is not found
        return null;
    }

    public void getGameDetails(Game game) {
        conn.connect();
        String query = "SELECT * FROM games WHERE game_id = " + game.getItemID();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                game.setItemID(rs.getInt("id"));
                game.setName(rs.getString("name"));
                game.setDescription(rs.getString("description"));
                game.setPrice(rs.getInt("price"));
                game.setDiscountID(rs.getInt("discountid"));
                game.setCover(rs.getString("cover"));

                String statusString = rs.getString("status");
                ItemStatus status = ItemStatus.valueOf(statusString);
                game.setStatus(status);

                ArrayList<Review> reviews = getReviewsForGame(game);
                game.setReviews(reviews);

                ArrayList<DLC> dlcList = getDLCs(game);
                game.setDLC(dlcList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    public void getDLCDetails(DLC dlc) {
        conn.connect();
        String query = "SELECT * FROM dlcs WHERE dlc_id = " + dlc.getItemID();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                dlc.setItemID(rs.getInt("id"));
                dlc.setName(rs.getString("name"));
                dlc.setDescription(rs.getString("description"));
                dlc.setPrice(rs.getInt("price"));
                dlc.setDiscountID(rs.getInt("discountid"));
                dlc.setCover(rs.getString("cover"));

                String statusString = rs.getString("status");
                ItemStatus status = ItemStatus.valueOf(statusString);
                dlc.setStatus(status);

                ArrayList<Review> reviews = getReviewsForDLC(dlc);
                dlc.setReviews(reviews);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }
}
