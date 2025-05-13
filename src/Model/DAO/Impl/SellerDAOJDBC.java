package Model.DAO.Impl;

import DB.DBException;
import DB.DB;
import Model.DAO.SellerDAO;
import Model.Entities.Department;
import Model.Entities.Seller;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDAOJDBC implements SellerDAO {
    private Connection conn;

    public SellerDAOJDBC(Connection conn)
    {
        this.conn = conn;
    }

    @Override
    public void Insert(Seller obj) {
        PreparedStatement st = null;
        try
        {
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES ( ?, ?, ?, ?, ? ) ", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2,obj.getEmail());
            st.setDate(3,new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4,obj.getBaseSalary());
            st.setInt(5,obj.getDepartment().getID());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0)
            {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next())
                {
                    int Id = rs.getInt(1);
                    obj.setID(Id);
                }
                DB.closeResultSet(rs);
            }
            else
            {
                throw new DBException("Unexpected error! No rows affected!");
            }
        }
        catch (SQLException e)
        {
            throw new DBException(e.getMessage());
        }
        finally
        {
            DB.closeStatement(st);;
        }
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
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName " +
                            "FROM seller INNER JOIN department " +
                            "ON seller.DepartmentId = department.Id " +
                            "ORDER BY Name ");

            rs = st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> Map = new HashMap<>();

            while (rs.next())
            {
                Department Dep = Map.get(rs.getInt("DepartmentId"));

                if (Dep == null)
                {
                    Dep = InstantiateDepartment(rs);
                    Map.put(rs.getInt("DepartmentId"), Dep);
                }

                Seller obj = InstantiateSeller(rs, Dep);
                list.add(obj);
            }
            return list;
        }
        catch (SQLException e)
        {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> FindByDepartment(Department Department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName " +
                         "FROM seller INNER JOIN department " +
                         "ON seller.DepartmentId = department.Id " +
                         "WHERE DepartmentId = ? " +
                         "ORDER BY Name ");

            st.setInt(1,Department.getID());
            rs = st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> Map = new HashMap<>();

            while (rs.next())
            {
                Department Dep = Map.get(rs.getInt("DepartmentId"));

                if (Dep == null)
                {
                    Dep = InstantiateDepartment(rs);
                    Map.put(rs.getInt("DepartmentId"), Dep);
                }

                Seller obj = InstantiateSeller(rs, Dep);
                list.add(obj);
            }
            return list;
        }
        catch (SQLException e)
        {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
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
