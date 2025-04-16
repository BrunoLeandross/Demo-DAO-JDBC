package Model.DAO;
import java.util.List;
import Model.Entities.Department;

public interface DepartmentDAO
{
    void Insert(Department obj);
    void UpDate(Department obj);
    void DeleteByID(Integer ID);
    Department FindByID(Integer ID);
    List<Department> FindAll();
}
