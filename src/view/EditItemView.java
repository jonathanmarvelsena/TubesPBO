package view;

import model.Item;
import model.Publisher;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.Controller;

public class EditItemView {
    Controller con = Controller.getInstance();
    JFrame updateItem;
    JLabel UpdateItemMenu = new JLabel("Edit Item");
    JLabel gameOrDLC = new JLabel("Edit Game or DLC : ");
    JTextField idField;
    JLabel idItem = new JLabel("ID Game/DLC : ");
    JTextField itemNameField;
    JLabel itemName = new JLabel("Game/DLC Name : ");
    JTextField descriptionField;
    JLabel description = new JLabel("Description    : ");
    JTextField priceField;
    JLabel price = new JLabel("Price    : ");
    JButton btnSubmit = new JButton("Submit");
    JButton btnBack = new JButton("Back");
    JRadioButton updateGame = new JRadioButton("Game");
    JRadioButton updateDLC = new JRadioButton("DLC");
    Object[][] data = null;
    JTable itemTable = null;
    String[] columnNames = { "ID", "Name", "Type", "Description", "Price", "Publisher Name" };
    Publisher publisher;

    Object[][] getDataItem() {
        String game = "";
        if (updateDLC.isSelected()) {
            game = "DLC";
        } else if (updateGame.isSelected()) {
            game = "Game";
        }
        int cnt = 0;
        for (int i = 0; i < data.length; i++) {
            if (game.equals((String) data[i][2]) && (Integer) data[i][5] == publisher.getId()) {
                cnt++;
            }
        }
        Object[][] data2 = new Object[cnt][6];
        int cnt2 = 0;
        for (int i = 0; i < data.length; i++) {
            if (game.equals(data[i][2]) && (Integer) data[i][5] == publisher.getId()) {
                data2[cnt2] = data[i];
                cnt2++;
            }
        }
        return data2;
    }

    public void loadTable() {
        itemTable.clearSelection();
        DefaultTableModel model = new DefaultTableModel(getDataItem(), columnNames);
        itemTable.setModel(model);
    }

    public EditItemView(Publisher publisher, ArrayList<Item> itemList) {
        this.publisher = publisher;
        updateItem = new JFrame("Edit Item");
        updateItem.setSize(450, 400);
        updateItem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateItem.setLocationRelativeTo(null);
        updateItem.setLayout(null);
        updateItem.getContentPane().setBackground(Color.DARK_GRAY);

        UpdateItemMenu.setBounds(20, 25, 170, 23);
        UpdateItemMenu.setForeground(Color.WHITE);
        updateItem.add(UpdateItemMenu);

        JSeparator separatorLine = new JSeparator();
        separatorLine.setBounds(20, 45, 75, 5);
        separatorLine.setForeground(Color.LIGHT_GRAY);
        updateItem.add(separatorLine);

        gameOrDLC.setBounds(20, 60, 200, 23);
        gameOrDLC.setForeground(Color.WHITE);
        updateItem.add(gameOrDLC);

        updateGame.setBounds(150, 60, 70, 23);
        updateDLC.setBounds(220, 60, 80, 23);
        updateGame.setBackground(Color.DARK_GRAY);
        updateGame.setForeground(Color.WHITE);
        updateDLC.setBackground(Color.DARK_GRAY);
        updateDLC.setForeground(Color.WHITE);
        updateItem.add(updateGame);
        updateItem.add(updateDLC);
        ButtonGroup bgUpdateItem = new ButtonGroup();
        updateDLC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTable();
            }
        });
        updateGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTable();
            }
        });
        bgUpdateItem.add(updateGame);
        bgUpdateItem.add(updateDLC);

        JSeparator separatorLine2 = new JSeparator();
        separatorLine2.setBounds(20, 90, 390, 5);
        separatorLine2.setForeground(Color.LIGHT_GRAY);
        updateItem.add(separatorLine2);

        data = new Object[itemList.size()][6];

        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            data[i][0] = item.getItemID();
            data[i][1] = item.getName();
            data[i][2] = item.getType();
            data[i][3] = item.getDescription();
            data[i][4] = item.getPrice();
            data[i][5] = item.getPublisherID();
        }

        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);

        itemTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(itemTable);
        scrollPane.setBounds(20, 110, 390, 80);
        updateItem.add(scrollPane);
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
                idField.setText(String.valueOf((Integer) getDataItem()[index][0]));

            }
        });

        idItem.setBounds(20, 210, 200, 23);
        idItem.setForeground(Color.WHITE);
        updateItem.add(idItem);

        idField = new JTextField();
        idField.setBounds(130, 210, 260, 23);
        idField.setForeground(Color.WHITE);
        idField.setBackground(Color.DARK_GRAY);
        updateItem.add(idField);

        btnSubmit.setBounds(40, 255, 160, 23);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBackground(Color.decode("#717D7E"));
        updateItem.add(btnSubmit);

        btnBack.setBounds(210, 255, 160, 23);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.decode("#717D7E"));
        updateItem.add(btnBack);

        itemTable.setBackground(Color.DARK_GRAY);
        itemTable.setForeground(Color.WHITE);
        itemTable.getTableHeader().setBackground(Color.DARK_GRAY);
        itemTable.getTableHeader().setForeground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.DARK_GRAY);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] x = null;
                for (Object[] v : data) {
                    if (String.valueOf(v[0]).equals(idField.getText())) {
                        x = v;
                        break;
                    }
                }
                if (x != null) {
                    new EditGameView(publisher, (String) x[2], (Integer) x[0]);
                }
                updateItem.dispose();

            }
        });
        updateItem.setVisible(true);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePublisherView(publisher);
                updateItem.dispose();
            }
        });

    }

}
