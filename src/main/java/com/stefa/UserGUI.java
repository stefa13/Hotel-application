package com.stefa;

import com.stefa.domain.Reservation;
import com.stefa.domain.Room;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserGUI extends Observer {
    public JPanel mainPanel;
    private JTabbedPane tabbedPanel;
    private JPanel makeReservationPanel;
    private JTable table;
    private JPanel formLayoutPanel;
    private JTextField nameTextField;
    private JTextField checkInTextField;
    private JTextField checkOutTextField;
    private JTextField numberGuestsTextField;
    private JButton reserveButton;
    private JScrollPane scrollPane;
    private JTextField idTextField;
    private JButton cancelButton;
    private Service service;

    public UserGUI(Service service) {
        this.service = service;
        try {
            populateList(this.service.getAllRooms());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        reserveButton.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please provide the name of the client");
                    return;
                }
                if (checkInTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please provide the check-in date");
                    return;
                }
                if (checkOutTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please provide the check-out date");
                    return;
                }
                if (numberGuestsTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please provide the number of guests");
                    return;
                }
                String name = nameTextField.getText();
                int numberOfGuests = Integer.parseInt(numberGuestsTextField.getText());
                Date checkInDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkInTextField.getText());
                Date checkOutDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkOutTextField.getText());
                try {
                    service.addReservation(name, checkInDate, checkOutDate, numberOfGuests);
                    JOptionPane.showMessageDialog(null, "Thank you for your reservation!"+"\n"+"Your reservation ID is "+ service.getIdOfLastReservation()+"\n"+ "Please keep this ID in case you want to cancel.");
                    nameTextField.setText("");
                    numberGuestsTextField.setText("");
                    checkInTextField.setText("");
                    checkOutTextField.setText("");
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idString = idTextField.getText();
                if (idString.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the ID of the reservation you want to cancel");
                } else {
                    int id = Integer.parseInt(idString);
                    try {
                        service.removeReservation(id);
                        idTextField.setText("");
                        JOptionPane.showMessageDialog(null, "Your reservation has been cancelled");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }

            }
        });
    }

    private void populateList(ArrayList<Room> rooms) throws FileNotFoundException, ParseException {
//        DefaultTableModel model = new DefaultTableModel() {
//            String[] employee = {"emp 1", "emp 2","f","d"};
//
//            @Override
//            public int getColumnCount() {
//                return employee.length;
//            }
//
//            @Override
//            public String getColumnName(int index) {
//                return employee[index];
//            }
//
//            @SneakyThrows
//            @Override
//            public int getRowCount() {
//                return rooms.size();
//            }
//        };
//        table.setModel(model);
//        Vector<String> header = new Vector<String>();
//        header.add("Type");
//        header.add("Capacity");
//        header.add("Price/night");
//        header.add("Description");
//        Vector<String> vector = new Vector<>();
//
//        DefaultTableModel model = new DefaultTableModel();
//        model.setColumnCount(4);
//        model.setRowCount(service.getAllRooms().size());
        String[] columnNames = {"Type", "Capacity", "Price/night","Description"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        table.setModel(model);

        for (Room r : rooms) {
            model.addRow(new Object[]{r.getType(), r.getCapacity(),r.getPrice(),r.getDescription()});
        }

    }

    @Override
    public void Update() throws FileNotFoundException, ParseException {
        populateList(service.getAllRooms());
    }
}
