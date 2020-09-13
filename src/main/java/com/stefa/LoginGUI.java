package com.stefa;

import javax.swing.*;

public class LoginGUI {
    public JPanel loginPanel;
    public UserGUI userGUI;
    private JButton userButton;
    private JButton adminButton;
    private AdminGUI adminGUI;
    private JFrame loginFrame = new JFrame("Login");


    public LoginGUI(UserGUI userGUI, AdminGUI adminGUI) {
        this.userGUI = userGUI;
        this.adminGUI = adminGUI;
        loginFrame.setContentPane(loginPanel);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setVisible(true);
        Utils.centreWindow(loginFrame);
        userButton.addActionListener(e -> {
            JFrame userFrame = new JFrame("Hotel - User");
            userFrame.setContentPane(userGUI.mainPanel);
            userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            userFrame.pack();
            userFrame.setVisible(true);
            loginFrame.setVisible(false);
            loginFrame.dispose();
            Utils.centreWindow(userFrame);
        });
        adminButton.addActionListener(e -> {
            JFrame adminFrame = new JFrame("Hotel - Admin");
            adminFrame.setContentPane(adminGUI.adminPanel);
            adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            adminFrame.pack();
            adminFrame.setVisible(true);
            loginFrame.setVisible(false);
            loginFrame.dispose();
            Utils.centreWindow(adminFrame);
        });
    }
}
