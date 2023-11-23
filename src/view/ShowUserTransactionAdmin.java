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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Admin;
import model.User;

/**
 *
 * @author abil
 */
public class ShowUserTransactionAdmin {
    Controller con = Controller.getInstance();
    JFrame container;
    JButton btnBack, btnSelect;

    public ShowUserTransactionAdmin(Admin admin, ArrayList<User> users) {
        container = new JFrame("Show User Transaction");
        container.setSize(480, 300);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLocationRelativeTo(null);
        container.setLayout(null);
        container.getContentPane().setBackground(Color.DARK_GRAY);

        JLabel title = new JLabel("Show User Transaction");
        title.setBounds(15, 15, 150, 23);
        title.setForeground(Color.WHITE);
        container.add(title);

        String[] columnNames = {"ID", "Name", "Status", "Wallet"};

        Object[][] data = new Object[users.size()][4]; 

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            data[i][0] = user.getId(); 
            data[i][1] = user.getName(); 
            data[i][2] = user.getStatus().toString();
            data[i][3] = user.getWallet(); 
        }
    
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        JTable userTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBounds(15, 50, 450, 100);
        container.add(scrollPane);

        JSeparator garisPemisah = new JSeparator();
        garisPemisah.setBounds(28, 150, 410, 5);
        garisPemisah.setForeground(Color.LIGHT_GRAY);
        container.add(garisPemisah);

        JLabel inputid = new JLabel("User ID");
        inputid.setBounds(28, 170, 100, 23);
        inputid.setForeground(Color.WHITE);
        inputid.setBackground(Color.decode("#717D7E"));
        container.add(inputid);
        JTextField input = new JTextField();
        input.setBounds(150, 170, 200, 23);
        input.setForeground(Color.WHITE);
        input.setBackground(Color.DARK_GRAY);
        container.add(input);

        btnSelect = new JButton("Select");
        btnSelect.setBounds(370, 170, 150, 23);
        btnSelect.setForeground(Color.WHITE);
        btnSelect.setBackground(Color.decode("#717D7E"));
        container.add(btnSelect);

        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = con.getUserById(Integer.parseInt(input.getText()));
                new ShowTransactionUser(user);
                container.setVisible(false);
            }
        });

        //Bagian Button Back
        btnBack = new JButton("Back");
        btnBack.setBounds(270, 230, 150, 23);
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
