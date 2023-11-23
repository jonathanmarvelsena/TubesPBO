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
import javax.swing.JSeparator;

import model.Admin;

/**
 *
 * @author abil
 */
public class ShowBanUser {
    JFrame container;
    JButton btnBack;

    public ShowBanUser(Admin admin) {
        container = new JFrame("Show Banned User");
        container.setSize(480, 300);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLocationRelativeTo(null);
        container.setLayout(null);
        container.getContentPane().setBackground(Color.DARK_GRAY);

        JLabel title = new JLabel("Show Banned User");
        title.setBounds(15, 15, 150, 23);
        title.setForeground(Color.WHITE);
        container.add(title);

        JSeparator garisPemisah = new JSeparator();
        garisPemisah.setBounds(28, 150, 410, 5);
        garisPemisah.setForeground(Color.LIGHT_GRAY);
        container.add(garisPemisah);

        //Bagian Button Back
        btnBack = new JButton("Back");
        btnBack.setBounds(270, 182, 150, 23);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.decode("#717D7E"));
        container.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeAdmin(admin);
                container.setVisible(false);
            }
        });

        

        container.setVisible(true);
    }
}
