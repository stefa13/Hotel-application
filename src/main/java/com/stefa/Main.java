package com.stefa;

import com.stefa.domain.Reservation;
import com.stefa.repository.RepositoryReservations;
import com.stefa.repository.RepositoryRooms;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        RepositoryReservations repositoryReservations = new RepositoryReservations("src/main/reservations.txt");
        RepositoryRooms repositoryRooms = new RepositoryRooms("src/main/rooms.txt");
        Service service = new Service(repositoryRooms, repositoryReservations);
        UserGUI userGUI = new UserGUI(service);
        AdminGUI adminGUI = new AdminGUI(service);
        LoginGUI loginGUI = new LoginGUI(userGUI, adminGUI);
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setContentPane(loginGUI.loginPanel);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setVisible(true);

    }
}