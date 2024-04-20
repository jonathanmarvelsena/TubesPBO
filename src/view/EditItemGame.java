package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import controller.Controller;
import model.Item;
import model.ItemStatus;
import model.Publisher;

public class EditItemGame {
    Controller con = Controller.getInstance();
    JFrame update_item;
    JLabel updateItemMenu = null;
    JTextField itemNameField;
    JLabel itemName = null;
    JTextField descriptionField;
    JLabel description = new JLabel("Description              : ");
    JTextField priceField;
    JLabel harga = new JLabel("Price                      : ");
    JCheckBox statusCheckBox = new JCheckBox("Available");
    JLabel status = new JLabel("status                    : ");
    JButton btnSubmit = new JButton("Submit");
    JButton btnBack = new JButton("Back");
    String nameType = "";

    public EditItemGame(Publisher publisher, String nameType, int id) {
        Item item = null;
        for (Item v : con.getAvailableItems()) {
            if (v.getItemID() == id) {
                item = v;
                break;
            }
        }
        this.nameType = nameType;
        updateItemMenu = new JLabel("Update " + nameType);
        itemName = new JLabel(nameType + " Name               : ");
        update_item = new JFrame("Edit " + nameType);
        update_item.setSize(400, 300);
        update_item.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        update_item.setLocationRelativeTo(null);
        update_item.setLayout(null);
        update_item.getContentPane().setBackground(Color.DARK_GRAY);

        updateItemMenu.setBounds(20, 25, 170, 23);
        updateItemMenu.setForeground(Color.WHITE);
        update_item.add(updateItemMenu);

        JSeparator garisPemisah = new JSeparator();
        garisPemisah.setBounds(20, 45, 75, 5);
        garisPemisah.setForeground(Color.LIGHT_GRAY);
        update_item.add(garisPemisah);

        itemName.setBounds(20, 60, 170, 23);
        itemName.setForeground(Color.WHITE);
        update_item.add(itemName);

        itemNameField = new JTextField();
        itemNameField.setBounds(130, 60, 230, 23);
        itemNameField.setForeground(Color.WHITE);
        itemNameField.setBackground(Color.DARK_GRAY);
        update_item.add(itemNameField);

        description.setBounds(20, 90, 200, 23);
        description.setForeground(Color.WHITE);
        update_item.add(description);

        descriptionField = new JTextField();
        descriptionField.setBounds(130, 90, 230, 23);
        descriptionField.setForeground(Color.WHITE);
        descriptionField.setBackground(Color.DARK_GRAY);
        update_item.add(descriptionField);

        harga.setBounds(20, 120, 200, 23);
        harga.setForeground(Color.WHITE);
        update_item.add(harga);

        priceField = new JTextField();
        priceField.setBounds(130, 120, 230, 23);
        priceField.setForeground(Color.WHITE);
        priceField.setBackground(Color.DARK_GRAY);
        update_item.add(priceField);

        status.setBounds(20, 150, 200, 23);
        status.setForeground(Color.WHITE);
        update_item.add(status);

        statusCheckBox.setBounds(127, 150, 100, 23);
        statusCheckBox.setForeground(Color.WHITE);
        statusCheckBox.setBackground(Color.DARK_GRAY);
        update_item.add(statusCheckBox);

        btnSubmit.setBounds(40, 195, 100, 23);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBackground(Color.decode("#717D7E"));
        update_item.add(btnSubmit);

        btnBack.setBounds(200, 195, 100, 23);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.decode("#717D7E"));
        update_item.add(btnBack);
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (con.updateGame(id, itemNameField.getText(), priceField.getText(), descriptionField.getText())) {
                    con.updateStatusItem(id, statusCheckBox.isSelected() ? "AVAILABLE" : "NOT_AVAILABLE");
                    new EditItem(publisher, con.getAvailableItems());
                    JOptionPane.showMessageDialog(null, "Update success");
                    update_item.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Update failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        update_item.setVisible(true);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditItem(publisher, con.getAvailableItems());
                update_item.dispose();
            }
        });

        if (item != null) {
            itemNameField.setText(item.getName());
            priceField.setText(String.valueOf(item.getPrice()));
            descriptionField.setText(item.getDescription());
            statusCheckBox.setSelected(item.getStatus() == ItemStatus.AVAILABLE);
        }
        update_item.setVisible(true);
    }
}
