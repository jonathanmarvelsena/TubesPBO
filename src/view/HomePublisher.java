package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePublisher {
    JFrame home_publisher;
    JButton btnAddItem;
    JButton btnEditItem;
    JButton btnRemovedGame;
    JButton btnShowRemovedGame;


    public HomePublisher (){
        home_publisher = new JFrame("Home Publisher");
        home_publisher.setSize(320, 270);
        home_publisher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home_publisher.setLocationRelativeTo(null);
        home_publisher.setLayout(null);
        home_publisher.getContentPane().setBackground(Color.DARK_GRAY);

        JLabel welcomePublisher = new JLabel(" welcome to menu publisher ");
        welcomePublisher.setBounds(77, 25, 170, 23);
        welcomePublisher.setForeground(Color.WHITE);
        home_publisher.add(welcomePublisher);

        JSeparator garisPemisah = new JSeparator();
        garisPemisah.setBounds(77, 45, 160, 5);
        garisPemisah.setForeground(Color.LIGHT_GRAY);
        home_publisher.add(garisPemisah);

        btnAddItem = new JButton("Add Item");
        btnAddItem.setBounds(80, 60, 150, 23);
        btnAddItem.setForeground(Color.WHITE);
        btnAddItem.setBackground(Color.decode("#717D7E"));
        home_publisher.add(btnAddItem);

        btnEditItem = new JButton("Edit Item");
        btnEditItem.setBounds(80, 90, 150, 23);
        btnEditItem.setForeground(Color.WHITE);
        btnEditItem.setBackground(Color.decode("#717D7E"));
        home_publisher.add(btnEditItem);

        btnShowRemovedGame = new JButton("Show Removed Game");
        btnShowRemovedGame.setBounds(70, 120, 170, 23);
        btnShowRemovedGame.setForeground(Color.WHITE);
        btnShowRemovedGame.setBackground(Color.decode("#717D7E"));
        home_publisher.add(btnShowRemovedGame);

        btnRemovedGame = new JButton("Removed Game");
        btnRemovedGame.setBounds(80, 150, 150, 23);
        btnRemovedGame.setForeground(Color.WHITE);
        btnRemovedGame.setBackground(Color.decode("#717D7E"));
        home_publisher.add(btnRemovedGame);

        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeUser();
                home_publisher.setVisible(false);
            }
        });
        home_publisher.setVisible(true);
    }

    public static void main(String[] args) {
        new HomePublisher();
    }


}
