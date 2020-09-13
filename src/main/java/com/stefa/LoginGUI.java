package com.stefa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI {
    public JPanel loginPanel;
    private JButton userButton;
    private JButton adminButton;
    public UserGUI userGUI;
    private AdminGUI adminGUI;


    public LoginGUI(UserGUI userGUI, AdminGUI adminGUI) {
        this.userGUI = userGUI;
        this.adminGUI = adminGUI;
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame userFrame = new JFrame("Hotel - User");
                userFrame.setContentPane(userGUI.mainPanel);
                userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                userFrame.pack();
                userFrame.setVisible(true);
            }
        });
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame adminFrame = new JFrame("Hotel - Admin");
                adminFrame.setContentPane(adminGUI.adminPanel);
                adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                adminFrame.pack();
                adminFrame.setVisible(true);
            }
        });
    }
}
