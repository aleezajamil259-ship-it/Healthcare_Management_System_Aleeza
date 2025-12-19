package com.healthcare.model;

/**
 * Model class representing a Clinician
 */
public class Clinician {

    private String clinician_id;
    private String first_name;
    private String last_name;
    private String title;
    private String speciality;
    private String gmc_number;
    private String phone_number;
    private String email;
    private String workplace_id;
    private String workplace_type;
    private String employment_status;
    private String start_date;

    public Clinician() {
    }

    public Clinician(String clinician_id, String first_name, String last_name, String title,
                     String speciality, String gmc_number, String phone_number, String email,
                     String workplace_id, String workplace_type, String employment_status,
                     String start_date) {

        this.clinician_id = clinician_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.title = title;
        this.speciality = speciality;
        this.gmc_number = gmc_number;
        this.phone_number = phone_number;
        this.email = email;
        this.workplace_id = workplace_id;
        this.workplace_type = workplace_type;
        this.employment_status = employment_status;
        this.start_date = start_date;
    }

    // Getters and Setters

    public String getClinician_id() {
        return clinician_id;
    }

    public void setClinician_id(String clinician_id) {
        this.clinician_id = clinician_id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getGmc_number() {
        return gmc_number;
    }

    public void setGmc_number(String gmc_number) {
        this.gmc_number = gmc_number;
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

    public String getWorkplace_id() {
        return workplace_id;
    }

    public void setWorkplace_id(String workplace_id) {
        this.workplace_id = workplace_id;
    }

    public String getWorkplace_type() {
        return workplace_type;
    }

    public void setWorkplace_type(String workplace_type) {
        this.workplace_type = workplace_type;
    }

    public String getEmployment_status() {
        return employment_status;
    }

    public void setEmployment_status(String employment_status) {
        this.employment_status = employment_status;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    @Override
    public String toString() {
        return first_name + " " + last_name + " - " + speciality + " (" + clinician_id + ")";
    }
}
