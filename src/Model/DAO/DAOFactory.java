package Model.DAO;

import Model.DAO.Impl.SellerDAOJDBC;
import DB.DB;

public class DAOFactory
{
    public static SellerDAO CreateSellerDAO()
    {
        return new SellerDAOJDBC(DB.getConnection());
    }
}
