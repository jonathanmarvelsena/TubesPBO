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

import model.Admin;
import model.Transaction;
import model.User;

/**
 *
 * @author abil
 */
public class ShowtransactionTransactionAdmin {

    JFrame container;
    JButton btnBack;
    JTextField txtUserId;

     public ShowtransactionTransactionAdmin(Admin admin,ArrayList<User> user) {
        container = new JFrame("Show transaction Transaction");
        container.setSize(500, 350);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLocationRelativeTo(null);
        container.setLayout(null);
        container.getContentPane().setBackground(Color.DARK_GRAY);

        JLabel title = new JLabel("Show transaction Transaction");
        title.setBounds(15, 15, 150, 23);
        title.setForeground(Color.WHITE);
        container.add(title);

        // Tambahkan label dan JTextField untuk memasukkan user ID
        JLabel lblUserId = new JLabel("User ID:");
        lblUserId.setBounds(15, 220, 70, 23);
        lblUserId.setForeground(Color.WHITE);
        container.add(lblUserId);

        txtUserId = new JTextField();
        txtUserId.setBounds(90, 220, 100, 23);
        container.add(txtUserId);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(200, 220, 80, 23);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBackground(Color.decode("#717D7E"));
        container.add(btnSearch);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mendapatkan nilai user_Id dari JTextField
                int userId = Integer.parseInt(txtUserId.getText());

                // Membuat ArrayList baru untuk menampung transaksi yang sesuai dengan user_Id yang dicari
                ArrayList<Transaction> userTransactions = new ArrayList<>();

                // Mengisi ArrayList userTransactions dengan transaksi yang sesuai user_Id
                for (Transaction transaction : transactions) {
                    if (transaction.getUserID() == userId) {
                        userTransactions.add(transaction);
                    }
                }

                // Menampilkan hasil transaksi user dalam tabel
                displayUserTransactions(userTransactions);
            }
        });

        //Bagian Button Back
        btnBack = new JButton("Back");
        btnBack.setBounds(315, 250, 150, 23);
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

    private void displayUserTransactions(ArrayList<ShoppingCart> useruser) {
        String[] columnNames = {"user_Id", "Item_Id", "Description"};
        Object[][] data = new Object[useruser.size()][3];
    
        for (int i = 0; i < useruser.size(); i++) {
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
    }
    

}
