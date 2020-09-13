package com.stefa;

import com.stefa.repository.RepositoryReservations;
import com.stefa.repository.RepositoryRooms;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        RepositoryReservations repositoryReservations = new RepositoryReservations("src/main/reservations.txt");
        RepositoryRooms repositoryRooms = new RepositoryRooms("src/main/rooms.txt");
        Service service = new Service(repositoryRooms, repositoryReservations);
        UserGUI userGUI = new UserGUI(service);
        AdminGUI adminGUI = new AdminGUI(service);
        LoginGUI loginGUI = new LoginGUI(userGUI, adminGUI);
    }
}