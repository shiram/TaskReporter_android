package com.tr.hunter.taskreporter;

public class UserData {
    private int user_id;
    private String firstname;
    private String lastname;
    private String employee_id;
    private String email;
    private String user_image;
    private int is_team_leader;
    private int is_employee;


    public UserData(int user_id, String firstname, String lastname, String employee_id, String email, String user_image, int is_team_leader, int is_employee) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.employee_id = employee_id;
        this.email = email;
        this.user_image = user_image;
        this.is_team_leader = is_team_leader;
        this.is_employee = is_employee;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public int getIs_team_leader() {
        return is_team_leader;
    }

    public void setIs_team_leader(int is_team_leader) {
        this.is_team_leader = is_team_leader;
    }

    public int getIs_employee() {
        return is_employee;
    }

    public void setIs_employee(int is_employee) {
        this.is_employee = is_employee;
    }
}
