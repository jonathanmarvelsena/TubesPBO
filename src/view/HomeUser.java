package view;

import javax.swing.*;

import controller.Controller;
import model.Item;
import model.ShoppingCart;
import model.User;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HomeUser {
    Controller con = Controller.getInstance();
    JFrame homeUser;
    JButton btnShow;
    JButton btnTopup;
    JButton btnShowItemLibrary;
    JButton btnShoppingCart;
    JButton btnShowUserTransaction;
    JButton btnBack;

    public HomeUser(User user) {
        homeUser = new JFrame("Home ");
        homeUser.setSize(320, 350);
        homeUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeUser.setLocationRelativeTo(null);
        homeUser.setLayout(null);
        homeUser.getContentPane().setBackground(Color.DARK_GRAY);

        JLabel welcomeUser = new JLabel(" Welcome to Steam ");
        welcomeUser.setBounds(100, 25, 170, 23);
        welcomeUser.setForeground(Color.WHITE);
        homeUser.add(welcomeUser);

        JSeparator separatorLine = new JSeparator();
        separatorLine.setBounds(80, 45, 150, 5);
        separatorLine.setForeground(Color.LIGHT_GRAY);
        homeUser.add(separatorLine);

        btnShow = new JButton("Game list");
        btnShow.setBounds(80, 60, 150, 23);
        btnShow.setForeground(Color.WHITE);
        btnShow.setBackground(Color.decode("#717D7E"));
        homeUser.add(btnShow);

        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowItemList(user);
                homeUser.dispose();
            }
        });

        btnShowItemLibrary = new JButton("Game Library");
        btnShowItemLibrary.setBounds(80, 90, 150, 23);
        btnShowItemLibrary.setForeground(Color.WHITE);
        btnShowItemLibrary.setBackground(Color.decode("#717D7E"));
        homeUser.add(btnShowItemLibrary);

        btnShowItemLibrary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Item> library = con.getLibrary(user);
                new ShowItemLibrary(user, library);
                homeUser.dispose();
            }
        });

        btnShoppingCart = new JButton("Shopping Cart");
        btnShoppingCart.setBounds(80, 120, 150, 23);
        btnShoppingCart.setForeground(Color.WHITE);
        btnShoppingCart.setBackground(Color.decode("#717D7E"));
        homeUser.add(btnShoppingCart);

        btnShoppingCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ShoppingCart> cart = user.getCart();
                new ShowShoppingCart(user, cart);
                homeUser.dispose();
            }
        });

        btnTopup = new JButton("Top Up");
        btnTopup.setBounds(80, 150, 150, 23);
        btnTopup.setForeground(Color.WHITE);
        btnTopup.setBackground(Color.decode("#717D7E"));
        homeUser.add(btnTopup);

        btnTopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TopUp(user);
                homeUser.dispose();
            }
        });

        btnShowUserTransaction = new JButton("Transaction History");
        btnShowUserTransaction.setBounds(80, 180, 150, 23);
        btnShowUserTransaction.setForeground(Color.WHITE);
        btnShowUserTransaction.setBackground(Color.decode("#717D7E"));
        homeUser.add(btnShowUserTransaction);

        btnShowUserTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowTransactionUser(user);
                homeUser.dispose();
            }
        });

        btnBack = new JButton("Log out");
        btnBack.setBounds(80, 230, 150, 23);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.decode("#717D7E"));
        homeUser.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                homeUser.setVisible(false);
            }
        });

        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeUser(user);
                homeUser.setVisible(false);
            }
        });
        homeUser.setVisible(true);
    }
}
