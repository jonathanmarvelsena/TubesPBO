// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */
// package view;

// import java.awt.Color;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JSeparator;

// import model.Admin;

// /**
//  *
//  * @author abil
//  */
// public class HomeAdmin {

//     JFrame homeAdmin;
//     JButton btnViewAllUser;
//     JButton btnBanUser;
//     JButton btnShowBanUser;
//     JButton btnShowMonthlyTransaction;
//     JButton btnShowUserTransaction;

//     public HomeAdmin(Admin admin) {
//         homeAdmin = new JFrame("Main Menu Admin");
//         homeAdmin.setSize(300, 400);
//         homeAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         homeAdmin.setLocationRelativeTo(null);
//         homeAdmin.setLayout(null);
//         homeAdmin.getContentPane().setBackground(Color.DARK_GRAY);

//         JLabel title = new JLabel("Main Menu Admin");
//         title.setBounds(90, 30, 150, 23);
//         title.setForeground(Color.WHITE);
//         homeAdmin.add(title);

//         //Garis Pemisah
//         JSeparator garisPemisah = new JSeparator();
//         garisPemisah.setBounds(90, 55, 100, 5);
//         garisPemisah.setForeground(Color.LIGHT_GRAY);
//         homeAdmin.add(garisPemisah);

//         //Bagian Button View All Users
//         btnViewAllUser = new JButton("View All Users");
//         btnViewAllUser.setBounds(50, 90, 190, 23);
//         btnViewAllUser.setForeground(Color.WHITE);
//         btnViewAllUser.setBackground(Color.decode("#717D7E"));
//         homeAdmin.add(btnViewAllUser);

//         btnViewAllUser.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 new ViewAllUsers();
//                 homeAdmin.setVisible(false);
//             }
//         });

//         //Bagian Button Ban User
//         btnBanUser = new JButton("Ban Users");
//         btnBanUser.setBounds(50, 120, 190, 23);
//         btnBanUser.setForeground(Color.WHITE);
//         btnBanUser.setBackground(Color.decode("#717D7E"));
//         homeAdmin.add(btnBanUser);

//         btnBanUser.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 new BanUser();
//                 homeAdmin.setVisible(false);
//             }
//         });

//         //Bagian Button Show Ban User
//         btnShowBanUser = new JButton("Show Ban Users");
//         btnShowBanUser.setBounds(50, 150, 190, 23);
//         btnShowBanUser.setForeground(Color.WHITE);
//         btnShowBanUser.setBackground(Color.decode("#717D7E"));
//         homeAdmin.add(btnShowBanUser);

//         btnShowBanUser.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 new ShowBanUser();
//                 homeAdmin.setVisible(false);
//             }
//         });

//         //Bagian Button Show Monthly Transaction
//         btnShowMonthlyTransaction = new JButton("Show Monthly Transaction");
//         btnShowMonthlyTransaction.setBounds(50, 180, 190, 23);
//         btnShowMonthlyTransaction.setForeground(Color.WHITE);
//         btnShowMonthlyTransaction.setBackground(Color.decode("#717D7E"));
//         homeAdmin.add(btnShowMonthlyTransaction);

//         btnShowMonthlyTransaction.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 new ShowMonthlyTransaction();
//                 homeAdmin.setVisible(false);
//             }
//         });

//         //Bagian Button Show UserTransaction
//         btnShowUserTransaction = new JButton("Show User Transaction");
//         btnShowUserTransaction.setBounds(50, 210, 190, 23);
//         btnShowUserTransaction.setForeground(Color.WHITE);
//         btnShowUserTransaction.setBackground(Color.decode("#717D7E"));
//         homeAdmin.add(btnShowUserTransaction);

//         btnShowUserTransaction.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 new ShowUserTransaction();
//                 homeAdmin.setVisible(false);
//             }
//         });

//         homeAdmin.setVisible(true);
//     }

// }
