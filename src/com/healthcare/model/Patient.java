package com.healthcare.model;

/**
 * Model class representing a Patient
 */
public class Patient {

    private String patient_id;
    private String first_name;
    private String last_name;
    private String date_of_birth;
    private String nhs_number;
    private String gender;
    private String phone_number;
    private String email;
    private String address;
    private String postcode;
    private String emergency_contact_name;
    private String emergency_contact_phone;
    private String registration_date;
    private String gp_surgery_id;

    public Patient() {
    }

    public Patient(String patient_id, String first_name, String last_name, String date_of_birth,
                   String nhs_number, String gender, String phone_number, String email,
                   String address, String postcode, String emergency_contact_name,
                   String emergency_contact_phone, String registration_date, String gp_surgery_id) {

        this.patient_id = patient_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.nhs_number = nhs_number;
        this.gender = gender;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.emergency_contact_name = emergency_contact_name;
        this.emergency_contact_phone = emergency_contact_phone;
        this.registration_date = registration_date;
        this.gp_surgery_id = gp_surgery_id;
    }

    // Getters and Setters

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getNhs_number() {
        return nhs_number;
    }

    public void setNhs_number(String nhs_number) {
        this.nhs_number = nhs_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmergency_contact_name() {
        return emergency_contact_name;
    }

    public void setEmergency_contact_name(String emergency_contact_name) {
        this.emergency_contact_name = emergency_contact_name;
    }

    public String getEmergency_contact_phone() {
        return emergency_contact_phone;
    }

    public void setEmergency_contact_phone(String emergency_contact_phone) {
        this.emergency_contact_phone = emergency_contact_phone;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public String getGp_surgery_id() {
        return gp_surgery_id;
    }

    public void setGp_surgery_id(String gp_surgery_id) {
        this.gp_surgery_id = gp_surgery_id;
    }

    @Override
    public String toString() {
        return first_name + " " + last_name + " (" + patient_id + ")";
    }
}
