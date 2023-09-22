package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("---- Test findById ----");
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println(sellerDao.findById(5));

        System.out.println("---- Test findByIdDepartment ----");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller obj :
                list) {
            System.out.println(obj);
        }

        System.out.println("---- Test findAll ----");
        List<Seller> list2 = sellerDao.findAll();
        for (Seller obj :
                list2) {
            System.out.println(obj);
        }

        System.out.print("---- Insert ----");
        Seller newSeller = new Seller(
                "Guilherme Luan",
                "guilhermeluan@gmail.com",
                simpleDateFormat.parse("21/12/2004"),
                2400.00,
                department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());


    }
}