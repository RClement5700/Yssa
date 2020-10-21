package pojos;

import java.sql.Time;

public class Employee {

    private int employeeId;
    private String username;
    private String fullName;

    public Employee(int employeeId, String username, String fullName) {
        this.employeeId = employeeId;
        this.username = username;
        this.fullName = fullName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
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
