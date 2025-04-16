package Model.DAO;

import Model.DAO.Impl.SellerDAOJDBC;

public class DAOFactory
{
    public static SellerDAO CreateSellerDAO()
    {
        return new SellerDAOJDBC();
    }
}
