package view;

import model.Publisher;
import model.User;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import controller.Controller;

public class ShowGameList {
        Controller con = Controller.getInstance();
        JFrame show_game;
        JLabel list_game = new JLabel("Game or DLC ");
        Object[][] data = null;
        JTable itemTable = null;
        String[] columnNames = {"Name", "Type", "Description","Price"};
        User user;

    public ShowGameList(User user){
        show_game = new JFrame("Add Item");
        show_game.setBounds(20, 25, 400, 400);
        show_game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        show_game.setLocationRelativeTo(null);
        show_game.setLayout(null);
        show_game.getContentPane().setBackground(Color.DARK_GRAY); 

        

        
    }
}
