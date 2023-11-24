package controller;

import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Account;
import model.AccountStatus;
import model.Admin;
import model.DLC;
import model.Game;
import model.Item;
import model.ItemStatus;
import model.Publisher;
import model.Review;
import model.ShoppingCart;
import model.Transaction;
import model.User;

public class Controller {
    private static Controller instance;

    static DatabaseHandler conn = new DatabaseHandler();

    public Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Account getUser(String username, String password) {
        conn.connect();
        String queryUser = "SELECT * FROM users WHERE username = ? AND password = ?";
        String queryAdmin = "SELECT * FROM admin WHERE username = ? AND password = ?";
        String queryPublisher = "SELECT * FROM publisher WHERE username = ? AND password = ?";

        Account account = null;

        try {
            // Check for user
            PreparedStatement stmtUser = conn.con.prepareStatement(queryUser);
            stmtUser.setString(1, username);
            stmtUser.setString(2, password);
            ResultSet rsUser = stmtUser.executeQuery();

            if (rsUser.next()) {
                User user = new User();
                user.setId(rsUser.getInt("user_id"));
                user.setName(rsUser.getString("username"));
                user.setPassword(rsUser.getString("password"));
                user.setStatus(AccountStatus.valueOf(rsUser.getString("user_status")));
                user.setWallet(rsUser.getDouble("wallet"));
                account = user;
            }

            // Check for admin if not found
            if (account == null) {
                PreparedStatement stmtAdmin = conn.con.prepareStatement(queryAdmin);
                stmtAdmin.setString(1, username);
                stmtAdmin.setString(2, password);
                ResultSet rsAdmin = stmtAdmin.executeQuery();

                if (rsAdmin.next()) {
                    Admin admin = new Admin();
                    admin.setId(rsAdmin.getInt("admin_id"));
                    admin.setName(rsAdmin.getString("username"));
                    admin.setPassword(rsAdmin.getString("password"));
                    admin.setStatus(AccountStatus.valueOf(rsAdmin.getString("admin_status")));
                    account = admin;
                }
            }

            // Check for publisher if not found
            if (account == null) {
                PreparedStatement stmtPublisher = conn.con.prepareStatement(queryPublisher);
                stmtPublisher.setString(1, username);
                stmtPublisher.setString(2, password);
                ResultSet rsPublisher = stmtPublisher.executeQuery();

                if (rsPublisher.next()) {
                    Publisher publisher = new Publisher();
                    publisher.setId(rsPublisher.getInt("publisher_id"));
                    publisher.setName(rsPublisher.getString("username"));
                    publisher.setPassword(rsPublisher.getString("password"));
                    publisher.setStatus(AccountStatus.valueOf(rsPublisher.getString("publisher_status")));
                    account = publisher;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        conn.disconnect();
        return account;
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
            String statusString = user.getStatus().toString();
            stmt.setString(4, statusString);
            stmt.setDouble(5, user.getWallet());

            // Execute the SQL statement
            int rowsAffected = stmt.executeUpdate();

            // Check if the insertion was successful
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect(); // Make sure to close the connection in the finally block
        }
    }

    // INSERT (punya item = game)
    public boolean insertNewGame(Game game, Publisher publisher) {
        conn.connect();
        String query = "INSERT INTO item VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, game.getItemID());
            stmt.setString(2, game.getName());
            stmt.setString(3, "Game");
            stmt.setDouble(4, game.getPrice());
            stmt.setString(5, game.getDescription());
            stmt.setInt(6, publisher.getId());
    
            // Handling null status
            String statusString = (game.getStatus() != null) ? game.getStatus().toString() : "AVAILABLE";
            stmt.setString(7, statusString);

            // Execute the SQL statement
            int rowsAffected = stmt.executeUpdate();

            // Check if the insertion was successful
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect(); 
        }
    }


    // INSERT (punya item = dlc)
    public boolean insertNewDLC(DLC game, Publisher publisher) {
        conn.connect();
        String query = "INSERT INTO item VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, game.getItemID());
            stmt.setString(2, game.getName());
            stmt.setString(3, "DLC");
            stmt.setDouble(4, game.getPrice());
            stmt.setString(5, game.getDescription());
            stmt.setInt(6, publisher.getId());
            // Handling null status
            String statusString = (game.getStatus() != null) ? game.getStatus().toString() : "AVAILABLE";
            stmt.setString(7, statusString);

            // Execute the SQL statement
            int rowsAffected = stmt.executeUpdate();

            // Check if the insertion was successful
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }

    // update wallet user
    public boolean updateWallet(User user, double topUpAmount) {
        conn.connect();
        double currentWalletAmount = user.getWallet();
        double updatedWalletAmount = currentWalletAmount + topUpAmount;
        String query = "UPDATE users SET wallet = " + updatedWalletAmount + " WHERE user_id = " + user.getId();
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }

    public ArrayList<User> getUserList(){
        conn.connect();
        String query = "SELECT * FROM users WHERE user_status = 'NOT_BANNED'";
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setStatus(AccountStatus.valueOf(rs.getString("user_status")));
                user.setWallet(rs.getDouble("wallet"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // UPDATE status jadi banned
    public boolean updateStatusUser(int id) {
        conn.connect();
        String query = "UPDATE users SET user_status= 'BANNED'"
                + "WHERE user_id='" + id + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public ArrayList<User> getUserBanned() {
        conn.connect();
        String query = "SELECT * FROM users WHERE user_status= 'BANNED'";
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setStatus(AccountStatus.valueOf(rs.getString("user_status")));
                user.setWallet(rs.getDouble("wallet"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean updateStatusItem(int id) {
        conn.connect();
        String query = "UPDATE item SET item_status= 'NOT_AVAILABLE'"
                + "WHERE item_id='" + id + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public ArrayList<Item> getRemoveItem() {
        conn.connect();
        String query = "SELECT * FROM item WHERE item_status = 'NOT_AVAILABLE'";
    
        ArrayList<Item> items = new ArrayList<>();
    
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rsGame = stmt.executeQuery(query);
            while (rsGame.next()) {
                Item game = new Item();
                game.setItemID(rsGame.getInt("item_id"));
                game.setName(rsGame.getString("name"));
                game.setType(rsGame.getString("type"));
                game.setPrice(rsGame.getDouble("price"));
                game.setDescription(rsGame.getString("description"));
                game.setPublisherID(rsGame.getInt("publisher_id"));
                game.setStatus(ItemStatus.valueOf(rsGame.getString("item_status")));
                items.add(game);
            }  
        } catch (SQLException e) {
            e.printStackTrace();
        }   
        return items;
    }
    

    public ArrayList<Game> getGames() {
        conn.connect();
        String query = "SELECT * FROM item WHERE type = 'Game'";
        ArrayList<Game> games = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Game game = new Game();
                game.setItemID(rs.getInt("item_id"));
                game.setName(rs.getString("name"));
                game.setType(rs.getString("type"));
                game.setDescription(rs.getString("description"));
                game.setPrice(rs.getInt("price"));
                game.setPublisherID(rs.getInt("publisher_id"));

                // Handling the ItemStatus enum
                String statusString = rs.getString("item_status");
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

    public ArrayList<Game> getPublishedGames(Publisher publisher) {
        conn.connect();
        String query = "SELECT * FROM item WHERE type = 'Game' AND publisher_id = "+ publisher.getId();
        ArrayList<Game> games = new ArrayList<>();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Game game = new Game();
                game.setItemID(rs.getInt("item_id"));
                game.setName(rs.getString("name"));
                game.setType(rs.getString("type"));
                game.setDescription(rs.getString("description"));
                game.setPrice(rs.getInt("price"));
                game.setPublisherID(rs.getInt("publisher_id"));

                // Handling the ItemStatus enum
                String statusString = rs.getString("item_status");
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

    public ArrayList<DLC> getPublishedDLC(Publisher publisher) {
        conn.connect();
        String query = "SELECT * FROM item WHERE type = 'DLC' AND publisher_id = "+ publisher.getId();
        ArrayList<DLC> games = new ArrayList<>();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                DLC game = new DLC();
                game.setItemID(rs.getInt("item_id"));
                game.setName(rs.getString("name"));
                game.setType(rs.getString("type"));
                game.setDescription(rs.getString("description"));
                game.setPrice(rs.getInt("price"));
                game.setPublisherID(rs.getInt("publisher_id"));

                // Handling the ItemStatus enum
                String statusString = rs.getString("item_status");
                ItemStatus status = ItemStatus.valueOf(statusString); // Assuming statusString is a valid enum name
                game.setStatus(status);

                // Handling reviews
                ArrayList<Review> reviews = getReviewsForDLC(game); // Implement getReviewsForGame method
                game.setReviews(reviews);


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
        String query = "SELECT * FROM item i JOIN game_dlc_relation g ON g.game_id = '" + game.getItemID()
                + "' WHERE i.type = 'DLC'";
        ArrayList<DLC> dlcs = new ArrayList<>();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                DLC dlc = new DLC();
                dlc.setItemID(rs.getInt("id"));
                dlc.setName(rs.getString("name"));
                dlc.setType(rs.getString("type"));
                dlc.setDescription(rs.getString("description"));
                dlc.setPrice(rs.getInt("price"));
                dlc.setPublisherID(rs.getInt("publisher_id"));
                // dlc.setStatus(ItemStatus.valueOf(rs.getString("item_status")));
                // Handling the ItemStatus enum
                String statusString = rs.getString("item_status");
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

    // public Game getGameById(int gameId) {
    //     conn.connect();
    //     String query = "SELECT * FROM item WHERE item_id = " + gameId;

    //     try {
    //         Statement stmt = conn.con.createStatement();
    //         ResultSet rs = stmt.executeQuery(query);

    //         if (rs.next()) {
    //             // Retrieve other attributes based on your database schema
    //             String name = rs.getString("name");
    //             String description = rs.getString("description");
    //             String type = "Game";
    //             double price = rs.getDouble("price");
    //             int publisherID = rs.getInt("publisher_id");
    //             String statusString = rs.getString("item_status");
    //             ItemStatus status = ItemStatus.valueOf(statusString);
    //             // Fetch other attributes as needed

    //             return new Game(gameId, name, type, description, price, publisherID, null);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     } finally {
    //         conn.disconnect();
    //     }

    //     // Return null if the game is not found
    //     return null;
    // }

    // public DLC getDLCById(int dlcId) {
    //     conn.connect();
    //     String query = "SELECT * FROM item WHERE item_id = " + dlcId;

    //     try {
    //         Statement stmt = conn.con.createStatement();
    //         ResultSet rs = stmt.executeQuery(query);

    //         if (rs.next()) {
    //             // Retrieve other attributes based on your database schema
    //             String name = rs.getString("name");
    //             String description = rs.getString("description");
    //             double price = rs.getDouble("price");
    //             int discountId = rs.getInt("discountid");
    //             String statusString = rs.getString("status");
    //             ItemStatus status = ItemStatus.valueOf(statusString);
    //             // Fetch other attributes as needed

    //             return new DLC(dlcId, name, description, price, discountId, null, status);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     } finally {
    //         conn.disconnect();
    //     }

    //     // Return null if the DLC is not found
    //     return null;
    // }

    public void getGameDetails(Game game) {
        conn.connect();
        String query = "SELECT * FROM item WHERE item_id = " + game.getItemID();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                game.setItemID(rs.getInt("item_id"));
                game.setName(rs.getString("name"));
                game.setType(rs.getString("type"));
                game.setDescription(rs.getString("description"));
                game.setPrice(rs.getInt("price"));
                game.setPublisherID(rs.getInt("publisher_id"));

                String statusString = rs.getString("item_status");
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
        String query = "SELECT * FROM item i WHERE item_id = " + dlc.getItemID();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                dlc.setItemID(rs.getInt("item_id"));
                dlc.setName(rs.getString("name"));
                dlc.setType(rs.getString("type"));
                dlc.setDescription(rs.getString("description"));
                dlc.setPrice(rs.getInt("price"));
                dlc.setPublisherID(rs.getInt("publisher_id"));
                String statusString = rs.getString("item_status");
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

    public boolean updateGame(Game game, String name, String price, String description) {
        conn.connect();
        String query = "UPDATE item"
                + " SET name='" + name + "',"
                + "price='" + price + "',"
                + "description='" + description
                + "WHERE item_id = " + game.getItemID();
        PreparedStatement stmt;
        try {
            stmt = conn.con.prepareStatement(query);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDLC(DLC dlc, String name, String price, String description) {
        conn.connect();
        String query = "UPDATE item"
                + " SET name='" + name + "',"
                + "price='" + price + "',"
                + "description='" + description
                + "WHERE item_id = " + dlc.getItemID();
        PreparedStatement stmt;
        try {
            stmt = conn.con.prepareStatement(query);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // public boolean removeGame(Game game) {
    //     conn.connect();
    //     String query = "UPDATE item"
    //             + " SET item_status='NOT_AVAILABLE'"
    //             + "WHERE item_id = " + game.getItemID();
    //     PreparedStatement stmt;
    //     try {
    //         stmt = conn.con.prepareStatement(query);
    //         stmt.executeUpdate();
    //         return true;
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return false;
    //     }
    // }

    // public boolean removeDLC(DLC dlc) {
    //     conn.connect();
    //     String query = "UPDATE item"
    //             + " SET item_status='NOT_AVAILABLE'"
    //             + "WHERE item_id = " + dlc.getItemID();
    //     PreparedStatement stmt;
    //     try {
    //         stmt = conn.con.prepareStatement(query);
    //         stmt.executeUpdate();
    //         return true;
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return false;
    //     }
    // }

    public boolean insertIntoShoppingCart(User user, Item item) {
        ShoppingCart cart = user.getCart();

        for (Item cartItem : cart.getItems()) {
            if (cartItem.getItemID() == item.getItemID()) {
                // Item is already in the cart
                // You can update the quantity or take other actions
                return false; // Indicates that the item was not added to the cart
            }
        }

        // Item is not in the cart, add it
        cart.addItem(item);
        return true; // Indicates successful addition to the cart
    }

    public ArrayList<Transaction> getTransactions() {
        conn.connect();
        String query = "SELECT * FROM transaction";
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionID(rs.getInt("transaction_id"));
                transaction.setUserID(rs.getInt("user_id"));
                transaction.setDate(rs.getTimestamp("transaction_date"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the connection when done
        }

        return transactions;
    }

    public boolean purchase(User user, ShoppingCart cart) {
        conn.connect();
    
        try {
            // Insert into transaction table
            String transactionQuery = "INSERT INTO transaction (user_id, transaction_date) VALUES (?, ?)";
            PreparedStatement transactionStmt = conn.con.prepareStatement(transactionQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            transactionStmt.setInt(1, user.getId());
            transactionStmt.setTimestamp(2, Timestamp.from(Instant.now()));
    
            int rowsAffected = transactionStmt.executeUpdate();
    
            // Check if the insertion into transaction was successful
            if (rowsAffected > 0) {
                // Retrieve the generated transaction ID
                ResultSet generatedKeys = transactionStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int transactionId = generatedKeys.getInt(1);
    
                    // Insert into shoppingcart table
                    String shoppingCartQuery = "INSERT INTO shoppingcart (transaction_id, item_id, description) VALUES (?, ?, ?)";
                    PreparedStatement shoppingCartStmt = conn.con.prepareStatement(shoppingCartQuery);
    
                    for (Item item : cart.getItems()) {
                        shoppingCartStmt.setInt(1, transactionId);
                        shoppingCartStmt.setInt(2, item.getItemID());
                        shoppingCartStmt.setString(3, "Item purchase");  // Adjust this accordingly
    
                        // Execute the SQL statement for shoppingcart
                        shoppingCartStmt.executeUpdate();
                    }
    
                    return true;
                }
            }
    
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }

    public ArrayList<ShoppingCart> getShoppingCart(User user) {
        conn.connect();
        String query = "SELECT * FROM shoppingcart sc JOIN transaction t ON t.transaction_id = sc.transaction_id WHERE t.user_id = "+user.getId();
        ArrayList<ShoppingCart> transactions = new ArrayList<>();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ShoppingCart transaction = new ShoppingCart();
                transaction.setTransactionID(rs.getInt("transaction_id"));
                transaction.setitemID(rs.getInt("item_id"));
                transaction.setDescription(rs.getString("description"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the connection when done
        }

        return transactions;
    }

    public ArrayList<ShoppingCart> getShoppingCart(User user, int month, int year) {
        conn.connect();
        String query = "SELECT * FROM shoppingcart sc JOIN transaction t ON t.transaction_id = sc.transaction_id WHERE MONTH(t.transaction_date) = "+month+" YEAR(t.transaction_id)";
        ArrayList<ShoppingCart> transactions = new ArrayList<>();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ShoppingCart transaction = new ShoppingCart();
                transaction.setTransactionID(rs.getInt("transaction_id"));
                transaction.setitemID(rs.getInt("item_id"));
                transaction.setDescription(rs.getString("description"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the connection when done
        }

        return transactions;
    }

    public ArrayList<Item> getItem() {
        conn.connect();
        String query = "SELECT * FROM item WHERE item_status= 'AVAILABLE'";
        ArrayList<Item> items = new ArrayList<>();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Item item = new Item();
                item.setItemID(rs.getInt("item_id"));
                item.setName(rs.getString("name"));
                item.setType(rs.getString("type"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getInt("price"));
                item.setPublisherID(rs.getInt("publisher_id"));

                // Handling the ItemStatus enum
                String statusString = rs.getString("item_status");
                ItemStatus status = ItemStatus.valueOf(statusString); // Assuming statusString is a valid enum name
                item.setStatus(status);

                // Handling reviews
                if(item instanceof Game){
                    Game game = (Game) item;
                ArrayList<Review> reviews = getReviewsForGame(game); // Implement getReviewsForGame method
                item.setReviews(reviews);
                }else if(item instanceof DLC){
                    DLC dlc = (DLC) item;
                    ArrayList<Review> reviews = getReviewsForDLC(dlc); // Implement getReviewsForGame method
                item.setReviews(reviews);
                }
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the connection when done
        }

        return items;
    }
}
