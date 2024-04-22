package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import controller.Controller;

import model.Publisher;
import model.User;
import model.DLC;
import model.Game;
import model.Item;

import javax.swing.JOptionPane;

public class AddItemView {
    Controller con = Controller.getInstance();
    JFrame addItem;
    JLabel addItemMenu = new JLabel("Add Item");
    JLabel gameOrDLC = new JLabel("Add Game or DLC : ");
    JTextField gameNameField;
    JLabel gameName = new JLabel("Name : ");
    JTextField descriptionField;
    JLabel description = new JLabel("Description              : ");
    JTextField priceField;
    JLabel price = new JLabel("Price                      : ");
    JLabel gameList = new JLabel("Game                      : ");
    JButton btnSubmit = new JButton("Submit");
    JButton btnBack = new JButton("Back");
    JRadioButton addGame = new JRadioButton("Game");
    JRadioButton addDLC = new JRadioButton("DLC");
    JComboBox<String> comboBox = null;

    int currY = 25;

    public void addItemPair(Component left, Component right, int width, int height) {
        if (left != null) {
            left.setBounds(20, currY, width, height);
            addItem.add(left);
        }
        if (right != null) {
            right.setBounds(130, currY, width, height);
            addItem.add(right);
        }
        currY += height + 5;
    }

    public void addItemPair(Component left, JRadioButton[] right, int width, int height, int interval) {
        if (left != null) {
            left.setBounds(20, currY, width, height);
            addItem.add(left);
        }
        int temp = 130;
        ButtonGroup bgAddItem = new ButtonGroup();
        for (JRadioButton c : right) {
            c.setBounds(temp, currY, interval, height);
            temp += interval;
            bgAddItem.add(c);
            addItem.add(c);
        }
        currY += height + 5;
    }

    public AddItemView(Publisher publisher) {
        addItem = new JFrame("Add Item");
        addItem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addItem.setLocationRelativeTo(null);
        addItem.setLayout(null);
        addItem.getContentPane().setBackground(Color.DARK_GRAY);

        addItemMenu.setForeground(Color.WHITE);
        addItemPair(addItemMenu, null, 170, 23);

        JSeparator separatorLine = new JSeparator();
        separatorLine.setForeground(Color.LIGHT_GRAY);
        addItemPair(separatorLine, null, 60, 23);

        gameOrDLC.setForeground(Color.WHITE);
        addGame.setBackground(Color.DARK_GRAY);
        addGame.setForeground(Color.WHITE);
        addDLC.setBackground(Color.DARK_GRAY);
        addDLC.setForeground(Color.WHITE);
        addItemPair(gameOrDLC, new JRadioButton[] { addGame, addDLC }, 250, 23, 70);

        gameName.setForeground(Color.WHITE);
        gameNameField = new JTextField();
        gameNameField.setForeground(Color.WHITE);
        gameNameField.setBackground(Color.DARK_GRAY);
        addItemPair(gameName, gameNameField, 250, 23);

        description.setForeground(Color.WHITE);
        descriptionField = new JTextField();
        descriptionField.setForeground(Color.WHITE);
        descriptionField.setBackground(Color.DARK_GRAY);
        addItemPair(description, descriptionField, 250, 23);

        price.setForeground(Color.WHITE);
        priceField = new JTextField();
        priceField.setForeground(Color.WHITE);
        priceField.setBackground(Color.DARK_GRAY);
        addItemPair(price, priceField, 250, 23);

        gameList.setForeground(Color.WHITE);
        comboBox = new JComboBox<>();
        addItemPair(gameList, comboBox, 250, 23);

        JSeparator separatorLine2 = new JSeparator();
        separatorLine2.setForeground(Color.LIGHT_GRAY);
        separatorLine2.setVisible(false);
        addItemPair(separatorLine2, null, 350, 23);

        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBackground(Color.decode("#717D7E"));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.decode("#717D7E"));
        addItemPair(btnSubmit, btnBack, 90, 23);

        addDLC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addDLC.isSelected()) {
                    gameList.setVisible(true);
                    comboBox.setVisible(true);

                } else if (addGame.isSelected()) {
                    comboBox.setVisible(false);
                    gameList.setVisible(false);
                }
            }
        });

        addGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addDLC.isSelected()) {
                    gameList.setVisible(true);
                    comboBox.setVisible(true);

                } else if (addGame.isSelected()) {
                    comboBox.setVisible(false);
                    gameList.setVisible(false);
                }
            }
        });
        for (Item item : con.getAvailableItems()) {
            if (item.getType().equals("Game") && item.getPublisherID() == publisher.getId()) {
                comboBox.addItem(item.getName());
            }
        }
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gameName = gameNameField.getText();
                String gameDescription = descriptionField.getText();
                String priceString = priceField.getText();

                ArrayList<User> userList = con.getUnbannedUsers();
                // Check for empty fields
                if (gameName.isEmpty() || gameDescription.isEmpty() || priceString.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields");
                    return;
                }

                // Validate price input
                Double gamePrice;
                try {
                    gamePrice = Double.parseDouble(priceString);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid price format");
                    return;
                }

                if (addGame.isSelected()) {
                    Game game = new Game();
                    game.setName(gameName);
                    game.setDescription(gameDescription);
                    game.setPrice(gamePrice);

                    // Check if publisher is not null before passing to insertNewGame
                    if (publisher != null) {
                        boolean insert = con.insertNewGame(game, publisher);
                        if (insert) {
                            JOptionPane.showMessageDialog(null, "Insert successful");
                            new HomePublisherView(publisher);
                            addItem.dispose();

                            for (User user : userList) {
                                user.notifyUser(gameName + " is now available!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Insert failed");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Publisher information missing");
                    }
                } else if (addDLC.isSelected()) {
                    DLC dlc = new DLC();
                    dlc.setName(gameName);
                    dlc.setDescription(gameDescription);
                    dlc.setPrice(gamePrice);

                    // Check if publisher is not null before passing to insertNewGame
                    if (publisher != null) {
                        boolean insert = con.insertNewDLC(dlc, publisher);
                        if (insert) {
                            JOptionPane.showMessageDialog(null, "Insert successful");
                            new HomePublisherView(publisher);
                            addItem.dispose();

                            for (User user : userList) {
                                user.notifyUser(gameName + " is now available!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Insert failed");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Publisher information missing");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Select game or DLC", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        addItem.setVisible(true);
        addItem.setSize(430, currY + 75);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePublisherView(publisher);
                addItem.dispose();
            }
        });

    }
}
