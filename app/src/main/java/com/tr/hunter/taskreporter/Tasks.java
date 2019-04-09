package com.tr.hunter.taskreporter;

public class Tasks {
    private int task_id;
    private int employee_id;
    private String team_leader_names;
    private String team_leader_email;
    private String team_leader_emp_id;
    private String task_title;
    private String task_desc;
    private String start_date;
    private String end_date;
    private String is_role_task;

    public Tasks(int task_id, int employee_id, String team_leader_names, String team_leader_email, String team_leader_emp_id, String task_title, String task_desc, String start_date, String end_date, String is_role_task) {
        this.task_id = task_id;
        this.employee_id = employee_id;
        this.team_leader_names = team_leader_names;
        this.team_leader_email = team_leader_email;
        this.team_leader_emp_id = team_leader_emp_id;
        this.task_title = task_title;
        this.task_desc = task_desc;
        this.start_date = start_date;
        this.end_date = end_date;
        this.is_role_task = is_role_task;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getTeam_leader_names() {
        return team_leader_names;
    }

    public void setTeam_leader_names(String team_leader_names) {
        this.team_leader_names = team_leader_names;
    }

    public String getTeam_leader_email() {
        return team_leader_email;
    }

    public void setTeam_leader_email(String team_leader_email) {
        this.team_leader_email = team_leader_email;
    }

    public String getTeam_leader_emp_id() {
        return team_leader_emp_id;
    }

    public void setTeam_leader_emp_id(String team_leader_emp_id) {
        this.team_leader_emp_id = team_leader_emp_id;
    }

    public String getTask_title() {
        return task_title;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public String getTask_desc() {
        return task_desc;
    }

    public void setTask_desc(String task_desc) {
        this.task_desc = task_desc;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getIs_role_task() {
        return is_role_task;
    }

    public void setIs_role_task(String is_role_task) {
        this.is_role_task = is_role_task;
    }
}
