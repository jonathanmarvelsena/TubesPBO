package view;

import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controller.Controller;
import model.Item;
import model.Publisher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class showRemovedGame {
    Controller con = Controller.getInstance();
    JTextField id;
    JFrame container;
    JButton btnBack;

    public showRemovedGame(Publisher publisher, ArrayList<Item> nonBannedItems) {
        container = new JFrame("Ban User");
        container.setSize(500, 380);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLocationRelativeTo(null);
        container.setLayout(null);
        container.getContentPane().setBackground(Color.DARK_GRAY);

        String[] columnNames = { "item_id", "Name", "Type", "Price", "Description", "Publisher_id", "Item_status" };

        Object[][] data = new Object[nonBannedItems.size()][7];

        for (int i = 0; i < nonBannedItems.size(); i++) {
            Item items = nonBannedItems.get(i);
            data[i][0] = items.getItemID();
            data[i][1] = items.getName();
            data[i][2] = items.getType();
            data[i][3] = items.getPrice();
            data[i][4] = items.getDescription();
            data[i][5] = items.getPublisherID();
            data[i][6] = items.getStatus().toString();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        JTable itemTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(itemTable);
        scrollPane.setBounds(15, 50, 450, 150);
        container.add(scrollPane);

        JLabel labelName = new JLabel("ID of item to be remove");
        id = new JTextField();
        labelName.setBounds(15, 250, 150, 23);
        id.setBounds(160, 250, 305, 23);
        labelName.setForeground(Color.WHITE);
        id.setForeground(Color.WHITE);
        id.setBackground(Color.DARK_GRAY);
        container.add(labelName);
        container.add(id);

        // Bagian Button Back
        btnBack = new JButton("Back");
        btnBack.setBounds(326, 290, 140, 23);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.decode("#717D7E"));
        container.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePublisher(publisher);
                container.setVisible(false);
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean ban = con.updateStatusUser(Integer.parseInt(id.getText()));
                if (ban) {
                    JOptionPane.showMessageDialog(container, "Item Succesfully banned", "Success",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(container, "Item Not Found", "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
                new RemovedGame(publisher, nonBannedItems);
                container.setVisible(false);
            }
        });
        container.setVisible(true);
    }
}
