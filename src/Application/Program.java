package Application;

import Model.DAO.DAOFactory;
import Model.DAO.SellerDAO;
import Model.Entities.Department;
import Model.Entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args)
    {
        //Department obj = new Department(1,"Books");
        //Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(),3000.0, obj);

        SellerDAO sellerDAO = DAOFactory.CreateSellerDAO();

        System.out.println("=== TEST 1: Seller findById ===");
        Seller seller = sellerDAO.FindByID(3);
        System.out.println(seller);
    }
}
