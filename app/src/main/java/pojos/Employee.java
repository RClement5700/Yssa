package pojos;

public class Employee {

    int employeeId;
    String username;
    String password;
    String assignment;

    public Employee(int employeeId, String username, String password, String assignment) {
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.assignment = assignment;
    }
}
