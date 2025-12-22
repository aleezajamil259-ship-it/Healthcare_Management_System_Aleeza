package com.healthcare.view;

import com.healthcare.controller.HealthcareController;
import com.healthcare.model.Staff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StaffPanel extends JPanel {
    private HealthcareController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField staff_idField, first_nameField, last_nameField, roleField, departmentField;
    private JTextField facility_idField, phone_numberField, emailField, employment_statusField;
    private JTextField start_dateField, line_managerField, access_levelField;

    public StaffPanel(HealthcareController controller) {
        this.controller = controller;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columns = {
                "Staff ID", "First Name", "Last Name", "Role", "Department", "Facility ID",
                "Phone Number", "Email", "Employment Status", "Start Date", "Line Manager", "Access Level"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(24);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) loadSelectedStaff();
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Staff"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = createFormPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder("Staff Details"));
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

        addPair(panel, gbc, row, 0, "Staff ID:", staff_idField = new JTextField(15));
        addPair(panel, gbc, row, 2, "First Name:", first_nameField = new JTextField(15));
        addPair(panel, gbc, row, 4, "Last Name:", last_nameField = new JTextField(15));
        row++;

        addPair(panel, gbc, row, 0, "Role:", roleField = new JTextField(15));
        addPair(panel, gbc, row, 2, "Department:", departmentField = new JTextField(15));
        addPair(panel, gbc, row, 4, "Facility ID:", facility_idField = new JTextField(15));
        row++;

        addPair(panel, gbc, row, 0, "Phone Number:", phone_numberField = new JTextField(15));
        addPair(panel, gbc, row, 2, "Email:", emailField = new JTextField(15));
        addPair(panel, gbc, row, 4, "Employment Status:", employment_statusField = new JTextField(15));
        row++;

        addPair(panel, gbc, row, 0, "Start Date:", start_dateField = new JTextField(15));
        addPair(panel, gbc, row, 2, "Line Manager:", line_managerField = new JTextField(15));
        addPair(panel, gbc, row, 4, "Access Level:", access_levelField = new JTextField(15));

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
        addButton(panel, "Add", e -> addStaff());
        addButton(panel, "Update", e -> updateStaff());
        addButton(panel, "Delete", e -> deleteStaff());
        addButton(panel, "Clear", e -> clearForm());
        addButton(panel, "Refresh", e -> refreshData());
        return panel;
    }

    private void addButton(JPanel panel, String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
    }

    private void addStaff() {
        try {
            Staff staff = createStaffFromForm();
            if (staff != null) {
                controller.addStaff(staff);
                refreshData();
                clearForm();
                JOptionPane.showMessageDialog(this, "Staff added!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStaff() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a staff member.");
            return;
        }
        try {
            String id = (String) tableModel.getValueAt(row, 0);
            controller.deleteStaff(id);
            controller.addStaff(createStaffFromForm());
            refreshData();
            clearForm();
            JOptionPane.showMessageDialog(this, "Staff updated!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStaff() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a staff member.");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Delete this staff member?", "Confirm",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            controller.deleteStaff((String) tableModel.getValueAt(row, 0));
            refreshData();
            clearForm();
        }
    }

    private Staff createStaffFromForm() {
        if (staff_idField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Staff ID required.");
            return null;
        }
        return new Staff(
                staff_idField.getText().trim(),
                first_nameField.getText().trim(),
                last_nameField.getText().trim(),
                roleField.getText().trim(),
                departmentField.getText().trim(),
                facility_idField.getText().trim(),
                phone_numberField.getText().trim(),
                emailField.getText().trim(),
                employment_statusField.getText().trim(),
                start_dateField.getText().trim(),
                line_managerField.getText().trim(),
                access_levelField.getText().trim()
        );
    }

    private void loadSelectedStaff() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            staff_idField.setText((String) tableModel.getValueAt(row, 0));
            first_nameField.setText((String) tableModel.getValueAt(row, 1));
            last_nameField.setText((String) tableModel.getValueAt(row, 2));
            roleField.setText((String) tableModel.getValueAt(row, 3));
            departmentField.setText((String) tableModel.getValueAt(row, 4));
            facility_idField.setText((String) tableModel.getValueAt(row, 5));
            phone_numberField.setText((String) tableModel.getValueAt(row, 6));
            emailField.setText((String) tableModel.getValueAt(row, 7));
            employment_statusField.setText((String) tableModel.getValueAt(row, 8));
            start_dateField.setText((String) tableModel.getValueAt(row, 9));
            line_managerField.setText((String) tableModel.getValueAt(row, 10));
            access_levelField.setText((String) tableModel.getValueAt(row, 11));
        }
    }

    private void clearForm() {
        staff_idField.setText("");
        first_nameField.setText("");
        last_nameField.setText("");
        roleField.setText("");
        departmentField.setText("");
        facility_idField.setText("");
        phone_numberField.setText("");
        emailField.setText("");
        employment_statusField.setText("");
        start_dateField.setText("");
        line_managerField.setText("");
        access_levelField.setText("");
    }

    public void refreshData() {
        tableModel.setRowCount(0);
        for (Staff s : controller.getAllStaff()) {
            tableModel.addRow(new Object[]{
                    s.getStaff_id(), s.getFirst_name(), s.getLast_name(),
                    s.getRole(), s.getDepartment(), s.getFacility_id(),
                    s.getPhone_number(), s.getEmail(), s.getEmployment_status(),
                    s.getStart_date(), s.getLine_manager(), s.getAccess_level()
            });
        }
    }
}