package view;

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
import javax.swing.table.DefaultTableModel;
import controller.Controller;
import model.Game;
import model.Item;
import model.Publisher;
import model.User;

public class EditItemGame {
    Controller con = Controller.getInstance();
    JFrame update_item;
    JLabel updateItemMenu = null;
    JTextField isiNamaItem;
    JLabel namaItem = null;
    JTextField isiDeskripsi;
    JLabel deskripsi = new JLabel("Deskripsi              : ");
    JTextField isiHarga;
    JLabel harga = new JLabel("Price                      : ");
    JButton btnSubmit = new JButton("Submit");
    JButton btnBack = new JButton("Back");
    String nameType = "";

    public EditItemGame(Publisher publisher, String nameType, int id){
        Item item = null;
        for (Item v : con.getItem())
        {
            if (v.getItemID() == id) { item = v; break; }
        }
        this.nameType = nameType;
        updateItemMenu = new JLabel("Update " + nameType);
        namaItem = new JLabel(nameType + " Name : ");
        update_item = new JFrame("Edit " + nameType);
        update_item.setSize(400, 380);
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

        namaItem.setBounds(20, 60, 170, 23);
        namaItem.setForeground(Color.WHITE);
        update_item.add(namaItem);

        isiNamaItem = new JTextField();
        isiNamaItem.setBounds(130, 60, 230, 23);
        isiNamaItem.setForeground(Color.WHITE);
        isiNamaItem.setBackground(Color.DARK_GRAY);
        update_item.add(isiNamaItem);

        deskripsi.setBounds(20, 90, 200, 23);
        deskripsi.setForeground(Color.WHITE);
        update_item.add(deskripsi);

        isiDeskripsi = new JTextField();
        isiDeskripsi.setBounds(130, 90, 230, 23);
        isiDeskripsi.setForeground(Color.WHITE);
        isiDeskripsi.setBackground(Color.DARK_GRAY);
        update_item.add(isiDeskripsi);

        harga.setBounds(20, 120, 200, 23);
        harga.setForeground(Color.WHITE);
        update_item.add(harga);

        isiHarga = new JTextField();
        isiHarga.setBounds(130, 120, 230, 23);
        isiHarga.setForeground(Color.WHITE);
        isiHarga.setBackground(Color.DARK_GRAY);
        update_item.add(isiHarga);

        btnSubmit.setBounds(40, 150, 160, 23);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBackground(Color.decode("#717D7E"));
        update_item.add(btnSubmit);

        btnBack.setBounds(210, 150, 160, 23);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.decode("#717D7E"));
        update_item.add(btnBack);
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (con.updateGame(id, isiNamaItem.getText(), isiHarga.getText(), isiDeskripsi.getText()))
                {
                    new EditItem(publisher, con.getItem());
                    // success
                }
                else 
                {
                    // error
                }
            }
        });
        update_item.setVisible(true);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditItem(publisher, con.getItem());
            }
        });

        if (item != null)
        {
            isiNamaItem.setText(item.getName());
            isiHarga.setText(String.valueOf(item.getPrice()));
            isiDeskripsi.setText(item.getDescription());
        }
        update_item.setVisible(true);
    }
}
