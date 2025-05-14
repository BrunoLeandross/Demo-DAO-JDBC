package Application;

import Model.DAO.DAOFactory;
import Model.DAO.SellerDAO;
import Model.Entities.Department;
import Model.Entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        //Department obj = new Department(1,"Books");
        //Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(),3000.0, obj);

        SellerDAO sellerDAO = DAOFactory.CreateSellerDAO();

        System.out.println("=== TEST 1: Seller findById ===");
        Seller seller = sellerDAO.FindByID(3);
        System.out.println(seller);

        System.out.println("\n=== TEST 2: Seller findByDepartment ===");
        Department Department = new Department(2,null);
        List<Seller> ListSeller = sellerDAO.FindByDepartment(Department);
        for(Seller obj : ListSeller)
        {
            System.out.println(obj);
        }

        System.out.println("\n=== TEST 3: Seller findAll ===");
        ListSeller = sellerDAO.FindAll();
        for(Seller obj : ListSeller)
        {
            System.out.println(obj);
        }

        System.out.println("\n=== TEST 4: Seller Insert ===");
        Seller NewSeller = new Seller(null, "Gred", "greg@gmail.com", new Date(), 4000.0, Department);
        sellerDAO.Insert(NewSeller);
        System.out.println("Inserted! New ID = " + NewSeller.getID());

        System.out.println("\n=== TEST 5: Seller UPDATE ===");
        seller = sellerDAO.FindByID(1);
        seller.setName("Martha Waine");
        sellerDAO.UpDate(seller);
        System.out.println("UPDATE Completed");

        System.out.println("\n=== TEST 6: Seller DELETE ===");
        System.out.println("Enter ID for Delete test: ");
        int ID = sc.nextInt();
        sellerDAO.DeleteByID(ID);
        System.out.println("Delete completed");
        sc.close();
    }
}
