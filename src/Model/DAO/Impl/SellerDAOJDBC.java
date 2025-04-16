package Model.DAO.Impl;

import Model.DAO.SellerDAO;
import Model.Entities.Seller;

import java.util.List;

public class SellerDAOJDBC implements SellerDAO {
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
        return null;
    }

    @Override
    public List<Seller> FindAll() {
        return List.of();
    }
}
