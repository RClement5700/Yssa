package pojos;

import java.util.ArrayList;

public class Department extends ArrayList<Aisle> {
    String departmentTitle;

    public Department(String departmentTitle) {
        this.departmentTitle = departmentTitle;
    }

}
