package com.healthcare.model;

/**
 * Model class representing a Prescription
 */
public class Prescription {

    private String prescription_id;
    private String patient_id;
    private String clinician_id;
    private String appointment_id;
    private String prescription_date;
    private String medication_name;
    private String dosage;
    private String frequency;
    private String duration_days;
    private String quantity;
    private String instructions;
    private String pharmacy_name;
    private String status;
    private String issue_date;
    private String collection_date;

    public Prescription() {
    }

    public Prescription(String prescription_id, String patient_id, String clinician_id, String medication_name,
                        String dosage, String quantity, String pharmacy_name, String prescription_date,
                        String status, String instructions) {

        this(prescription_id, patient_id, clinician_id, "", prescription_date, medication_name, dosage, "", "", quantity,
             instructions, pharmacy_name, status, "", "");
    }

    public Prescription(String prescription_id, String patient_id, String clinician_id, String appointment_id,
                        String prescription_date, String medication_name, String dosage, String frequency,
                        String duration_days, String quantity, String instructions, String pharmacy_name,
                        String status, String issue_date, String collection_date) {

        this.prescription_id = prescription_id;
        this.patient_id = patient_id;
        this.clinician_id = clinician_id;
        this.appointment_id = appointment_id;
        this.prescription_date = prescription_date;
        this.medication_name = medication_name;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration_days = duration_days;
        this.quantity = quantity;
        this.instructions = instructions;
        this.pharmacy_name = pharmacy_name;
        this.status = status;
        this.issue_date = issue_date;
        this.collection_date = collection_date;
    }

    // Getters and Setters

    public String getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(String prescription_id) {
        this.prescription_id = prescription_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getClinician_id() {
        return clinician_id;
    }

    public void setClinician_id(String clinician_id) {
        this.clinician_id = clinician_id;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getPrescription_date() {
        return prescription_date;
    }

    public void setPrescription_date(String prescription_date) {
        this.prescription_date = prescription_date;
    }

    public String getMedication_name() {
        return medication_name;
    }

    public void setMedication_name(String medication_name) {
        this.medication_name = medication_name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDuration_days() {
        return duration_days;
    }

    public void setDuration_days(String duration_days) {
        this.duration_days = duration_days;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPharmacy_name() {
        return pharmacy_name;
    }

    public void setPharmacy_name(String pharmacy_name) {
        this.pharmacy_name = pharmacy_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getCollection_date() {
        return collection_date;
    }

    public void setCollection_date(String collection_date) {
        this.collection_date = collection_date;
    }

    @Override
    public String toString() {
        return prescription_id + " - " + medication_name + " (" + prescription_date + ")";
    }
}
