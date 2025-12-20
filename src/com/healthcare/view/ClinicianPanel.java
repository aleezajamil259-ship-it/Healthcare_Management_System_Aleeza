package com.healthcare.view;

import com.healthcare.controller.HealthcareController;
import com.healthcare.model.Clinician;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ClinicianPanel extends JPanel {
    private HealthcareController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField clinician_idField, first_nameField, last_nameField, titleField, specialityField;
    private JTextField gmc_numberField, phone_numberField, emailField, workplace_idField;
    private JTextField workplace_typeField, employment_statusField, start_dateField;

    public ClinicianPanel(HealthcareController controller) {
        this.controller = controller;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columns = {
                "Clinician ID", "First Name", "Last Name", "Title",
                "Speciality", "GMC Number", "Phone Number", "Email",
                "Workplace ID", "Workplace Type", "Employment Status", "Start Date"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(24);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) loadSelectedClinician();
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Clinicians"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = createFormPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder("Clinician Details"));
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

        addPair(panel, gbc, row, 0, "Clinician ID:", clinician_idField = new JTextField(15));
        addPair(panel, gbc, row, 2, "First Name:", first_nameField = new JTextField(15));
        addPair(panel, gbc, row, 4, "Last Name:", last_nameField = new JTextField(15));
        row++;

        addPair(panel, gbc, row, 0, "Title:", titleField = new JTextField(15));
        addPair(panel, gbc, row, 2, "Speciality:", specialityField = new JTextField(15));
        addPair(panel, gbc, row, 4, "GMC Number:", gmc_numberField = new JTextField(15));
        row++;

        addPair(panel, gbc, row, 0, "Phone Number:", phone_numberField = new JTextField(15));
        addPair(panel, gbc, row, 2, "Email:", emailField = new JTextField(15));
        addPair(panel, gbc, row, 4, "Workplace ID:", workplace_idField = new JTextField(15));
        row++;

        addPair(panel, gbc, row, 0, "Workplace Type:", workplace_typeField = new JTextField(15));
        addPair(panel, gbc, row, 2, "Employment Status:", employment_statusField = new JTextField(15));
        addPair(panel, gbc, row, 4, "Start Date:", start_dateField = new JTextField(15));

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
        addButton(panel, "Add", e -> addClinician());
        addButton(panel, "Update", e -> updateClinician());
        addButton(panel, "Delete", e -> deleteClinician());
        addButton(panel, "Clear", e -> clearForm());
        addButton(panel, "Refresh", e -> refreshData());
        return panel;
    }

    private void addButton(JPanel panel, String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
    }

    private void addClinician() {
        try {
            Clinician c = createClinicianFromForm();
            if (c != null) {
                controller.addClinician(c);
                refreshData();
                clearForm();
                JOptionPane.showMessageDialog(this, "Clinician added!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateClinician() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a clinician.");
            return;
        }
        try {
            String id = (String) tableModel.getValueAt(row, 0);
            controller.deleteClinician(id);
            controller.addClinician(createClinicianFromForm());
            refreshData();
            clearForm();
            JOptionPane.showMessageDialog(this, "Clinician updated!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteClinician() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a clinician.");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Delete this clinician?", "Confirm",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            controller.deleteClinician((String) tableModel.getValueAt(row, 0));
            refreshData();
            clearForm();
        }
    }

    private Clinician createClinicianFromForm() {
        if (clinician_idField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Clinician ID required.");
            return null;
        }
        return new Clinician(
                clinician_idField.getText().trim(),
                first_nameField.getText().trim(),
                last_nameField.getText().trim(),
                titleField.getText().trim(),
                specialityField.getText().trim(),
                gmc_numberField.getText().trim(),
                phone_numberField.getText().trim(),
                emailField.getText().trim(),
                workplace_idField.getText().trim(),
                workplace_typeField.getText().trim(),
                employment_statusField.getText().trim(),
                start_dateField.getText().trim()
        );
    }

    private void loadSelectedClinician() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            clinician_idField.setText((String) tableModel.getValueAt(row, 0));
            first_nameField.setText((String) tableModel.getValueAt(row, 1));
            last_nameField.setText((String) tableModel.getValueAt(row, 2));
            titleField.setText((String) tableModel.getValueAt(row, 3));
            specialityField.setText((String) tableModel.getValueAt(row, 4));
            gmc_numberField.setText((String) tableModel.getValueAt(row, 5));
            phone_numberField.setText((String) tableModel.getValueAt(row, 6));
            emailField.setText((String) tableModel.getValueAt(row, 7));
            workplace_idField.setText((String) tableModel.getValueAt(row, 8));
            workplace_typeField.setText((String) tableModel.getValueAt(row, 9));
            employment_statusField.setText((String) tableModel.getValueAt(row, 10));
            start_dateField.setText((String) tableModel.getValueAt(row, 11));
        }
    }

    private void clearForm() {
        clinician_idField.setText("");
        first_nameField.setText("");
        last_nameField.setText("");
        titleField.setText("");
        specialityField.setText("");
        gmc_numberField.setText("");
        phone_numberField.setText("");
        emailField.setText("");
        workplace_idField.setText("");
        workplace_typeField.setText("");
        employment_statusField.setText("");
        start_dateField.setText("");
    }

    public void refreshData() {
        tableModel.setRowCount(0);
        for (Clinician c : controller.getAllClinicians()) {
            tableModel.addRow(new Object[]{
                    c.getClinician_id(), c.getFirst_name(), c.getLast_name(), c.getTitle(),
                    c.getSpeciality(), c.getGmc_number(), c.getPhone_number(), c.getEmail(),
                    c.getWorkplace_id(), c.getWorkplace_type(), c.getEmployment_status(), c.getStart_date()
            });
        }
    }
}