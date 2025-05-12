package Model.DAO;

import java.util.List;

import Model.Entities.Department;
import Model.Entities.Seller;

public interface SellerDAO
{
    void Insert(Seller obj);
    void UpDate(Seller obj);
    void DeleteByID(Integer ID);
    Seller FindByID(Integer ID);
    List<Seller> FindAll();
    List<Seller> FindByDepartment(Department Department);
}
