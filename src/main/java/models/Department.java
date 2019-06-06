package models;

import java.util.Objects;

public class Department {
    private String name;
    private String description;
    private String employee_no;
    private int id;

    public Department(String name, String description, String employee_no) {
        this.name = name;
        this.description = description;
        this.employee_no = employee_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmployee_no() {
        return employee_no;
    }

    public void setEmployee_no(String employee_no) {
        this.employee_no = employee_no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id &&
                name.equals(that.name) &&
                description.equals(that.description) &&
                employee_no.equals(that.employee_no);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, employee_no, id);
    }
}
