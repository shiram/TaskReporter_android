package com.tr.hunter.taskreporter;

public class Config {
    public static final String MYPREFERENCES = "MyPrefs";
    public static int RESULT_LOAD = 1;

    //Error Messages
    public static String NO_SERVER_RESPONSE = "Please Check your Internet Connection.";
    public static String NO_DATA_ERROR = "No Data Found, try again please.";

    //Keys to map values to server
    public static String FIRSTNAME = "firstname";
    public static String LASTNAME = "lastname";
    public static String EMAIL = "email";
    public static String PASSWORD = "password";
    public static String USER_ID = "user_id";
    public static String EMP_ID = "employee_id";
    public static String USER_IMAGE = "image_file";
    public static String USER_IMAGE_EN = "image_file_en";

    //Display feedback, processing
    public static String PROGRESS_BAR = "Processing...";
    public static String REGISTER_PROGRESS = "Registering...";
    public static String LOGIN_PROGRESS = "Login...";


    //Urls
    public static String url = "http://10.0.2.2/task_reporter/";
    public static String REGISTER = url+"register.php";
    public static String LOGIN = url+"login.php";
    public static String GET_ACCOUNTS = url+"get_accounts.php";
    public static String MAKE_TEAM_LEADER = url+"make_team_leader.php";
    public static String MAKE_EMPLOYEE = url+"make_employee.php";
    public static String REVERT_EMPLOYEE = url+"revert_employee.php";
    public static String REVERT_TEAM_LEADER = url+"revert_team_leader.php";
    public static String GET_EMPLOYEES = url+"get_employees.php";
    public static String ASSIGN_TASKS = url+"assign_tasks.php";
    public static String GET_USER_TASKS = url+"get_user_tasks.php";

}
