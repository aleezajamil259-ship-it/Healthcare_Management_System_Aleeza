package com.healthcare.view;

import com.healthcare.controller.HealthcareController;
import com.healthcare.model.Referral;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReferralPanel extends JPanel {
    private HealthcareController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField referral_idField, patient_idField, referring_clinician_idField, referred_to_clinician_idField;
    private JTextField referring_facility_idField, referred_to_facility_idField, referral_dateField, urgency_levelField;
    private JTextField referral_reasonField, clinical_summaryField, requested_investigationsField;
    private JTextField statusField, appointment_idField, notesField, created_dateField, last_updatedField;

    public ReferralPanel(HealthcareController controller) {
        this.controller = controller;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columns = {
                "Referral ID", "Patient ID", "Referring Clinician ID", "Referred To Clinician ID",
                "Referring Facility ID", "Referred To Facility ID", "Referral Date", "Urgency Level",
                "Referral Reason", "Clinical Summary", "Requested Investigations",
                "Status", "Appointment ID", "Notes", "Created Date", "Last Updated"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(24);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedReferral();
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Referrals"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = createFormPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder("Referral Details"));
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
        panel.add(new JLabel("Referral ID:"), gbc);
        gbc.gridx = 1;
        referral_idField = new JTextField(15);
        panel.add(referral_idField, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Patient ID:"), gbc);
        gbc.gridx = 3;
        patient_idField = new JTextField(15);
        panel.add(patient_idField, gbc);

        gbc.gridx = 4;
        panel.add(new JLabel("Referral Date:"), gbc);
        gbc.gridx = 5;
        referral_dateField = new JTextField(15);
        panel.add(referral_dateField, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Referring Clinician ID:"), gbc);
        gbc.gridx = 1;
        referring_clinician_idField = new JTextField(15);
        panel.add(referring_clinician_idField, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Referred To Clinician ID:"), gbc);
        gbc.gridx = 3;
        referred_to_clinician_idField = new JTextField(15);
        panel.add(referred_to_clinician_idField, gbc);

        gbc.gridx = 4;
        panel.add(new JLabel("Urgency Level:"), gbc);
        gbc.gridx = 5;
        urgency_levelField = new JTextField(15);
        panel.add(urgency_levelField, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Referring Facility ID:"), gbc);
        gbc.gridx = 1;
        referring_facility_idField = new JTextField(15);
        panel.add(referring_facility_idField, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Referred To Facility ID:"), gbc);
        gbc.gridx = 3;
        referred_to_facility_idField = new JTextField(15);
        panel.add(referred_to_facility_idField, gbc);

        gbc.gridx = 4;
        panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 5;
        statusField = new JTextField(15);
        panel.add(statusField, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Appointment ID:"), gbc);
        gbc.gridx = 1;
        appointment_idField = new JTextField(15);
        panel.add(appointment_idField, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Created Date:"), gbc);
        gbc.gridx = 3;
        created_dateField = new JTextField(15);
        panel.add(created_dateField, gbc);

        gbc.gridx = 4;
        panel.add(new JLabel("Last Updated:"), gbc);
        gbc.gridx = 5;
        last_updatedField = new JTextField(15);
        panel.add(last_updatedField, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Referral Reason:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        referral_reasonField = new JTextField(40);
        panel.add(referral_reasonField, gbc);
        gbc.gridwidth = 1;

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Clinical Summary:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        clinical_summaryField = new JTextField(40);
        panel.add(clinical_summaryField, gbc);
        gbc.gridwidth = 1;

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Requested Investigations:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        requested_investigationsField = new JTextField(40);
        panel.add(requested_investigationsField, gbc);
        gbc.gridwidth = 1;

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Notes:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        notesField = new JTextField(40);
        panel.add(notesField, gbc);
        gbc.gridwidth = 1;

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        addButton(panel, "Add", e -> addReferral());
        addButton(panel, "Update", e -> updateReferral());
        addButton(panel, "Delete", e -> deleteReferral());
        addButton(panel, "Generate File", e -> generateReferralFile());
        addButton(panel, "Clear", e -> clearForm());
        addButton(panel, "Refresh", e -> refreshData());
        return panel;
    }

    private void addButton(JPanel panel, String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
    }

    private void addReferral() {
        try {
            Referral referral = createReferralFromForm();
            if (referral != null) {
                controller.addReferral(referral);
                refreshData();
                clearForm();
                JOptionPane.showMessageDialog(this, "Referral added successfully!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateReferral() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a referral to update.");
            return;
        }

        try {
            String referralID = (String) tableModel.getValueAt(selectedRow, 0);
            Referral referral = createReferralFromForm();
            if (referral != null) {
                controller.deleteReferral(referralID);
                controller.addReferral(referral);
                refreshData();
                clearForm();
                JOptionPane.showMessageDialog(this, "Referral updated successfully!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating referral: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteReferral() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a referral to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this referral?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String referralID = (String) tableModel.getValueAt(selectedRow, 0);
            controller.deleteReferral(referralID);
            refreshData();
            clearForm();
            JOptionPane.showMessageDialog(this, "Referral deleted successfully!");
        }
    }

    private void generateReferralFile() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a referral to generate file.");
            return;
        }

        String referralID = (String) tableModel.getValueAt(selectedRow, 0);
        Referral referral = controller.getAllReferrals().stream()
                .filter(r -> r.getReferral_id().equals(referralID))
                .findFirst().orElse(null);

        if (referral != null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Referral File");
            fileChooser.setSelectedFile(new java.io.File("referral_" + referralID + ".txt"));
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                controller.generateReferralFile(referral, fileChooser.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Referral file generated successfully!");
            }
        }
    }

    private Referral createReferralFromForm() {
        if (referral_idField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Referral ID is required.");
            return null;
        }

        return new Referral(
                referral_idField.getText().trim(),
                patient_idField.getText().trim(),
                referring_clinician_idField.getText().trim(),
                referred_to_clinician_idField.getText().trim(),
                referring_facility_idField.getText().trim(),
                referred_to_facility_idField.getText().trim(),
                referral_dateField.getText().trim(),
                urgency_levelField.getText().trim(),
                referral_reasonField.getText().trim(),
                clinical_summaryField.getText().trim(),
                requested_investigationsField.getText().trim(),
                statusField.getText().trim(),
                appointment_idField.getText().trim(),
                notesField.getText().trim(),
                created_dateField.getText().trim(),
                last_updatedField.getText().trim()
        );
    }

    private void loadSelectedReferral() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            referral_idField.setText((String) tableModel.getValueAt(selectedRow, 0));
            patient_idField.setText((String) tableModel.getValueAt(selectedRow, 1));
            referring_clinician_idField.setText((String) tableModel.getValueAt(selectedRow, 2));
            referred_to_clinician_idField.setText((String) tableModel.getValueAt(selectedRow, 3));
            referring_facility_idField.setText((String) tableModel.getValueAt(selectedRow, 4));
            referred_to_facility_idField.setText((String) tableModel.getValueAt(selectedRow, 5));
            referral_dateField.setText((String) tableModel.getValueAt(selectedRow, 6));
            urgency_levelField.setText((String) tableModel.getValueAt(selectedRow, 7));
            referral_reasonField.setText((String) tableModel.getValueAt(selectedRow, 8));
            clinical_summaryField.setText((String) tableModel.getValueAt(selectedRow, 9));
            requested_investigationsField.setText((String) tableModel.getValueAt(selectedRow, 10));
            statusField.setText((String) tableModel.getValueAt(selectedRow, 11));
            appointment_idField.setText((String) tableModel.getValueAt(selectedRow, 12));
            notesField.setText((String) tableModel.getValueAt(selectedRow, 13));
            created_dateField.setText((String) tableModel.getValueAt(selectedRow, 14));
            last_updatedField.setText((String) tableModel.getValueAt(selectedRow, 15));
        }
    }

    private void clearForm() {
        referral_idField.setText("");
        patient_idField.setText("");
        referring_clinician_idField.setText("");
        referred_to_clinician_idField.setText("");
        referring_facility_idField.setText("");
        referred_to_facility_idField.setText("");
        referral_dateField.setText("");
        urgency_levelField.setText("");
        referral_reasonField.setText("");
        clinical_summaryField.setText("");
        requested_investigationsField.setText("");
        statusField.setText("");
        appointment_idField.setText("");
        notesField.setText("");
        created_dateField.setText("");
        last_updatedField.setText("");
    }

    public void refreshData() {
        tableModel.setRowCount(0);
        List<Referral> referrals = controller.getAllReferrals();
        for (Referral referral : referrals) {
            tableModel.addRow(new Object[]{
                    referral.getReferral_id(),
                    referral.getPatient_id(),
                    referral.getReferring_clinician_id(),
                    referral.getReferred_to_clinician_id(),
                    referral.getReferring_facility_id(),
                    referral.getReferred_to_facility_id(),
                    referral.getReferral_date(),
                    referral.getUrgency_level(),
                    referral.getReferral_reason(),
                    referral.getClinical_summary(),
                    referral.getRequested_investigations(),
                    referral.getStatus(),
                    referral.getAppointment_id(),
                    referral.getNotes(),
                    referral.getCreated_date(),
                    referral.getLast_updated()
            });
        }
    }
}