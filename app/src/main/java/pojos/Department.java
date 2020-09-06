package pojos;

import java.util.ArrayList;

public class Department {
    String departmentTitle;
    ArrayList<Aisle> aisles;

    public Department(String departmentTitle, ArrayList<Aisle> aisles) {
        this.departmentTitle = departmentTitle;
        this.aisles = aisles;
    }

    public int getAisleCount() {
        return aisles.size();
    }
}
