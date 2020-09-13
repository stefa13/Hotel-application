package com.stefa;

import com.stefa.domain.Reservation;
import com.stefa.domain.Room;
import com.stefa.repository.IRepository;
import com.stefa.repository.RepositoryReservations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Service extends Subject{
    private IRepository<Room> repoRooms;
    private IRepository<Reservation> repoReservations;

    Service(IRepository<Room> repoRooms, IRepository<Reservation> repoReservations) {
        this.repoRooms = repoRooms;
        this.repoReservations = repoReservations;
    }

    public void addRoom(int number, String type, int capacity, double price, String description) throws IOException, ParseException {
        if (number < 0) {
            throw new RuntimeException("The number of the room should not be negative.");
        }
        if (capacity < 0) {
            throw new RuntimeException("The capacity of the room should not be negative.");
        }
        if (price < 0) {
            throw new RuntimeException("The price should not be negative.");
        }
        if (type.equals("")) {
            throw new RuntimeException("Please provide the type of the room.");
        }
        repoRooms.add(Room.builder().number(number).type(type).capacity(capacity).price(price).description(description).build());
        Notify();
    }

    public void addReservation(String clientName, Date checkInDate, Date checkOutDate, int numberOfGuests) throws IOException, ParseException {
        if (clientName.equals("")) {
            throw new RuntimeException("Please provide the name of the client");
        }
        if (numberOfGuests <= 0) {
            throw new RuntimeException("The number of guests should be at least 1.");
        }
        if (checkInDate.getDay() < 1 || checkInDate.getDay() > 31 || checkOutDate.getDay() < 1 || checkOutDate.getDay() > 31) {
            throw new RuntimeException("Please check your dates. Day must be between 1 and 31.");
        }
        if (checkInDate.getMonth() < 1 || checkInDate.getMonth() > 12 || checkOutDate.getMonth() < 1 || checkOutDate.getMonth() > 12) {
            throw new RuntimeException("Please check your dates. Month must be between 1 and 12.");
        }
        if (checkOutDate.before(checkInDate)) {
            throw new RuntimeException("Check-out date must be after check-in date.");
        }
        repoReservations.add(Reservation.builder().clientName(clientName).checkInDate(checkInDate).checkOutDate(checkOutDate).numberOfGuests(numberOfGuests).build());
        Notify();
    }

    public void removeRoom(int number) throws IOException, ParseException {
        if (number < 0) {
            throw new RuntimeException("The number of the room should not be negative.");
        }
        repoRooms.remove(Room.builder().number(number).build());
        Notify();
    }

    public void removeReservation(int id) throws IOException, ParseException {
        repoReservations.remove(Reservation.builder().id(id).build());
        Notify();
    }

    public void updateRoom(int number, String type, int capacity, double price, String description) throws IOException, ParseException {
        if (number < 0) {
            throw new RuntimeException("The number of the room should not be negative.");
        }
        if (capacity < 0) {
            throw new RuntimeException("The capacity of the room should not be negative.");
        }
        if (price < 0) {
            throw new RuntimeException("The price should not be negative.");
        }
        if (type.equals("")) {
            throw new RuntimeException("Please provide the type of the room.");
        }
        repoRooms.update(Room.builder().number(number).type(type).capacity(capacity).price(price).description(description).build());
        Notify();
    }

    public void updateReservation(int id, String clientName, Date checkInDate, Date checkOutDate, int numberOfGuests) throws IOException, ParseException {
        if (id < 0) {
            throw new RuntimeException("ID should not be negative");
        }
        if (clientName.equals("")) {
            throw new RuntimeException("Please provide the name of the client");
        }
        if (numberOfGuests <=0) {
            throw new RuntimeException("The number of guests should be at least 1.");
        }
        repoReservations.update(Reservation.builder().id(id).clientName(clientName).checkInDate(checkInDate).checkOutDate(checkOutDate).numberOfGuests(numberOfGuests).build());
        Notify();
    }

    public ArrayList<Room> getAllRooms() throws FileNotFoundException, ParseException {
        return repoRooms.getAll();
    }

    public ArrayList<Reservation> getAllReservations() throws FileNotFoundException, ParseException {
        return repoReservations.getAll();
    }

    public int getIdOfLastReservation() throws FileNotFoundException, ParseException {
        ArrayList<Reservation> reservations = repoReservations.getAll();
        return reservations.get(reservations.size() - 1).getId();
    }
}
