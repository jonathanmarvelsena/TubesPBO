/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author abil
 */
public class Register {

    JFrame container;
    JTextField username;
    JPasswordField password;
    JButton btnBack;
    JButton btnRegistrasi;

    public Register() {

        container = new JFrame("Steam Register");
        container.setSize(480, 300);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLocationRelativeTo(null);
        container.setLayout(null);
        container.getContentPane().setBackground(Color.DARK_GRAY);

        JLabel title = new JLabel("CREATE YOUR ACCOUNT");
        title.setBounds(15, 15, 150, 23);
        title.setForeground(Color.WHITE);
        container.add(title);

        //Username
        JLabel labelUsername = new JLabel("Username");
        username = new JTextField();
        labelUsername.setBounds(15, 70, 150, 23);
        username.setBounds(100, 70, 320, 23);
        labelUsername.setForeground(Color.WHITE);
        username.setForeground(Color.WHITE);
        username.setBackground(Color.DARK_GRAY);
        container.add(username);
        container.add(labelUsername);

        //Email
        JLabel email = new JLabel("Email");
        username = new JTextField();
        email.setBounds(15, 102, 150, 23);
        username.setBounds(100, 102, 320, 23);
        email.setForeground(Color.WHITE);
        username.setForeground(Color.WHITE);
        username.setBackground(Color.DARK_GRAY);
        container.add(username);
        container.add(email);

        //Password
        JLabel labelPassword = new JLabel("Password ");
        password = new JPasswordField();
        labelPassword.setBounds(15, 134, 150, 23);
        password.setBounds(100, 134, 320, 23);
        labelPassword.setForeground(Color.WHITE);
        password.setForeground(Color.WHITE);
        password.setBackground(Color.DARK_GRAY);
        container.add(labelPassword);
        container.add(password);

        //Bagian Button Registrasi
        btnRegistrasi = new JButton("Create Account");
        btnRegistrasi.setBounds(100, 182, 150, 23);
        btnRegistrasi.setForeground(Color.WHITE);
        btnRegistrasi.setBackground(Color.decode("#717D7E"));
        container.add(btnRegistrasi);

        btnRegistrasi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register();
                container.setVisible(false);
            }
        });
        container.setVisible(true);

        //Bagian Button Back
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