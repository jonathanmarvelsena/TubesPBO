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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import controller.Controller;
import model.Admin;
import model.User;

/**
 *
 * @author abil
 */
public class ShowBanUser {
    Controller con = Controller.getInstance();
    JFrame container;
    JButton btnBack;

    public ShowBanUser(Admin admin, ArrayList<User> bannedUsers) {
        container = new JFrame("Show Banned User");
        container.setSize(500, 380);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLocationRelativeTo(null);
        container.setLayout(null);
        container.getContentPane().setBackground(Color.DARK_GRAY);

        JLabel title = new JLabel("Show Banned User");
        title.setBounds(15, 15, 150, 23);
        title.setForeground(Color.WHITE);
        container.add(title);

        String[] columnNames = {"ID", "Name", "Status", "Wallet"};

        Object[][] data = new Object[bannedUsers.size()][4]; 

        for (int i = 0; i < bannedUsers.size(); i++) {
            User user = bannedUsers.get(i);
            data[i][0] = user.getId(); 
            data[i][1] = user.getName(); 
            data[i][2] = user.getStatus().toString();
            data[i][3] = user.getWallet(); 
        }
    
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        JTable userTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBounds(15, 50, 450, 150);
        container.add(scrollPane);

        JSeparator garisPemisah = new JSeparator();
        garisPemisah.setBounds(15, 230, 450, 5);
        garisPemisah.setForeground(Color.LIGHT_GRAY);
        container.add(garisPemisah);

        //Bagian Button Back
        btnBack = new JButton("Back");
        btnBack.setBounds(315, 250, 150, 23);
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
