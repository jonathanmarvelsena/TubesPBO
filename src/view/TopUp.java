package view;

import javax.swing.*;

import controller.Controller;
import model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopUp {
    Controller con = Controller.getInstance();
    JFrame top_up;
    JLabel walletLabel = new JLabel("Top Up Wallet");
    JTextField topUpAmountField;
    JLabel topUpAmount = new JLabel("Top up amount  : ");
    JLabel paymentMethod = new JLabel("Payment method                 :");
    String paymentMethods[] = { "Paypal", "Visa" };
    JComboBox<String> isiPembayaran = new JComboBox<>(paymentMethods);
    JButton btnPay = new JButton("Pay");
    JButton btnBack = new JButton("Back");

    public TopUp(User user) {
        top_up = new JFrame("Top Up");
        top_up.setSize(400, 240);
        top_up.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        top_up.setLocationRelativeTo(null);
        top_up.setLayout(null);
        top_up.getContentPane().setBackground(Color.DARK_GRAY);

        walletLabel.setBounds(20, 25, 170, 23);
        walletLabel.setForeground(Color.WHITE);
        top_up.add(walletLabel);

        JSeparator separatorLine = new JSeparator();
        separatorLine.setBounds(20, 45, 60, 5);
        separatorLine.setForeground(Color.LIGHT_GRAY);
        top_up.add(separatorLine);

        topUpAmount.setBounds(20, 60, 170, 23);
        topUpAmount.setForeground(Color.WHITE);
        top_up.add(topUpAmount);

        topUpAmountField = new JTextField();
        topUpAmountField.setBounds(180, 60, 160, 23);
        topUpAmountField.setForeground(Color.WHITE);
        topUpAmountField.setBackground(Color.DARK_GRAY);
        top_up.add(topUpAmountField);

        paymentMethod.setBounds(20, 100, 160, 23);
        paymentMethod.setForeground(Color.WHITE);
        top_up.add(paymentMethod);
        isiPembayaran.setBounds(180, 100, 160, 23);
        isiPembayaran.setForeground(Color.WHITE);
        isiPembayaran.setBackground(Color.decode("#717D7E"));
        top_up.add(isiPembayaran);

        btnPay.setBounds(20, 140, 140, 23);
        btnPay.setForeground(Color.WHITE);
        btnPay.setBackground(Color.decode("#717D7E"));
        top_up.add(btnPay);

        btnBack.setBounds(200, 140, 140, 23);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.decode("#717D7E"));
        top_up.add(btnBack);

        btnPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double topUpAmount = Double.parseDouble(topUpAmountField.getText());
                boolean insert = con.updateWallet(user, topUpAmount);
                if (insert) {
                    JOptionPane.showMessageDialog(null, "Top Up successful");
                    new HomeUser(user);
                    top_up.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Top Up failed");
                }
            }
        });
        top_up.setVisible(true);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeUser(user);
                top_up.setVisible(false);
            }
        });

        top_up.setVisible(true);

    }
}