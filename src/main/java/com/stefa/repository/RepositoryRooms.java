package com.stefa.repository;

import com.stefa.domain.Room;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RepositoryRooms implements IRepository<Room> {
    private String fileName;

    public RepositoryRooms(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void add(Room room) throws IOException {
        ArrayList<Room> rooms = getAll();
        for (Room r : rooms) {
            if (r.getNumber() == room.getNumber()) {
                throw new RuntimeException("Room already found.");
            }
        }
        rooms.add(room);
        writeToFile(rooms);
    }

    @Override
    public void remove(Room room) throws IOException {
        ArrayList<Room> rooms = getAll();
        for (int i = 0; i < rooms.size(); ++i) {
            if (rooms.get(i).getNumber() == room.getNumber()) {
                rooms.remove(i);
                writeToFile(rooms);
                return;
            }
        }
        throw new RuntimeException("Room not found.");
    }

    @Override
    public void update(Room room) throws IOException {
        ArrayList<Room> rooms = getAll();
        for (int i = 0; i < rooms.size(); ++i) {
            if (rooms.get(i).getNumber() == room.getNumber()) {
                rooms.set(i, room);
                writeToFile(rooms);
                return;
            }
        }
        throw new RuntimeException("Room not found.");
    }

    @Override
    public ArrayList<Room> getAll() throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        ArrayList<Room> rooms = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(",");
            int number = Integer.parseInt(tokens[0]);
            String type = tokens[1];
            int capacity = Integer.parseInt(tokens[2]);
            double price = Double.parseDouble(tokens[3]);
            String description = tokens[4];
            rooms.add(Room.builder().number(number).type(type).capacity(capacity).price(price).description(description).build());
        }
        return rooms;
    }

    public void writeToFile(ArrayList<Room> rooms) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        for (Room room : rooms) {
            fileWriter.write(room.getNumber() + "," + room.getType() + "," + room.getCapacity() + "," + room.getPrice() + "," + room.getDescription() + "\n");
        }
        fileWriter.close();
    }
}
