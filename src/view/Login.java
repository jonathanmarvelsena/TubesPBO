/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import controller.Controller;
import model.Account;
import model.AccountStatus;
import model.Admin;
import model.Publisher;
import model.User;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;

/**
 *
 * @author abil
 */
public class Login {
    Controller con = Controller.getInstance();

    JFrame container;
    JTextField name;
    JPasswordField password;
    JButton btnLogin;
    JButton btnRegister;

    public Login() {
        container = new JFrame("Login");
        container.setSize(480, 300);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLocationRelativeTo(null);
        container.setLayout(null);
        container.getContentPane().setBackground(Color.DARK_GRAY);

        // Account Name
        JLabel labelName = new JLabel("Username ");
        name = new JTextField();
        labelName.setBounds(28, 30, 150, 23);
        name.setBounds(120, 30, 320, 23);
        labelName.setForeground(Color.WHITE);
        name.setForeground(Color.WHITE);
        name.setBackground(Color.DARK_GRAY);
        container.add(labelName);
        container.add(name);

        // Password
        JLabel labelPassword = new JLabel("Password ");
        password = new JPasswordField();
        labelPassword.setBounds(50, 62, 150, 23);
        password.setBounds(120, 62, 320, 23);
        labelPassword.setForeground(Color.WHITE);
        password.setForeground(Color.WHITE);
        password.setBackground(Color.DARK_GRAY);
        container.add(labelPassword);
        container.add(password);

        // Bagian Button Login
        btnLogin = new JButton("Login");
        btnLogin.setBounds(120, 100, 160, 23);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(Color.decode("#717D7E"));
        container.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = name.getText();
                String userPassword = new String(password.getPassword());
                Account loggedInUser = con.getUser(userName, userPassword);

                if (loggedInUser instanceof User) {
                    if (loggedInUser.getStatus() == AccountStatus.BANNED) {
                        JOptionPane.showMessageDialog(null, "This account is banned");
                        return;
                    }
                    container.dispose();
                    JOptionPane.showMessageDialog(null, "Login successful");
                    new HomeUser((User) loggedInUser);
                } else if (loggedInUser instanceof Admin) {
                    container.dispose();
                    JOptionPane.showMessageDialog(null, "Login successful");
                    new HomeAdmin((Admin) loggedInUser);
                } else if (loggedInUser instanceof Publisher) {
                    container.dispose();
                    JOptionPane.showMessageDialog(null, "Login successful");
                    new HomePublisher((Publisher) loggedInUser);
                } else {
                    JOptionPane.showMessageDialog(container, "Email or password incorrect", "User not found",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JSeparator separatorLine = new JSeparator();
        separatorLine.setBounds(28, 150, 410, 5);
        separatorLine.setForeground(Color.LIGHT_GRAY);
        container.add(separatorLine);

        // Registration
        JLabel registerField = new JLabel("Don't have a Steam Account? ");
        registerField.setBounds(50, 182, 180, 23);
        registerField.setForeground(Color.WHITE);
        container.add(registerField);

        btnRegister = new JButton("Create A New Account...");
        btnRegister.setBounds(230, 182, 210, 23);
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setBackground(Color.decode("#717D7E"));
        container.add(btnRegister);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register();
                container.setVisible(false);
            }
        });
        container.setVisible(true);
    }
}
