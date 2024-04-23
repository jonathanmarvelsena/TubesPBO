package view;

import model.ShoppingCart;
import model.User;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.Controller;

public class SelectGiftUserView {
    Controller con = Controller.getInstance();
    JFrame update_item;
    JLabel UpdateItemMenu = new JLabel("Select User");
    JLabel gameOrDLC = new JLabel("Select User ID : ");
    JTextField itemField;

    JButton btnSubmit = new JButton("Buy Game");
    JButton btnBack = new JButton("Back");

    Object[][] data = null;
    JTable itemTable = null;
    String[] columnNames = { "ID", "Name" };
    User user;
    ArrayList<ShoppingCart> cart;

    public void loadTable() {
        itemTable.clearSelection();
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        itemTable.setModel(model);
    }

    public SelectGiftUserView(User user, ArrayList<ShoppingCart> cart, double total) {
        this.user = user;
        this.cart = cart;
        update_item = new JFrame("Add Item");
        update_item.setSize(450, 400);
        update_item.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        update_item.setLocationRelativeTo(null);
        update_item.setLayout(null);
        update_item.getContentPane().setBackground(Color.DARK_GRAY);

        UpdateItemMenu.setBounds(20, 25, 170, 23);
        UpdateItemMenu.setForeground(Color.WHITE);
        update_item.add(UpdateItemMenu);

        JSeparator separatorLine = new JSeparator();
        separatorLine.setBounds(20, 45, 75, 5);
        separatorLine.setForeground(Color.LIGHT_GRAY);
        update_item.add(separatorLine);

        gameOrDLC.setBounds(20, 60, 200, 23);
        gameOrDLC.setForeground(Color.WHITE);
        update_item.add(gameOrDLC);

        JSeparator separatorLine2 = new JSeparator();
        separatorLine2.setBounds(20, 90, 390, 5);
        separatorLine2.setForeground(Color.LIGHT_GRAY);
        update_item.add(separatorLine2);

        ArrayList<User> users = con.getAllUserList();
        data = new Object[users.size()][2];

        for (int i = 0; i < users.size(); i++) {
            User item = users.get(i);
            data[i][0] = item.getId();
            data[i][1] = item.getName();
        }

        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);

        itemTable = new JTable(model);
        loadTable();
        JScrollPane scrollPane = new JScrollPane(itemTable);
        scrollPane.setBounds(20, 110, 390, 80);
        update_item.add(scrollPane);
        itemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                String src = e.getSource().toString();
                int start = src.indexOf("{") + 1;
                int stop = src.length() - 1;
                String s = src.substring(start, stop);
                if (s.isEmpty()) {
                    return;
                }
                int index = Integer.parseInt(s);
                itemField.setText(String.valueOf((Integer) data[index][0]));

            }
        });

        itemField = new JTextField();
        itemField.setBounds(130, 210, 260, 23);
        itemField.setForeground(Color.WHITE);
        itemField.setBackground(Color.DARK_GRAY);
        update_item.add(itemField);

        btnSubmit.setBounds(40, 255, 160, 23);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBackground(Color.decode("#717D7E"));
        update_item.add(btnSubmit);

        btnBack.setBounds(210, 255, 160, 23);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.decode("#717D7E"));
        update_item.add(btnBack);

        itemTable.setBackground(Color.DARK_GRAY);
        itemTable.setForeground(Color.WHITE);
        itemTable.getTableHeader().setBackground(Color.DARK_GRAY);
        itemTable.getTableHeader().setForeground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.DARK_GRAY);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = 0;
                for (int i = 0; i < cart.size(); i++) {
                    total += con.getItemById(cart.get(i).getitemID()).getPrice();
                }

                new ShoppingCartView(user, cart);
                update_item.setVisible(false);
                con.topUpWallet(user, -total);

                ArrayList<ShoppingCart> copyCart = new ArrayList<>(cart);
                for (ShoppingCart c : copyCart) {
                    if (cart != null) {
                        con.gift(user, Integer.parseInt(itemField.getText()), c);
                        cart.remove(c); // Remove gifted item from account
                    }
                }

                JOptionPane.showMessageDialog(null, "Gift successful");
                new HomeUserView(user);
                update_item.setVisible(false);
            }
        });

        update_item.setVisible(true);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShoppingCartView(user, cart);
                update_item.setVisible(false);
            }
        });

    }

}
