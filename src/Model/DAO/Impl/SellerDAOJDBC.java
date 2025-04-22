package Model.DAO.Impl;

import DB.DBException;
import DB.DB;
import Model.DAO.SellerDAO;
import Model.Entities.Department;
import Model.Entities.Seller;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDAOJDBC implements SellerDAO {
    private Connection conn;

    public SellerDAOJDBC(Connection conn)
    {
        this.conn = conn;
    }

    @Override
    public void Insert(Seller obj) {

    }

    @Override
    public void UpDate(Seller obj) {

    }

    @Override
    public void DeleteByID(Integer ID) {

    }

    @Override
    public Seller FindByID(Integer ID) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            st = conn.prepareStatement(
                    "SELECT Seller.*, Department.Name as DepName FROM Seller " +
                         "INNER JOIN Department ON Seller.DepartmentID = Department.ID " +
                         "WHERE Seller.ID = ?");

            st.setInt(1, ID);
            rs = st.executeQuery();
            if (rs.next())
            {
                Department department = InstantiateDepartment(rs);
                Seller seller = InstantiateSeller(rs, department);
                return seller;
            }
            else
            {
                return null;
            }
        }
        catch (SQLException e)
        {
            throw new DBException(e.getMessage());
        }
        finally
        {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> FindAll() {
        return List.of();
    }

    private Department InstantiateDepartment(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setID(rs.getInt("DepartmentId"));
        department.setName(rs.getString("DepName"));
        return department;
    }

    private Seller InstantiateSeller(ResultSet rs, Department department) throws SQLException {
        Seller seller = new Seller();
        seller.setID(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setBirthDate(rs.getDate("BirthDate"));
        seller.setDepartment(department);
        return seller;
    }
}
