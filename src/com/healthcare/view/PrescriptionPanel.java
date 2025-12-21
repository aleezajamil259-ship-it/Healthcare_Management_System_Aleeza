package com.healthcare.view;

import com.healthcare.controller.HealthcareController;
import com.healthcare.model.Prescription;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PrescriptionPanel extends JPanel {
    private HealthcareController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField prescription_idField, patient_idField, clinician_idField, appointment_idField;
    private JTextField prescription_dateField, medication_nameField, dosageField, frequencyField;
    private JTextField duration_daysField, quantityField, instructionsField, pharmacy_nameField;
    private JTextField statusField, issue_dateField, collection_dateField;

    public PrescriptionPanel(HealthcareController controller) {
        this.controller = controller;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columns = {
                "Prescription ID", "Patient ID", "Clinician ID", "Appointment ID",
                "Prescription Date", "Medication Name", "Dosage", "Frequency", "Duration Days",
                "Quantity", "Instructions", "Pharmacy Name", "Status", "Issue Date", "Collection Date"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(24);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) loadSelectedPrescription();
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Prescriptions"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = createFormPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder("Prescription Details"));
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

        addPair(panel, gbc, row, 0, "Prescription ID:", prescription_idField = new JTextField(15));
        addPair(panel, gbc, row, 2, "Patient ID:", patient_idField = new JTextField(15));
        addPair(panel, gbc, row, 4, "Clinician ID:", clinician_idField = new JTextField(15));
        row++;

        addPair(panel, gbc, row, 0, "Appointment ID:", appointment_idField = new JTextField(15));
        addPair(panel, gbc, row, 2, "Prescription Date:", prescription_dateField = new JTextField(15));
        addPair(panel, gbc, row, 4, "Medication Name:", medication_nameField = new JTextField(15));
        row++;

        addPair(panel, gbc, row, 0, "Dosage:", dosageField = new JTextField(15));
        addPair(panel, gbc, row, 2, "Frequency:", frequencyField = new JTextField(15));
        addPair(panel, gbc, row, 4, "Duration Days:", duration_daysField = new JTextField(15));
        row++;

        addPair(panel, gbc, row, 0, "Quantity:", quantityField = new JTextField(15));
        addPair(panel, gbc, row, 2, "Instructions:", instructionsField = new JTextField(15));
        addPair(panel, gbc, row, 4, "Pharmacy Name:", pharmacy_nameField = new JTextField(15));
        row++;

        addPair(panel, gbc, row, 0, "Status:", statusField = new JTextField(15));
        addPair(panel, gbc, row, 2, "Issue Date:", issue_dateField = new JTextField(15));
        addPair(panel, gbc, row, 4, "Collection Date:", collection_dateField = new JTextField(15));

        return panel;
    }

    private void addPair(JPanel panel, GridBagConstraints gbc, int row, int col,
                         String label, JTextField field) {
        gbc.gridy = row;

        gbc.gridx = col;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = col + 1;
        panel.add(field, gbc);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        addButton(panel, "Add", e -> addPrescription());
        addButton(panel, "Update", e -> updatePrescription());
        addButton(panel, "Delete", e -> deletePrescription());
        addButton(panel, "Clear", e -> clearForm());
        addButton(panel, "Refresh", e -> refreshData());
        return panel;
    }

    private void addButton(JPanel panel, String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
    }

    private void addPrescription() {
        try {
            Prescription prescription = createPrescriptionFromForm();
            if (prescription != null) {
                controller.addPrescription(prescription);
                refreshData();
                clearForm();
                JOptionPane.showMessageDialog(this, "Prescription added!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePrescription() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a prescription.");
            return;
        }
        try {
            String id = (String) tableModel.getValueAt(row, 0);
            controller.deletePrescription(id);
            controller.addPrescription(createPrescriptionFromForm());
            refreshData();
            clearForm();
            JOptionPane.showMessageDialog(this, "Prescription updated!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletePrescription() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a prescription.");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Delete this prescription?",
                "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            controller.deletePrescription((String) tableModel.getValueAt(row, 0));
            refreshData();
            clearForm();
        }
    }

    private Prescription createPrescriptionFromForm() {
        if (prescription_idField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Prescription ID required.");
            return null;
        }
        return new Prescription(
                prescription_idField.getText().trim(),
                patient_idField.getText().trim(),
                clinician_idField.getText().trim(),
                appointment_idField.getText().trim(),
                prescription_dateField.getText().trim(),
                medication_nameField.getText().trim(),
                dosageField.getText().trim(),
                frequencyField.getText().trim(),
                duration_daysField.getText().trim(),
                quantityField.getText().trim(),
                instructionsField.getText().trim(),
                pharmacy_nameField.getText().trim(),
                statusField.getText().trim(),
                issue_dateField.getText().trim(),
                collection_dateField.getText().trim()
        );
    }

    private void loadSelectedPrescription() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            prescription_idField.setText((String) tableModel.getValueAt(row, 0));
            patient_idField.setText((String) tableModel.getValueAt(row, 1));
            clinician_idField.setText((String) tableModel.getValueAt(row, 2));
            appointment_idField.setText((String) tableModel.getValueAt(row, 3));
            prescription_dateField.setText((String) tableModel.getValueAt(row, 4));
            medication_nameField.setText((String) tableModel.getValueAt(row, 5));
            dosageField.setText((String) tableModel.getValueAt(row, 6));
            frequencyField.setText((String) tableModel.getValueAt(row, 7));
            duration_daysField.setText((String) tableModel.getValueAt(row, 8));
            quantityField.setText((String) tableModel.getValueAt(row, 9));
            instructionsField.setText((String) tableModel.getValueAt(row, 10));
            pharmacy_nameField.setText((String) tableModel.getValueAt(row, 11));
            statusField.setText((String) tableModel.getValueAt(row, 12));
            issue_dateField.setText((String) tableModel.getValueAt(row, 13));
            collection_dateField.setText((String) tableModel.getValueAt(row, 14));
        }
    }

    private void clearForm() {
        prescription_idField.setText("");
        patient_idField.setText("");
        clinician_idField.setText("");
        appointment_idField.setText("");
        prescription_dateField.setText("");
        medication_nameField.setText("");
        dosageField.setText("");
        frequencyField.setText("");
        duration_daysField.setText("");
        quantityField.setText("");
        instructionsField.setText("");
        pharmacy_nameField.setText("");
        statusField.setText("");
        issue_dateField.setText("");
        collection_dateField.setText("");
    }

    public void refreshData() {
        tableModel.setRowCount(0);
        for (Prescription p : controller.getAllPrescriptions()) {
            tableModel.addRow(new Object[]{
                    p.getPrescription_id(), p.getPatient_id(), p.getClinician_id(), p.getAppointment_id(),
                    p.getPrescription_date(), p.getMedication_name(), p.getDosage(), p.getFrequency(),
                    p.getDuration_days(), p.getQuantity(), p.getInstructions(), p.getPharmacy_name(),
                    p.getStatus(), p.getIssue_date(), p.getCollection_date()
            });
        }
    }
}