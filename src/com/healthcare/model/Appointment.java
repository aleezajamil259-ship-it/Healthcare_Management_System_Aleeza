package com.healthcare.model;

/**
 * Model class representing an Appointment
 */
public class Appointment {

    private String appointment_id;
    private String patient_id;
    private String clinician_id;
    private String facility_id;
    private String appointment_date;
    private String appointment_time;
    private String duration_minutes;
    private String appointment_type;
    private String status;
    private String reason_for_visit;
    private String notes;
    private String created_date;
    private String last_modified;

    public Appointment() {
    }

    public Appointment(String appointment_id, String patient_id, String clinician_id, String facility_id,
                       String appointment_date, String appointment_time, String status,
                       String reason_for_visit, String notes) {

        this(appointment_id, patient_id, clinician_id, facility_id, appointment_date, appointment_time,
             "", "", status, reason_for_visit, notes, "", "");
    }

    public Appointment(String appointment_id, String patient_id, String clinician_id, String facility_id,
                       String appointment_date, String appointment_time, String duration_minutes,
                       String appointment_type, String status, String reason_for_visit, String notes,
                       String created_date, String last_modified) {

        this.appointment_id = appointment_id;
        this.patient_id = patient_id;
        this.clinician_id = clinician_id;
        this.facility_id = facility_id;
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.duration_minutes = duration_minutes;
        this.appointment_type = appointment_type;
        this.status = status;
        this.reason_for_visit = reason_for_visit;
        this.notes = notes;
        this.created_date = created_date;
        this.last_modified = last_modified;
    }

    // Getters and Setters

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
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

    public String getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(String facility_id) {
        this.facility_id = facility_id;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment_time = appointment_time;
    }

    public String getDuration_minutes() {
        return duration_minutes;
    }

    public void setDuration_minutes(String duration_minutes) {
        this.duration_minutes = duration_minutes;
    }

    public String getAppointment_type() {
        return appointment_type;
    }

    public void setAppointment_type(String appointment_type) {
        this.appointment_type = appointment_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason_for_visit() {
        return reason_for_visit;
    }

    public void setReason_for_visit(String reason_for_visit) {
        this.reason_for_visit = reason_for_visit;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }

    @Override
    public String toString() {
        return appointment_id + " - " + appointment_date + " " + appointment_time + " (" + status + ")";
    }
}
