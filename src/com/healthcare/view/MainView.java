package com.healthcare.view;

import com.healthcare.controller.HealthcareController;
import javax.swing.*;
import java.awt.*;

/**
 * Main View class - Main GUI window with tabs for different entities
 */
public class MainView extends JFrame {
    private HealthcareController controller;
    private JTabbedPane tabbedPane;
    private PatientPanel patientPanel;
    private ClinicianPanel clinicianPanel;
    private FacilityPanel facilityPanel;
    private AppointmentPanel appointmentPanel;
    private PrescriptionPanel prescriptionPanel;
    private ReferralPanel referralPanel;
    private StaffPanel staffPanel;

    public MainView(HealthcareController controller) {
        this.controller = controller;
        initializeGUI();
        refreshAllPanels();
    }

    private void initializeGUI() {
        setTitle("Healthcare Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1250, 800);
        setLocationRelativeTo(null);

        // Use a clean background
        getContentPane().setBackground(new Color(245, 247, 250));

        // Header bar - SIMPLE AND CENTERED
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        header.setBackground(new Color(44, 62, 80)); // Dark blue-gray header

        // CENTERED TITLE
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel("Healthcare Management System");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titlePanel.add(titleLabel);
        
        header.add(titlePanel, BorderLayout.CENTER);

        // Simple subtitle centered below
        JLabel subtitleLabel = new JLabel("Clinical Data Management Dashboard");
        subtitleLabel.setForeground(new Color(200, 215, 230));
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel subtitlePanel = new JPanel(new BorderLayout());
        subtitlePanel.setOpaque(false);
        subtitlePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        subtitlePanel.add(subtitleLabel, BorderLayout.CENTER);
        
        header.add(subtitlePanel, BorderLayout.SOUTH);

        add(header, BorderLayout.NORTH);

        // Create menu bar
        createMenuBar();

        // Create tabbed pane - simple and clean
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Create panels for each entity
        patientPanel = new PatientPanel(controller);
        clinicianPanel = new ClinicianPanel(controller);
        facilityPanel = new FacilityPanel(controller);
        appointmentPanel = new AppointmentPanel(controller);
        prescriptionPanel = new PrescriptionPanel(controller);
        referralPanel = new ReferralPanel(controller);
        staffPanel = new StaffPanel(controller);

        // Add panels to tabs
        tabbedPane.addTab("Patients", patientPanel);
        tabbedPane.addTab("Clinicians", clinicianPanel);
        tabbedPane.addTab("Facilities", facilityPanel);
        tabbedPane.addTab("Appointments", appointmentPanel);
        tabbedPane.addTab("Prescriptions", prescriptionPanel);
        tabbedPane.addTab("Referrals", referralPanel);
        tabbedPane.addTab("Staff", staffPanel);

        // Style the tabs
        UIManager.put("TabbedPane.selected", new Color(240, 240, 240));
        SwingUtilities.updateComponentTreeUI(tabbedPane);

        add(tabbedPane, BorderLayout.CENTER);

        // Simple status bar
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        statusBar.setBackground(new Color(250, 250, 250));
        
        JLabel statusLabel = new JLabel("Ready");
        statusLabel.setForeground(new Color(100, 100, 100));
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusBar.add(statusLabel, BorderLayout.WEST);
        
        // Simple timestamp
        JLabel timestampLabel = new JLabel("Today: " + 
            new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        timestampLabel.setForeground(new Color(120, 120, 120));
        timestampLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        statusBar.add(timestampLabel, BorderLayout.EAST);
        
        add(statusBar, BorderLayout.SOUTH);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(250, 250, 250));
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));

        // File menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        JMenuItem loadMenuItem = new JMenuItem("Load Data");
        loadMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        loadMenuItem.addActionListener(e -> loadData());
        fileMenu.add(loadMenuItem);
        
        fileMenu.addSeparator();
        
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        aboutMenuItem.addActionListener(e -> showAboutDialog());
        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
    }

    private void loadData() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Data Directory");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String dataDirectory = fileChooser.getSelectedFile().getAbsolutePath();
            controller.loadData(dataDirectory);
            refreshAllPanels();
            JOptionPane.showMessageDialog(this, 
                "Data loaded successfully from:\n" + dataDirectory,
                "Data Loaded",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this,
            "Healthcare Management System\n" +
            "Version 2.0\n" +
            "© 2024 All Rights Reserved",
            "About",
            JOptionPane.INFORMATION_MESSAGE);
    }

    public void refreshAllPanels() {
        patientPanel.refreshData();
        clinicianPanel.refreshData();
        facilityPanel.refreshData();
        appointmentPanel.refreshData();
        prescriptionPanel.refreshData();
        referralPanel.refreshData();
        staffPanel.refreshData();
    }
}