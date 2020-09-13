package com.stefa.repository;

import com.stefa.Utils;
import com.stefa.domain.Reservation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class RepositoryReservations implements IRepository<Reservation> {
    private String fileName;
    private static int currentId = 0;

    public RepositoryReservations(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void add(Reservation reservation) throws IOException, ParseException {
        ArrayList<Reservation> reservations = getAll();
//        for (Reservation r : reservations) {
//            if (r.getId() == reservation.getId()) {
//                throw new RuntimeException("Reservation already found.");
//            }
//        }
        RepositoryReservations.currentId = reservations.get(reservations.size() - 1).getId();
        reservation.setId(++RepositoryReservations.currentId);
        reservations.add(reservation);
        writeToFile(reservations);
    }

    @Override
    public void remove(Reservation reservation) throws IOException, ParseException {
        ArrayList<Reservation> reservations = getAll();
        for (int i = 0; i < reservations.size(); ++i) {
            if (reservations.get(i).getId() == reservation.getId()) {
                reservations.remove(i);
                writeToFile(reservations);
                return;
            }
        }
        throw new RuntimeException("Reservation not found.");
    }

    @Override
    public void update(Reservation reservation) throws IOException, ParseException {
        ArrayList<Reservation> reservations = getAll();
        for (int i = 0; i < reservations.size(); ++i) {
            if (reservations.get(i).getId() == reservation.getId()) {
                reservations.set(i, reservation);
                writeToFile(reservations);
                return;
            }
        }
        throw new RuntimeException("Room not found.");
    }

    @Override
    public ArrayList<Reservation> getAll() throws FileNotFoundException, ParseException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        ArrayList<Reservation> reservations = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(",");
            for (int i = 0; i < tokens.length; ++i) {
                tokens[i] = tokens[i].strip();
            }
            int id = Integer.parseInt(tokens[0]);
            String clientName = tokens[1];
            DateFormat format = new SimpleDateFormat("d/M/y");
            Date checkInDate = format.parse(tokens[2]);
            Date checkOutDate = format.parse(tokens[3]);
            int numberOfGuests = Integer.parseInt(tokens[4]);
            reservations.add(Reservation.builder().id(id).clientName(clientName).checkInDate(checkInDate).checkOutDate(checkOutDate).numberOfGuests(numberOfGuests).build());
        }
        return reservations;
    }

    public void writeToFile(ArrayList<Reservation> reservations) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        for (Reservation reservation : reservations) {
            fileWriter.write(reservation.getId() + "," + reservation.getClientName() + "," + Utils.writeDate(reservation.getCheckInDate()) + "," + Utils.writeDate(reservation.getCheckOutDate()) + "," + reservation.getNumberOfGuests() + "\n");
        }
        fileWriter.close();
    }
}

