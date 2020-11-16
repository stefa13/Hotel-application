package com.stefa.repository;

import com.stefa.domain.Reservation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class SQLRepositoryReservations implements IRepository<Reservation> {
    private final Connection connection;

    public SQLRepositoryReservations(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Reservation reservation) throws IOException, ParseException, SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Reservation(client_name, check_in, check_out, num_guests) VALUES (?, ?, ?, ?)");

        statement.setString(1, reservation.getClientName());
        statement.setDate(2, new java.sql.Date(reservation.getCheckInDate().getTime()));
        statement.setDate(3, new java.sql.Date(reservation.getCheckOutDate().getTime()));
        statement.setInt(4, reservation.getNumberOfGuests());

        try {
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Reservation already found.");
        }
    }

    @Override
    public void remove(Reservation reservation) throws IOException, ParseException, SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Reservation WHERE id = ?");
        statement.setInt(1, reservation.getId());

        try {
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Reservation not found.");
        }
    }

    @Override
    public void update(Reservation reservation) throws IOException, ParseException, SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Reservation SET client_name = ?, check_in = ?, check_out = ?, num_guests = ?");
        statement.setString(1, reservation.getClientName());
        statement.setDate(2, new java.sql.Date(reservation.getCheckInDate().getTime()));
        statement.setDate(3, new java.sql.Date(reservation.getCheckOutDate().getTime()));
        statement.setInt(4, reservation.getNumberOfGuests());

        try {
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Reservation not found.");
        }
    }

    @Override
    public ArrayList<Reservation> getAll() throws FileNotFoundException, ParseException, SQLException {
        ArrayList<Reservation> reservations = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Reservation");
        while (resultSet.next()) {
            reservations.add(new Reservation(
                    resultSet.getInt("id"),
                    resultSet.getString("client_name"),
                    resultSet.getDate("check_in"),
                    resultSet.getDate("check_out"),
                    resultSet.getInt("num_guests"))
            );
        }
        return reservations;
    }
}
