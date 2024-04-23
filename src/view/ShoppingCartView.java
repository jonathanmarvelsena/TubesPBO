package view;

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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import controller.Controller;
import model.ShoppingCart;
import model.User;

public class ShoppingCartView {
    Controller con = Controller.getInstance();
    JFrame container;
    JButton btnBuy = new JButton("Buy");
    JButton btnGift = new JButton("Gift");
    JButton btnRemove = new JButton("Remove");
    JButton btnBack = new JButton("Back");
    JLabel shopingCartLabel = new JLabel("Shopping Cart");

    public ShoppingCartView(User user, ArrayList<ShoppingCart> cart) {
        container = new JFrame("Shopping Cart");
        container.setSize(480, 400);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLocationRelativeTo(null);
        container.setLayout(null);
        container.getContentPane().setBackground(Color.DARK_GRAY);

        shopingCartLabel.setBounds(20, 25, 200, 23);
        shopingCartLabel.setForeground(Color.WHITE);
        container.add(shopingCartLabel);

        JSeparator separatorLine = new JSeparator();
        separatorLine.setBounds(20, 45, 80, 5);
        separatorLine.setForeground(Color.LIGHT_GRAY);
        container.add(separatorLine);

        String[] columnNames = { "Name", "Type", "Price", "Status" };

        Object[][] data = new Object[cart.size()][4];

        for (int i = 0; i < cart.size(); i++) {
            ShoppingCart shoppingCart = cart.get(i);
            data[i][0] = con.getItemById(shoppingCart.getitemID()).getName();
            data[i][1] = con.getItemById(shoppingCart.getitemID()).getType();
            data[i][2] = con.getItemById(shoppingCart.getitemID()).getPrice();
            data[i][3] = shoppingCart.getStatus();
        }

        // DefaultTableModel model = new DefaultTableModel(data, columnNames);
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            // Override getColumnClass to specify the column class for rendering checkboxes
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) {
                    return Boolean.class; // Return Boolean class for the checkbox column
                }
                return super.getColumnClass(columnIndex);
            }
        };

        JTable libraryTable = new JTable(model);

        model.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int column = e.getColumn();
                    if (column == 3) { // Ganti indeks kolom sesuai dengan kebutuhan Anda
                        int row = e.getFirstRow();
                        Boolean checked = (Boolean) model.getValueAt(row, column);
                        cart.get(row).setStatus(checked);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(libraryTable);
        scrollPane.setBounds(20, 70, 420, 100);
        container.add(scrollPane);

        libraryTable.setBackground(Color.DARK_GRAY);
        libraryTable.setForeground(Color.WHITE);
        libraryTable.getTableHeader().setBackground(Color.DARK_GRAY);
        libraryTable.getTableHeader().setForeground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.DARK_GRAY);

        JSeparator separatorLine2 = new JSeparator();
        separatorLine2.setBounds(20, 190, 420, 5);
        separatorLine2.setForeground(Color.LIGHT_GRAY);
        container.add(separatorLine2);

        btnBuy.setBounds(20, 210, 205, 23);
        btnBuy.setForeground(Color.WHITE);
        btnBuy.setBackground(Color.decode("#717D7E"));
        container.add(btnBuy);
        // final double finalTotal = total;
        btnBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = 0;
                for (int i = 0; i < cart.size(); i++) {
                    if (cart.get(i).getStatus()) {
                        total += con.getItemById(cart.get(i).getitemID()).getPrice();
                    }
                }

                JOptionPane.showMessageDialog(null, "Your Wallet : " + user.getWallet());

                if (user.getWallet() < total) {
                    double remainingAmount = total - user.getWallet();
                    JOptionPane.showMessageDialog(null, "Wallet funds not enough. Need additional: " + remainingAmount);
                } else {
                    ArrayList<ShoppingCart> copyCart = new ArrayList<>(cart);
                    cart.clear();

                    for (ShoppingCart c : copyCart) {
                        if (c.getStatus()) {
                            con.purchase(user, c);
                        } else {
                            cart.add(c);
                        }
                    }
                    con.topUpWallet(user, -total);
                    JOptionPane.showMessageDialog(null, "Total Purchase : " + total);
                    JOptionPane.showMessageDialog(null, "Purchase Succesful");
                }
                new HomeUserView(user);
                container.setVisible(false);
            }
        });

        btnGift.setBounds(235, 210, 205, 23);
        btnGift.setForeground(Color.WHITE);
        btnGift.setBackground(Color.decode("#717D7E"));
        container.add(btnGift);

        btnGift.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double total = 0;
                for (int i = 0; i < cart.size(); i++) {
                    total += con.getItemById(cart.get(i).getitemID()).getPrice();
                }

                if (user.getWallet() < total) {
                    JOptionPane.showMessageDialog(null, "Wallet funds not enough");
                } else {
                    new SelectGiftUserView(user, cart, total);
                    container.setVisible(false);
                }
            }
        });

        btnRemove.setBounds(20, 250, 420, 23);
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setBackground(Color.decode("#717D7E"));
        container.add(btnRemove);

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ShoppingCart> copyCart = new ArrayList<>(cart);
                cart.clear();
                for (ShoppingCart c : copyCart) {
                    if (!c.getStatus()) {
                        cart.add(c);
                    }
                }
                String[] columnNames = { "Name", "Type", "Price", "Status" };

                Object[][] data = new Object[cart.size()][4];

                for (int i = 0; i < cart.size(); i++) {
                    ShoppingCart shoppingCart = cart.get(i);
                    data[i][0] = con.getItemById(shoppingCart.getitemID()).getName();
                    data[i][1] = con.getItemById(shoppingCart.getitemID()).getType();
                    data[i][2] = con.getItemById(shoppingCart.getitemID()).getPrice();
                    data[i][3] = shoppingCart.getStatus();
                }

                // DefaultTableModel model = new DefaultTableModel(data, columnNames);
                DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                    // Override getColumnClass to specify the column class for rendering checkboxes
                    @Override
                    public Class<?> getColumnClass(int columnIndex) {
                        if (columnIndex == 3) {
                            return Boolean.class; // Return Boolean class for the checkbox column
                        }
                        return super.getColumnClass(columnIndex);
                    }
                };

                libraryTable.setModel(model);
            }
        });

        btnBack.setBounds(20, 280, 420, 23);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.decode("#717D7E"));
        container.add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeUserView(user);
                container.setVisible(false);
            }
        });
        container.setVisible(true);
    }
}