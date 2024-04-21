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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Controller;
import model.Account;
import model.User;

/**
 *
 * @author abil
 */
public class Register {
    Controller con = Controller.getInstance();

    JFrame container;
    JTextField username;
    JPasswordField password;
    JButton btnBack;
    JButton btnRegister;

    public Register() {

        container = new JFrame("Register");
        container.setSize(480, 300);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLocationRelativeTo(null);
        container.setLayout(null);
        container.getContentPane().setBackground(Color.DARK_GRAY);

        JLabel title = new JLabel("CREATE YOUR ACCOUNT");
        title.setBounds(15, 15, 150, 23);
        title.setForeground(Color.WHITE);
        container.add(title);

        // Username
        JLabel labelUsername = new JLabel("Username");
        username = new JTextField();
        labelUsername.setBounds(15, 70, 150, 23);
        username.setBounds(100, 70, 320, 23);
        labelUsername.setForeground(Color.WHITE);
        username.setForeground(Color.WHITE);
        username.setBackground(Color.DARK_GRAY);
        container.add(username);
        container.add(labelUsername);

        // Password
        JLabel labelPassword = new JLabel("Password ");
        password = new JPasswordField();
        labelPassword.setBounds(15, 102, 150, 23);
        password.setBounds(100, 102, 320, 23);
        labelPassword.setForeground(Color.WHITE);
        password.setForeground(Color.WHITE);
        password.setBackground(Color.DARK_GRAY);
        container.add(labelPassword);
        container.add(password);

        // Register
        btnRegister = new JButton("Create Account");
        btnRegister.setBounds(100, 182, 150, 23);
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setBackground(Color.decode("#717D7E"));
        container.add(btnRegister);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = username.getText();
                String pass = new String(password.getPassword());
                // ArrayList<User> listUser = con.getAllUserList();

                Account newAccount = con.getUser(name, pass);
                // User newUser = new User(name, pass, listUser.size() + 1);
                User newUser = (User) newAccount;

                boolean cek = con.insertNewUser(newUser);
                if (cek) {
                    JOptionPane.showMessageDialog(container, "Register successful", "Error",
                            JOptionPane.WARNING_MESSAGE);
                    new HomeUser(newUser);
                    container.dispose();
                } else {
                    JOptionPane.showMessageDialog(container, "Register failed", "Error",
                            JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        container.setVisible(true);

        btnBack = new JButton("Back");
        btnBack.setBounds(270, 182, 150, 23);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.decode("#717D7E"));
        container.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                container.setVisible(false);
            }
        });
    }
}
