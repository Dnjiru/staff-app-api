package models;

import java.util.Objects;

public class Employee {
    private String name;
    private String ek;
    private String role;
    private int id;

    public Employee(String name, String ek, String role) {
        this.name = name;
        this.ek = ek;
        this.role = role;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEk() {
        return ek;
    }

    public void setEk(String ek) {
        this.ek = ek;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        Employee employee = (Employee) o;
        return id == employee.id &&
                name.equals(employee.name) &&
                ek.equals(employee.ek) &&
                role.equals(employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ek, role, id);
    }
}
