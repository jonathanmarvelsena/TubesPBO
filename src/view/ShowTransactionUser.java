package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.User;

/**
 *
 * @author abil
 */
public class ShowTransactionUser {
    Controller con = Controller.getInstance();
    JFrame container;
    JButton btnBack;

    public ShowTransactionUser(User user) {
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

        JSeparator garisPemisah = new JSeparator();
        garisPemisah.setBounds(28, 150, 410, 5);
        garisPemisah.setForeground(Color.LIGHT_GRAY);
        container.add(garisPemisah);

        String[] columnNames = {"user_Id", "Item", "Description"};
        Object[][] data = new Object[getShoppingCart(user).size()][3];
    
        for (int i = 0; i < user.size(); i++) {
            ShoppingCart transaction = useruser.get(i);
            data[i][0] = transaction.getTransactionID();
            data[i][1] = transaction.getitemID();
            data[i][2] = transaction.getDescription();
        }
    
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
    
        JTable transactionTable = new JTable(model);
    
        JScrollPane scrollPane = new JScrollPane(transactionTable);
        scrollPane.setBounds(15, 250, 450, 150);
        container.add(scrollPane);

        //Bagian Button Back
        btnBack = new JButton("Back");
        btnBack.setBounds(270, 182, 150, 23);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.decode("#717D7E"));
        container.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeUser(user);
                container.setVisible(false);
            }
        });
        container.setVisible(true);
    }
}
