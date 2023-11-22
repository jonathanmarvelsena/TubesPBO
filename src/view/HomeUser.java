package view;

import javax.swing.*;

import model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomeUser {
    JFrame home_user;
    JButton btnShowGameList;
    JButton btnTopup;
    JButton btnShowGameLibrary;
    JButton btnShopingCart;
    JButton btnShowUserTransaction;

    public HomeUser (User user) {
        home_user = new JFrame("Home ");
        home_user.setSize(320, 300);
        home_user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home_user.setLocationRelativeTo(null);
        home_user.setLayout(null);
        home_user.getContentPane().setBackground(Color.DARK_GRAY);

        JLabel welcomeUser = new JLabel(" welcome to steam ");
        welcomeUser.setBounds(100, 25, 170, 23);
        welcomeUser.setForeground(Color.WHITE);
        home_user.add(welcomeUser);

        JSeparator garisPemisah = new JSeparator();
        garisPemisah.setBounds(80, 45, 150, 5);
        garisPemisah.setForeground(Color.LIGHT_GRAY);
        home_user.add(garisPemisah);

        btnShowGameList = new JButton("Game list");
        btnShowGameList.setBounds(80, 60, 150, 23);
        btnShowGameList.setForeground(Color.WHITE);
        btnShowGameList.setBackground(Color.decode("#717D7E"));
        home_user.add(btnShowGameList);

        btnShowGameList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowGameList();
                home_user.dispose();
            }
        });

        btnShowGameLibrary = new JButton("Game Library");
        btnShowGameLibrary.setBounds(80, 90, 150, 23);
        btnShowGameLibrary.setForeground(Color.WHITE);
        btnShowGameLibrary.setBackground(Color.decode("#717D7E"));
        home_user.add(btnShowGameLibrary);

        btnShowGameLibrary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowGameLibrary();
                home_user.dispose();
            }
        });

        btnShopingCart = new JButton("Shoping Cart");
        btnShopingCart.setBounds(80, 120, 150, 23);
        btnShopingCart.setForeground(Color.WHITE);
        btnShopingCart.setBackground(Color.decode("#717D7E"));
        home_user.add(btnShopingCart);

        btnShopingCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShoppingCart();
                home_user.dispose();
            }
        });

        btnTopup = new JButton("Top Up");
        btnTopup.setBounds(80, 150, 150, 23);
        btnTopup.setForeground(Color.WHITE);
        btnTopup.setBackground(Color.decode("#717D7E"));
        home_user.add(btnTopup);

        btnTopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TopUp(user);
                home_user.dispose();
            }
        });

        btnShowUserTransaction = new JButton("history transaction");
        btnShowUserTransaction.setBounds(80, 180, 150, 23);
        btnShowUserTransaction.setForeground(Color.WHITE);
        btnShowUserTransaction.setBackground(Color.decode("#717D7E"));
        home_user.add(btnShowUserTransaction);

        btnShowUserTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowTransactionUser(user);
                home_user.dispose();
            }
        });

        btnShowGameList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeUser(user);
                home_user.setVisible(false);
            }
        });
        home_user.setVisible(true);
    }
}
