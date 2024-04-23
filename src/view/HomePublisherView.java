package view;

import javax.swing.*;
import controller.Controller;
import model.Item;
import model.Publisher;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HomePublisherView {
    Controller con = Controller.getInstance();
    JFrame homePublisherFrame;
    JButton btnAddItem;
    JButton btnBack;
    JButton btnEditItem;
    JButton btnRemovedGame;
    JButton btnShowRemovedGame;

    public HomePublisherView(Publisher publisher) {
        homePublisherFrame = new JFrame("Home");
        homePublisherFrame.setSize(300, 400);
        homePublisherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homePublisherFrame.setLocationRelativeTo(null);
        homePublisherFrame.setLayout(null);
        homePublisherFrame.getContentPane().setBackground(Color.DARK_GRAY);

        JLabel welcomePublisher = new JLabel(" Welcome to Publisher Menu ");
        welcomePublisher.setBounds(70, 25, 170, 23);
        welcomePublisher.setForeground(Color.WHITE);
        homePublisherFrame.add(welcomePublisher);

        JSeparator separatorLine = new JSeparator();
        separatorLine.setBounds(50, 45, 190, 5);
        separatorLine.setForeground(Color.LIGHT_GRAY);
        homePublisherFrame.add(separatorLine);

        btnAddItem = new JButton("Add Item");
        btnAddItem.setBounds(50, 90, 190, 23);
        btnAddItem.setForeground(Color.WHITE);
        btnAddItem.setBackground(Color.decode("#717D7E"));
        homePublisherFrame.add(btnAddItem);

        btnEditItem = new JButton("Edit Item");
        btnEditItem.setBounds(50, 120, 190, 23);
        btnEditItem.setForeground(Color.WHITE);
        btnEditItem.setBackground(Color.decode("#717D7E"));
        homePublisherFrame.add(btnEditItem);

        btnShowRemovedGame = new JButton("Show Removed Items");
        btnShowRemovedGame.setBounds(50, 150, 190, 23);
        btnShowRemovedGame.setForeground(Color.WHITE);
        btnShowRemovedGame.setBackground(Color.decode("#717D7E"));
        homePublisherFrame.add(btnShowRemovedGame);

        btnRemovedGame = new JButton("Remove Item");
        btnRemovedGame.setBounds(50, 180, 190, 23);
        btnRemovedGame.setForeground(Color.WHITE);
        btnRemovedGame.setBackground(Color.decode("#717D7E"));
        homePublisherFrame.add(btnRemovedGame);

        btnBack = new JButton("Logout");
        btnBack.setBounds(50, 270, 190, 23);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.decode("#717D7E"));
        homePublisherFrame.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginView();
                homePublisherFrame.setVisible(false);
            }
        });

        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddItemView(publisher);

                homePublisherFrame.dispose();
            }
        });

        btnShowRemovedGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Item> removeItems = con.getRemovedItem();
                new RemovedGameView(publisher, removeItems);
                homePublisherFrame.dispose();
            }
        });

        btnRemovedGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Item> items = con.getUnavailableItems();
                new RemoveGameView(publisher, items);
                homePublisherFrame.dispose();
            }
        });
        homePublisherFrame.setVisible(true);

        btnEditItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Item> itemPublisher = con.getAllItems();
                new EditItemView(publisher, itemPublisher);
                homePublisherFrame.dispose();
            }
        });
        homePublisherFrame.setVisible(true);
    }

}
