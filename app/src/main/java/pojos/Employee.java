package pojos;

import java.sql.Time;

public class Employee {

    private int employeeId;
    private String username;
    private String password;
    private String assignment;

    public Employee(int employeeId, String username, String password, String assignment) {
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.assignment = assignment;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAssignment() {
        return assignment;
    }

    public void punchIn(Time time) {

    }

    public void punchOut(Time time) {

    }

    public void goToBreak(Time time) {
        //will receive notification 2.5 mins before it is time to return to work
    }

    public void goToLunch(Time time) {
        //will receive notification 5 mins before it is time to return to work
    }
}
