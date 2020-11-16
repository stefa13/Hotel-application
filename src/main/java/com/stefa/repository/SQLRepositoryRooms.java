package com.stefa.repository;

import com.stefa.domain.Room;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class SQLRepositoryRooms implements IRepository<Room> {
    private final Connection connection;

    public SQLRepositoryRooms(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Room room) throws IOException, ParseException, SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Room(number, type, capacity, price, description) VALUES (?, ?, ?, ?, ?)");
        statement.setInt(1, room.getNumber());
        statement.setString(2, room.getType());
        statement.setInt(3, room.getCapacity());
        statement.setDouble(4, room.getPrice());
        statement.setString(5, room.getDescription());

        try {
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Room already found.");
        }
    }

    @Override
    public void remove(Room room) throws IOException, ParseException, SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Room WHERE number = ?");
        statement.setInt(1, room.getNumber());

        try {
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Room not found.");
        }
    }

    @Override
    public void update(Room room) throws IOException, ParseException, SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Room SET number = ?, type = ?, capacity = ?, price = ?, description = ?");
        statement.setInt(1, room.getNumber());
        statement.setString(2, room.getType());
        statement.setInt(3, room.getCapacity());
        statement.setDouble(4, room.getPrice());
        statement.setString(5, room.getDescription());

        try {
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Room not found.");
        }
    }

    @Override
    public ArrayList<Room> getAll() throws FileNotFoundException, ParseException, SQLException {
        ArrayList<Room> rooms = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Room");
        while (resultSet.next()) {
            rooms.add(new Room(
                    resultSet.getInt("number"),
                    resultSet.getString("type"),
                    resultSet.getInt("capacity"),
                    resultSet.getDouble("price"),
                    resultSet.getString("description"))
            );
        }
        return rooms;
    }
}
