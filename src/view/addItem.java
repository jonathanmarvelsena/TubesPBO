package view;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import model.Publisher;

public class addItem {
    JFrame add_item;
    JLabel addItemMenu = new JLabel("Add Item");
    JLabel gameOrDLC = new JLabel("Add Game or DLC : ");
    JTextField isiNamaGame;
    JLabel namaGame = new JLabel("Game/DLC Name : ");
    JTextField isideskripsi;
    JLabel deskripsi = new JLabel("Deskripsi              : ");
    JTextField isiHarga;
    JLabel harga = new JLabel("Price                      : ");
    JButton btnSubmit = new JButton("Submit");
    JRadioButton addGame = new JRadioButton("Game");
    JRadioButton addDLC = new JRadioButton("DLC");

    public addItem(Publisher publisher){
        add_item = new JFrame("Add Item");
        add_item.setSize(430, 300);
        add_item.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add_item.setLocationRelativeTo(null);
        add_item.setLayout(null);
        add_item.getContentPane().setBackground(Color.DARK_GRAY);

        addItemMenu.setBounds(20, 25, 170, 23);
        addItemMenu.setForeground(Color.WHITE);
        add_item.add(addItemMenu);

        JSeparator garisPemisah = new JSeparator();
        garisPemisah.setBounds(20, 45, 60, 5);
        garisPemisah.setForeground(Color.LIGHT_GRAY);
        add_item.add(garisPemisah);
        
        gameOrDLC.setBounds(20, 60, 200, 23);
        gameOrDLC.setForeground(Color.WHITE);
        add_item.add(gameOrDLC);


        addGame.setBounds(130, 60, 70, 23);
        addDLC.setBounds(200, 60, 80, 23);
        addGame.setBackground(Color.DARK_GRAY);
        addGame.setForeground(Color.WHITE);
        addDLC.setBackground(Color.DARK_GRAY);
        addDLC.setForeground(Color.WHITE);
        add_item.add(addGame);
        add_item.add(addDLC);

        ButtonGroup bgAddItem = new ButtonGroup();
        bgAddItem.add(addGame);
        bgAddItem.add(addDLC);
        
        namaGame.setBounds(20, 90, 200, 23);
        namaGame.setForeground(Color.WHITE);
        add_item.add(namaGame);

        isiNamaGame = new JTextField();
        isiNamaGame.setBounds(130, 90, 250, 23);
        isiNamaGame.setForeground(Color.WHITE);
        isiNamaGame.setBackground(Color.DARK_GRAY);
        add_item.add(isiNamaGame);

        deskripsi.setBounds(20, 120, 200, 23);
        deskripsi.setForeground(Color.WHITE);
        add_item.add(deskripsi);

        isideskripsi = new JTextField();
        isideskripsi.setBounds(130, 120, 250, 23);
        isideskripsi.setForeground(Color.WHITE);
        isideskripsi.setBackground(Color.DARK_GRAY);
        add_item.add(isideskripsi);

        harga.setBounds(20, 150, 200, 23);
        harga.setForeground(Color.WHITE);
        add_item.add(harga);

        isiHarga = new JTextField();
        isiHarga.setBounds(130, 150, 250, 23);
        isiHarga.setForeground(Color.WHITE);
        isiHarga.setBackground(Color.DARK_GRAY);
        add_item.add(isiHarga);

        btnSubmit.setBounds(20, 200, 170, 23);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBackground(Color.decode("#717D7E"));
        add_item.add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        add_item.setVisible(true);

    }
   
}
