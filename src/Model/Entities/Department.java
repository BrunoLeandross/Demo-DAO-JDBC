package Model.Entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable
{
    private Integer ID;
    private String Name;

    public Department()
    {
    }

    public Department(Integer ID, String Name)
    {
        this.ID = ID;
        this.Name = Name;
    }

    public Integer getID()
    {
        return ID;
    }

    public void setID(Integer ID)
    {
        this.ID = ID;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String Name)
    {
        this.Name = Name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(ID, that.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ID);
    }

    public String toString()
    {
        return "Department [id = " + ID + ", name = " + Name + "]";
    }
}
