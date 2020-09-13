package com.stefa;

import com.stefa.domain.Reservation;
import com.stefa.domain.Room;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class AdminGUI extends Observer{
    public JPanel adminPanel;
    private JTabbedPane tabbedPane;
    private JPanel reservationsPanel;
    private JPanel roomsPanel;
    private JTable reservationsTable;
    private JPanel formLayoutPanel;
    private JTextField clientNameTextField;
    private JTextField checkInDateTextField;
    private JTextField checkOutDateTextField;
    private JTextField numberOfGuestsTextField;
    private JTable roomsTable;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JPanel buttonsPanel;
    private JPanel formLayout;
    private JTextField roomNumberTextField;
    private JTextField typeTextField;
    private JTextField capacityTextField;
    private JTextField priceTextField;
    private JTextField descriptionTextField;
    private JPanel panel;
    private JPanel buttonsPanel2;
    private JButton addButton1;
    private JButton deleteButton1;
    private JButton updateButton1;
    private Service service;

    public AdminGUI(Service service) throws FileNotFoundException, ParseException {
        this.service = service;
        service.addObserver(this);
        populateReservationsTable(service.getAllReservations());
        populateRoomsTable(service.getAllRooms());
        addButton.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (clientNameTextField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please provide the name of the client");
                        return;
                    }
                    if (checkInDateTextField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please provide the check-in date");
                        return;
                    }
                    if (checkOutDateTextField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please provide the check-out date");
                        return;
                    }
                    if (numberOfGuestsTextField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please provide the number of guests");
                        return;
                    }
                    String clientName = clientNameTextField.getText();
                    int numberOfGuests = Integer.parseInt(numberOfGuestsTextField.getText());
                    Date checkInDate = new SimpleDateFormat("dd/MM/yyyy").parse( checkInDateTextField.getText());
                    Date checkOutDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkOutDateTextField.getText());
                    try {
                        service.addReservation(clientName, checkInDate, checkOutDate, numberOfGuests);
                        JOptionPane.showMessageDialog(null, "You successfully added the reservation");
                        clientNameTextField.setText("");
                        numberOfGuestsTextField.setText("");
                        checkInDateTextField.setText("");
                        checkOutDateTextField.setText("");
                    }catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
            }

        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = reservationsTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select the reservation you want to delete");
                    return;
                }
                try {
                    service.removeReservation(service.getAllReservations().get(reservationsTable.getSelectedRow()).getId());
                    clientNameTextField.setText("");
                    numberOfGuestsTextField.setText("");
                    checkInDateTextField.setText("");
                    checkOutDateTextField.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = reservationsTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select the reservation you want to update");
                }
                String clientName = clientNameTextField.getText();
                int numberOfGuests = Integer.parseInt(numberOfGuestsTextField.getText());
                Date checkInDate = new SimpleDateFormat("dd/MM/yyyy").parse( checkInDateTextField.getText());
                Date checkOutDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkOutDateTextField.getText());
                int id = service.getAllReservations().get(reservationsTable.getSelectedRow()).getId();
                try {
                    service.updateReservation(id, clientName, checkInDate, checkOutDate, numberOfGuests);
                    JOptionPane.showMessageDialog(null, "You successfully updated the reservation");
                    clientNameTextField.setText("");
                    numberOfGuestsTextField.setText("");
                    checkInDateTextField.setText("");
                    checkOutDateTextField.setText("");
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        reservationsTable.addMouseListener(new MouseAdapter() {
            @SneakyThrows
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = reservationsTable.getSelectedRow();
                Reservation reservation = service.getAllReservations().get(selectedRow);
                clientNameTextField.setText(reservation.getClientName());
                checkInDateTextField.setText(Utils.writeDate(reservation.getCheckInDate()));
                checkOutDateTextField.setText(Utils.writeDate(reservation.getCheckOutDate()));
                numberOfGuestsTextField.setText(Integer.toString(reservation.getNumberOfGuests()));
            }
        });
        addButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (roomNumberTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"Please provide the room number");
                    return;
                }
                if (typeTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"Please provide the type of the room");
                    return;
                }
                if (capacityTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"Please provide the capacity of the room");
                    return;
                }
                if (priceTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"Please provide the price of the room");
                    return;
                }
                if (descriptionTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"Please provide a description of the room");
                    return;
                }

                int roomNumber = Integer.parseInt(roomNumberTextField.getText());
                String type = typeTextField.getText();
                int capacity = Integer.parseInt(capacityTextField.getText());
                double price = Double.parseDouble(priceTextField.getText());
                String description = descriptionTextField.getText();
                try {
                    service.addRoom(roomNumber, type, capacity, price, description);
                    roomNumberTextField.setText("");
                    typeTextField.setText("");
                    capacityTextField.setText("");
                    priceTextField.setText("");
                    descriptionTextField.setText("");

                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        deleteButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = roomsTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select the room you want to delete");
                    return;
                }
                try {
                    service.removeRoom(Integer.parseInt(roomNumberTextField.getText()));
                    roomNumberTextField.setText("");
                    typeTextField.setText("");
                    priceTextField.setText("");
                    capacityTextField.setText("");
                    descriptionTextField.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        roomsTable.addMouseListener(new MouseAdapter() {
            @SneakyThrows
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = roomsTable.getSelectedRow();
                Room room = service.getAllRooms().get(selectedRow);
                roomNumberTextField.setText(Integer.toString(room.getNumber()));
                typeTextField.setText(room.getType());
                capacityTextField.setText(Integer.toString(room.getCapacity()));
                priceTextField.setText(Double.toString(room.getPrice()));
                descriptionTextField.setText(room.getDescription());
            }
        });
        updateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = roomsTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select the room you want to update");
                }
                int roomNumber = Integer.parseInt(roomNumberTextField.getText());
                String type = typeTextField.getText();
                int capacity = Integer.parseInt(capacityTextField.getText());
                double price = Double.parseDouble(priceTextField.getText());
                String description = descriptionTextField.getText();
                try {
                    service.updateRoom(roomNumber, type, capacity, price, description);
                    roomNumberTextField.setText("");
                    typeTextField.setText("");
                    capacityTextField.setText("");
                    priceTextField.setText("");
                    descriptionTextField.setText("");

                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    private void populateReservationsTable(ArrayList<Reservation> reservations) {
        String[] columnNames = {"ID", "Client name", "Check-in","Check-out","Number of guests"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        reservationsTable.setModel(model);
        for (Reservation r : reservations) {
            model.addRow(new Object[]{r.getId(), r.getClientName(),Utils.writeDate(r.getCheckInDate()), Utils.writeDate(r.getCheckOutDate()),r.getNumberOfGuests()});
        }
    }

    private void populateRoomsTable(ArrayList<Room> rooms) {
        String[] columnNames = {"Room number", "Type", "Capacity","Price/night","Description"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        roomsTable.setModel(model);

        for (Room r : rooms) {
            model.addRow(new Object[]{r.getNumber(), r.getType(),r.getCapacity(), r.getPrice(),r.getDescription()});
        }
    }

    @Override
    public void Update() throws FileNotFoundException, ParseException {
        populateReservationsTable(service.getAllReservations());
        populateRoomsTable(service.getAllRooms());
    }

}
