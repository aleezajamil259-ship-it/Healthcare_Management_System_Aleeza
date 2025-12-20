package com.healthcare.model;

/**
 * Model class representing a Referral
 */
public class Referral {

    private String referral_id;
    private String patient_id;
    private String referring_clinician_id;
    private String referred_to_clinician_id;
    private String referring_facility_id;
    private String referred_to_facility_id;
    private String referral_date;
    private String urgency_level;
    private String referral_reason;
    private String clinical_summary;
    private String requested_investigations;
    private String status;
    private String appointment_id;
    private String notes;
    private String created_date;
    private String last_updated;

    public Referral() {
    }

    public Referral(String referral_id, String patient_id, String referring_clinician_id,
                    String referred_to_clinician_id, String referring_facility_id,
                    String referred_to_facility_id, String referral_date, String urgency_level,
                    String clinical_summary, String status) {

        this(referral_id, patient_id, referring_clinician_id, referred_to_clinician_id,
             referring_facility_id, referred_to_facility_id, referral_date, urgency_level,
             "", clinical_summary, "", status, "", "", "", "");
    }

    public Referral(String referral_id, String patient_id, String referring_clinician_id,
                    String referred_to_clinician_id, String referring_facility_id,
                    String referred_to_facility_id, String referral_date, String urgency_level,
                    String referral_reason, String clinical_summary, String requested_investigations,
                    String status, String appointment_id, String notes, String created_date,
                    String last_updated) {

        this.referral_id = referral_id;
        this.patient_id = patient_id;
        this.referring_clinician_id = referring_clinician_id;
        this.referred_to_clinician_id = referred_to_clinician_id;
        this.referring_facility_id = referring_facility_id;
        this.referred_to_facility_id = referred_to_facility_id;
        this.referral_date = referral_date;
        this.urgency_level = urgency_level;
        this.referral_reason = referral_reason;
        this.clinical_summary = clinical_summary;
        this.requested_investigations = requested_investigations;
        this.status = status;
        this.appointment_id = appointment_id;
        this.notes = notes;
        this.created_date = created_date;
        this.last_updated = last_updated;
    }

    // Getters and Setters

    public String getReferral_id() {
        return referral_id;
    }

    public void setReferral_id(String referral_id) {
        this.referral_id = referral_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getReferring_clinician_id() {
        return referring_clinician_id;
    }

    public void setReferring_clinician_id(String referring_clinician_id) {
        this.referring_clinician_id = referring_clinician_id;
    }

    public String getReferred_to_clinician_id() {
        return referred_to_clinician_id;
    }

    public void setReferred_to_clinician_id(String referred_to_clinician_id) {
        this.referred_to_clinician_id = referred_to_clinician_id;
    }

    public String getReferring_facility_id() {
        return referring_facility_id;
    }

    public void setReferring_facility_id(String referring_facility_id) {
        this.referring_facility_id = referring_facility_id;
    }

    public String getReferred_to_facility_id() {
        return referred_to_facility_id;
    }

    public void setReferred_to_facility_id(String referred_to_facility_id) {
        this.referred_to_facility_id = referred_to_facility_id;
    }

    public String getReferral_date() {
        return referral_date;
    }

    public void setReferral_date(String referral_date) {
        this.referral_date = referral_date;
    }

    public String getUrgency_level() {
        return urgency_level;
    }

    public void setUrgency_level(String urgency_level) {
        this.urgency_level = urgency_level;
    }

    public String getReferral_reason() {
        return referral_reason;
    }

    public void setReferral_reason(String referral_reason) {
        this.referral_reason = referral_reason;
    }

    public String getClinical_summary() {
        return clinical_summary;
    }

    public void setClinical_summary(String clinical_summary) {
        this.clinical_summary = clinical_summary;
    }

    public String getRequested_investigations() {
        return requested_investigations;
    }

    public void setRequested_investigations(String requested_investigations) {
        this.requested_investigations = requested_investigations;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
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

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    @Override
    public String toString() {
        return referral_id + " - " + referral_date + " (" + urgency_level + ")";
    }
}
