package com.healthcare.view;

import com.healthcare.controller.HealthcareController;
import com.healthcare.model.Facility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FacilityPanel extends JPanel {
    private HealthcareController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField facility_idField, facility_nameField, facility_typeField, addressField, postcodeField;
    private JTextField phone_numberField, emailField, opening_hoursField, manager_nameField, capacityField, specialities_offeredField;

    public FacilityPanel(HealthcareController controller) {
        this.controller = controller;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columns = {
                "Facility ID", "Facility Name", "Facility Type", "Address", "Postcode",
                "Phone Number", "Email", "Opening Hours", "Manager Name", "Capacity", "Specialities Offered"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(24);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) loadSelectedFacility();
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Facilities"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = createFormPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder("Facility Details"));
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

        addPair(panel, gbc, row, 0, "Facility ID:", facility_idField = new JTextField(15));
        addPair(panel, gbc, row, 2, "Facility Name:", facility_nameField = new JTextField(15));
        addPair(panel, gbc, row, 4, "Facility Type:", facility_typeField = new JTextField(15));
        row++;

        addPair(panel, gbc, row, 0, "Address:", addressField = new JTextField(15));
        addPair(panel, gbc, row, 2, "Postcode:", postcodeField = new JTextField(15));
        addPair(panel, gbc, row, 4, "Phone Number:", phone_numberField = new JTextField(15));
        row++;

        addPair(panel, gbc, row, 0, "Email:", emailField = new JTextField(15));
        addPair(panel, gbc, row, 2, "Opening Hours:", opening_hoursField = new JTextField(15));
        addPair(panel, gbc, row, 4, "Manager Name:", manager_nameField = new JTextField(15));
        row++;

        addPair(panel, gbc, row, 0, "Capacity:", capacityField = new JTextField(15));
        addPair(panel, gbc, row, 2, "Specialities Offered:", specialities_offeredField = new JTextField(15));

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
        addButton(panel, "Add", e -> addFacility());
        addButton(panel, "Update", e -> updateFacility());
        addButton(panel, "Delete", e -> deleteFacility());
        addButton(panel, "Clear", e -> clearForm());
        addButton(panel, "Refresh", e -> refreshData());
        return panel;
    }

    private void addButton(JPanel panel, String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
    }

    private void addFacility() {
        try {
            Facility facility = createFacilityFromForm();
            if (facility != null) {
                controller.addFacility(facility);
                refreshData();
                clearForm();
                JOptionPane.showMessageDialog(this, "Facility added!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateFacility() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a facility.");
            return;
        }
        try {
            String id = (String) tableModel.getValueAt(row, 0);
            controller.deleteFacility(id);
            controller.addFacility(createFacilityFromForm());
            refreshData();
            clearForm();
            JOptionPane.showMessageDialog(this, "Facility updated!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteFacility() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a facility.");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Delete this facility?", "Confirm",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            controller.deleteFacility((String) tableModel.getValueAt(row, 0));
            refreshData();
            clearForm();
        }
    }

    private Facility createFacilityFromForm() {
        if (facility_idField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Facility ID required.");
            return null;
        }
        return new Facility(
                facility_idField.getText().trim(),
                facility_nameField.getText().trim(),
                facility_typeField.getText().trim(),
                addressField.getText().trim(),
                postcodeField.getText().trim(),
                phone_numberField.getText().trim(),
                emailField.getText().trim(),
                opening_hoursField.getText().trim(),
                manager_nameField.getText().trim(),
                capacityField.getText().trim(),
                specialities_offeredField.getText().trim()
        );
    }

    private void loadSelectedFacility() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            facility_idField.setText((String) tableModel.getValueAt(row, 0));
            facility_nameField.setText((String) tableModel.getValueAt(row, 1));
            facility_typeField.setText((String) tableModel.getValueAt(row, 2));
            addressField.setText((String) tableModel.getValueAt(row, 3));
            postcodeField.setText((String) tableModel.getValueAt(row, 4));
            phone_numberField.setText((String) tableModel.getValueAt(row, 5));
            emailField.setText((String) tableModel.getValueAt(row, 6));
            opening_hoursField.setText((String) tableModel.getValueAt(row, 7));
            manager_nameField.setText((String) tableModel.getValueAt(row, 8));
            capacityField.setText((String) tableModel.getValueAt(row, 9));
            specialities_offeredField.setText((String) tableModel.getValueAt(row, 10));
        }
    }

    private void clearForm() {
        facility_idField.setText("");
        facility_nameField.setText("");
        facility_typeField.setText("");
        addressField.setText("");
        postcodeField.setText("");
        phone_numberField.setText("");
        emailField.setText("");
        opening_hoursField.setText("");
        manager_nameField.setText("");
        capacityField.setText("");
        specialities_offeredField.setText("");
    }

    public void refreshData() {
        tableModel.setRowCount(0);
        for (Facility f : controller.getAllFacilities()) {
            tableModel.addRow(new Object[]{
                    f.getFacility_id(), f.getFacility_name(), f.getFacility_type(), f.getAddress(), f.getPostcode(),
                    f.getPhone_number(), f.getEmail(), f.getOpening_hours(), f.getManager_name(), 
                    f.getCapacity(), f.getSpecialities_offered()
            });
        }
    }
}