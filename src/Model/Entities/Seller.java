package Model.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable
{
    private Integer ID;
    private String Name;
    private String Email;
    private Date BirthDate;
    private Double BaseSalary;
    private Department Department;

    public Seller ()
    {
    }

    public Seller(Integer ID, String Name, String Email, Date BirthDate, Double BaseSalary, Department Department)
    {
        this.ID = ID;
        this.Name = Name;
        this.Email = Email;
        this.BirthDate = BirthDate;
        this.Department = Department;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public Double getBaseSalary() {
        return BaseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        BaseSalary = baseSalary;
    }

    public Model.Entities.Department getDepartment() {
        return Department;
    }

    public void setDepartment(Model.Entities.Department department) {
        Department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(ID, seller.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ID);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", BirthDate=" + BirthDate +
                ", BaseSalary=" + BaseSalary +
                ", Department=" + Department +
                '}';
    }
}
