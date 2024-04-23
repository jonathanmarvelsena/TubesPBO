/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import controller.Controller;
import model.Admin;
import model.User;

public class HomeAdminView {
    Controller con = Controller.getInstance();
    JFrame homeAdmin;
    JButton btnViewAllUser;
    JButton btnBannedUser;
    JButton btnLogout;
    JButton btnShowBannedUser;
    JButton btnShowMonthlyTransaction;
    JButton btnShowUserTransaction;

    public HomeAdminView(Admin admin) {
        homeAdmin = new JFrame("Admin Main Menu");
        homeAdmin.setSize(300, 400);
        homeAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeAdmin.setLocationRelativeTo(null);
        homeAdmin.setLayout(null);
        homeAdmin.getContentPane().setBackground(Color.DARK_GRAY);

        JLabel title = new JLabel("Welcome to Admin Menu");
        title.setBounds(75, 30, 150, 23);
        title.setForeground(Color.WHITE);
        homeAdmin.add(title);

        JSeparator sectionDivider = new JSeparator();
        sectionDivider.setBounds(50, 55, 190, 5);
        sectionDivider.setForeground(Color.LIGHT_GRAY);
        homeAdmin.add(sectionDivider);

        btnViewAllUser = new JButton("View All Users");
        btnViewAllUser.setBounds(50, 90, 190, 23);
        btnViewAllUser.setForeground(Color.WHITE);
        btnViewAllUser.setBackground(Color.decode("#717D7E"));
        homeAdmin.add(btnViewAllUser);

        btnViewAllUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<User> nonBannedUsers = con.getUnbannedUsers();
                new ViewAllUsers(admin, nonBannedUsers);
                homeAdmin.setVisible(false);
            }
        });

        btnBannedUser = new JButton("Ban User");
        btnBannedUser.setBounds(50, 120, 190, 23);
        btnBannedUser.setForeground(Color.WHITE);
        btnBannedUser.setBackground(Color.decode("#717D7E"));
        homeAdmin.add(btnBannedUser);

        btnBannedUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<User> nonBannedUsers = con.getUnbannedUsers();
                new BanUserView(admin, nonBannedUsers);
                homeAdmin.setVisible(false);
            }
        });

        btnShowBannedUser = new JButton("Show Banned Users");
        btnShowBannedUser.setBounds(50, 150, 190, 23);
        btnShowBannedUser.setForeground(Color.WHITE);
        btnShowBannedUser.setBackground(Color.decode("#717D7E"));
        homeAdmin.add(btnShowBannedUser);

        btnShowBannedUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<User> bannedUsers = con.getBannedUsers();
                new BannedUserView(admin, bannedUsers);
                homeAdmin.setVisible(false);
            }
        });

        btnShowMonthlyTransaction = new JButton("Show Monthly Transaction");
        btnShowMonthlyTransaction.setBounds(50, 180, 190, 23);
        btnShowMonthlyTransaction.setForeground(Color.WHITE);
        btnShowMonthlyTransaction.setBackground(Color.decode("#717D7E"));
        homeAdmin.add(btnShowMonthlyTransaction);

        btnShowMonthlyTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectMonthYearView(admin);
                homeAdmin.setVisible(false);
            }
        });

        btnShowUserTransaction = new JButton("Show User Transaction");
        btnShowUserTransaction.setBounds(50, 210, 190, 23);
        btnShowUserTransaction.setForeground(Color.WHITE);
        btnShowUserTransaction.setBackground(Color.decode("#717D7E"));
        homeAdmin.add(btnShowUserTransaction);

        btnShowUserTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AllUserTransactionViewByAdmin(admin, con.getAllUserList());
                homeAdmin.setVisible(false);
            }
        });

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(50, 270, 190, 23);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setBackground(Color.decode("#717D7E"));
        homeAdmin.add(btnLogout);

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginView();
                homeAdmin.setVisible(false);
            }
        });

        homeAdmin.setVisible(true);
    }
}
