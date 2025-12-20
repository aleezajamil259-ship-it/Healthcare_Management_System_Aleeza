package com.healthcare.model;

/**
 * Model class representing Staff (non-clinical)
 */
public class Staff {
    private String staff_id;
    private String first_name;
    private String last_name;
    private String role;
    private String department;
    private String facility_id;
    private String phone_number;
    private String email;
    private String employment_status;
    private String start_date;
    private String line_manager;
    private String access_level;

    public Staff() {
    }

    public Staff(String staff_id, String first_name, String last_name, String role,
                 String facility_id, String phone_number, String email) {
        this(staff_id, first_name, last_name, role, "", facility_id, phone_number, email, "", "", "", "");
    }

    public Staff(String staff_id, String first_name, String last_name, String role, String department,
                 String facility_id, String phone_number, String email, String employment_status,
                 String start_date, String line_manager, String access_level) {
        this.staff_id = staff_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.role = role;
        this.department = department;
        this.facility_id = facility_id;
        this.phone_number = phone_number;
        this.email = email;
        this.employment_status = employment_status;
        this.start_date = start_date;
        this.line_manager = line_manager;
        this.access_level = access_level;
    }

    // Getters and Setters
    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(String facility_id) {
        this.facility_id = facility_id;
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

    public String getLine_manager() {
        return line_manager;
    }

    public void setLine_manager(String line_manager) {
        this.line_manager = line_manager;
    }

    public String getAccess_level() {
        return access_level;
    }

    public void setAccess_level(String access_level) {
        this.access_level = access_level;
    }

    @Override
    public String toString() {
        return first_name + " " + last_name + " - " + role + " (" + staff_id + ")";
    }
}