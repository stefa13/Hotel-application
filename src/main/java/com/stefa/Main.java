package com.stefa;

import com.stefa.repository.SQLRepositoryReservations;
import com.stefa.repository.SQLRepositoryRooms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Hotel", "postgres", "password");

        if (connection != null) {
            System.out.println("Connected to the database!");
        } else {
            System.out.println("Failed to make connection!");
            return;
        }

        SQLRepositoryRooms repositoryRooms = new SQLRepositoryRooms(connection);
        SQLRepositoryReservations repositoryReservations = new SQLRepositoryReservations(connection);


//        RepositoryReservations repositoryReservations = new RepositoryReservations("src/main/reservations.txt");
//        RepositoryRooms repositoryRooms = new RepositoryRooms("src/main/rooms.txt");
//
        Service service = new Service(repositoryRooms, repositoryReservations);
        UserGUI userGUI = new UserGUI(service);
        AdminGUI adminGUI = new AdminGUI(service);
        LoginGUI loginGUI = new LoginGUI(userGUI, adminGUI);
    }
}