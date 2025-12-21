package com.healthcare.view;

import com.healthcare.controller.HealthcareController;
import com.healthcare.model.Appointment;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class AppointmentPanel extends JPanel {
    private HealthcareController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField appointment_idField, patient_idField, clinician_idField, facility_idField;
    private JTextField appointment_dateField, appointment_timeField, duration_minutesField, appointment_typeField;
    private JTextField statusField, reason_for_visitField, notesField, created_dateField, last_modifiedField;

    public AppointmentPanel(HealthcareController controller) {
        this.controller = controller;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columns = {"Appointment ID", "Patient ID", "Clinician ID", "Facility ID", 
                          "Appointment Date", "Appointment Time", "Duration Minutes", "Appointment Type", 
                          "Status", "Reason for Visit", "Notes", "Created Date", "Last Modified"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(24);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedAppointment();
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Appointments"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = createFormPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder("Appointment Details"));
        add(formPanel, BorderLayout.SOUTH);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.NORTH);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        panel.add(new JLabel("Appointment ID:"), gbc);
        gbc.gridx = 1;
        appointment_idField = new JTextField(15);
        panel.add(appointment_idField, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Patient ID:"), gbc);
        gbc.gridx = 3;
        patient_idField = new JTextField(15);
        panel.add(patient_idField, gbc);

        gbc.gridx = 4;
        panel.add(new JLabel("Clinician ID:"), gbc);
        gbc.gridx = 5;
        clinician_idField = new JTextField(15);
        panel.add(clinician_idField, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Facility ID:"), gbc);
        gbc.gridx = 1;
        facility_idField = new JTextField(15);
        panel.add(facility_idField, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Appointment Date:"), gbc);
        gbc.gridx = 3;
        appointment_dateField = new JTextField(15);
        panel.add(appointment_dateField, gbc);

        gbc.gridx = 4;
        panel.add(new JLabel("Appointment Time:"), gbc);
        gbc.gridx = 5;
        appointment_timeField = new JTextField(15);
        panel.add(appointment_timeField, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Duration Minutes:"), gbc);
        gbc.gridx = 1;
        duration_minutesField = new JTextField(15);
        panel.add(duration_minutesField, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Appointment Type:"), gbc);
        gbc.gridx = 3;
        appointment_typeField = new JTextField(15);
        panel.add(appointment_typeField, gbc);

        gbc.gridx = 4;
        panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 5;
        statusField = new JTextField(15);
        panel.add(statusField, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Reason for Visit:"), gbc);
        gbc.gridx = 1;
        reason_for_visitField = new JTextField(15);
        panel.add(reason_for_visitField, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Notes:"), gbc);
        gbc.gridx = 3;
        notesField = new JTextField(15);
        panel.add(notesField, gbc);

        gbc.gridx = 4;
        panel.add(new JLabel("Created Date:"), gbc);
        gbc.gridx = 5;
        created_dateField = new JTextField(15);
        panel.add(created_dateField, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Last Modified:"), gbc);
        gbc.gridx = 1;
        last_modifiedField = new JTextField(15);
        panel.add(last_modifiedField, gbc);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        addButton(panel, "Add", e -> addAppointment());
        addButton(panel, "Update", e -> updateAppointment());
        addButton(panel, "Delete", e -> deleteAppointment());
        addButton(panel, "Clear", e -> clearForm());
        addButton(panel, "Refresh", e -> refreshData());
        return panel;
    }

    private void addButton(JPanel panel, String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
    }

    private void addAppointment() {
        try {
            Appointment appointment = createAppointmentFromForm();
            if (appointment != null) {
                controller.addAppointment(appointment);
                refreshData();
                clearForm();
                JOptionPane.showMessageDialog(this, "Appointment added successfully!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateAppointment() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select an appointment to update.");
            return;
        }

        try {
            String appointmentID = (String) tableModel.getValueAt(selectedRow, 0);
            Appointment appointment = createAppointmentFromForm();
            if (appointment != null) {
                controller.deleteAppointment(appointmentID);
                controller.addAppointment(appointment);
                refreshData();
                clearForm();
                JOptionPane.showMessageDialog(this, "Appointment updated successfully!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating appointment: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteAppointment() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select an appointment to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this appointment?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String appointmentID = (String) tableModel.getValueAt(selectedRow, 0);
            controller.deleteAppointment(appointmentID);
            refreshData();
            clearForm();
            JOptionPane.showMessageDialog(this, "Appointment deleted successfully!");
        }
    }

    private Appointment createAppointmentFromForm() {
        if (appointment_idField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Appointment ID is required.");
            return null;
        }

        return new Appointment(
            appointment_idField.getText().trim(),
            patient_idField.getText().trim(),
            clinician_idField.getText().trim(),
            facility_idField.getText().trim(),
            appointment_dateField.getText().trim(),
            appointment_timeField.getText().trim(),
            duration_minutesField.getText().trim(),
            appointment_typeField.getText().trim(),
            statusField.getText().trim(),
            reason_for_visitField.getText().trim(),
            notesField.getText().trim(),
            created_dateField.getText().trim(),
            last_modifiedField.getText().trim()
        );
    }

    private void loadSelectedAppointment() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            appointment_idField.setText((String) tableModel.getValueAt(selectedRow, 0));
            patient_idField.setText((String) tableModel.getValueAt(selectedRow, 1));
            clinician_idField.setText((String) tableModel.getValueAt(selectedRow, 2));
            facility_idField.setText((String) tableModel.getValueAt(selectedRow, 3));
            appointment_dateField.setText((String) tableModel.getValueAt(selectedRow, 4));
            appointment_timeField.setText((String) tableModel.getValueAt(selectedRow, 5));
            duration_minutesField.setText((String) tableModel.getValueAt(selectedRow, 6));
            appointment_typeField.setText((String) tableModel.getValueAt(selectedRow, 7));
            statusField.setText((String) tableModel.getValueAt(selectedRow, 8));
            reason_for_visitField.setText((String) tableModel.getValueAt(selectedRow, 9));
            notesField.setText((String) tableModel.getValueAt(selectedRow, 10));
            created_dateField.setText((String) tableModel.getValueAt(selectedRow, 11));
            last_modifiedField.setText((String) tableModel.getValueAt(selectedRow, 12));
        }
    }

    private void clearForm() {
        appointment_idField.setText("");
        patient_idField.setText("");
        clinician_idField.setText("");
        facility_idField.setText("");
        appointment_dateField.setText("");
        appointment_timeField.setText("");
        duration_minutesField.setText("");
        appointment_typeField.setText("");
        statusField.setText("");
        reason_for_visitField.setText("");
        notesField.setText("");
        created_dateField.setText("");
        last_modifiedField.setText("");
    }

    public void refreshData() {
        tableModel.setRowCount(0);
        List<Appointment> appointments = controller.getAllAppointments();
        for (Appointment appointment : appointments) {
            tableModel.addRow(new Object[]{
                appointment.getAppointment_id(),
                appointment.getPatient_id(),
                appointment.getClinician_id(),
                appointment.getFacility_id(),
                appointment.getAppointment_date(),
                appointment.getAppointment_time(),
                appointment.getDuration_minutes(),
                appointment.getAppointment_type(),
                appointment.getStatus(),
                appointment.getReason_for_visit(),
                appointment.getNotes(),
                appointment.getCreated_date(),
                appointment.getLast_modified()
            });
        }
    }
}