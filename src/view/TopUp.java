package view;

import javax.swing.*;

import controller.Controller;
import model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopUp {
    Controller con = Controller.getInstance();
    JFrame topUp;
    JLabel walletLabel = new JLabel("Top Up Wallet");
    JTextField topUpAmountField;
    JLabel topUpAmount = new JLabel("Top up amount  : ");
    JLabel paymentMethod = new JLabel("Payment method                 :");
    String paymentMethods[] = { "Paypal", "Visa" };
    JComboBox<String> isiPembayaran = new JComboBox<>(paymentMethods);
    JButton btnPay = new JButton("Pay");
    JButton btnBack = new JButton("Back");

    public TopUp(User user) {
        topUp = new JFrame("Top Up");
        topUp.setSize(400, 240);
        topUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topUp.setLocationRelativeTo(null);
        topUp.setLayout(null);
        topUp.getContentPane().setBackground(Color.DARK_GRAY);

        walletLabel.setBounds(20, 25, 170, 23);
        walletLabel.setForeground(Color.WHITE);
        topUp.add(walletLabel);

        JSeparator separatorLine = new JSeparator();
        separatorLine.setBounds(20, 45, 60, 5);
        separatorLine.setForeground(Color.LIGHT_GRAY);
        topUp.add(separatorLine);

        topUpAmount.setBounds(20, 60, 170, 23);
        topUpAmount.setForeground(Color.WHITE);
        topUp.add(topUpAmount);

        topUpAmountField = new JTextField();
        topUpAmountField.setBounds(180, 60, 160, 23);
        topUpAmountField.setForeground(Color.WHITE);
        topUpAmountField.setBackground(Color.DARK_GRAY);
        topUp.add(topUpAmountField);

        paymentMethod.setBounds(20, 100, 160, 23);
        paymentMethod.setForeground(Color.WHITE);
        topUp.add(paymentMethod);
        isiPembayaran.setBounds(180, 100, 160, 23);
        isiPembayaran.setForeground(Color.WHITE);
        isiPembayaran.setBackground(Color.decode("#717D7E"));
        topUp.add(isiPembayaran);

        btnPay.setBounds(20, 140, 140, 23);
        btnPay.setForeground(Color.WHITE);
        btnPay.setBackground(Color.decode("#717D7E"));
        topUp.add(btnPay);

        btnBack.setBounds(200, 140, 140, 23);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.decode("#717D7E"));
        topUp.add(btnBack);

        btnPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double topUpAmount = Double.parseDouble(topUpAmountField.getText());
                boolean insert = con.updateWallet(user, topUpAmount);
                if (insert) {
                    JOptionPane.showMessageDialog(null, "Top Up successful");
                    new HomeUser(user);
                    topUp.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Top Up failed");
                }
            }
        });
        topUp.setVisible(true);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeUser(user);
                topUp.setVisible(false);
            }
        });

        topUp.setVisible(true);

    }
}