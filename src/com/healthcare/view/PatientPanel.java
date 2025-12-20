package com.healthcare.view;

import com.healthcare.controller.HealthcareController;
import com.healthcare.model.Patient;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class PatientPanel extends JPanel {
    private HealthcareController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField patient_idField, first_nameField, last_nameField, date_of_birthField, nhs_numberField;
    private JTextField genderField, phone_numberField, emailField, addressField, postcodeField;
    private JTextField emergency_contact_nameField, emergency_contact_phoneField, registration_dateField, gp_surgery_idField;

    public PatientPanel(HealthcareController controller) {
        this.controller = controller;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columns = {"Patient ID", "First Name", "Last Name", "Date of Birth", "NHS Number", 
                          "Gender", "Phone Number", "Email", "Address", "Postcode", 
                          "Emergency Contact Name", "Emergency Contact Phone", "Registration Date", "GP Surgery ID"};
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
                loadSelectedPatient();
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Patients"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = createFormPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder("Patient Details"));
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
        panel.add(new JLabel("Patient ID:"), gbc);
        gbc.gridx = 1;
        patient_idField = new JTextField(15);
        panel.add(patient_idField, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 3;
        first_nameField = new JTextField(15);
        panel.add(first_nameField, gbc);

        gbc.gridx = 4;
        panel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 5;
        last_nameField = new JTextField(15);
        panel.add(last_nameField, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Date of Birth:"), gbc);
        gbc.gridx = 1;
        date_of_birthField = new JTextField(15);
        panel.add(date_of_birthField, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("NHS Number:"), gbc);
        gbc.gridx = 3;
        nhs_numberField = new JTextField(15);
        panel.add(nhs_numberField, gbc);

        gbc.gridx = 4;
        panel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 5;
        genderField = new JTextField(15);
        panel.add(genderField, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1;
        phone_numberField = new JTextField(15);
        panel.add(phone_numberField, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 3;
        emailField = new JTextField(15);
        panel.add(emailField, gbc);

        gbc.gridx = 4;
        panel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 5;
        addressField = new JTextField(15);
        panel.add(addressField, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Postcode:"), gbc);
        gbc.gridx = 1;
        postcodeField = new JTextField(15);
        panel.add(postcodeField, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Emergency Contact Name:"), gbc);
        gbc.gridx = 3;
        emergency_contact_nameField = new JTextField(15);
        panel.add(emergency_contact_nameField, gbc);

        gbc.gridx = 4;
        panel.add(new JLabel("Emergency Contact Phone:"), gbc);
        gbc.gridx = 5;
        emergency_contact_phoneField = new JTextField(15);
        panel.add(emergency_contact_phoneField, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Registration Date:"), gbc);
        gbc.gridx = 1;
        registration_dateField = new JTextField(15);
        panel.add(registration_dateField, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("GP Surgery ID:"), gbc);
        gbc.gridx = 3;
        gp_surgery_idField = new JTextField(15);
        panel.add(gp_surgery_idField, gbc);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        addButton(panel, "Add", e -> addPatient());
        addButton(panel, "Update", e -> updatePatient());
        addButton(panel, "Delete", e -> deletePatient());
        addButton(panel, "Clear", e -> clearForm());
        addButton(panel, "Refresh", e -> refreshData());
        return panel;
    }

    private void addButton(JPanel panel, String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
    }

    private void addPatient() {
        try {
            Patient patient = createPatientFromForm();
            if (patient != null) {
                controller.addPatient(patient);
                refreshData();
                clearForm();
                JOptionPane.showMessageDialog(this, "Patient added successfully!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePatient() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a patient to update.");
            return;
        }

        try {
            String patientID = (String) tableModel.getValueAt(selectedRow, 0);
            Patient patient = createPatientFromForm();
            if (patient != null) {
                controller.deletePatient(patientID);
                controller.addPatient(patient);
                refreshData();
                clearForm();
                JOptionPane.showMessageDialog(this, "Patient updated successfully!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating patient: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletePatient() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a patient to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this patient?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String patientID = (String) tableModel.getValueAt(selectedRow, 0);
            controller.deletePatient(patientID);
            refreshData();
            clearForm();
            JOptionPane.showMessageDialog(this, "Patient deleted successfully!");
        }
    }

    private Patient createPatientFromForm() {
        if (patient_idField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Patient ID is required.");
            return null;
        }

        return new Patient(
            patient_idField.getText().trim(),
            first_nameField.getText().trim(),
            last_nameField.getText().trim(),
            date_of_birthField.getText().trim(),
            nhs_numberField.getText().trim(),
            genderField.getText().trim(),
            phone_numberField.getText().trim(),
            emailField.getText().trim(),
            addressField.getText().trim(),
            postcodeField.getText().trim(),
            emergency_contact_nameField.getText().trim(),
            emergency_contact_phoneField.getText().trim(),
            registration_dateField.getText().trim(),
            gp_surgery_idField.getText().trim()
        );
    }

    private void loadSelectedPatient() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            patient_idField.setText((String) tableModel.getValueAt(selectedRow, 0));
            first_nameField.setText((String) tableModel.getValueAt(selectedRow, 1));
            last_nameField.setText((String) tableModel.getValueAt(selectedRow, 2));
            date_of_birthField.setText((String) tableModel.getValueAt(selectedRow, 3));
            nhs_numberField.setText((String) tableModel.getValueAt(selectedRow, 4));
            genderField.setText((String) tableModel.getValueAt(selectedRow, 5));
            phone_numberField.setText((String) tableModel.getValueAt(selectedRow, 6));
            emailField.setText((String) tableModel.getValueAt(selectedRow, 7));
            addressField.setText((String) tableModel.getValueAt(selectedRow, 8));
            postcodeField.setText((String) tableModel.getValueAt(selectedRow, 9));
            emergency_contact_nameField.setText((String) tableModel.getValueAt(selectedRow, 10));
            emergency_contact_phoneField.setText((String) tableModel.getValueAt(selectedRow, 11));
            registration_dateField.setText((String) tableModel.getValueAt(selectedRow, 12));
            gp_surgery_idField.setText((String) tableModel.getValueAt(selectedRow, 13));
        }
    }

    private void clearForm() {
        patient_idField.setText("");
        first_nameField.setText("");
        last_nameField.setText("");
        date_of_birthField.setText("");
        nhs_numberField.setText("");
        genderField.setText("");
        phone_numberField.setText("");
        emailField.setText("");
        addressField.setText("");
        postcodeField.setText("");
        emergency_contact_nameField.setText("");
        emergency_contact_phoneField.setText("");
        registration_dateField.setText("");
        gp_surgery_idField.setText("");
    }

    public void refreshData() {
        tableModel.setRowCount(0);
        List<Patient> patients = controller.getAllPatients();
        for (Patient patient : patients) {
            tableModel.addRow(new Object[]{
                patient.getPatient_id(),
                patient.getFirst_name(),
                patient.getLast_name(),
                patient.getDate_of_birth(),
                patient.getNhs_number(),
                patient.getGender(),
                patient.getPhone_number(),
                patient.getEmail(),
                patient.getAddress(),
                patient.getPostcode(),
                patient.getEmergency_contact_name(),
                patient.getEmergency_contact_phone(),
                patient.getRegistration_date(),
                patient.getGp_surgery_id()
            });
        }
    }
}